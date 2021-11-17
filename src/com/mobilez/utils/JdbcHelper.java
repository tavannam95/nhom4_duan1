/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilez.utils;

import java.sql.*;

/**
 *
 * @author Nobi
 */
public class JdbcHelper {

    static String url = "jdbc:sqlserver://localhost;database=MOBILEZ";
    static String user = "admin", pass = "1";

    public static PreparedStatement getStm(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, pass);
        PreparedStatement stm;
        if (sql.trim().startsWith("{")) {
            stm = connection.prepareCall(sql);
        } else {
            stm = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stm.setObject(i + 1, args[i]);
        }
        return stm;
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement pst = JdbcHelper.getStm(sql, args);
        return pst.executeQuery();
    }

    public static Object value(String sql, Object... args) throws SQLException {
        ResultSet rs = JdbcHelper.query(sql, args);
        while (rs.next()) {
            return rs.getObject(1);
        }
        rs.close();
        return null;
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement pst = JdbcHelper.getStm(sql, args);
            try {
                return pst.executeUpdate();
            } finally {
                pst.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
