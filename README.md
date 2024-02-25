# spring-cinema-api

This repository contains simple cinema REST API written with [Java 17](https://www.java.com/en/)
and [Spring Boot](https://spring.io/projects/spring-boot).

## Features

- API documentation available at `GET /swagger-ui/index.html` path
- CRUD operations for all entities through REST endpoints:
    - genre
    - language
    - movie
    - person
    - reservation
    - screening
    - seat
- validation of data using [Jakarta Bean Validation](https://beanvalidation.org/)
- specific usage endpoints:
    - `GET /api/screening/{id}/takenSeats` - returns list of taken seats for particular screening
    - `GET /api/person/{id}/genre` - returns list of genres of films that particular person played in
    - `GET /api/movie` - returns paginated list of movies filtered and sorted by any combination of following fields:
        - `title` - movie title (matches titles which include given word)
        - `genreIds` - list of IDs of movie genres
        - `languageId` - ID of movie language
        - `releaseDate` - release date of movie (matches movies released after given date)

There is also a [Postman](https://www.postman.com/) collection of requests presenting all available operations
inside [this](src/test/resources) directory.

## Running app

In order to run this app, you must have [Docker](https://www.docker.com/)
(and [Docker Compose](https://docs.docker.com/compose/)) installed.
Then, you have to run the following command in order to start [PostgreSQL](https://www.postgresql.org.pl/) database
inside docker container:

```shell
$ docker compose up
```

Then, you can run the application using your IDE or by executing this command:

```shell
$ gradle bootRun
```

The application will be available on port `8080` on your local machine.