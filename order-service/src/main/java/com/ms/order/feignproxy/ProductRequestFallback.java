package com.ms.order.feignproxy;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import com.ms.order.model.Product;

/**
 * THis class is for fault tolerance/hystrix. In case serice call failed
 * 
 * @author Bharat2010
 *
 */
@Configuration
@Slf4j
public class ProductRequestFallback implements ProductRequestFeignProxy {

	@Override
	public List<Product> retrieveProducts()
	{
		log.error(" fallback");
 		return new ArrayList<>();
	}

}
