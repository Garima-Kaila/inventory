# CI server [ ![Codeship Status for Garima-Kaila/inventory](https://codeship.com/projects/994162c0-544e-0134-507e-4ab5dfe4e53b/status?branch=master)]

# GIT sub module
> git pull --recurse-submodules
> git submodule update --remote --recursive


The OpenShift `jbossews` cartridge documentation can be found at:

http://openshift.github.io/documentation/oo_cartridge_guide.html#tomcat

MySQL 5.5 database added.  Please make note of these credentials:
<!---
...   Root User: adminjhZbJVw
...   Root Password: y-pKXp4uKrTy
...   Database Name: inventory
-->
Connection URL: mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/

<!---
Please make note of these MySQL credentials again:
...  Root User: adminjhZbJVw
...  Root Password: y-pKXp4uKrTy
-->
URL: https://inventory-flufystudio.rhcloud.com/phpmyadmin/

# To setup deployment to OpenShift with Codeship

- on the codeship site navigate to: 

>Project settings > General > SSH public key

 
> Copy the contents and save to a file: (e.g.: codeship.pub)

- open a terminal and run:

>rhc sshkey add codeship codeship.pub 

- go back to the codeship site and navigate to

>Project settings > deployment > custom script

- put these lines to the deployment box

>git remote add OpenShift your_repo

>git push -f OpenShift master

- push to your repo ... done
