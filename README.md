[![Build Status](https://dev.azure.com/everythingisdata/Micro-Services-With-Docker-Springboot/_apis/build/status/everythingisdata.Micro-Services-Spring-Boot%20(1)?branchName=master)](https://dev.azure.com/everythingisdata/Micro-Services-With-Docker-Springboot/_build/latest?definitionId=4&branchName=master)

[![Build Status](https://travis-ci.org/everythingisdata/Micro-Services-Spring-Boot.svg?branch=master)](https://travis-ci.org/everythingisdata/Micro-Services-Spring-Boot)



# Micorservices 
Creating MicroServices with the help of spring boot and Docker. Docke-compose
### Tech Stack
- Java 8
- Sprint Boot
- Maven
- H2 (With the dev profile you can use H2, but with the Prod Profile you need to use MySQL)
- tomcat

## Getting Start with MicroServices
Run Discovery server as spring boot

### Micro Services
  - Currency Converter
  - Exchange Rates
  - Text Translation
  - Weather  

### Configuration 
  Port for each Service should be unique. 

Spring Boot
#### URLs

 - http://localhost:876/euraka
 - http://localhost:8320/exchange-rates/from/EUR/to/INR
 - http://localhost:8220/currency-converter/from/EUR/to/INR/quantity/100
 - http://localhost:8220/currency-converter-feing/from/EUR/to/INR/quantity/1001
