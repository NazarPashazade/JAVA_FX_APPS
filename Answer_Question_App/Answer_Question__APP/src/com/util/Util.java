package com.util;

import java.sql.*;

public class Util {

    public static void closeAll(Connection c, PreparedStatement ps, ResultSet rs) {
        try {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                c.close();
            }
            if (rs != null) {
                c.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
