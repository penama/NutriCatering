FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/NutriCatering-0.0.1-SNAPSHOT.jar app.jar

ENV SERVER_PORT=8090
ENV SPRING_PROFILES_ACTIVE=dev

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Dserver.port=${SERVER_PORT}", "app.jar"]
