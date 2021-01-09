package com.example.loginregister;


import android.net.Uri;

import java.io.InputStream;

public class ShopItem {
    String name;
    int price;
    int id;

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
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

}
