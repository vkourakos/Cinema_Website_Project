package com.example.ergasia2.otherStuff;


import com.example.ergasia2.mainPackage.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDb {
    private Connection connection;

    public CustomerDb() {
        connection = DbUtil.getConnection();
    }

    public int getCustomerId(String username) {
        int id=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID from customers where user_username=?");
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




    public Customers GetCustomerDetails (String username) {
        Customers customers=new Customers();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from customers where user_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                customers.setID(rs.getInt("ID"));
                customers.setUsername(rs.getString("user_username"));
                customers.setName(rs.getString("NAME"));
                customers.setRole("customer");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
