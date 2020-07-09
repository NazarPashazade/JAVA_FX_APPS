package com.DAO;

import java.sql.*;

public class DBHelper {

    public static Connection getConnection() {
        String path = "jdbc:mysql://localhost:3306/answer_question_g2?user=root&password=1234";
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }
}
