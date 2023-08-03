package com.example.ergasia2.Admin;


import com.example.ergasia2.otherStuff.SystemD;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/pickContentAdmin")
public class AddContentAdmin extends HttpServlet {
    SystemD d = new SystemD();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<form method='POST'>");
        out.println("<label> Pick a user to make content Admin</label>");
        out.println("<select name='item'>");

        try {

            ResultSet resultSet = d.getCustomersNames();

            while (resultSet.next()) {
                String itemName = resultSet.getString("name");
                out.println("<option value='" + itemName + "'>" + itemName + "</option>");
            }

            resultSet.close();

        } catch (SQLException e) {
            // Handle any potential database errors
            e.printStackTrace();
        }

        out.println("</select>");
        out.println("<button type='submit'>Confirm Choices</button>");
        out.println("</form>");
        out.println("<form action='admin.jsp'>");
        out.println("<input type='submit' value='Return'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("item");
        System.out.println(cname);
        int cid;
        try {
            cid = d.getLastCAdminId() + 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
                d.addContentAdmin(cid , cname, d.getUsernameFromName(cname));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            d.changeRoleToCAdmin(d.getUsernameFromName(cname));
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        try {
            d.deleteCustomer(d.getUsernameFromName(cname));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);

    }


}