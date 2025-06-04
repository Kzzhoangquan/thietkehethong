FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/databaseservice-0.0.1-SNAPSHOT.jar databaseservice.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "databaseservice.jar"]