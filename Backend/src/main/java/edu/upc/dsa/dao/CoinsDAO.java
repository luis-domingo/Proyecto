package edu.upc.dsa.dao;

import java.sql.SQLException;

public interface CoinsDAO {

    public int getCoins(String id) throws SQLException;
    public void updateCoins(String id, String coins) throws SQLException;
}
