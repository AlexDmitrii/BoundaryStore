package com.MongoBoundary.services;

import com.MongoBoundary.models.Product;

public interface ProductService {

    Product createProduct(Product product);
    boolean deleteProduct(String productId);
    Product editProduct(Product product);

}
