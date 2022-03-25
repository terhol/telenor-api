package com.example.telenorapi.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_type")
    private String type;
    @Column(name = "price")
    private double price;
    @Column(name = "store_address")
    private String city;
    @Column(name = "product_properties")
    private String property;





}
