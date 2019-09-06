[![Build Status](https://dev.azure.com/everythingisdata/Micro-Services-With-Docker-Springboot/_apis/build/status/everythingisdata.Micro-Services-Spring-Boot%20(1)?branchName=master)](https://dev.azure.com/everythingisdata/Micro-Services-With-Docker-Springboot/_build/latest?definitionId=4&branchName=master)

[![Build Status](https://travis-ci.org/everythingisdata/Micro-Services-Spring-Boot.svg?branch=master)](https://travis-ci.org/everythingisdata/Micro-Services-Spring-Boot)



# Microservices
---
This repository has basic code related to micro service in spring boot. 
- Creation of Microservices
- Inter-communication in between Microservices
- Dockerization of Microservices
- Docker-compose in Microservices

### Tech Stack
- Java 8
- Sprint Boot
- Maven
- H2 (With the dev profile you can use H2, but with the Prod Profile you need to use MySQL)
- tomcat
- Docker, Docker-compose

### Components
  - Currency Converter
  - Exchange Rates
  - Zuul Api Gateway
  - eureka discovery


### Commands to build and run 
mvn clean install 
docker-compose up --build
docker-compose up  --force-recreate --build



### Configuration 
  Port for each Service should be unique. 

Spring Boot
#### Access URL Individual Service 
 - http://localhost:8761/
 - http://localhost:8300/
 - http://localhost:8320/exchangerate/from/EUR/to/INR
 - http://localhost:8220/converter/from/EUR/to/INR/quantity/100 
 
#### Access through Edge Gateway
 - http://localhost:8600/converter-service/
 - http://localhost:8600/exchangerate/
 - http://localhost:8600/exchangerate/from/EUR/to/INR
 - http://localhost:8600/converter/from/EUR/to/INR/quantity/100 

