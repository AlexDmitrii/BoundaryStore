package com.MongoBoundary.repositories;

import com.MongoBoundary.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

    Product findProductByProductId(String id);

}
