/**
 * 
 */
package com.ms.product.service;

import java.util.List;

import com.ms.product.domain.Product;

/**
 * @author Bharat2010
 *
 */
public interface ProductService {

	List<Product> getAllProduct();

	void save(Product product);

	void deleteAll();

}
