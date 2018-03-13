/**
 * 
 */
package com.bharat.microservices.currencyexchanges.Controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bharat.microservices.currencyexchanges.ExchangeValueRepository;
import com.bharat.microservices.currencyexchanges.model.ExchangeValue;

/**
 * @author s727953
 *
 */
@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment env;
	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		// ExchangeValue ex = new ExchangeValue(100L, from, to,
		// BigDecimal.valueOf(65));
		// ex.setPort(Integer.parseInt(env.getProperty("local.server.port")));

		ExchangeValue ex = repository.findAllByFromAndTo(from, to);
		LOGGER.info("{}   ExchangeValue", ex);
		ex.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return ex;
	}

}
