package com.example.loginregister.models;

import java.util.List;

public class ForumTopic {
    String title;
    String id;
    String numPublications;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumPublications() {
        return numPublications;
    }

    public void setNumPublications(String numPublications) {
        this.numPublications = numPublications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ForumTopic(String title, String id, String numPublications) {
        this.title = title;
        this.id = id;
        this.numPublications = numPublications;
    }

    public ForumTopic(){

    }

    public ForumTopic(String title, String id) {
        this.title = title;
        this.id = id;
    }


}
