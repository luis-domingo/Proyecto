package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class ForumTopic {
    String title;
    String id;
    int numPublications;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPublications() {
        return numPublications;
    }

    public void setNumPublications(int numPublications) {
        this.numPublications = numPublications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ForumTopic(String title, int numPublications) {
        this();
        this.title = title;
        this.numPublications = numPublications;
    }

    public ForumTopic(){
        this.id = RandomUtils.getId();
    }
}
