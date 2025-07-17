# 9. Integration Testing

## Goals

This week focuses on learning about how to set up test frameworks, how to set up the test context and test data, mocking application resources, verifying actual results of tests against expected results, and cleanup of test resources.

Interns are required to write test cases for previous applications.

## Implementation

To show my implementation of this, I am once again referring to [Workout Tracker Demo](https://github.com/islajr/fip-backend-path/tree/master/3-6-project-learning/workout-tracker-demo) where I wrote unit tests for both the service and repository layer.

### JUnit Testing

As part of the testing process, i ensured to write unit tests for each method contained in the testing scope, making allowances where necessary and ensuring that each method returns the proper response.

### Mocking and Mockito

For classes and packages that were not written by me, their implementation was mocked using Mockito, allowing me to focus on what truly mattered - testing my custom classes and applications.
