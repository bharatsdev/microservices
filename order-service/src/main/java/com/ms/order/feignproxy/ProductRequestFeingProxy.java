package com.ms.order.feignproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ms.order.model.Product;

@FeignClient(value = "product-service", fallback = ProductRequestFallback.class)
@RibbonClient(value = "product-service")
public interface ProductRequestFeingProxy {

	/**
	 * Fetch Product details from product service
	 * 
	 * @return
	 */
	@GetMapping("/order/products")
	public  List<Product> retrieveProducts();

}
