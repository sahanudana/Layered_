package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
}
