<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/6/2023
  Time: 2:36 μ.μ.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/addMovie.css">
</head>
<body>
<form action="/addMovie" method="POST">
    <label for="movieName">Movie Name:</label>
    <input type="text" id="movieName" name="movieName" required>
    <label for="movieType">Movie Type:</label>
    <input type="text" id="movieType" name="movieType" required>
    <label for="movieDescription">Movie Description:</label>
    <input type="text" id="MovieDescription" name="movieDescription" required><br>
    <input type="submit" value="Add Movie">
</form><br>
<form action="content_admin.jsp">
    <input type="submit" value="Return">
</form>

</body>
</html>
