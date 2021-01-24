package com.example.loginregister.models;


public class UserItem {
    String name;
    int quantity;
    String ID;


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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", ID='" + ID + '\'' +
                '}';
    }

    public UserItem(String name, int quantity, String ID) {
        this.name = name;
        this.quantity = quantity;
        this.ID = ID;
    }
}