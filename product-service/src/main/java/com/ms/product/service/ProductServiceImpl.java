package com.ms.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.product.domain.Product;
import com.ms.product.repo.ProductRepository;

/**
 * @author Bharat2010
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Product product) {
		productRepository.save(product);

	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}
}
