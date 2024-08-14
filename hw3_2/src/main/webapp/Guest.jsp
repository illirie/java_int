<%--
  Created by IntelliJ IDEA.
  User: karin
  Date: 14.08.2024
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Guest page</title>
</head>
<body>
<table>

  <tr>
    <td>To insert:</td>
    <td><input type="button" value="insert"
               onclick="window.location.href='guests/GuestInsert.jsp'" /></td>
  </tr>
  <tr>
    <td>To delete:</td>
    <td><input type="button" value="delete"
               onclick="window.location.href='guests/Delete/Guest.jsp'" /></td>
  </tr>
  <tr>
    <td>To select:</td>
    <td><input type="button" value="select"
               onclick="window.location.href='guests/SelectGuest.jsp'" /></td>
  </tr>
  <tr>
    <td>To update:</td>
    <td><input type="button" value="update"
               onclick="window.location.href='guests/UpdateGuest.jsp'" /></td>
  </tr>
  <tr>
    <td>To add reservation:</td>
    <td><input type="button" value="add"
               onclick="window.location.href='guests/AddReservation.jsp'" /></td>
  </tr>
</table>
</body>
</html>
