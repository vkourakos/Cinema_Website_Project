package com.example.ergasia2.contAdmin;

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

@WebServlet("/pickMovie")
public class PickMovieServlet extends HttpServlet {
    SystemD d;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        d = new SystemD();
        HttpSession session=request.getSession(true);
        int cid = (int) session.getAttribute("cont_id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<form action='/pickCinema' method='GET'>");
        out.println("<label>Step 1: Pick a Movie</label>");
        out.println("<select name='item'>");

        try {

            ResultSet resultSet = d.getMoviesNames();

            while (resultSet.next()) {
                String itemId = resultSet.getString("id");
                String itemName = resultSet.getString("name");
                out.println("<option value='" + itemId + "'>" + itemName + "</option>");
            }

            resultSet.close();

        } catch (SQLException e) {
            // Handle any potential database errors
            e.printStackTrace();
        }

        out.println("</select>");
        out.println("<input type=\"hidden\" id=\"cid\" value=\"" + cid + "\" name=\"cid\">");
        out.println("<button type='submit'>Confirm Choices</button>");
        out.println("</form>");
        out.println("<form action='content_admin.jsp'>");
        out.println("<input type='submit' value='Return'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}