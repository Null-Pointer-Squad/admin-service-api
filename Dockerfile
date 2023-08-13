FROM openjdk:20
EXPOSE 8080
ARG JAR_FILE=target/admin-service-app.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]