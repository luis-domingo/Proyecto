package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.Coins;
import edu.upc.dsa.models.Map;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MapsDAOImpl implements MapsDAO{

    final static Logger logger = Logger.getLogger(MapsDAOImpl.class);

    @Override
    public Map getMap(String name) throws SQLException {
        Session session = null;
        Map mapResult = new Map();
        try {
            List<Map> mapList = new LinkedList<Map>();
            session = FactorySession.openSession();
            HashMap<String, String> conditions = new HashMap<String, String>();
            conditions.put("name", name);
            mapList = session.findAllItems(Map.class,conditions);
            mapResult = mapList.get(0);
        }
        catch (IOException e) {
            logger.warn("Exception message: "  + e.getMessage());
        }
        finally {
            session.close();
        }
        return mapResult;
    }
}
