package edu.upc.dsa;

import javax.ws.rs.core.GenericEntity;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface Session {
    void save(Object entity);
    void close() throws SQLException;
    Object get(Object entity);
    void update(Object object);
    void delete(Object object);
    <theClass> List<theClass> findAllItems(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
