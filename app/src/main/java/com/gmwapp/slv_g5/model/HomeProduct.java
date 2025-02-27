package com.gmwapp.slv_g5.model;

public class HomeProduct {

    private Integer id;
    private String name;
    private String unit;
    private String measurement;
    private String price;
    private String image;
    private String updatedAt;
    private String createdAt;
    private String description;
    private String ratings;

    public HomeProduct(Integer id, String name, String unit, String measurement, String price,
                       String image, String updatedAt, String createdAt, String description, String ratings) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.measurement = measurement;
        this.price = price;
        this.image = image;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.description = description;
        this.ratings = ratings;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
