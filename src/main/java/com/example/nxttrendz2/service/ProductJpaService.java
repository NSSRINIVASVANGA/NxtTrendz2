package com.example.nxttrendz2.service;

import com.example.nxttrendz2.model.*;
import com.example.nxttrendz2.repository.*;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class ProductJpaService implements ProductRepository {
    @Autowired
    public ProductJpaRepository productJpaRepository;

    @Autowired
    public CategoryJpaRepository categoryJpaRepository;

    @Override
    public ArrayList<Product> getProducts() {
        List<Product> productsList = productJpaRepository.findAll();
        ArrayList<Product> products = new ArrayList<>(productsList);
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        try {
            Category category = product.getCategory();
            int categoryId = category.getId();
            Category newCategory = categoryJpaRepository.findById(categoryId).get();
            product.setCategory(newCategory);
            productJpaRepository.save(product);
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong categoryId");
        }
    }

    @Override
    public Product getProductById(int productId) {
        try {
            Product product = productJpaRepository.findById(productId).get();
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        try {
            Product newProduct = productJpaRepository.findById(productId).get();
            if (product.getName() != null) {
                newProduct.setName(product.getName());
            }
            if (product.getDescription() != null) {
                newProduct.setDescription(product.getDescription());
            }
            if (product.getPrice() >= 0) {
                newProduct.setPrice(product.getPrice());
            }
            if (product.getCategory() != null) {
                Category category = product.getCategory();
                int categoryId = category.getId();
                Category newCategory = categoryJpaRepository.findById(categoryId).get();
                newProduct.setCategory(newCategory);
            }
            productJpaRepository.save(newProduct);
            return newProduct;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            productJpaRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Category getProductCategory(int productId) {
        Product product = productJpaRepository.findById(productId).get();
        Category category = product.getCategory();
        return category;
    }
}