package edu.upc.dsa.dao;

import edu.upc.dsa.models.UserItem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InvDAO {
    List<UserItem> getUserItems(String ID) throws SQLException;
    boolean buyItem(UserItem item, String ID);
}
