package ru.akirakozov.sd.refactoring.servlet;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cawa on 21.10.2017.
 */
public class DBClient {
    String address;
    Connection c;
    Statement statement;

    DBClient() throws SQLException{
        address = Config.DB_URL;
        c = DriverManager.getConnection(address);
        statement = c.createStatement();
    }

    void put(String name, long price) throws SQLException {
        String sql = "INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")";
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    LinkedList<String> getKeys() {
        LinkedList<String> keys = new LinkedList<>();
        keys.add("name");
        keys.add("price");
        return keys;
    }

    LinkedList<LinkedList<String>> getAll() throws SQLException {
        String request = "SELECT * FROM PRODUCT";
        return getListFromRequest(getKeys(), request);
    }

    LinkedList<LinkedList<String>> getMax() throws SQLException {
        String request = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
        return getListFromRequest(getKeys(), request);
    }

    LinkedList<LinkedList<String>> getMin() throws SQLException {
        String request = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
        return getListFromRequest(getKeys(), request);
    }

    LinkedList<LinkedList<String>> getListFromRequest(LinkedList<String> list, String request)  throws SQLException{
        ResultSet rs = statement.executeQuery(request);
        LinkedList<LinkedList<String>> ans = new LinkedList<>();
        while (rs.next()) {
            LinkedList<String> vals = new LinkedList<>();
            for (String str: list) {
                vals.add(rs.getString(str));
            }
            ans.push(vals);
        }
        rs.close();
        return ans;
    }

    Integer getSum() throws SQLException {
        String request = "SELECT SUM(price) FROM PRODUCT";
        return getIntFromRequest(request);
    }

    Integer getCount() throws SQLException {
        String request = "SELECT COUNT(*) FROM PRODUCT";
        return getIntFromRequest(request);
    }

    Integer getIntFromRequest(String request)  throws SQLException{
        ResultSet rs = statement.executeQuery(request);
        Integer ans = rs.getInt(1);
        rs.close();
        return ans;
    }

    void close() throws SQLException{
        statement.close();
        c.close();
    }
}
