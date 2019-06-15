package com.techwizard.eurakaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurakaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurakaServerApplication.class, args);
	}
}
