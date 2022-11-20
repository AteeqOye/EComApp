package com.example.ecomapp.models;

import java.io.Serializable;

public class ShowAllModel implements Serializable {

    String image_url;
    String description;
    String name;
    String rating;
    int price;
    String type;

    public ShowAllModel() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ShowAllModel(String image_url, String description, String name, String rating, int price, String type) {
        this.image_url = image_url;
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.type = type;
    }
}
