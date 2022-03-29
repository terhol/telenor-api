package com.example.telenorapi.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "product_type")
    private String type;
    @Column(name = "price")
    private double price;
    @Column(name = "store_address")
    @JsonProperty("store_address")
    private String city;
    @Column(name = "product_properties")
    private String properties;



}






