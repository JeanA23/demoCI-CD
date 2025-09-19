FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app
COPY target/Test-0.0.1-SNAPSHOT.jar testmanager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "testmanager.jar"]