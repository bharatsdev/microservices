/**
 * 
 */
package com.techwizard.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techwizard.model.ExchangeRates;
import com.techwizards.repositry.ExchangeValueRepository;

/**
 * @author aitechwizards
 *
 */
@RestController
public class ExchangeRatesController {
	@Autowired
	private Environment env;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExchangeRatesController.class);

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("exchange-rates/from/{from}/to/{to}")
	public ExchangeRates retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		LOGGER.info("From {} to {}", from, to);
		ExchangeRates ex = repository.findAllByFromAndTo(from, to);
		LOGGER.info("{}   exchangeRate", ex);
		// writing the response service Port in Response. it will tell which service
		// instance served this request

		ex.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return ex;
	}

}
