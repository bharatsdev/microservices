package com.ms.product.service;

import com.ms.product.domain.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProduct();

	void save(Product product);

	void deleteAll();
}
