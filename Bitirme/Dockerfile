FROM postgres
ENV POSTGRES_PASSWORD afatsum123
ENV POSTGRES_DB pollution


FROM openjdk:17-alpine
ARG JAR_FILE=target/mobileaction-last-project-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]