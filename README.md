# Bilu

A modular backend application built with **Java** and **Quarkus**, structured with clear separation between presentation, application, domain, and infrastructure concerns.

## Overview

This project is organized to keep business rules isolated from framework details and external integrations. The current focus is a Docker integration module that lists containers and fetches container details by ID.

## Architecture

The project follows a modular structure inspired by Clean Architecture and DDD.

```text
src/main/java/dev/bilu
├── modules
│   └── docker
│       ├── application
│       ├── domain
│       │   ├── entities
│       │   └── port
│       ├── infrastructure
│       │   ├── factory
│       │   ├── mappers
│       │   └── repository
│       └── presentation
│           ├── dto
│           └── mappers
└── shared
    └── usecase
```

### Layer responsibilities

**Presentation**

* Exposes HTTP endpoints
* Handles request and response DTOs
* Uses mappers to transform domain objects into API responses

**Application**

* Contains use cases
* Orchestrates the flow between presentation and domain
* Defines application-level rules

**Domain**

* Holds entities and ports
* Represents the core model of the application
* Stays free of framework-specific details

**Infrastructure**

* Integrates with external services and libraries
* Implements ports
* Contains repository logic and low-level mapping from external objects

## Current module

### Docker module

The Docker module manages container data exposed by the Docker client library.

Main features:

* List containers
* Get container by ID
* Map Docker data to application DTOs
* Represent container networks, ports, and mounts in a structured way

## Project structure

```text
.
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── docker
    │   │   ├── Dockerfile.jvm
    │   │   ├── Dockerfile.legacy-jar
    │   │   ├── Dockerfile.native
    │   │   └── Dockerfile.native-micro
    │   ├── java
    │   │   └── dev
    │   │       └── bilu
    │   │           ├── modules
    │   │           │   └── docker
    │   │           │       ├── application
    │   │           │       ├── domain
    │   │           │       ├── infrastructure
    │   │           │       └── presentation
    │   │           └── shared
    │   │               └── usecase
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── dev
                └── bilu
```

## Tech stack

* Java
* Quarkus
* Maven
* Docker

## Getting started

### Prerequisites

* Java installed
* Maven Wrapper available in the repository
* Docker installed if you want to run or test Docker-related features

### Run locally

```bash
./mvnw quarkus:dev
```

### Run tests

```bash
./mvnw test
```

## API endpoints

Current Docker endpoints:

* `GET /list` — list containers
* `GET /getById/{id}` — get a container by ID

## Docker files

The repository includes multiple Dockerfile variants for different build targets:

* `Dockerfile.jvm`
* `Dockerfile.legacy-jar`
* `Dockerfile.native`
* `Dockerfile.native-micro`

## Future improvements

* Add authentication and authorization
* Expand test coverage
* Add persistence if needed
* Improve observability
* Add CI/CD pipeline

## Contributing

Feel free to open issues or submit pull requests.
