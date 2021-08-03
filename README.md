# SpringBoot-Oauth

Explore Java Springboot Framework with Oauth2 scenario unit testing using TestRestTemplate & MockMVC. For making assertions in JUnit I choose Hamcrest.

## Prerequisites
1. Java 8
2. JPA Mysql
3. Gradle
4. Oauth2 
5. Dev Tools 
6. Lombok 
7. Swagger
8. MockMVC
9. TestRestTemplate
10. Hamcrest

## Setup MySQL
Import import-dev.sql to your MySQL

## Collection Endpoints
Import Buku_Oauth.postman_collection to Postman

## API Documentation
Open your browser for SpringFox http://localhost:8200/api/v2/api-docs <br />
Open your browser for Swagger UI http://localhost:8200/api/swagger-ui.html#/ <br />

## How it work
To run application,open cmd in your project directory and type
```
gradlew bootRun
```

## Unit Testing
To run unit testing,open cmd in your project directory and type
```
gradlew clean test --info
```

JUnit Testing result
![Alt text](asset/unit_test.PNG?raw=true "JUnit Testing result")

### Notes
For those who having this problem in Eclipse:
![Alt text](asset/gradle_eclipse_error.PNG?raw=true "Dependency Gradle Eclipse Error")
![Alt text](asset/gradle_eclipse.PNG?raw=true "Gradle Eclipse Error")
![Alt text](asset/gradle_check_version.PNG?raw=true "Gradle command check version")

Type this command:
```
gradle wrapper --gradle-version 7.1.1 --distribution-type all
```