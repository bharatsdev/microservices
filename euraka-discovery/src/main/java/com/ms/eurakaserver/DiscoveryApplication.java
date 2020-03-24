package com.ms.eurakaserver;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplication {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DiscoveryApplication.class);

	/**
	 * @return
	 */
	@GetMapping("/")
	private String getGreetings() {
		LOGGER.info("Response from NetflixEurakaServerApplication ");
		return "This response from First NetflixEurakaServerApplicatione !";
	}

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}
}
