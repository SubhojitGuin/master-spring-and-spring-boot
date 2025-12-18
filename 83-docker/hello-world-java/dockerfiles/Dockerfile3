FROM maven:3.9.11-amazoncorretto-25-alpine AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/in28minutes/rest/webservices/hello_world_java/HelloWorldJavaApplication.java	/home/app/src/main/java/com/in28minutes/rest/webservices/hello_world_java/HelloWorldJavaApplication.java

RUN mvn -f /home/app/pom.xml clean package

# Docker reuses layer
# Upto here, Docker will only run this if there is any change in the files and dependencies
# specified above, otherwise, it will skip it and move to the next part directly
# Docker caches every layer and tries to reuse it making the process faster after the first time

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:21-ea-jdk-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
