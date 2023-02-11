package com.example.wagba_application;

public class CartInfoModel {
    private String name;
    private String price;
    private String Availability;

//    public CartInfoModel(String order_details, String num_item, String dish_name, String dish_price) {
//        this.order_details = order_details;
//        this.num_item = num_item;
//        this.dish_name = dish_name;
//        this.dish_price = dish_price;
//    }


    public CartInfoModel(String name, String price, String availability) {
        this.name = name;
        this.price = price;
        Availability = availability;
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
}
