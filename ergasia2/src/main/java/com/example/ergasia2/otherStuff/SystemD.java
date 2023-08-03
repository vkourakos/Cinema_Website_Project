package com.example.ergasia2.otherStuff;


import java.sql.*;
import java.time.LocalTime;

public class SystemD {
    private Connection connection;

    public SystemD() {
        connection = DbUtil.getConnection();
    }


    public String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }



    public String getSalt(String username) {
        String salt=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT salt from user where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                salt=rs.getString("salt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salt;
    }

    public String loginusernameCheck(String username) {
        String answer=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next())
            {
                answer="There is no user with the username: "+username+", please enter a valid username!";
            }
            else
            {
                answer=username;
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }


    public String passwordCheck(String username,String password) {
        String answer=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where (username=? and password=?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next())
            {

                answer="Wrong Password!";
            }
            else
            {

                answer="You logged in!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }



    public String getRole(String username) {
        String role=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM content_admin WHERE user_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs1 = preparedStatement.executeQuery();
            if (rs1.next())
            {
                role="content_admin";

            }
            else
            {
                preparedStatement = connection.prepareStatement("SELECT * FROM admins where user_username=?");
                preparedStatement.setString(1, username);
                ResultSet rs2 = preparedStatement.executeQuery();

                if (rs2.next())
                {
                    role="admin";

                }
                else
                {
                    role = "customer";

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public int getLastMovieId() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM movies");
        int lastId = 1000;


        if (resultSet.next()) {
            lastId = resultSet.getInt(1);

        }
        return  lastId;
    }
    public int getLastProvolhId() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) FROM provoles");
        int lastId = 1000;


        if (resultSet.next()) {
            lastId = resultSet.getInt(1);

        }
        return  lastId;
    }
    public int getLastCAdminId() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM content_admin");
        int lastId = 1000;


        if (resultSet.next()) {
            lastId = resultSet.getInt(1);

        }
        return  lastId;
    }


    public void addMovie(int id, String name, String type, String description, int caID) throws SQLException {

        String query = "INSERT INTO movies (ID, NAME, TYPE, SUMMARY, CONTENT_ADMIN_ID) VALUES (?, ?, ?, ?, ?)";
       PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setInt (1, id);
        statement2.setString(2, name);
        statement2.setString(3, type);
        statement2.setString(4, description);
        statement2.setInt(5, caID);
        int rowsInserted = statement2.executeUpdate();
        statement2.close();

        if (rowsInserted > 0) {
            System.out.println("Movie inserted successfully.");

        } else {
            System.out.println("Failed to insert the movie.");

        }
    }

    public  void changeRoleToCAdmin(String username) throws SQLException {
        String query = "UPDATE user SET role = 'content_admin' WHERE username = ?";
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setString(1, username);
        int rowsInserted = statement2.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("User role updated successfully!");
        } else {
            System.out.println("User not found or role already set as content_admin.");
        }


    }

    public  void movieCAdmin(int oldCAdminId, int newCAdminId) throws SQLException {
        String query = "UPDATE movies SET CONTENT_ADMIN_ID = ? WHERE CONTENT_ADMIN_ID = ?";
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setInt(1, newCAdminId);
        statement2.setInt(2, oldCAdminId);
        int rowsInserted = statement2.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("ID  updated successfully!");
        } else {
            System.out.println("ID not found .");
        }


    }

    public  void provolesCAdmin(int oldCAdminId, int newCAdminId) throws SQLException {
        String query = "UPDATE provoles SET CONTENT_ADMIN_ID = ? WHERE CONTENT_ADMIN_ID = ?";
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setInt(1, newCAdminId);
        statement2.setInt(2, oldCAdminId);
        int rowsInserted = statement2.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("ID  updated successfully!");
        } else {
            System.out.println("ID not found .");
        }


    }

    public  void deleteCustomer(String username) throws SQLException {
        String query = "DELETE FROM customers WHERE user_username = ?";
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setString(1, username);
        int rowsInserted = statement2.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Customer not found.");
        }


    }

    public  void deleteCAdmin(int id) throws SQLException {
        String query = "DELETE FROM content_admin WHERE ID = ?";
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setInt(1, id);
        int rowsInserted = statement2.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("CAdmin deleted successfully!");
        } else {
            System.out.println("CAdmin not found.");
        }


    }

    public void addContentAdmin(int id,String name, String username) throws SQLException {

        String query = "INSERT INTO content_admin (ID, NAME, user_username) VALUES (?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query);
        statement2.setInt (1, id);
        statement2.setString(2, name);
        statement2.setString(3, username);
        int rowsInserted = statement2.executeUpdate();
        statement2.close();

        if (rowsInserted > 0) {
            System.out.println("cadmin inserted successfully.");

        } else {
            System.out.println("cadmin to insert the movie.");

        }
    }
    public ResultSet getMovies() throws SQLException {
        Statement stmt = connection.createStatement();

        String query = "SELECT NAME, TYPE, SUMMARY FROM movies";

        return stmt.executeQuery(query);
    }

    public ResultSet getMoviesNames() throws SQLException {
        Statement stmt = connection.createStatement();

        String query = "SELECT ID, NAME FROM movies";

        return stmt.executeQuery(query);
    }

    public ResultSet getProvolesDated(Date date1, Date date2) throws SQLException {
        String query = "SELECT p.MOVIES_NAME, c.NAME, p.ID, p.DATES, p.TIMES FROM provoles p join cinemas c on p.CINEMAS_ID = c.ID WHERE p.DATES >= ? AND p.DATES <= ?";
        PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.setDate(1, new java.sql.Date(date1.getTime()));
        statement.setDate(2, new java.sql.Date(date2.getTime()));
        return statement.executeQuery();
    }

    public boolean checkAvailability(int id ) throws SQLException {
        String query = "SELECT c.SEATS FROM cinemas c join provoles p on p.CINEMAS_ID = c.ID WHERE p.ID =? ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        int numberOfSeats = 0;
        if (resultSet.next()) {
            numberOfSeats = resultSet.getInt("SEATS");
        }

        resultSet.close();
        statement.close();
        String query2 = "SELECT COUNT(*) AS reservation_count FROM reservations WHERE PROVOLES_ID = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setInt(1, id);
        ResultSet resultSet2 = statement2.executeQuery();

        int numberOfr = 0;
        if (resultSet2.next()) {
            numberOfr = resultSet2.getInt("reservation_count");
        }

        resultSet2.close();
        statement2.close();
        return numberOfSeats - numberOfr > 0;


    }





    public ResultSet getCustomersNames() throws SQLException {
        Statement stmt = connection.createStatement();

        String query = "SELECT name FROM user where role = 'customer'";

        return stmt.executeQuery(query);
    }

    public ResultSet getCAdminNames() throws SQLException {
        Statement stmt = connection.createStatement();

        String query = "SELECT ID, NAME FROM content_admin";
        ResultSet resultSet = stmt.executeQuery(query);

        return resultSet;
    }

    public ResultSet getCinemasNames() throws SQLException {
        Statement stmt = connection.createStatement();

        String query = "SELECT ID, NAME FROM cinemas";
        ResultSet resultSet = stmt.executeQuery(query);

        return resultSet;
    }

    public String getMovieName(int id) {
        String name = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT NAME from movies where ID=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                name=rs.getString("NAME");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public String getUsernameFromName(String name) {
        String username = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username from user where name=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                username=rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    public ResultSet getReservations(int id) throws SQLException {

        Statement stmt = connection.createStatement();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT r.PROVOLES_MOVIES_NAME, c.NAME, p.DATES, p.TIMES  FROM reservations r join provoles p  on r.PROVOLES_MOVIES_NAME = p.MOVIES_NAME join cinemas c on r.PROVOLES_MOVIES_ID = c.ID where r.CUSTOMERS_ID =?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }


    public void addProvolh(int mid, String mname, int cid, int caID, Date date, LocalTime time) throws SQLException {

        int pid = getLastProvolhId() +1;

        String query = "INSERT INTO provoles (MOVIES_ID, MOVIES_NAME, CINEMAS_ID, ID, CONTENT_ADMIN_ID, DATES, TIMES) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query);
        java.sql.Time sqlTime = java.sql.Time.valueOf(time);
        statement2.setInt (1, mid);
        statement2.setString(2, mname);
        statement2.setInt(3, cid);
        statement2.setInt(4, pid);
        statement2.setInt(5, caID);
        statement2.setDate(6,date);
        statement2.setTime(7,sqlTime);
        int rowsInserted = statement2.executeUpdate();
        statement2.close();

    }

    public void addReservation(int provolh_id, int cust_id) throws SQLException {
        String query = "SELECT MOVIES_ID, MOVIES_NAME, CINEMAS_ID FROM provoles WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, provolh_id);
        ResultSet resultSet = statement.executeQuery();
        int moviesId = 0;
        String moviesName = "";
        int cinemasId = 0;
        if (resultSet.next()) {
            moviesId = resultSet.getInt("MOVIES_ID");
            moviesName = resultSet.getString("MOVIES_NAME");
            cinemasId = resultSet.getInt("CINEMAS_ID");
        }
        System.out.println(moviesId +" " + moviesName +" " +cinemasId +" " +cust_id +" " + provolh_id);



        String query2 = "INSERT INTO reservations (PROVOLES_MOVIES_ID, PROVOLES_MOVIES_NAME, PROVOLES_CINEMAS_ID, CUSTOMERS_ID, PROVOLES_ID) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.setInt (1, moviesId);
        statement2.setString(2, moviesName);
        statement2.setInt(3, cinemasId);
        statement2.setInt(4, cust_id);
        statement2.setInt(5, provolh_id);

        int rowsInserted = statement2.executeUpdate();
        statement2.close();

    }
}
