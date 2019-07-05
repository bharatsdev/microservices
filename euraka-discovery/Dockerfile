#Base Image
FROM openjdk:8-jdk-alpine
MAINTAINER  everythingisdata
ADD ["target/euraka-discovery-0.0.1-SNAPSHOT.jar", "euraka-discovery.jar"]
ENTRYPOINT ["java","-jar","euraka-discovery.jar"]
EXPOSE 8761 8787