package com.example.ergasia2.servletStuff;

import com.example.ergasia2.otherStuff.AdminDb;
import com.example.ergasia2.otherStuff.ContentAdminDb;
import com.example.ergasia2.otherStuff.CustomerDb;
import com.example.ergasia2.otherStuff.SystemD;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SystemD d;
    private ContentAdminDb cdb;
    private AdminDb adb;
    private CustomerDb customerDb;

    public LoginServlet() {
        super();
        d = new SystemD();
        cdb = new ContentAdminDb();
        adb = new AdminDb();
        customerDb = new CustomerDb();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String usernamevalidation=d.loginusernameCheck(username);

        if (usernamevalidation!=username)
        {
            request.setAttribute("message", usernamevalidation);
            request.setAttribute("username", username);
            RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
            view.forward(request, response);
        }else
        {
            password=password+d.getSalt(username);
            MessageDigest digest;
            try {
                digest = MessageDigest.getInstance("SHA-1");
                byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                password=d.bytesToHex(encodedhash);
                String passwordvalidation=d.passwordCheck(username, password);

                if (passwordvalidation=="You logged in!")

                {
                    System.out.println("You logged in!");
                    String role=d.getRole(username);
                    System.out.println("ROLE ====="+role);
                    HttpSession session = request.getSession(true);
                    synchronized(session)
                    {
                        session.setAttribute("username", username);
                        session.setAttribute("role", role);




                        if (role.equals("content_admin"))
                        {
                            session.setAttribute("cont_id", cdb.getContentAdminId(username));
                            System.out.println("INSIDE role contentadmin!");
                            request.setAttribute("username", username);
                            request.setAttribute("role", role);
                            request.setAttribute("id", cdb.getContentAdminId(username));
                            response.sendRedirect("/content_admin.jsp");
                        }
                        else if (role.equals("admin"))
                        {
                            session.setAttribute("adm_id", adb.getAdminId(username));
                            System.out.println("INSIDE role admin!");
                            request.setAttribute("username", username);
                            request.setAttribute("role", role);
                            request.setAttribute("id", adb.getAdminId(username));
                            response.sendRedirect("/admin.jsp");
                        }
                        else
                        {
                            session.setAttribute("cust_id", customerDb.getCustomerId(username));
                            System.out.println("INSIDE role customer!");
                            request.setAttribute("username", username);
                            request.setAttribute("role", role);
                            request.setAttribute("id", customerDb.getCustomerId(username));
                            response.sendRedirect("/customer.jsp");


                        }
                    }
                }
                else
                {
                    request.setAttribute("message", passwordvalidation);
                    RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
                    view.forward(request, response);
                }
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }
    }
}