package com.everythingisdata.gateway;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulEdgeGateway {

	public static void main(String[] args) {
		SpringApplication.run(ZuulEdgeGateway.class, args);
	}

	// @Bean
	// public AlwaysSampler defaultSampler() {
	// return new AlwaysSampler();
	// }

	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder(); // For encrypting user password
	}
}
