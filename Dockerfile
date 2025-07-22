FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# DA PERMISOS DE EJECUCIÓN AL MAVEN WRAPPER
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]