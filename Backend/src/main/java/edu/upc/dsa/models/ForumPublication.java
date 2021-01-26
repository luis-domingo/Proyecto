package edu.upc.dsa.models;

public class ForumPublication {
    String name;
    String date;
    String content;
    String topic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ForumPublication(String name, String date, String content, String topic) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.topic = topic;
    }

    public ForumPublication() {
    }
}
