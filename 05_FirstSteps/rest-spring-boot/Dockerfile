FROM maven:3-eclipse-temurin-17 as build

WORKDIR /app

COPY . .

RUN mvn clean package -Dmaven.test.skip

FROM eclipse-temurin:17-jdk-alpine

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]