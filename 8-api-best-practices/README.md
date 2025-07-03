# 8. RESTful API Best Practices

## Goals

This week's lesson aims to teach interns best practices for resource URLs, parameter passing, response codes, security.

Interns are required to apply the best practices learned to previously created projects.

## Implementation

To show my implementation of this, I am once again referring to [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo) where I have applied a number of the best practices I learned.

### API Documentation with Swagger

Using the Swagger dependency, I was able to generate documentation for my the API that featured the User Entity and all it's associated endpoints.
This documentation can be accessed by navigating to **/swagger-ui.html**

### Error Handling

In the service classes such as [UserService](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/service/UserService.java) and [MyUserDetailsService](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/service/MyUserDetailsService.java), proper exception handling was implemented using some custom exception defined in the [exception/auth](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/exception/auth) package.
These exceptions were handled globally by a [GlobalExceptionHandler](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/exception/GlobalExceptionHandler.java) class contained in the [exception](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/exception) package.

### API Versioning

In the [User Controller](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/controller/UserController.java), I changed the general request mapping to be versioned, by adding a 'v1' prefix. This will secure against any possible mismatches later on if there's any change to the User entity.

### Correction of Request Method

In the [User Controller](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/controller/UserController.java), I applied my knowledge of the Request Method: PATCH and changed the Mapping method used to update the User.
