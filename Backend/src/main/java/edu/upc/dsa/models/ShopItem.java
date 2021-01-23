package edu.upc.dsa.models;

import edu.upc.dsa.models.Item;

public class ShopItem {
    String name;
    String price;
    String id;

    public ShopItem(){

    }

    public ShopItem(String name, String price, String id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
