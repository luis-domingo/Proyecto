package edu.upc.dsa.models;

public class UserImg {
    String name;
    String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserImg(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
