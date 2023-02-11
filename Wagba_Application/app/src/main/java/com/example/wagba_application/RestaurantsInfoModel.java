package com.example.wagba_application;

public class RestaurantsInfoModel {
    private String name;
    private Integer img;

    public RestaurantsInfoModel(String name, Integer img) {
        this.name = name;
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }


}
