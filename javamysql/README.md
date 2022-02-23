# Java app accessing MySQL inside a Kubernetes cluster

The main configuration file is given at: https://github.com/charroux/noops/blob/main/javamysql/src/main/resources/application.properties

This configuration is similar to the structure of the database set at: https://github.com/charroux/noops/tree/main/mysql#cr%C3%A9ation-dune-table-et-dun-utilisateur

Take care at the url of the database. Here: 

127.0.0.1:50628. 

It must be the url of the kubernetes service given at: https://github.com/charroux/noops/tree/main/mysql#lurl-du-service 


## Accessing the database through the Java app

### List the content of the database

http://localhost:8080/all

### Add a user

Send an Http post request to http://localhost:8080/add?name=<a name>&email=<an email>

### Remove a user 

Send an Http delete request to http://localhost:8080/add?name=<a name>&email=<an email>


