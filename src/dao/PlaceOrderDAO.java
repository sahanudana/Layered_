package dao;

import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;

import java.sql.*;

public interface PlaceOrderDAO {
    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
    public ItemDTO findItem(String newItemCode) throws SQLException, ClassNotFoundException;
    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public boolean existOrder(String orderId) throws SQLException, ClassNotFoundException;

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
}
