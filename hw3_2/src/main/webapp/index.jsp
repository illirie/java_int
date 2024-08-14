<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome Page</title>
</head>
<body>

<h2 align="center">Welcome to database management system</h2>
<br />
<table align="center">

    <tr>
        <td>To work with rates table:</td>
        <td><input type="button" value="Rates data"
                   onclick="window.location.href='Rate.jsp'" /></td>
    </tr>
    <tr>
        <td>To work with rooms table:</td>
        <td><input type="button" value="Rooms data"
                   onclick="window.location.href='Room.jsp'" /></td>
    </tr>
    <tr>
        <td>To work with guests table:</td>
        <td><input type="button" value="Guest data"
                   onclick="window.location.href='Guest.jsp'" /></td>
    </tr>
    <tr>
        <td>To work with employee table:</td>
        <td><input type="button" value="Employee data"
                   onclick="window.location.href='Employee.jsp'" /></td>
    </tr>
    <tr>
        <td>To work with banned table:</td>
        <td><input type="button" value="Banned data"
                   onclick="window.location.href='Banned.jsp'" /></td>
    </tr>
</table>

</body>


</html>