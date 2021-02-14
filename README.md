# Banking-system

* [Objetive](#objetive)
* [Technologies](#technologies)
* [Test](#test)
* [How to run project](#run)
* [API](#api)
* [Database Design](#database)
* [Use Case Diagram](#casediagram)



## <a name=objective> Main Objective <a/> 
This application simulates a bank, in it we will have access to different accounts (credit card, savings account, checking account, student account). We can make transfers between accounts, collect commissions or interest and make penalties.

It also has different roles, an Admin role, an account holder role, and a third party role.

## <a name=technologies> Technologies </a>

This project is done with Spring Boot 2.4 and the following libraries :

- Spring Boot Data JPA for database model and repositories.
- Spring Boot Web for create the controller layer.
- Spring Boot Security for protect different endpoints and use basic auth.
- Spring Boot Validations for validate the body of the different request.
- Spring Boot Test for unitary and integration testing with JUnit and Mockito
- Swagger for API Documentation
- Flyway for database migrations

We use maven for dependency management.


## <a name=test> Test </a>
The coverage of the project are

![Test Coverage](https://github.com/nereagarcia12/banking-system/blob/master/src/main/resources/images/coverage.png)

We use JUnit, Mockito and MockMVC, we do different test by a layer:
- Controller: I test the contract, the endpoints urls, mocking the service. We use with MockMvc, mockito and Junit.
- Service: These tests are done with Mockito and JUnit mocking the different repositories.
- Repository: These tests are done with JUnit without any mock.
- Unit testing for different objects and classes with JUnit.
  

## <a name=run> How to run the project</a>
You need a Mysql database and maven installed in your system.

First of all you should create a banking_system database with the following sentences:
```
Drop Schema banking_system;
Create Schema banking_system;
use banking_system;
```
Automatically when you start the project flyway will create tables( first migration) and add sample data(second migration) , the database migrations are in
```
src/resources/db/migration/V1__createtables.sql
src/resources/db/migration/V2__addsampledata.sql
```

After that you can run the proyect with the command
```
mvnw spring-boot:run
```
Or with run of your IDE.

## <a name=api> API </a>
You can access to all the examples in Postman with the next Postman collection:
https://www.getpostman.com/collections/e09115d57bc84543432b

![Postman](https://github.com/nereagarcia12/banking-system/blob/master/src/main/resources/images/postman.png)


You also can access to Swagger when the server is started in the next url:
http://localhost:8080/swagger-ui.html#/

![Swagger](https://github.com/nereagarcia12/banking-system/blob/master/src/main/resources/images/swagger.png)

## <a name=database> Database Design </a>

![Database Design](https://github.com/nereagarcia12/banking-system/blob/master/src/main/resources/images/databasediagram.png)

## <a name=casediagram> Use Case Diagram  </a>

![Use Case Diagram](https://github.com/nereagarcia12/banking-system/blob/master/src/main/resources/images/CaseDiagram.png)