package com.example.ergasia2.contAdmin;


import com.example.ergasia2.otherStuff.SystemD;
import jakarta.servlet.RequestDispatcher;
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
import java.time.LocalTime;

@WebServlet("/pickCinema")
public class PickCinemaServlet extends HttpServlet {
    SystemD d = new SystemD();
    String  movieid;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        movieid = request.getParameter("item");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<form method='POST'>");
        out.println("<label>Step 2: Pick a Cinema and a Time</label>");
        out.println("<select name='item'>");
        try {

            ResultSet resultSet = d.getCinemasNames();
            while (resultSet.next()) {
                String itemId = resultSet.getString("id");
                String itemName = resultSet.getString("name");
                out.println("<option value='" + itemId + "'>" + itemName + "</option>");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println("</select>");
        out.println("<label>Step 2: Choose a Date</label>");
        out.println("<input type='date' name='date' required>");
        out.println("<label>Step 3: Choose a Time</label>");
        out.println("<input type='time' name='time' required>");
        out.println("<button type='submit'>Confirm Choices</button>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session=request.getSession(true);
            int caid = (int) session.getAttribute("cont_id");
            String cinemaId = request.getParameter("item");
            Date selectedDate = Date.valueOf(request.getParameter("date"));
            String timeString = request.getParameter("time");
            LocalTime selectedTime;
            selectedTime = LocalTime.parse(timeString);

            System.out.println(cinemaId + selectedTime + " post1");
            int movieid2 = Integer.parseInt(movieid);
            int cinemaId2 = Integer.parseInt(cinemaId);

        try {
            d.addProvolh(movieid2, d.getMovieName(movieid2), cinemaId2, caid, selectedDate,selectedTime);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("content_admin.jsp");
        dispatcher.forward(request, response);
    }
}