package com.example.ergasia2.otherStuff;

import com.example.ergasia2.adminStuff.ContentAdmins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentAdminDb {
    private Connection connection;

    public ContentAdminDb() {
        connection = DbUtil.getConnection();
    }

    public int getContentAdminId(String username) {
        int id=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID from content_admin where user_username=?");
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




    public ContentAdmins GetContentAdminDetails (String username) {
        ContentAdmins contentAdmins=new ContentAdmins();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from content_admin where user_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                contentAdmins.setID(rs.getInt("ID"));
                contentAdmins.setUsername(rs.getString("user_username"));
                contentAdmins.setName(rs.getString("NAME"));
                contentAdmins.setRole("content_admin");

                //System.out.println("PRINTING THE STUDENT: "+ contentAdmins.toString());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return contentAdmins;
    }
}
