package com.ms.order.feignproxy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.ms.order.model.Product;

/**
 * THis class is for fault tolerance/hystrix. In case serice call failed
 * 
 * @author Bharat2010
 *
 */
@Configuration
public class ProductRequestFallback implements ProductequestFeingProxy {

	@Override
	public List<Product> retriveProducts() {
 		return new ArrayList<>();
	}

}
