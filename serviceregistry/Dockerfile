FROM eclipse-temurin:17-jre-alpine-3.23
LABEL authors="rajasekhar"

WORKDIR /app

COPY target/*.jar service-registry.jar

ENTRYPOINT ["java", "-jar", "service-registry.jar"]