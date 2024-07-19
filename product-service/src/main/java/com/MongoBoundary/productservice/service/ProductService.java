package com.MongoBoundary.productservice.service;

import com.MongoBoundary.productservice.model.Product;
import com.MongoBoundary.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;

    public List<Product> getListProducts() {
        return productRepo.findAll();
    }

    public Product createProduct(Product product) {
        productRepo.save(product);
        return product;
    }

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

    public Product updateProduct(Product product) {
        productRepo.save(product);
        return product;
    }

    public Product findProductByProductId(String productId) {
        return productRepo.findProductByProductId(productId);
    }

}
