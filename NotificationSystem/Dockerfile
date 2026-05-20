FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/NotificationSystem-0.0.1-SNAPSHOT.jar app.jar


EXPOSE  8098

ENTRYPOINT ["java","-jar","app.jar"]