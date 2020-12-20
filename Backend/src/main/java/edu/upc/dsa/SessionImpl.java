package edu.upc.dsa;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session {
    private final Connection conn;

    final static Logger logger = Logger.getLogger(SessionImpl.class);

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;
        logger.info("Voy a preparar la frase a introducir en la BBDD");
        try {
            pstm = conn.prepareStatement(insertQuery);
            logger.info(pstm.toString());

            int i = 2;
            for(String field : ObjectHelper.getFields(entity)){
                pstm.setObject(i,ObjectHelper.getter(entity, field));
                i++;
            }


            /*for(i=0; i< ObjectHelper.getFields(entity).length; i++){
                String field = ObjectHelper.getFields(entity)[i];
                logger.info(field);
                pstm.setObject(i, ObjectHelper.getter(entity, field));
            }*/

            pstm.executeQuery();

        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        conn.close();

    }

    public Object get(Object entity) {
        String selectQuery = QueryHelper.createQuerySELECT(entity);
        Object res = null;
        PreparedStatement pstm = null;
        logger.info("Voy a preparar la frase a introducir en la BBDD");
        try {
            pstm = conn.prepareStatement(selectQuery);
            logger.info(pstm.toString());

            int i = 1;
            for(String field : ObjectHelper.getFields(entity)){
                pstm.setObject(i,ObjectHelper.getter(entity, field));
                i++;
            }

            res = pstm.executeQuery();

        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void update(Object object) {

    }

    public void delete(Object object) {

    }

    public List<Object> findAll(Class theClass) {
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
