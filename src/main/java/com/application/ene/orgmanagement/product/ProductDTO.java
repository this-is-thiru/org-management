package com.application.ene.orgmanagement.product;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private String description;
    private int price;

    public ProductDTO(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // getters and setters

}