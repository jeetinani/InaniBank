FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/gateway.jar .
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "gateway.jar"]