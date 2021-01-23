package edu.upc.dsa.dao;

import edu.upc.dsa.models.Stats;

import java.sql.SQLException;

public interface StatsDAO {

    public void setStats(String id, String games, String best, String last) throws SQLException;
    public Stats getStats(String id) throws SQLException;

}
