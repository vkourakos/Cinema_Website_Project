package com.example.ergasia2.otherStuff;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DbUtil {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;}
        else {
            try {
                InitialContext ctx = new InitialContext();
                DataSource datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
                connection = datasource.getConnection();
            }catch(Exception e) {
                e.printStackTrace();
            }

            return connection;

        }

    }
}
