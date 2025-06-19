# WEEK 3 - 6

## WEEK 3

### Goals

This week aims to provide interns with an overview of the Spring framework with emphasis on its paradigms for RESTful architectures, including a persistence layer, service layer, and endpoints.

Interns are required to create a Spring Boot Application with atleast 4 endpoints.

### Implementation

This week, I have gotten to building the demo of my Workout Tracker API, and while a lot of work remains to be done, I decided to come up with something very very basic that is within the scope of the deliverables and also shows what I intend to do over the next few weeks.

Check out [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/e5506950ce61e0a1a3f4479a518e24073d7de974/3-6-project-learning/workout-tracker-demo) to see how I created endpoints that return bare HTTP responses with hardcoded string values in the payload.

## WEEK 4

### Goals

This week's lesson is for interns to gain an overview of ORM paradigms, implementing entity classes and repositories, persisting data, querying with JPQL/HQL etc.

Interns are required to use important Hibernate annotations to design a database entity with, at least, ten (10) fields.

### Implementation

Within the context of the [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/e5506950ce61e0a1a3f4479a518e24073d7de974/3-6-project-learning/workout-tracker-demo), I am presenting the [User](https://github.com/islajr/fip-backend-path/blob/997a035f79aeb1d66d33d20d92b9e4d686201278/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/model/User.java) entity that defines the details of each user of the application.

## WEEK 5

### Goals

This week's lesson is centred on Database Migrations. It centres on implementing database migration with flyway. 

Interns are required to show the implementation of said migrations using [Flyway](https://documentation.red-gate.com/fd/redgate-flyway-documentation-138346877.html).

### Implementation

Over the past week, I have developed some basic user features of [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo), including registering, login, updating, and deleting. Also, I have added token-based authentication using JWT and all of this, at this point, connects to a database on my local machine.

Database migrations are important for maintaing version control over databases and ensuring that developers know the current state of their databases at every point in time.

For the migration implementation, I used this database as a subject. The user database consists of important fields, such as username, email, password and role. The migrations and migration history are contained in the [migrations](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo/src/main/resources/db/migration) folder of [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo).

To implement the deliverables, I created two migration files:

- [Baseline](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/resources/db/migration/V1__baseline.sql) migration file and because my database already existed, this file was empty.
- [Add last seen](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/resources/db/migration/V2__add_last_seen_to_users.sql) migration file was created to really test the setup, and it effectively added a "last_seen" column to the database.

Running the application executed any pending migrations in ORDER, and added a "last_seen" column to the database entity.

## WEEK 6

### Goals

This week's lesson is on implementing service classes. It also focuses on Dependency Injection, Autowiring, and best practices for implementing business logic.

Interns are required to practice dependency injection using constructors, setters and fields.

### Implementation

Given the goal of this week's lesson, I have implemented dependency injection severally across [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo).

Practically every service class, constructor class, and entity model utilizes or contributes towards the use of dependency injection in some way.

The following are examples of where dependency injection, getters and setters have been used across [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo)

- [User Controller](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/controller/UserController.java): Utilizes **dependency injection** on **line 16** where the User Service class is injected.
- [User Service](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/service/UserService.java): Utilizes **dependency injection** from **lines 32 - 36** where various service classes are injected.
- [User Entity](https://github.com/islajr/fip-backend-path/blob/master/3-6-project-learning/workout-tracker-demo/src/main/java/org/project/workouttrackerdemo/model/User.java): Shows **best practices for creating getters and setters** from **lines 11 - 13** using the lombok dependency.

-- *text highlighted in blue are links, and can be followed to reference what is being discussed within a particular context*
