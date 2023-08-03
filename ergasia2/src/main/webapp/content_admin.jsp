
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cinema App - Content Admins' menu</title>
    <meta name="description" content="Cinema App">
    <script src="./javascript/error.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="css/contentAdmin.css">


    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.

    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("index.jsp");
    }else{

        if(session.getAttribute("role")!="content_admin")
        {

            session.removeAttribute("username");
            session.invalidate();
            request.setAttribute("msg","Access to page denied, you were logged out for security reasons.");
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request,response);
        }
    }
%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#myNavbar">
                <span class="glyphicon glyphicon-menu-hamburger"></span>
            </button>
            <h1 class="brand brand-name navbar-left" style="font-family:Georgia" > Cinema App - Content Admins' menu </h1>
        </div>

        <%String name="content_admin"; %><div style="text-align:right;color:#bfbfbf;size:12px;">Welcome, <%= session.getAttribute("username") %> </div>

        <div class="collapse navbar-collapse navbar-right" id="myNavbar">

            <ul class="nav navbar-nav">
                <li><a href="contentAdmin?action=viewAvailableMovies">View
                    all available movies</a></li>
                <li><a href="contentAdmin?action=AddMovie">Add new movie</a></li>
                <li><a href="contentAdmin?action=assignCinemaTime">Assign movie to cinema and time</a></li>
                <li><a href="/logout">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>








</body>
</html>
