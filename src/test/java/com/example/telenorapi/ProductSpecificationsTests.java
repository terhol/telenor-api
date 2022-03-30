package com.example.telenorapi;

import com.example.telenorapi.models.Product;
import com.example.telenorapi.repositories.ProductRepository;
import com.example.telenorapi.specifications.ProductSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class ProductSpecificationsTests {
    @Autowired
    ProductRepository productRepository;


    @Test
    public void repositoryDataIsLoaded() {
        List<Product> products = productRepository.findAll();
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(100);
    }

    @Test
    public void findByType() {
        Specification<Product> specification = ProductSpecification.isCorrectType("phone");
        List<Product> products = productRepository.findAll(specification);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(42);
    }

    @Test
    public void findByPrice() {
        Specification<Product> specification = ProductSpecification.isCorrectPrice(100.0, 200.0);
        List<Product> products = productRepository.findAll(specification);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(8);
        assertThat(products.get(0).getPrice()).isEqualTo(130.00);
    }

    @Test
    public void findByPriceWhenPriceIsIncorrect() {
        Specification<Product> specification = ProductSpecification.isCorrectPrice(200.0, 100.0);
        List<Product> products = productRepository.findAll(specification);
        assertThat(products).isEmpty();
    }

    @Test
    public void findByCity() {
        Specification<Product> specification = ProductSpecification.isCorrectAddress("Stockholm");
        List<Product> products = productRepository.findAll(specification);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(40);
        assertThat(products.get(0).getCity()).isEqualTo("Odell gatan, Stockholm");
    }

    @Test
    public void findByProperty() {
        Specification<Product> specification = ProductSpecification.isCorrectProperty("color");
        List<Product> products = productRepository.findAll(specification);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(42);
        assertThat(products.get(41).getProperties()).isEqualTo("color:silver");
    }

    @Test
    public void findByColor() {
        Specification<Product> specification = ProductSpecification.isCorrectParamColor("guld");
        List<Product> products = productRepository.findAll(specification);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(7);
        assertThat(products.get(0).getProperties()).isEqualTo("color:guld");
    }

    @Test
    public void findByMissingValue() {
        Specification<Product> specification = ProductSpecification.isCorrectParamColor(null);
        List<Product> products = productRepository.findAll(specification);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(100);
    }
}
