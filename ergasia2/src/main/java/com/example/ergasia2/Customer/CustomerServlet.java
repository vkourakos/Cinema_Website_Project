package com.example.ergasia2.Customer;

import com.example.ergasia2.mainPackage.Customers;
import com.example.ergasia2.otherStuff.CustomerDb;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CustomerDb customerDb;
    public CustomerServlet() {
        super();
        customerDb = new CustomerDb();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session=request.getSession(true);
        String username=(String) session.getAttribute("username");
        int id = (int) session.getAttribute("cust_id");
        Customers customers = customerDb.GetCustomerDetails(username);
        session.setAttribute("name", customers.getName());

        if(action.equalsIgnoreCase("viewMovieSchedule")) {
            request.setAttribute("action","viewMovieSchedule");
            System.out.println("Name= "+ customers.getName());
            response.sendRedirect("/movieSchedule");

        }else if (action.equalsIgnoreCase("MovieAvailability")){
            request.setAttribute("action","MovieAvailability");
            session.setAttribute("action","MovieAvailability");
            request.setAttribute("id", id);
            System.out.println("add");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pickProvolh");
            requestDispatcher.forward(request,response);

        }else if (action.equalsIgnoreCase("TicketReservation")){
            request.setAttribute("action","TicketReservation");
            session.setAttribute("action","TicketReservation");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pickProvolh");
            requestDispatcher.forward(request,response);
        }

        else if (action.equalsIgnoreCase("ReservationHistory")){
            request.setAttribute("action","ReservationHistory");
            request.setAttribute("id", id);
            System.out.println("add");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/reservationHistory");
            requestDispatcher.forward(request,response);
        }
    }
}