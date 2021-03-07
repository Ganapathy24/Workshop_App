package com.example.workshop;

public class UserEntity {
    String name;
    String category;
    String shopName;
    String phoneNumber;
    double latitute;
    double longitute;

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public UserEntity(String name, String shopName, String category, String phoneNumber, double latitute, double longitute) {
        this.name = name;
        this.shopName = shopName;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.latitute = latitute;
        this.longitute = longitute;

    }

    public String getName() {
        return name;
    }

    public String getShopName() {
        return shopName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }
}
