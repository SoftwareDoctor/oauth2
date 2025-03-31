FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/oauth2-0.0.1-SNAPSHOT.jar auth-server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/auth-server.jar"]
