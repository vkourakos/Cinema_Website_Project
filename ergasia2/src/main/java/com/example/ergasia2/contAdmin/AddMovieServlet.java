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
import java.sql.SQLException;

@WebServlet("/addMovie")
public class AddMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in add");
        SystemD d = new SystemD();
        HttpSession session=request.getSession(true);
        int id = (int) session.getAttribute("cont_id");
        String movieName = request.getParameter("movieName");
        String movieType = request.getParameter("movieType");
        String movieDescription = request.getParameter("movieDescription");
        int movie_id;
        try {
            movie_id = d.getLastMovieId() + 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(movieName + " " + movieType + " " +movieDescription+ " " + movie_id + " " + id);


        try {
             d.addMovie(movie_id , movieName, movieType, movieDescription, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("content_admin.jsp");
        dispatcher.forward(request, response);
    }
}