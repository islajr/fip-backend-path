# 7. Implementing REST Resource Endpoints

## Goals

This week's lesson aims to teach interns everything that goes into implementing REST resource endpoints, namely: declaring the endpoints, capturing parameters, returning respones from endpoints, service injections, handling errors, securing the endpoints, and providing a REST API specification.

Interns are required to create a spring boot application with at least four (4) endpoints, handle exception handling, validate input, and create a service, repository and controller layer.

## Implementation

To show my implementation of this, I am once again referring to [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo) where various authentication endpoints have been exposed for registration, login, updating and deleting users.

### Exception Handling

In the service classes such as [UserService](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/service/UserService.java) and [MyUserDetailsService](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/service/MyUserDetailsService.java), proper exception handling was implemented using some custom exception defined in the [exception/auth](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/exception/auth) package.
These exceptions were handled globally by a [GlobalExceptionHandler](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/exception/GlobalExceptionHandler.java) class contained in the [exception](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/exception) package.

### Input Validation

Using the [Jakarta Validation API](https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api), I was able to implement validation on the input DTOs contained in the [dto](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/dto) package of [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo)


