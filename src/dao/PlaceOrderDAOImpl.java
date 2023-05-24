package dao;

import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class PlaceOrderDAOImpl implements PlaceOrderDAO{

    @Override
    public ItemDTO findItem(String newItemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM Item WHERE code=?",newItemCode + "");
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }
    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }
    @Override
    public boolean existOrder(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        return resultSet.next();
    }
    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId());
    }
}
