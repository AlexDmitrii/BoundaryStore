package com.MongoBoundary.services.impl;

import com.MongoBoundary.models.Order;
import com.MongoBoundary.models.Product;
import com.MongoBoundary.repositories.ProductRepo;
import com.MongoBoundary.services.ProductService;
import com.MongoBoundary.util.Constant;
import com.MongoBoundary.util.Status;
import com.MongoBoundary.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Product> getListProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        productRepo.save(product);
        return product;
    }

    @Override
    public boolean deleteProduct(String productId) {
        try{
            Product deletingProduct = productRepo.findProductByProductId(productId);

            if (deletingProduct == null){
                return false;
            }

            productRepo.delete(deletingProduct);

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
