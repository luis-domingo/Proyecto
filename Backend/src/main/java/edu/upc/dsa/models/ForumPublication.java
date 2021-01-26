package edu.upc.dsa.models;

public class ForumPublication {
    String name;
    String date;
    String content;
    String idTopic;

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

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String topic) {
        this.idTopic = topic;
    }

    public ForumPublication(String name, String date, String content, String idTopic) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.idTopic = idTopic;
    }

    public ForumPublication() {
    }

    @Override
    public String toString() {
        return "ForumPublication{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", idTopic='" + idTopic + '\'' +
                '}';
    }
}
