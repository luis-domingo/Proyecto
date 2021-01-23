package com.example.loginregister.models;


public class UserItem {
    String name;
    int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }
    @Override
    public String toString() {
        return "UserItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public UserItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}