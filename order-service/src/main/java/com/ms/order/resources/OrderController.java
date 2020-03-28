package com.ms.order.resources;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.order.feignproxy.OrderfeignProxy;
import com.ms.order.model.Order;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

/**
 * @author everythingisdata
 *
 */
@RestController(value = "/order")
public class OrderController {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OrderController.class);
	private static final String LOCAL_SERVER_PORT = "local.server.port";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;
	@Autowired
	private OrderfeignProxy proxy;
	@Autowired
	private EurekaClient eurekaClient;

	/**
	 * @return
	 */
	@GetMapping("/")
	private ResponseEntity<String> getGreetings() {
		LOGGER.info("Response from  Order Service ");
		return new ResponseEntity<String>("This response from   Order Service : " + env.getProperty(LOCAL_SERVER_PORT),
				HttpStatus.OK);
	}

	@PostMapping
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

	/**
	 * This service will use RestTemplate to communicate with another Micro Service
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 */
	@GetMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public Order convert(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		LOGGER.info("Get Instace Info by serice Name");
		Application app = eurekaClient.getApplication("Exchange-Rate-Service");
		List<InstanceInfo> instanceList = app.getInstances();
		InstanceInfo instacne = instanceList.get(0);
		LOGGER.info("{}", instacne.getIPAddr());

		// Feign - Problem 1
		Map<String, String> urivariables = new HashMap<>();
		urivariables.put("from", from);
		urivariables.put("to", to);
		// ResponseEntity<CurrencyConvesion> responseEntity = new
		// RestTemplate().getForEntity(
		// "http://" + instacne.getIPAddr() + ":" + instacne.getPort() +
		// "/exchange-rates/from/{from}/to/{to}",
		// CurrencyConvesion.class, urivariables);
		// CurrencyConvesion bean = responseEntity.getBody();

		LOGGER.info("Get Instace Info by serice Name >>>>>");

		return null;
		// new CurrencyConvesion(1l, from, to, bean.getConversionMultiple(), quantity,
		// quantity.multiply(bean.getConversionMultiple()), bean.getPort());
	}

	/**
	 * Inter-communication in between Micro service over Feing client * @param from
	 * 
	 * @param to
	 * @param quantity
	 * @return
	 */
	@GetMapping(value = "/currency-converter-feing/from/{from}/to/{to}/quantity/{quantity}")
	public Order convertFeig(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		Order responseBean = proxy.retriveExchangeValue(from, to);

		LOGGER.info(" {}  Response ", responseBean);
		//
		return new Order(1l, from, to, responseBean.getConversionMultiple(), quantity,
				quantity.multiply(responseBean.getConversionMultiple()), responseBean.getPort());

		// return null;
	}

}
