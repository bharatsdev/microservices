/**
 * 
 */
package com.techwizard.resources;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.techwizard.CurrencyConversionApplication;
import com.techwizard.feignproxy.CurrencyExchangeFeignProxy;
import com.techwizard.model.CurrencyConvesion;

/**
 * @author everythingisdata
 *
 */
@RestController
public class CurrencyConvesionController {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CurrencyConversionApplication.class);

	@Autowired
	private CurrencyExchangeFeignProxy proxy;
	@Autowired
	private EurekaClient eurekaClient;
	
	
	/**
	 * @return
	 */
	@GetMapping("/")
	private String getGreetings() {
		LOGGER.info("Response from Currency Conversion Service ");
		return "This response from First Currency Conversion Service !";
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
	public CurrencyConvesion convert(@PathVariable("from") String from, @PathVariable("to") String to,
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
		ResponseEntity<CurrencyConvesion> responseEntity = new RestTemplate().getForEntity(
				"http://" + instacne.getIPAddr() + ":" + instacne.getPort() + "/exchange-rates/from/{from}/to/{to}",
				CurrencyConvesion.class, urivariables);
		CurrencyConvesion bean = responseEntity.getBody();

		LOGGER.info("Get Instace Info by serice Name >>>>>");

		return new CurrencyConvesion(1l, from, to, bean.getConversionMultiple(), quantity,
				quantity.multiply(bean.getConversionMultiple()), bean.getPort());
	}

	/**
	 * Inter-communication in between Micro service over Feing client * @param from
	 * 
	 * @param to
	 * @param quantity
	 * @return
	 */
	@GetMapping(value = "/currency-converter-feing/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvesion convertFeig(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		CurrencyConvesion responseBean = proxy.retriveExchangeValue(from, to);

		LOGGER.info(" {}  Response ", responseBean);

		return new CurrencyConvesion(1l, from, to, responseBean.getConversionMultiple(), quantity,
				quantity.multiply(responseBean.getConversionMultiple()), responseBean.getPort());
	}



}
