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
import java.sql.*;
import java.time.LocalDate;


@WebServlet("/pickProvolh")
public class PickProvolh extends HttpServlet {
    SystemD d;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        d = new SystemD();
        LocalDate currentDate = LocalDate.now();
        LocalDate nextMonth = currentDate.plusMonths(1);
        Date cD = Date.valueOf(currentDate);
        Date nM = Date.valueOf(nextMonth);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ResultSet rs ;
        try {
            rs = d.getProvolesDated(cD,nM);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("body {");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    background-image: linear-gradient(to top, #13547a 0%, #80d0c7 100%);");
        out.println("    font-family: 'Roboto', sans-serif;");
        out.println("    display: flex;");
        out.println("    flex-direction: column;");
        out.println("    align-items: center;");
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
        out.println(".form-container {");
        out.println("    display: flex;");
        out.println("    flex-direction: column;");
        out.println("    align-items: center;");
        out.println("    justify-content: center;");
        out.println("}");
        out.println(".return-button {");
        out.println("    display: block;");
        out.println("    margin-top: 20px;");
        out.println("    padding: 10px 20px;");
        out.println("    background-color: #e8a200;");

        out.println("    color: #fff;");
        out.println("    width: 200px;");
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
        out.println("<th>MOVIES_NAME</th>");
        out.println("<th>CINEMAS</th>");
        out.println("<th>DATES</th>");
        out.println("<th>TIME</th>");
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
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException(e);
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("<div class='form-container'>");
        out.println("<form method='POST'>");
        out.println("<br><label>Step 1: Pick a Movie</label>");
        out.println("<select name='item'>");



        try {

            rs.beforeFirst();


            while (rs.next()) {

                String itemId = rs.getString("ID");
                String itemName = rs.getString("MOVIES_NAME") + " | " + rs.getDate("DATES") + " | " + rs.getTime("TIMES");
                System.out.println(itemId + " " + itemName);
                out.println("<option value='" + itemId + "'>" + itemName + "</option>");
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</select>");
        out.println("<button class='return-button' type='submit'>Confirm Choices</button>");
        out.println("<input type='submit' class='return-button' value='Return' formaction='customer.jsp'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(true);
        System.out.println("post");
        String action = request.getParameter("action");
        int cid = (int) session.getAttribute("cust_id");
        String provolhid = request.getParameter("item");
        int provolhid2 = Integer.parseInt(provolhid);
        boolean availability;
        try {
             availability = d.checkAvailability(provolhid2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        if (!availability){
            response.setHeader("Refresh", "5;url=customer.jsp");
            out.println("<h1>No tickets Available. You will be redirected to the starting menu!</h1>");
            out.flush();

        }else{
            if (action.equalsIgnoreCase("MovieAvailability")) {
                response.getWriter().println("<h1>Tickets are Available!</h1>");
                response.getWriter().println("<button onclick=\"window.location.href='customer.jsp';\">Return</button>");
            }else{
                try {
                    d.addReservation(provolhid2, cid);
                    response.setHeader("Refresh", "5;url=customer.jsp");
                    out.println("<h1>Reservation Successful. You will be redirected to the starting menu!</h1>");
                    out.flush();

                }  catch (SQLIntegrityConstraintViolationException e) {
                    response.setHeader("Refresh", "5;url=customer.jsp");
                    out.println("<h1>Cannot make Reservation to this movie. You already have one!</h1>");
                    out.flush();

                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        }


    }
}