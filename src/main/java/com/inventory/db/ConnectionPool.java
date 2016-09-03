package com.inventory.db;

/**
 * Created by garima on 03-09-2016.
 */

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionPool {
    //// Singleton ////
    private static ConnectionPool instance;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    //// Singleton ////
    private static String DB_USERNAME = "inventory";
    private static String DB_PASSWORD = "inventory";
    private static String DB_DATABASE = "mysql";
    private static String DB_HOST = "localhost";
    private static String DB_PORT = "3306";

    private static String DB_NAME = "inventory";
    private static String DB_DRIVER = "com.mysql.jdbc.Driver";

    private static String DB_URL;
    private BasicDataSource basicDataSource;

    private ConnectionPool() {
        String openShiftDbUser = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        if (openShiftDbUser!=null && !openShiftDbUser.equals("")) {
            DB_USERNAME = openShiftDbUser;
            DB_PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            DB_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            DB_PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        }

        DB_URL = "jdbc:" + DB_DATABASE + "://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DB_DRIVER);

        basicDataSource.setUsername(DB_USERNAME);
        basicDataSource.setPassword(DB_PASSWORD);
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setInitialSize(1);
    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}