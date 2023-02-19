# DB deadlock

This Spring Boot project shows how the deadlock in database can occur when data are written into 2 tables simultaneously and how can be prevented by locking the tables. Application doesn't do much - run JUnit tests to observe.

## Prerequisites

* Docker and Docker Compose
* Java
* Maven

## Compiling and running the project

Run and setup MS SQL DB:

    docker-compose up

Compile and run JUnit tests:

    mvn clean test

All tests should pass. Check the difference between `DbDeadLockingServiceTest.writeBothOppositeOrderAlphaBeta` and `DbNotDeadLockingServiceTest.writeBothOppositeOrderAlphaBeta` - same test but one throws DB deadlock exception, the other one does not.

## Cleanup:

	docker-compose down

## References

* [MS SQL TABLOCKX](https://learn.microsoft.com/en-us/sql/t-sql/queries/hints-transact-sql-table?view=sql-server-ver16) - MS SQL documentation for locking the tables
