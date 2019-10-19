package com.everythingisdata.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulEdgeGateway {

	public static void main(String[] args) {
		SpringApplication.run(ZuulEdgeGateway.class, args);
	}

//	@Bean
//	public AlwaysSampler defaultSampler() {
//		return new AlwaysSampler();
//	}
}
