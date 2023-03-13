FROM adoptopenjdk/maven-openjdk11:latest

LABEL "image.tag"="latest"
COPY target/devops-0.0.1-SNAPSHOT.jar app.jar
#ENV spring_datasource_url jdbc:postgresql://13.39.24.217:5432/devops?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
#ENV spring_profiles_active=dev
ENTRYPOINT ["java", "-jar", "app.jar"]