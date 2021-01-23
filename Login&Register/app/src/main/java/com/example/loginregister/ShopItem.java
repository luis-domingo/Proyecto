package com.example.loginregister;


public class ShopItem {
    String name;
    int price;
    int ID;

    public String getName() {
        return name;
    }

    public void setItem(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getIdString() {
        return String.valueOf(ID);
    }

}
