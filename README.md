## Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Java](https://www.java.com)
- [Maven](https://maven.apache.org/)

## Clone the Repository

Before running the application, clone the repository to your local machine:

```
git clone https://github.com/YahorNaumovich/aston-task.git
```

## Navigate into the project directory:

```
cd aston-task
```

## Run Tests

To ensure that everything is working correctly, you can run the following command to execute the tests:

```
mvn clean test
```

## Build the Docker Image

```
docker-compose build
```

## Run the Application in Docker

```
docker-compose up
```

## Access and Test the Application

To test the application's endpoints, open a web browser and navigate to:

```
http://localhost:8081/swagger-ui/index.html
```

You will see the Swagger UI, which provides a list of all available API methods. You can test them directly from the
interface.

## Stop the Application

To stop the application, press `CTRL + C` in the terminal where Docker Compose is running. Alternatively, you can use the
following command to stop and remove the containers:

```
docker-compose down
```