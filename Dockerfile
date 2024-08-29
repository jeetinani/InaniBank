FROM openjdk:17
WORKDIR /app
COPY target/bank-application.jar .
COPY config ./config
COPY bank.db .
ENV SPRING_PROFILES_ACTIVE=externalDB
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "bank-application.jar"]