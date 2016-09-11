# Continuous Integration server [![Build Status](https://api.travis-ci.org/Garima-Kaila/inventory.svg?branch=master)](https://travis-ci.org/Garima-Kaila/inventory)

# Continuous Deployment server [![Codeship Status for Garima-Kaila/inventory](https://codeship.com/projects/994162c0-544e-0134-507e-4ab5dfe4e53b/status?branch=master)]  

# Code Analysis Server [![SonarQube.com Quality Gate status](https://sonarqube.com/api/badges/gate?key=inventory:inventory)](https://sonarqube.com/overview?id=inventory:inventory)

# GIT sub module
> git pull --recurse-submodules

> git submodule update --remote --recursive

# Deployed on OpenShift 

The OpenShift `jbossews` cartridge with MySQL 5.5 database added.  

URL: https://inventory-flufystudio.rhcloud.com

Please make note of these credentials:
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

<!---
https://aroundthecode.org/2016/06/18/travisci-sonarqube-anaysis/

> mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -B -e -V -Dsonar.host.url="https://sonarqube.com" -Dsonar.login="e87a4a9074f0dbd0ffc1aeb887f179b6bff0a38f"

> mvn clean install sonar:sonar -Dsonar.host.url=https://sonarqube.com -Dsonar.login=e87a4a9074f0dbd0ffc1aeb887f179b6bff0a38f

-->

