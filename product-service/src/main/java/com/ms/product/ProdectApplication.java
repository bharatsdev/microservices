package com.ms.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ms.product.domain.Product;
import com.ms.product.service.ProductService;

@SpringBootApplication
@EnableDiscoveryClient
public class ProdectApplication implements CommandLineRunner {
	@Autowired
	ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ProdectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		productService.deleteAll();

		// save a couple of customers
		productService.save(new Product("Pant", "3939",91));
		productService.save(new Product("shirt", "087",89));
		

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Product product : productService.getAllProduct()) {
			System.out.println(product);
		}
		System.out.println();

		 

	}

	// @Bean
	// public AlwaysSampler defaultSampler() {
	// return new AlwaysSampler();
	// }

}
