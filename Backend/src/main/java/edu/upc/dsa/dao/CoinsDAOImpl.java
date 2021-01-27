package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.Coins;
import edu.upc.dsa.models.ForumTopic;
import org.apache.log4j.Logger;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CoinsDAOImpl implements CoinsDAO{

    private static CoinsDAO instance;

    final static Logger logger = Logger.getLogger(CoinsDAOImpl.class);

    private CoinsDAOImpl() {
    }

    public static CoinsDAO getInstance() {
        if (instance==null) instance = new CoinsDAOImpl();
        return instance;
    }

    @Override
    public void newUser(String id) throws SQLException {
        Session session = null;
        Coins c = new Coins();
        c.setId(id);
        c.setCoins("500");
        try {
            session = FactorySession.openSession();

            //We put the values in Coins table of the BBDD
            session.save(c);
        }
        catch (IOException e) {
            logger.warn("Exception message: "  + e.getMessage());
        }
        finally {
            session.close();
        }
    }

    @Override
    public int getCoins(String id) throws SQLException {
        int numberCoins = 0;
        Session session = null;
        Coins c = new Coins();
        c.setId(id);
        List<Coins> list = new LinkedList<>();
        try {
            session = FactorySession.openSession();

            //We get the values from the table Coins
            HashMap<String, String> conditions = new HashMap<String, String>();
            conditions.put("id", "'" + id + "'");
            list = session.findAllItems(c.getClass(), conditions);

            c = list.get(0);

            //Set the coins to return
            numberCoins = Integer.parseInt(c.getCoins());

        }
        catch (IOException e) {
            logger.warn("Exception message: "  + e.getMessage());
        }
        finally {
            session.close();
        }

        return numberCoins;
    }

    @Override
    public void updateCoins(String id, String coins) throws SQLException {
        Session session = null;
        Coins c = new Coins();
        c.setId(id);
        try {
            session = FactorySession.openSession();
            HashMap<String, String> params = new HashMap<String, String>();
            HashMap<String, String> conditions = new HashMap<String, String>();
            params.put("coins", String.valueOf(coins));
            conditions.put("id", id);
            session.updateObject(c,conditions,params);

        }
        catch (IOException e) {
            logger.warn("Exception message: "  + e.getMessage());
        }
        finally {
            session.close();
        }
    }
}
