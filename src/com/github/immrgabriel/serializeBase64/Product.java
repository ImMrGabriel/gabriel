package com.github.immrgabriel.serializeBase64;

import java.io.Serializable;

public class Product implements Serializable{
    private int id;
    private String description;

//    public Product() {
//    }

    public Product(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
