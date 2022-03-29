package com.example.telenorapi.specifications;

import com.example.telenorapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductSpecification {
    @Autowired
    private static EntityManager em;

    public static Specification<Product> isCorrectType(String type){
        return (root, query, cb) -> type != null?
                cb.equal(root.get("type"), type)
                : cb.conjunction();
    }

    public static Specification<Product> isCorrectAddress(String city){
        return (root, query, cb) -> city !=null ?
                cb.like(root.get("city").as(String.class), "%" + city)
                : cb.conjunction();
    }

    public static Specification<Product> isCorrectPrice(Double minPrice, Double maxPrice){
        return (root, query, cb) -> maxPrice != null ?
                cb.between(root.get("price"), minPrice, maxPrice)
                : cb.conjunction();
    }

    public static Specification<Product> isCorrectParamColor(String color){
        return (root, query, cb) -> color != null ?
                cb.like(root.get("properties").as(String.class), "%" + color)
                : cb.conjunction();
    }


    public static Specification<Product> isCorrectProperty(String property) {
        return (root, query, cb) -> property != null ?
                cb.like(root.get("properties").as(String.class),  property + "%")
                : cb.conjunction();
    }
}
