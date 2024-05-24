package com.MongoBoundary.services;

import com.MongoBoundary.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getListProducts();
    Product createProduct(Product product);
    boolean deleteProduct(String productId);
    Product updateProduct(Product product);
    Product findProductByProductId(String productId);

}
