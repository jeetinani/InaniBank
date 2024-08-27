FROM openjdk:17
WORKDIR /app
COPY target/bank-application.jar .
COPY config ./config
COPY bank.db .
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "bank-application.jar"]