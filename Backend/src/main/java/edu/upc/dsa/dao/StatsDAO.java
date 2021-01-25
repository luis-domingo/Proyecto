package edu.upc.dsa.dao;

import edu.upc.dsa.models.Stats;

import java.sql.SQLException;
import java.util.List;

public interface StatsDAO {

    public void setStats(String id, String games, String best, String last) throws SQLException;
    public Stats getStats(String id) throws SQLException;
    public List<Stats> getAllStats() throws SQLException;
}
