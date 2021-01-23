package edu.upc.dsa.dao;

import edu.upc.dsa.models.UserItem;

import java.sql.SQLException;
import java.util.List;

public interface InvDAO {
    public List<UserItem> getUserItems() throws SQLException;

}
