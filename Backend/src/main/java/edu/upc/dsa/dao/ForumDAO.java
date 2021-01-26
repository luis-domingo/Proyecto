package edu.upc.dsa.dao;

import edu.upc.dsa.models.ForumPublication;
import edu.upc.dsa.models.ForumTopic;

import java.sql.SQLException;
import java.util.List;

public interface ForumDAO {
    public List<ForumTopic> getAllTopics() throws SQLException;
    public List<ForumPublication> getAllPublications(ForumTopic topic) throws SQLException;
    public void addPublication(ForumPublication forumPublication) throws SQLException;
    public void addTopic(ForumTopic topic) throws SQLException;
}
