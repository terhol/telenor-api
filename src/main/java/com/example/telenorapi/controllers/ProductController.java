package com.example.telenorapi.controllers;

import com.example.telenorapi.models.Product;
import com.example.telenorapi.repositories.ProductRepository;
import com.example.telenorapi.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductSpecification productSpecification;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping(value = "/product", produces = "application/json")
    public List<Product> findProduct(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String city
    ) {
        List<Product> products = productRepository.findAll(
                where(Objects.requireNonNull(ProductSpecification.isCorrectType(type)
                        .and(ProductSpecification.isCorrectAddress(city))
                ))
        );
        return products;

    }
}
