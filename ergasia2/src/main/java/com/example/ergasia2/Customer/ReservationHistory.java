package com.example.ergasia2.Customer;

import com.example.ergasia2.otherStuff.SystemD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet("/reservationHistory")
public class ReservationHistory extends HttpServlet {
    SystemD d;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        d = new SystemD();
        HttpSession session=request.getSession(true);
        int id = (int) session.getAttribute("cust_id");
        PrintWriter out = response.getWriter();
        ResultSet rs ;
        try {
            rs = d.getReservations(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Reservation History</title>");
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
        out.println("    color: white;");
        out.println("    font-weight: bold;");
        out.println("}");
        out.println(".scrollable-table {");
        out.println("    height: 300px;");
        out.println("    overflow-y: scroll;");
        out.println("}");
        out.println(".return-button {");
        out.println("    display: block;");
        out.println("    margin-top: 10px;");
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
        out.println("<h1>Reservation History</h1>");
        out.println("<div class='scrollable-table'>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Movie Title</th>");
        out.println("<th>Cinema name</th>");
        out.println("<th>Date</th>");
        out.println("<th>Time</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        try {
            if (!rs.next()) {
                System.out.println("No results found.");
                out.println("<tr>");
                out.println("<td>no reservations</td>");
                out.println("</tr>");
            } else {
                do {
                    String name = rs.getString("PROVOLES_MOVIES_NAME");
                    String type = rs.getString("NAME");
                    Date date = rs.getDate("DATES");
                    Time time = rs.getTime("TIMES");
                    out.println("<tr>");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + type + "</td>");
                    out.println("<td>" + date + "</td>");
                    out.println("<td>" + time + "</td>");
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