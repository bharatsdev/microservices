package com.everythingisdata.order.feignproxy;

//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.everythingisdata.order.model.Order;
 


/** Call Exchange Rate service through Naming Server. With out client side load Balancing
 * 	// http://localhost:8320/exchange-rates/from/EUR/to/INR
 *  */
//@FeignClient(name = "Exchange-Rate-Service", url = "localhost:8320")

/** Enable Client side load balancing */
@FeignClient(name = "product-service", configuration = LoggingConfiguration.class)
@RibbonClient(name = "product-service")

public interface OrderfeignProxy {

	/**
	 * Add Currency Exchange service name, by which it get register with Eureka
	 * Naming Server.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	@GetMapping("/exchange-rates/from/{from}/to/{to}")
	public Order retriveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
