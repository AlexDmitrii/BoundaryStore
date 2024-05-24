package com.MongoBoundary.controllers;

import com.MongoBoundary.models.Image;
import com.MongoBoundary.models.Product;
import com.MongoBoundary.services.ImageService;
import com.MongoBoundary.services.ProductService;
import com.MongoBoundary.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product){
        productService.createProduct(product);

        return product;
    }

    @PostMapping("/updateProduct")
    public ResponseEntity updateProduct(@RequestBody Product product){
        productService.updateProduct(product);

        return ResponseEntity.ok("Объект успешно отредактирован");
    }


    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity deleteProduct(@PathVariable String productId){

        boolean isDeleted = productService.deleteProduct(productId);

        if (!isDeleted){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Объект не найден");
        }

        return ResponseEntity.ok("Товар успешно удален");
    }
}
