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