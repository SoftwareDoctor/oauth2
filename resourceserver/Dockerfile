FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/resourceserver-0.0.1-SNAPSHOT.jar resource-server.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/resource-server.jar"]
