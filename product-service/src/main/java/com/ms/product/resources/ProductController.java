package com.ms.product.resources;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ms.product.domain.Product;
import com.ms.product.service.ProductService;
import com.netflix.discovery.EurekaClient;

/**
 * @author Bharat2010
 *
 */
@RestController
@Slf4j
public class ProductController {
	private static final String LOCAL_SERVER_PORT = "local.server.port";

	@Autowired
	private Environment env;

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	ProductService productService;

	/**
	 * @return
	 */
	@GetMapping("/")
	private ResponseEntity<String> getGreetings() {
		log.info("Response From Product Service!");
		return new ResponseEntity<>("Response From Product Service!", HttpStatus.OK);
	}

	/**
	 * This service will use RestTemplate to communicate with another Micro Service
	 * 
	 * 
	 */
	@GetMapping(value = "/products")
	public List<Product> getAllProducts() {
		log.info("[INFO] : GetAllProducts invoked..!");
		List<Product> products = productService.getAllProduct();

		// Application app = eurekaClient.getApplication("Exchange-Rate-Service");
		// List<InstanceInfo> instanceList = app.getInstances();
		// InstanceInfo instacne = instanceList.get(0);
		// LOGGER.info("{}", instacne.getIPAddr());

		// Feign - Problem 1
		Map<String, String> urivariables = new HashMap<>();
		// urivariables.put("from", from);
		// urivariables.put("to", to);
		// ResponseEntity<CurrencyConvesion> responseEntity = new
		// RestTemplate().getForEntity(
		// "http://" + instacne.getIPAddr() + ":" + instacne.getPort() +
		// "/exchange-rates/from/{from}/to/{to}",
		// CurrencyConvesion.class, urivariables);
		// CurrencyConvesion bean = responseEntity.getBody();

		log.info("Get Instace Info by serice Name >>>>>" + products);

		// new CurrencyConvesion(1l, from, to, bean.getConversionMultiple(), quantity,
		// quantity.multiply(bean.getConversionMultiple()), bean.getPort());

		return products;
	}

	/**
	 * Intercommunication in between Micro service over Feign client
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 */
	@GetMapping(value = "/currency-converter-feing/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<String> fetchProduct(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		return ResponseEntity.ok("[INFO] : Product controller port:" + env.getProperty(LOCAL_SERVER_PORT));
	}
}
