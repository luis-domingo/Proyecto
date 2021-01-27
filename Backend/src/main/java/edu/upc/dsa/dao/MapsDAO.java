package edu.upc.dsa.dao;


import edu.upc.dsa.models.Map;

import java.sql.SQLException;

public interface MapsDAO {
    public Map getMap(String name) throws SQLException;
}
