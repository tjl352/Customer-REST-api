# Customer REST api

## Request
Management has requested that we expose our customer database with a RESTfull api so that they can 
test writing a front end management tool. They have asked that we do this quickly.

## Database
```
CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` varchar(255) NULL,
  `address` varchar(255) NULL,
  `city` varchar(255) NULL,
  `join_date` date DEFAULT NULL,
  `first_name` varchar(255) NULL,
  `last_name` varchar(255) NULL,
  `phone_number` varchar(255) NULL,
  `zip` varchar(255) COLLATE NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT 
```

## Technologies
* Spring Boot
* Spring JPA
* Spring WEB
* MySql database

## User Stories
1. add customers to the system
1. retrieve all customers for a given state.
1. retrieve ALL the customers in the system
1. retrieve a customer by their customer id

## Getting started
1. Fork this repository to your github account
1. Clone or download the forked repository
1. Create a mysql db named 'cust_db' (see instructions below)
1. Import repo into your favorite ide.  Note: this is a 'gradle' project so your ide must support gradle
1. Implementations for Customer and CustomerRepository have been provided for you.  Understand how these work.
1. Provide tests and implementations for the REST api access over HTTP.

## MySql Database instruction
* You need to have a mysql server installed, or run one in a docker container. 
Connection details can be found in Customer-REST-api/src/main/resources/application.properties
    1. Database name: `cust_db` Must be created before building or running the app
    1. User / password: `custdb / custdb` You can change these if yours is different.
    1. my sql port: `3306` 
* If you have docker installed, you can use the following command to start up a mysql server in a container.
` docker run -d -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_USER=custdb -e MYSQL_PASSWORD=custdb -e MYSQL_DATABASE=cust_db -p 3306:3306 --name mysql-custdb mysql `
    * to connect to the container db with ms client `$ mysql -h127.0.0.1 -P3306 -ucustdb -pcustdb cust_db`
    * to stop the mysql container `$ docker stop mysql-custdb`
    * to remove the mysql container `$ docker rm mysql-custdb`