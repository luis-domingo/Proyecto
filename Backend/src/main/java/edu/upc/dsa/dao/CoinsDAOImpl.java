package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.Coins;
import org.apache.log4j.Logger;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
    public int getCoins(String id) throws SQLException {
        int numberCoins = 0;
        Session session = null;
        ResultSet res = null;
        Coins c = new Coins();
        c.setId(id);
        try {
            session = FactorySession.openSession();

            //We get the values from the table Coins
            res = (ResultSet)session.get(c);

            //Set the coins to return
            numberCoins = Integer.parseInt(res.getString(2));

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
        ResultSet res = null;
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
