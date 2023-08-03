package com.example.ergasia2.Admin;

import com.example.ergasia2.adminStuff.Admins;
import com.example.ergasia2.otherStuff.AdminDb;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AdminDb adminDb;


    public AdminServlet() {
        super();
        adminDb = new AdminDb();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session=request.getSession(true);
        String username=(String) session.getAttribute("username");
        String role=(String) session.getAttribute("role");
        int id = (int) session.getAttribute("adm_id");
        Admins admins = adminDb.GetAdminDetails(username);
        System.out.println("USERNAME= "+ username + "  ROLE= "+role + " cAdmins NAME = "+ admins.getName() + "cid = " + id);
        session.setAttribute("name", admins.getName());

        if(action.equalsIgnoreCase("addContentAdmin")) {
            request.setAttribute("action","addContentAdmin");
            request.setAttribute("id", id);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pickContentAdmin");
            requestDispatcher.forward(request,response);


        }else if (action.equalsIgnoreCase("deleteContentAdmin")){
            request.setAttribute("action","DeleteContentAdmin");
            request.setAttribute("id", id);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/deleteContentAdmin");
            requestDispatcher.forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }
}