package com.ms.product;

import com.ms.product.domain.Product;
import com.ms.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("-----Product Service-------");
		productService.deleteAll();

		// save a couple of customers
		productService.save(new Product("Pant", "3939", 91));
		productService.save(new Product("Shirt", "087", 89));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Product product : productService.getAllProduct()) {
			log.info(product.toString());
		}
		System.out.println();
	}
}
