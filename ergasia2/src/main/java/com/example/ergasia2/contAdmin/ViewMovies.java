package com.example.ergasia2.contAdmin;

import com.example.ergasia2.otherStuff.SystemD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/viewMovies")
public class ViewMovies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in dopost view");
        SystemD d = new SystemD();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ResultSet rs ;
        try {
            rs = d.getMovies();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Movie Information</title>");
            out.println("<style>");
            out.println("table {width: 100%; border-collapse: collapse;}");
            out.println("th, td {padding: 8px; text-align: left; border-bottom: 1px solid #ddd;}");
            out.println("th {background-color: #f2f2f2;}");
            out.println(".scrollable-table {height: 300px; overflow-y: scroll;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Movie Information</h1>");
            out.println("<div class='scrollable-table'>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Title</th>");
            out.println("<th>Type</th>");
            out.println("<th>Description</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
        try {
            if (!rs.next()) {
                System.out.println("No results found.");
            } else {
                do {
                    String name = rs.getString("NAME");
                    String type = rs.getString("TYPE");
                    String summary = rs.getString("SUMMARY");
                    out.println("<tr>");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + type + "</td>");
                    out.println("<td>" + summary + "</td>");
                    out.println("</tr>");

                } while (rs.next());
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("<form action=\"content_admin.jsp\">");
            out.println("<input type=\"submit\" value=\"Return\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

    }


}