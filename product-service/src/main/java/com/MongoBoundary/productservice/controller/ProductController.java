package com.MongoBoundary.productservice.controller;

import com.MongoBoundary.productservice.model.Product;
import com.MongoBoundary.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getListProducts();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product){
        productService.createProduct(product);

        return product;
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        productService.updateProduct(product);

        return ResponseEntity.ok("Объект успешно отредактирован");
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId){

        boolean isDeleted = productService.deleteProduct(productId);

        if (!isDeleted){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Объект не найден");
        }

        return ResponseEntity.ok("Товар успешно удален");
    }
}
