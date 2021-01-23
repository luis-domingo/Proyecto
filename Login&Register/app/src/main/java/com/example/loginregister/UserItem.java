package com.example.loginregister;


public class UserItem {
    String name;
    int quantity;
    int id;

    public String getName() {
        return name;
    }

    public void setItem(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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