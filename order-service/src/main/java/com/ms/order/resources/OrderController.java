package com.ms.order.resources;

import com.ms.order.feignproxy.ProductRequestFeignProxy;
import com.ms.order.model.Order;
import com.ms.order.model.Product;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bharat2010
 *
 */
@RestController(value = "/order")
@Slf4j
public class OrderController {
	private static final String LOCAL_SERVER_PORT = "local.server.port";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@Autowired
	private ProductRequestFeignProxy productRequestFeignProxy;

	@Autowired
	private EurekaClient eurekaClient;

	/**
	 *
	 * @return WIll return greeting message
	 */
	@GetMapping("/")
	private ResponseEntity<String> getGreetings() {
		log.info("Response from  Order Service ");
		return new ResponseEntity<>("This response from   Order Service : " + env.getProperty(LOCAL_SERVER_PORT),
				HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<String> fetchOrder() {
		return ResponseEntity.ok("Order Controller, Port: " + env.getProperty(LOCAL_SERVER_PORT));
	}

	@GetMapping
	public ResponseEntity<String> fetchOrderWithProducts() {
		// We use the restTemplate to call another service; in this case, the
		// product-service.
		// Remember that we are using the spring.application.name we defined for the
		// product in the
		// application.properties of the product microservice.
		String product = restTemplate.getForObject("http://product-service/product", String.class);
		return ResponseEntity.ok("Order Controller, Port: " + env.getProperty(LOCAL_SERVER_PORT) + " " + product);

	}

//	/**
//	 * This service will use RestTemplate to communicate with another Micro Service
//	 *
//	 * @param from
//	 * @param to
//	 * @param quantity
//	 * @return
//	 */
//	@GetMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
//	public Order convert(@PathVariable("from") String from, @PathVariable("to") String to,
//			@PathVariable("quantity") BigDecimal quantity) {
//
//		log.info("Get Instace Info by serice Name");
//		Application app = eurekaClient.getApplication("Exchange-Rate-Service");
//		List<InstanceInfo> instanceList = app.getInstances();
//		InstanceInfo instacne = instanceList.get(0);
//		log.info("{}", instacne.getIPAddr());
//
//		// Feign - Problem 1
//		Map<String, String> urivariables = new HashMap<>();
//		urivariables.put("from", from);
//		urivariables.put("to", to);
//		// ResponseEntity<CurrencyConvesion> responseEntity = new
//		// RestTemplate().getForEntity(
//		// "http://" + instacne.getIPAddr() + ":" + instacne.getPort() +
//		// "/exchange-rates/from/{from}/to/{to}",
//		// CurrencyConvesion.class, urivariables);
//		// CurrencyConvesion bean = responseEntity.getBody();
//
//		log.info("Get Instace Info by serice Name >>>>>");
//
//		return null;
//		// new CurrencyConvesion(1l, from, to, bean.getConversionMultiple(), quantity,
//		// quantity.multiply(bean.getConversionMultiple()), bean.getPort());
//	}

	/**
	 * Inter-communication in between Micro service over Feing client * @param from
	 **/

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> fetchProducts() {
		log.info("[INFO] : fetchProducts invoked....!");
		List<Product> responseBean = productRequestFeignProxy.retrieveProducts();
		log.info(" {}  Response ", responseBean);

		return ResponseEntity.ok().body(responseBean);
	}

}
