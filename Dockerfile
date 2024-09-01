FROM openjdk:17
WORKDIR /app
COPY target/bank-application.jar .
#needs to commented if not present
COPY config ./config
#needs to commented if not present
COPY bank.db .
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "bank-application.jar"]