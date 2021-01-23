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
    public List<ShopItem> getShopItems() throws SQLException {
        Session session = null;
        ShopItem a = new ShopItem();
        List<ShopItem> lista = new LinkedList<>();
        logger.info("Alguien esta intentando ver la tienda");
        try {
            session = FactorySession.openSession();
            lista = session.findAllItems(a.getClass());
            logger.info(lista.toString());
        }
        catch (IOException e) {
            logger.warn("Exception message: "  + e.getMessage());
        }
        finally {
            session.close();
        }
        return lista;
    }

}
