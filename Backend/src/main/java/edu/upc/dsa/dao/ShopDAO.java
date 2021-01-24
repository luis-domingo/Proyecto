package edu.upc.dsa.dao;

import edu.upc.dsa.models.ShopItem;
import edu.upc.dsa.models.UserItem;

import java.sql.SQLException;
import java.util.List;

public interface ShopDAO {
    public List<ShopItem> getShopItems() throws SQLException;
}
