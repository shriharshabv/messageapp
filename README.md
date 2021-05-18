# Dockerized Spring Boot Messaging Application

This is a spring boot application for sending messages from one user to another user using REST APIs.

Prerequisite: Docker must be installed in your system. [Download Docker here](https://docs.docker.com/get-docker/).

To run the application, first clone the repository to your local directory and then execute the following command(s) in the terminal from the Spring Boot Application's root directory:

`$ docker-compose build`

`$ docker-compose up`

All the necessary images will be created in Docker and then the Spring Boot Application will start running on Docker container.

Once the application is up and running, use **Postman** to test the following APIs.

### User API:

#### POST Request:
Create user:
[http://localhost:8082/api/users](http://localhost:8082/api/users)

Body:

    {
        "name": "Steve213"
    }

#### GET Requests:
Get all users:
[http://localhost:8082/api/users](http://localhost:8082/api/users)

Get user by id:
[http://localhost:8082/api/users/111](http://localhost:8082/api/users/111)

#### DELETE Request:
Delete user:
[http://localhost:8082/api/users/222](http://localhost:8082/api/users/222)

### Message API:

#### POST Request:
Post or send message:
[http://localhost:8082/api/messages/send](http://localhost:8082/api/messages/send)

Add the following parameter in the **request header**:

KEY: `user_id`

VALUE: 111 (Type of the value should be **Long**)

Body:

    {
        "receiverId" : 222,
        "message" : "Hey John! what's up?"
    }

#### GET Requests:
Get all messages:
[http://localhost:8082/api/messages](http://localhost:8082/api/messages)

Get message by id:
[http://localhost:8082/api/messages/1](http://localhost:8082/api/messages/1)

### The request header `user_id` has to be added to the following APIs:

Get all sent messages:
[http://localhost:8082/api/messages/sent](http://localhost:8082/api/messages/sent)

Get all received messages:
[http://localhost:8082/api/messages/received](http://localhost:8082/api/messages/received)

Get all messages received from a particular user:
[http://localhost:8082/api/messages/sender/2](http://localhost:8082/api/messages/sender/2)

