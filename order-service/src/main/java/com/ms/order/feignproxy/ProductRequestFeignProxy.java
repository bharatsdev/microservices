package com.ms.order.feignproxy;

import com.ms.order.model.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "product-service", fallback = ProductRequestFallback.class)
@RibbonClient(value = "product-service")
public interface ProductRequestFeignProxy {

	/**
	 * Fetch Product details from product service
	 *
	 * @return Will return the product catalogs
	 */
	@GetMapping("products")
	List<Product> retrieveProducts();

}
