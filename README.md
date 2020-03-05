[![Build Status](https://dev.azure.com/everythingisdata/Micro-Services-With-Docker-Springboot/_apis/build/status/everythingisdata.Micro-Services-Spring-Boot%20(1)?branchName=master)](https://dev.azure.com/everythingisdata/Micro-Services-With-Docker-Springboot/_build/latest?definitionId=4&branchName=master)

[![Build Status](https://travis-ci.org/everythingisdata/microservices-springboot.svg?branch=master)](https://travis-ci.org/everythingisdata/microservices-springboot)


# Product Ordering - MicroServices

	This repository has basic code related to MicroService in spring boot. 
	- Creation of MicroServices
	- Intercommunication in between MicroServices
	- Dockerization of MicroServices
	- Docker-Compose in MicroServices

# Technologies

	- Java 8
	- lombok
	- Spring Boot
	- Maven
	- H2 (With the Dev profile you can use H2, but with the Prod Profile you need to use MySQL)
	- Tomcat
	- Docker, Docker-compose

### Components
	  - Currency Converter
	  - Exchange Rates
	  - Zuul Api Gateway
	  - eureka discovery


## Commands to build and run 
	- mvn clean install 
	- docker-compose up --build
	- docker-compose up  --force-recreate --build

## Configuration 
  Port for each Service should be unique. Spring Boot
  
## Service Endpoints
	 - http://localhost:8766
	 - http://localhost:8300
## Edge Gateway URL
	 - http://localhost:8600/product-service
	 - http://localhost:8600/order-service

