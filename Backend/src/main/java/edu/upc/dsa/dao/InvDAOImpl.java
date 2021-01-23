package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.UserItem;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InvDAOImpl implements InvDAO{
    private static InvDAO instance;

    final static Logger logger = Logger.getLogger(InvDAO.class);

    private InvDAOImpl() {
    }

    public static InvDAO getInstance() {
        if (instance==null) instance = new InvDAOImpl();
        return instance;
    }
    public List<UserItem> getUserItems(String ID) throws SQLException {
        Session session = null;
        UserItem a = new UserItem();
        List<UserItem> lista = new LinkedList<>();
        logger.info("El usuario con ID: + " + ID + " está intentando ver su inventario");
        try {
            session = FactorySession.openSession();
            HashMap<String, String> hash = new HashMap<>();
            hash.put("ID", ID);
            lista = session.findAllItems(a.getClass(), hash);
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
