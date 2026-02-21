package com.application.ene.orgmanagement.product.internal;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String description;
    private int price;

    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // getters and setters

}