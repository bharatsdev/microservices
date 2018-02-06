package com.emirates.microservices.currencyconversionservice.feignProxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emirates.microservices.currencyconversionservice.model.CurrencyConvesionBean;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8001")
/** Call Currency Exchange service through Naming Server*/

//@FeignClient(name = "currency-exchange-service")
/** Call Currency Exchange service through Zuul API Gateway */
@FeignClient("netflix-zuul-api-getway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeFeignProxy {

	// @GetMapping("currency-exchange/from/{from}/to/{to}")
	/**
	 * @Desc : Add Currency Exchange service name, by which it get register with
	 *       Eureka Naming Server.
	 * @param from
	 * @param to
	 * @return
	 */
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConvesionBean retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
