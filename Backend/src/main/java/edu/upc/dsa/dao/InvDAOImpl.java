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
        List<UserItem> list = new LinkedList<>();
        logger.info("El usuario con ID: " + ID + " está intentando ver su inventario");
        try {
            session = FactorySession.openSession();
            HashMap<String, String> hash = new HashMap<>();
            hash.put("ID", ID);
            list = session.findAllItems(a.getClass(), hash);
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

    @Override
    public boolean buyItem(UserItem item, String ID){
        Session session = null;
        boolean done = false;
        logger.info("The user with ID " + ID + " is trying to buy " + item.getName());
        try{
            session = FactorySession.openSession();
            UserItem u = new UserItem();
            List<UserItem> list = getUserItems("'" + ID + "'");
            for(UserItem uitem : list){
                if(uitem.getName().equals(item.getName())) {
                    uitem.setQuantity(Integer.toString(Integer.parseInt(uitem.getQuantity()) + 1));
                    u = uitem;
                    logger.info("He encontrado el item que quiere comprar! Es " + u.toString());
                    break;
                }
            }
            HashMap<String, String> params = new HashMap<>();
            params.put("Quantity", u.getQuantity());
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("ID", ID);
            conditions.put("Name", u.getName());
            done = session.updateObject(u, conditions, params);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return done;
    }

}
