/**
 * 
 */
package com.techwizard.currencyconversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.techwizard.currencyconversion.feignproxy.CurrencyExchangeFeignProxy;
import com.techwizard.currencyconversion.model.CurrencyConvesionBean;

/**
 * @author s727953
 *
 */
@RestController
public class CurrencyConvesionController {
	public Logger LOGGER = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeFeignProxy proxy;

	@Autowired
	private Environment env;

	@GetMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvesionBean convert(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		// Feign - Problem 1
		Map<String, String> urivariables = new HashMap<>();
		urivariables.put("from", from);
		urivariables.put("to", to);
		ResponseEntity<CurrencyConvesionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConvesionBean.class,
				urivariables);
		CurrencyConvesionBean bean = responseEntity.getBody();

		return new CurrencyConvesionBean(1l, from, to, bean.getConversionMultiple(), quantity,
				quantity.multiply(bean.getConversionMultiple()), bean.getPort());
	}

	@GetMapping(value = "/currency-converter-feing/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvesionBean convertFeig(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		CurrencyConvesionBean responseBean = proxy.retriveExchangeValue(from, to);

		LOGGER.info(" {}  Response ", responseBean);

		return new CurrencyConvesionBean(1l, from, to, responseBean.getConversionMultiple(), quantity,
				quantity.multiply(responseBean.getConversionMultiple()), responseBean.getPort());
	}

}
