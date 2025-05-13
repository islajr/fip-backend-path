# 1. Environment Setup

## Goal
This module of the flexisaf internship programme requires interns to learn how to set up a DBMS and use DBMS admin tools

Interns are also required to set up a Java framework with its core libraries, application server setup (if required), setup build tools (Maven/Gradle).

## Implementation
**Boot App** is a barebones spring boot application that uses Java 21 and Maven as a build tool.
The build file - [pom.xml](boot-app/pom.xml) - contains dependencies for Spring Boot Starter Web, Spring Boot DevTools, Spring Boot Starter Test, Spring Data JPA, and Postgresql as a Relational DBMS. 
