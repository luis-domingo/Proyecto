package edu.upc.dsa.dao;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.ShopItem;

import java.sql.SQLException;
import java.util.LinkedList;

public interface ShopDAO {
    public LinkedList<ShopItem> getShopItems() throws SQLException;

}
