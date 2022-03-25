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

@Component
public class ProductSpecification {
    @Autowired
    private static EntityManager em;

    public static Specification<Product> isCorrectType(String type){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(type == null) return null;
                return criteriaBuilder.equal(root.get("type"), type);
            }
        };
    }

    public static Specification<Product> isCorrectAddress(String city){
        return (root, query, cb) -> city !=null ?
                cb.like(root.get("city").as(String.class), "%" + city + "%")
                : cb.conjunction();
    }
}
