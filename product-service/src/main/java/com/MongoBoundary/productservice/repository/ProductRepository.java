package com.MongoBoundary.productservice.repository;

import com.MongoBoundary.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findProductByProductId(String id);

    boolean existsByProductId(String id);

}