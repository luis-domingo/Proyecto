package com.example.loginregister.models;

public class Coins {

    String id;
    String coins;

    public Coins(){}

    public Coins(String id, String coins) {
        this.id = id;
        this.coins = coins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }
}
