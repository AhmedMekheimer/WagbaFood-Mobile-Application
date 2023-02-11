package com.example.wagba_application;


public class DishInfoModel {
    private String name;
    private String price;
    private String Availability;
    private Integer img;

    public DishInfoModel(String name, String price, String availability, Integer img) {
        this.name = name;
        this.price = price;
        this.Availability = availability;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
