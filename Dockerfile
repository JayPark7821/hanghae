FROM openjdk:17-ea-11-jdk-slim
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ARG PROFILE
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${PROFILE}", "/app.jar"]
