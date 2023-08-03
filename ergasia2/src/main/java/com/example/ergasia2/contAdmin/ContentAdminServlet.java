package com.example.ergasia2.contAdmin;

import com.example.ergasia2.adminStuff.ContentAdmins;
import com.example.ergasia2.otherStuff.ContentAdminDb;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;





@WebServlet("/contentAdmin")
public class ContentAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ContentAdminDb contentAdminDb;


    public ContentAdminServlet() {
        super();
        contentAdminDb = new ContentAdminDb();

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session=request.getSession(true);
        String username=(String) session.getAttribute("username");
        String role=(String) session.getAttribute("role");
        int id = (int) session.getAttribute("cont_id");
        ContentAdmins contentAdmin = contentAdminDb.GetContentAdminDetails(username);
        System.out.println("USERNAME= "+ username + "  ROLE= "+role + " contentAdmins NAME = "+ contentAdmin.getName() + "cid = " + id);
        session.setAttribute("name", contentAdmin.getName());

        if(action.equalsIgnoreCase("viewAvailableMovies")) {
            request.setAttribute("action","viewAvailableMovies");

            System.out.println("Name= "+ contentAdmin.getName());
            response.sendRedirect("/viewMovies");

        }else if (action.equalsIgnoreCase("AddMovie")){
            request.setAttribute("action","AddMovie");
            request.setAttribute("id", id);
            System.out.println("addm");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addMovie.jsp");
            requestDispatcher.forward(request,response);


        }else if (action.equalsIgnoreCase("assignCinemaTime")){
            request.setAttribute("action","assignCinemaTime");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pickMovie");
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }



}


