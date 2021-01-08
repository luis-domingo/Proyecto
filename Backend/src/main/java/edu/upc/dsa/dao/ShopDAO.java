package edu.upc.dsa.dao;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.ShopItem;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface ShopDAO {
    public List<ShopItem> getShopItems() throws SQLException;

}
