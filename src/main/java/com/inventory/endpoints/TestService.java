package com.inventory.endpoints;

/**
 * Created by garima on 03-09-2016.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.inventory.db.ConnectionPool;
import com.inventory.dto.TestDto;
import com.sun.jersey.api.ConflictException;
import com.sun.jersey.api.NotFoundException;

@Path("/test")
public class TestService {
    /**
     * Get a list of all the test values from the database.
     *
     * @return List of Test objects
     */
    @GET
    @Path("/")
    @Produces("application/json")
    public List<TestDto> getValues() {
        List<TestDto> values = new ArrayList<TestDto>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT id, value FROM test");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TestDto test = new TestDto();
                test.setId(resultSet.getLong("id"));
                test.setValue(resultSet.getString("value"));
                values.add(test);
            }
        } catch (SQLException e) {
            throw new WebApplicationException(e, Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("SQL Exception: " + e.getMessage())
                    .build()
            );
        }

        return values;
    }

    /**
     * Get a specific test value from the database.
     *
     * @param id The id of the value to get
     * @return The id and value
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public TestDto getValues(@PathParam("id") long id) {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT id, value FROM test WHERE id = ?");
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new NotFoundException();
            }

            TestDto test = new TestDto();
            test.setId(resultSet.getLong("id"));
            test.setValue(resultSet.getString("value"));
            return test;
        } catch (SQLException e) {
            throw new WebApplicationException(e, Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("SQL Exception: " + e.getMessage())
                    .build()
            );
        }
    }

    /**
     * Add a test value to the database.
     *
     * @param test A json object with the value to be added.
     * @return Created or error code.
     */
    @POST
    @Path("/")
    @Consumes("application/json")
    public Response addValue(TestDto test) {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO test (value) VALUES (?)");
            statement.setString(1, test.getValue());

            int result = statement.executeUpdate();
            if (result != 1) {
                throw new ConflictException("Failed: " + result);
            }
        } catch (SQLException e) {
            throw new WebApplicationException(e, Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("SQL Exception: " + e.getMessage())
                    .build()
            );
        }

        return Response.created(null).build();
    }
}
