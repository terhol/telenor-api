package com.example.telenorapi;

import com.example.telenorapi.controllers.ProductController;
import com.example.telenorapi.models.Product;
import com.example.telenorapi.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan
public class ProductControllerTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductController productController;

    @Test
    public void findProductWithNoParams() {
        List<Product> products = productController.findProduct(null,null,null,null,null,null,null,null);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(100);
    }

    @Test
    public void findProductWithOneParam() {
        List<Product> products = productController.findProduct("phone",null,null,null,null,null,null,null);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(42);
    }

    @Test
    public void findProductWithTwoParams() {
        List<Product> products = productController.findProduct("phone","Stockholm",null,null,null,null,null,null);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(15);
        assertThat(products.get(0).getPrice()).isEqualTo(271.00);
    }

    @Test
    public void findProductWithGbLimitParams() {
        List<Product> products = productController.findProduct(null,null,null,null,null,20.0,50.0,null);
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(34);
        assertThat(products.get(0).getPrice()).isEqualTo(415.00);
    }
    @Test
    public void findProductWithAllValidParams() {
        List<Product> products = productController.findProduct("subscription","Stockholm",300.0,500.0,null,20.0,50.0,"gb_limit");
        assertThat(products).isNotEmpty();
        assertThat(products).size().isEqualTo(4);
        assertThat(products.get(0).getPrice()).isEqualTo(415.00);
    }

    @Test
    public void findProductWithAllParams() {
        List<Product> products = productController.findProduct("subscription","Stockholm",300.0,500.0,"guld",20.0,50.0,"gb_limit");
        assertThat(products).isEmpty();
    }

    @Test
    public void findProductWithInvalidParams() {
        List<Product> products = productController.findProduct("tablet",null,null,null,null,null,null,null);
        assertThat(products).isEmpty();
    }




}
