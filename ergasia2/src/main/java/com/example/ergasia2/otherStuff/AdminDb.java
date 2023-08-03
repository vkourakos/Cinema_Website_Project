package com.example.ergasia2.otherStuff;

import com.example.ergasia2.adminStuff.Admins;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDb {
    private final Connection connection;

    public AdminDb() {
        connection = DbUtil.getConnection();
    }

    public int getAdminId(String username) {
        int id=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID from admins where user_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                id=rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }




    public Admins GetAdminDetails (String username) {
        Admins admins=new Admins();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from admins where user_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                admins.setID(rs.getInt("ID"));
                admins.setUsername(rs.getString("user_username"));
                admins.setName(rs.getString("NAME"));
                admins.setRole("content_admin");


            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
}
