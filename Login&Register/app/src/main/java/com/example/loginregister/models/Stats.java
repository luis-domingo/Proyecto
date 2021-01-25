package com.example.loginregister.models;

public class Stats {
    String name;
    String games;
    String best;
    String last;

    public Stats(){}

    public Stats(String name, String games, String best, String last){
        this.name = name;
        this.games = games;
        this.best = best;
        this.last = last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
