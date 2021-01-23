package edu.upc.dsa.models;

public class ShopItem {
    String name;
    String price;
    String ID;

    public ShopItem(){

    }

    public ShopItem(String name, String price, String id) {
        this.name = name;
        this.price = price;
        this.ID = ID;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", id='" + ID + '\'' +
                '}';
    }
}
