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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") Double min_price,
            @RequestParam(required = false) Double max_price,
            @RequestParam(required = false, name = "property:color") String color,
            @RequestParam(required = false, name = "property:gb_limit_min") Double gbLimitMin,
            @RequestParam(required = false, name = "property:gb_limit_max") Double gbLimitMax,
            @RequestParam(required = false, name = "property") String property


    ) {
        List<Product> productListFromDb = productRepository.findAll(
                where(Objects.requireNonNull(ProductSpecification.isCorrectType(type)
                        .and(ProductSpecification.isCorrectAddress(city))
                        .and(ProductSpecification.isCorrectPrice(min_price, max_price))
                        .and(ProductSpecification.isCorrectParamColor(color))
                        .and(ProductSpecification.isCorrectProperty(property))

                ))
        );

        if (gbLimitMax != null || gbLimitMin != null) {
            Double gbLimitMinSafe = gbLimitMin == null ? 0.0 : gbLimitMin;
            List<Product> updatedList = productListFromDb.stream()
                    .filter(product -> product.getType().equals("subscription"))
                    .filter(product -> {
                        Double gbLimit = Double.valueOf(product.getProperties().substring(product.getProperties().indexOf(":") + 1));
                        if (gbLimitMax == null) {
                            return gbLimit >= gbLimitMinSafe;
                        }
                        return gbLimit >= gbLimitMinSafe && gbLimit <= gbLimitMax;

                    })
                    .collect(Collectors.toList());
            return updatedList;

        }

        return productListFromDb;

    }
}
