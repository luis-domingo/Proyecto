package edu.upc.dsa.models;

public class Stats {
    String id;
    String games;
    String best;
    String last;

    public Stats(){}

    public Stats(String id, String games, String best, String last){
        this.id = id;
        this.games = games;
        this.best = best;
        this.last = last;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
