FROM openjdk:12-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} messageapp.jar
ENTRYPOINT ["java","-jar","/messageapp.jar"]
