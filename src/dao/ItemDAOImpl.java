package dao;

import db.DBConnection;
import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO{
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        while (rst.next()){
            ItemDTO itemDTO = new ItemDTO(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4));
            allItem.add(itemDTO);
        }
        return allItem;
    }
    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
    }
    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getCode());
    }
    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return resultSet.next();
    }
    @Override
    public String  generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")));
        }
        return allItems;
    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",code);
        rst.next();
        return new ItemDTO(code + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }
}
