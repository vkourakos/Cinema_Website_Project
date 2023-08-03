package com.example.ergasia2.Admin;


import com.example.ergasia2.otherStuff.SystemD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/deleteContentAdmin")
public class DeleteContentAdmin extends HttpServlet {
    SystemD d = new SystemD();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<form method='POST'>");
        out.println("<label> Pick a content Admin to delete </label>");
        out.println("<select name='item'>");

        try {

            ResultSet resultSet = d.getCAdminNames();

            while (resultSet.next()) {
                String itemId = resultSet.getString("ID");
                String itemName = resultSet.getString("NAME");
                out.println("<option value='" + itemId + "'>" + itemName + "</option>");
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
        HttpSession session=request.getSession(true);
        String cId = request.getParameter("item");
        int cid2 = Integer.parseInt(cId);
        session.setAttribute("cid", cid2);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<form action='/deleteContentAdmin2'>");
        out.println("<label> Pick a content Admin to take on the deleted one's movies </label>");
        out.println("<select name='item2'>");

        try {

            ResultSet resultSet = d.getCAdminNames();

            while (resultSet.next()) {

                int  itemId = Integer.parseInt(resultSet.getString("ID"));
                if(!(itemId == cid2)) {
                    String itemName = resultSet.getString("NAME");
                    out.println("<option value='" + itemId + "'>" + itemName + "</option>");
                }
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



        /*try {
            d.addProvolh(movieid2, d.getMovieName(movieid2), cinemaId2, caid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("content_admin.jsp");
        dispatcher.forward(request, response);*/

    }
}