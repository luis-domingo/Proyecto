package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.ForumPublication;
import edu.upc.dsa.models.ForumTopic;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ForumDAOImpl implements ForumDAO{

    private static ForumDAO instance;

    final static Logger logger = Logger.getLogger(ForumDAO.class);

    private ForumDAOImpl() {
    }

    public static ForumDAO getInstance() {
        if (instance == null) instance = new ForumDAOImpl();
        return instance;
    }

    @Override
    public List<ForumTopic> getAllTopics() throws SQLException {
        Session session = null;
        ForumTopic a = new ForumTopic();
        List<ForumTopic> list = new LinkedList<>();
        logger.info("Alguien esta intentando ver la lista de temas del foro");
        try {
            session = FactorySession.openSession();
            HashMap<String, String> hash = new HashMap<>();
            list = session.findAllItems(a.getClass(), hash);
            logger.info(list.toString());
        } catch (IOException e) {
            logger.warn("Exception message: " + e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<ForumPublication> getAllPublications(ForumTopic topic) throws SQLException {
        Session session = null;
        ForumPublication a = new ForumPublication();
        List<ForumPublication> list = new LinkedList<>();
        logger.info("Alguien esta intentando ver la lista de publicaciones del tema " + topic.getTitle());
        try {
            session = FactorySession.openSession();
            HashMap<String, String> hash = new HashMap<>();
            hash.put("publication", topic.getTitle());
            list = session.findAllItems(a.getClass(), hash);
            logger.info(list.toString());
        } catch (IOException e) {
            logger.warn("Exception message: " + e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }


    @Override
    public void addPublication(ForumPublication forumPublication) throws SQLException {
        Session session = null;
        try {
            session = FactorySession.openSession();
            logger.info("Alguien esta a punto de publicar en el tema " + forumPublication.getTopic() + " el siguiente mensaje " + forumPublication.getContent());
            session.save(forumPublication);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }
    @Override
    public void addTopic(ForumTopic forumTopic) throws SQLException{
        Session session = null;
        try {
            session = FactorySession.openSession();
            logger.info("Alguien esta a punto de publicar el tema " + forumTopic.getTitle());
            session.save(forumTopic);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }
}
