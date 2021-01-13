package com.ms.eurakaserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class DiscoveryApplication {
	/**
	 * @return
	 */
	@GetMapping("/")
	private String getGreetings() {
		log.info("Response from NetflixEurekaServerApplication ");

		return "This response from First NetflixEurekaServerApplication !";
	}

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}
}
