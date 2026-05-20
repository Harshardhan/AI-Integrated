FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/OTPSystem-0.0.1-SNAPSHOT.jar app.jar


EXPOSE  8096

ENTRYPOINT ["java","-jar","app.jar"]