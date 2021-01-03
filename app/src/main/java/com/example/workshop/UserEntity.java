package com.example.workshop;

public class UserEntity {
    String name;
    String category;
    String shopName;
    String phoneNumber;
    String latitute;
    String longitute;

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


    public UserEntity(String name, String shopName, String category, String phoneNumber, String latitute, String longitute) {
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

    public String getLatitute() {
        return latitute;
    }

    public void setLatitute(String latitute) {
        this.latitute = latitute;
    }

    public String getLongitute() {
        return longitute;
    }

    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }
}
