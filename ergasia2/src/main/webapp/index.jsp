<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="./js/index.js"  type="text/javascript" ></script>
    <script src="./js/error.js"  type="text/javascript" ></script>
</head>
<body >
<script type="text/javascript">

    var msg = "<%= request.getAttribute("message") %>";
    var user="<%= request.getAttribute("user") %>";
    if (msg=="Wrong Password!" || msg=="There is no user with the username: "+user+", please enter a valid username!" || msg=="There is already a user with the username: "+user+", please enter a different username." )
    {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: msg
        })

    }
</script>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setHeader("Expires", "0"); // Proxies.
    if (request.getAttribute("msg")=="You had no access to that page, you were logged out for safety reasons."){%> <script type="text/javascript">Swal.fire({icon: 'error',title: 'Oops...',text:'<%=request.getAttribute("msg")%>'})</script><% } %>



<div class="container">
<h2>Login</h2>
<form action="/login" method="post">
    <label for="username">Username</label>
    <input type="text" id="username" name="username" required>

    <label for="password">Password</label>
    <input type="password" id="password" name="password" required>

    <input type="submit" value="Login">
</form>
</div>
</body>
</html>