
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cinema App - Customer's menu</title>
    <meta name="description" content="Cinema App">
    <script src="./javascript/error.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="css/user.css">



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

        if(session.getAttribute("role")!="customer")
        {

            session.removeAttribute("username");
            session.invalidate();
            request.setAttribute("msg","Access to page denied, you were logged out for security reasons.");
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request,response);
        }
    }
%>

<header>
    <div class="container">
        <nav>
            <ul class="nav-container">
                <li class="nav-item"><a href="customer?action=viewMovieSchedule">Movie Schedule</a></li>
                <li class="nav-item"><a href="customer?action=MovieAvailability">Movie Availability</a></li>
                <li class="nav-item"><a href="customer?action=TicketReservation">Ticket Reservation</a></li>
                <li class="nav-item"><a href="customer?action=ReservationHistory">Reservation History</a></li>
                <li class="nav-item"><a href="/logout">Log out</a></li>
            </ul>

        </nav>
    </div>
</header>

<div class="accordion" onclick="location.href = 'customer?action=viewMovieSchedule'">
    <ul>
        <li>
            <div class="image_title">
                <a href="#">Transformers: The Last Knight</a>
            </div>
            <img src="https://image.ibb.co/k7P0kS/transformers4_640x320.jpg" alt="transformers4_640x320" border="0">
        </li>
        <li>
            <div class="image_title">
                <a href="#">Blade Runner 2049</a>
            </div>
            <img src="https://image.ibb.co/ct9rQS/Blade_Runner2049_640x320.jpg" alt="Blade_Runner2049_640x320" border="0">
        </li>
        <li>
            <div class="image_title">
                <a href="#">Guardians of the Galaxy: Vol. 2</a>
            </div>
            <img src="https://image.ibb.co/jAu0kS/GOG2_640x320.jpg" alt="GOG2_640x320" border="0">
        </li>
    </ul>
</div>







</body>
</html>
