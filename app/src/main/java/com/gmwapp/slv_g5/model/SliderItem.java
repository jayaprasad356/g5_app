package com.gmwapp.slv_g5.model;

public class SliderItem {
    private Integer id;
    private String name;
    private String link;
    private String image;
    private String updatedAt;
    private String createdAt;

    public SliderItem(Integer id, String name, String link, String image, String updatedAt, String createdAt) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.image = image;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}

