package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.ShopItem;
import edu.upc.dsa.util.ObjectHelper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopDAOImpl implements ShopDAO{
    private static ShopDAO instance;

    final static Logger logger = Logger.getLogger(ShopDAO.class);

    private ShopDAOImpl() {
    }

    public static ShopDAO getInstance() {
        if (instance==null) instance = new ShopDAOImpl();
        return instance;
    }
    public ShopItem[] getShopItems() throws SQLException {
        Session session = null;
        ShopItem a = new ShopItem();
        LinkedList<ShopItem> list = new LinkedList<ShopItem>();
        logger.info("Alguien esta intentando ver la tienda");
        try {
            session = FactorySession.openSession();
            logger.info(session.findAllItems(a).toString());
            logger.info(list.toString());
        }
        catch (IOException e) {
            logger.warn("Exception message: "  + e.getMessage());
        }
        finally {
            session.close();
        }
        return list;
    }

}
