package edu.upc.dsa;

import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;
import org.apache.log4j.Logger;
import org.mariadb.jdbc.internal.com.send.parameters.BigDecimalParameter;

import javax.ws.rs.core.GenericEntity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
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

            int i = 1;
            for(String field : ObjectHelper.getFields(entity)){
                pstm.setObject(i,ObjectHelper.getter(entity, field));
                i++;
            }

            pstm.executeQuery();

        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        conn.close();

    }

    public ResultSet get(Object entity) {
        String selectQuery = QueryHelper.createQuerySELECT(entity);
        ResultSet res = null;
        PreparedStatement pstm = null;
        logger.info("Voy a preparar la frase a introducir en la BBDD");
        try {
            pstm = conn.prepareStatement(selectQuery);
            logger.info(pstm.toString());

            int i = 1;
            String [] fields = ObjectHelper.getFields(entity);
            for(int j = 1; j < fields.length; j++){
                pstm.setObject(i,ObjectHelper.getter(entity, fields[j]));
                i++;
            }

            logger.info("La query que mando a la BBDD es " + pstm.toString());
            res = pstm.executeQuery();
            res.next();

        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void update(Object object) {

    }

    public void delete(Object object) {

    }

    public <theClass>List<theClass> findAllItems(Class theClass) {
        logger.info("Retrieving information from " + theClass);
        String selectQuery = QueryHelper.createQuerySELECTALL(theClass);
        ResultSet res = null;
        PreparedStatement pstm = null;
        List<theClass> result = new LinkedList<>();
        logger.info("Voy a preparar la frase a introducir en la BBDD");
        try {
            pstm = conn.prepareStatement(selectQuery);
            logger.info("La query que mando a la BBDD es " + pstm.toString());
            res = pstm.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            while (res.next()) {
                Object entity = theClass.getDeclaredConstructor().newInstance();
                logger.info("La BBDD me devuelve " + res.getString(2));
                String[] fields = ObjectHelper.getFields(entity);
                for (int k = 0; k < rsmd.getColumnCount(); k++) {
                    ObjectHelper.setter(entity, fields[k], res.getString(k+1));
                    logger.info(res.getString(k+1));
                }
                logger.info("Anado el siguiente elemento a la lista de resultados: " + entity.toString());
                result.add((theClass)entity);
                logger.info(result.toString());
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        logger.info("La lista que devuelvo es: " + result.toString());
        return result;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }

}
