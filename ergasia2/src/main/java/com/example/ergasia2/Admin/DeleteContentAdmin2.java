package com.example.ergasia2.Admin;

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

@WebServlet("/deleteContentAdmin2")
public class DeleteContentAdmin2 extends HttpServlet {
    SystemD d = new SystemD();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(true);
        int cId = (int) session.getAttribute("cid");
        String cId2= request.getParameter("item2");
        int cId3 = Integer.parseInt(cId2);
        System.out.println(cId +" , " + cId3);
        try {
            d.movieCAdmin(cId, cId3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            d.provolesCAdmin(cId, cId3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            d.deleteCAdmin(cId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);


    }
}