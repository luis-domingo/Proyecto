package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

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

    public ForumTopic(String title, String numPublications) {
        this();
        this.title = title;
        this.numPublications = numPublications;
    }

    public ForumTopic(){
        this.id = RandomUtils.getId();
    }

    @Override
    public String toString() {
        return "ForumTopic{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", numPublications=" + numPublications +
                '}';
    }
}
