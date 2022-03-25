package com.example.telenorapi.controllers;

import com.example.telenorapi.models.Product;
import com.example.telenorapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
