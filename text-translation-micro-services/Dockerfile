# FROM java:8

# # Install maven
# RUN apt-get -y update && apt-get install -y maven

# WORKDIR /code

# # Prepare by downloading dependencies
# ADD pom.xml /code/pom.xml

# # Adding source, compile and package into a fat jar
# ADD src /code/src
# RUN ["mvn", "package"]

# # EXPOSE 8200
# # CMD ["mvn", "tomcat7:run"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
EXPOSE 8200
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]