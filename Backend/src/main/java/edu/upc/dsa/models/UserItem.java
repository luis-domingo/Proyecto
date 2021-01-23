package edu.upc.dsa.models;

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
}
