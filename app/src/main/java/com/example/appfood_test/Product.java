package com.example.appfood_test;

public class Product {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImgFood() {
        return imgFood;
    }

    public void setImgFood(int imgFood) {
        this.imgFood = imgFood;
    }

    String name;
    String rating;
    String description;
    String price;
    int imgFood;

    public Product(String name, String rating, String description, String price, int imgFood) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.price = price;
        this.imgFood = imgFood;
    }

}
