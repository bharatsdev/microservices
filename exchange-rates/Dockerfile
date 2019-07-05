#Base Image
FROM openjdk:8-jdk-alpine

MAINTAINER  everythingisdata

ADD ["target/exchange-rates-0.0.1-SNAPSHOT.jar", "exchange-rates.jar"]

ENTRYPOINT ["java","-jar","exchange-rates.jar"]

EXPOSE 8200