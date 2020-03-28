package com.ms.product.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ms.product.domain.Product;

public interface ProductRepository  extends MongoRepository<Product, String>{

}
