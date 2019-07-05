#Base Image
FROM openjdk:8-jdk-alpine

MAINTAINER  everythingisdata

ADD ["target/currency-converter-0.0.1-SNAPSHOT.jar", "currency-converter.jar"]

ENTRYPOINT ["java","-jar","currency-converter.jar"]

EXPOSE 8300 8787