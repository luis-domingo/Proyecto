package edu.upc.dsa.models;

public class UserItem {
    String name;
    String quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
