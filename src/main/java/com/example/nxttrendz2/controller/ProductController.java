package com.example.nxttrendz2.controller;

import com.example.nxttrendz2.model.*;
import com.example.nxttrendz2.repository.*;
import com.example.nxttrendz2.service.*;
import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ProductController {
    @Autowired
    public ProductJpaService productService;

    @GetMapping("/categories/products")
    public ArrayList<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/categories/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/categories/products/{productId}")
    public Product getProductById(@PathVariable("productId") int productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/categories/products/{productId}")
    public Product updateProduct(@PathVariable("productId") int productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/categories/products/{productId}")
    public void deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/products/{productId}/category")
    public Category getProductCategory(@PathVariable("productId") int productId) {
        return productService.getProductCategory(productId);
    }
}