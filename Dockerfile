FROM adoptopenjdk/maven-openjdk11:latest

LABEL "image.tag"="latest"
COPY target/devops-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]