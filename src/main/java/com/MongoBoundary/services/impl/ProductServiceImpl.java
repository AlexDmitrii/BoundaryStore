package com.MongoBoundary.services.impl;

import com.MongoBoundary.models.Product;
import com.MongoBoundary.repositories.ProductRepo;
import com.MongoBoundary.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final MongoTemplate mongoTemplate;

    @Override
    public Product createProduct(Product product) {
        productRepo.save(product);
        return product;
    }

    @Override
    public boolean deleteProduct(String productId) {
        Product deletingProduct = productRepo.findProductByProductId(productId);

        if (deletingProduct == null){
            return false;
        }

        productRepo.delete(deletingProduct);

        return true;
    }

    @Override
    public Product updateProduct(Product product) {
        productRepo.save(product);

        return product;
    }

    @Override
    public Product findProductByProductId(String productId) {
        return productRepo.findProductByProductId(productId);
    }
}
