package com.example.workshop;

public class UserEntity {
    String name;
    String category;
    String shopName;
    String phoneNumber;
    String location;

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

    public void setLocation(String location) {
        this.location = location;
    }

    public UserEntity(String name, String shopName, String category, String phoneNumber, String location) {
        this.name = name;
        this.shopName = shopName;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.location = location;
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

    public String getLocation() {
        return location;
    }
}
