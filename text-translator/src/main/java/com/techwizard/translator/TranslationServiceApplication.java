package com.techwizard.translator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author techwizard
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class TranslationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslationServiceApplication.class, args);
	}

	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
}
