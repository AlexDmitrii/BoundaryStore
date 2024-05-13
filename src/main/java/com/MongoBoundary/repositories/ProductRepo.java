package com.MongoBoundary.repositories;

import com.MongoBoundary.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, Long> {
}
