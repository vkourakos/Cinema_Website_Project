package com.example.ergasia2.Customer;

import com.example.ergasia2.otherStuff.SystemD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet("/movieSchedule")
public class MovieSchedule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("body {");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("}");
        out.println("body {");
        out.println("    background-image: linear-gradient(to top, #13547a 0%, #80d0c7 100%);");
        out.println("    font-family: 'Roboto', sans-serif;");
        out.println("}");
        out.println(".container {");
        out.println("    text-align: center;");
        out.println("}");
        out.println(".input-field {");
        out.println("    display: block;");
        out.println("    margin-bottom: 20px;");
        out.println("    width: 200px;");
        out.println("    font-size: 20px;");
        out.println("    padding: 10px;");
        out.println("}");
        out.println(".button, .return-button {");
        out.println("    display: block;");
        out.println("    margin-top: 20px;");
        out.println("    padding: 15px 30px;");
        out.println("    width: 200px;");
        out.println("    background-color: #e8a200;");
        out.println("    color: #fff;");
        out.println("    font-size: 18px;");
        out.println("    border: none;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println(".label-field {");
        out.println("    display: block;");
        out.println("    margin-bottom: 30px;");
        out.println("    font-size: 24px;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<form method='POST'>");
        out.println("<label class='label-field'>Choose Date Range</label>");
        out.println("<input type='date' name='date' class='input-field' required>");
        out.println("<input type='date' name='date2' class='input-field' required>");
        out.println("<button type='submit' class='button'>Confirm Choices</button>");
        out.println("</form>");
        out.println("<form action='customer.jsp'>");
        out.println("<input type='submit' class='button return-button' value='Return'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemD d = new SystemD();
        Date firstDate = Date.valueOf(request.getParameter("date"));
        Date secondDate = Date.valueOf(request.getParameter("date2"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ResultSet rs ;
        try {
            rs = d.getProvolesDated(firstDate,secondDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Movie Schedule</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    background-image: linear-gradient(to top, #13547a 0%, #80d0c7 100%);");
        out.println("    font-family: 'Roboto', sans-serif;");
        out.println("}");
        out.println("table {");
        out.println("    width: 100%;");
        out.println("    border-collapse: collapse;");
        out.println("}");
        out.println("th, td {");
        out.println("    padding: 8px;");
        out.println("    text-align: left;");
        out.println("    border-bottom: 1px solid #ddd;");
        out.println("}");
        out.println("th {");
        out.println("    background-color: black;");
        out.println("    color: black;");
        out.println("    font-weight: bold;");
        out.println("}");
        out.println(".scrollable-table {");
        out.println("    height: 300px;");
        out.println("    overflow-y: scroll;");
        out.println("}");
        out.println(".return-button {");
        out.println("    display: block;");
        out.println("    margin-top: 20px;");
        out.println("    padding: 10px 20px;");
        out.println("    background-color: #e8a200;");
        out.println("    color: #fff;");
        out.println("    font-size: 16px;");
        out.println("    border: none;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Movie Schedule</h1>");
        out.println("<div class='scrollable-table'>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th style='color: #fff;'>MOVIES_NAME</th>");
        out.println("<th style='color: #fff;'>CINEMAS</th>");
        out.println("<th style='color: #fff;'>DATES</th>");
        out.println("<th style='color: #fff;'>TIME</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        try {
            if (!rs.next()) {
                System.out.println("No results found.");
            } else {
                do {
                    String name = rs.getString("MOVIES_NAME");
                    String cname = rs.getString("NAME");
                    Date date3 = rs.getDate("DATES");
                    Time time1 = rs.getTime("TIMES");
                    out.println("<tr>");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + cname + "</td>");
                    out.println("<td>" + date3 + "</td>");
                    out.println("<td>" + time1 + "</td>");
                    out.println("</tr>");
                } while (rs.next());
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("<form action=\"customer.jsp\">");
        out.println("<input type=\"submit\" class=\"return-button\" value=\"Return\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}