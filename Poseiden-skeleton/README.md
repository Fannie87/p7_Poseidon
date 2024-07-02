# spring-boot
## Technical:

1. Spring Boot 2.7.18
2. Java 17
3. Thymeleaf
4. Bootstrap v.4.3.1
5. Mysql 8.0

## How to launch the application
1. Create a database "poseidon" on mysql. You can change the user/password database connection in the file application.properties
2. Create the database with the queries in doc/data.sql
3. Launch the application with maven and java.
4. Then you can connect to the following url [http://localhost:8081/home](http://localhost:8081/home) with user/password : user/user.


## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Create database with name "demo" as configuration in application.properties
5. Run sql script to create table doc/data.sql

## Implement a Feature
1. Create mapping domain class and place in package com.nnk.springboot.domain
2. Create repository class and place in package com.nnk.springboot.repositories
3. Create controller class and place in package com.nnk.springboot.controllers

## Security
1. Create user service to load user from  database and place in package com.nnk.springboot.services
2. Add configuration class and place in package com.nnk.springboot.config

