package edu.upc.dsa.models;

import java.util.List;

public class ForumTopic {
    String title;
    List<ForumPublication> listPublications;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ForumPublication> getListPublications() {
        return listPublications;
    }

    public void setListPublications(List<ForumPublication> listPublications) {
        this.listPublications = listPublications;
    }

    public ForumTopic(String title, List<ForumPublication> listPublications) {
        this.title = title;
        this.listPublications = listPublications;
    }

    public ForumTopic(){

    }
}
