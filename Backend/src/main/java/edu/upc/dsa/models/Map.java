package edu.upc.dsa.models;

public class Map {
    String name;
    String map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Map(String name, String map) {
        this.name = name;
        this.map = map;
    }

    public Map(){}

    public Map(String name) {
        this.name = name;
    }

}
