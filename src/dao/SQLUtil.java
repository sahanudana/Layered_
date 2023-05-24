package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T execute(String sql, Object...args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement prsm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            prsm.setObject((i+1),args[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) prsm.executeQuery();
        }else {
            return (T) (Boolean) (prsm.executeUpdate() > 0);
        }
    }
}
