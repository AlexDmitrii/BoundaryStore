package com.MongoBoundary.services.impl;

import com.MongoBoundary.models.Product;
import com.MongoBoundary.repositories.ProductRepo;
import com.MongoBoundary.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Product createProduct(Product product) {
        productRepo.save(product);
        return product;
    }

    @Override
    public boolean deleteProduct(String productId) {
        return false;
    }

    @Override
    public Product editProduct(Product product) {
        return null;
    }
}
