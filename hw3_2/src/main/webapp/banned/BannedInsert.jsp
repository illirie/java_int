<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert Details</title>
</head>
<body>

<h2>Fill in the details</h2>

<form action="./InsertBanned" method="post">
    <table>
        <tr>
            <td>First name:</td>
            <td><input type="text" name="first_name" maxlength="30" size="25" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="last_name" maxlength="40" size="35" /></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" maxlength="40" size="35" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" maxlength="40" size="35" /></td>
        </tr>
        <tr>
            <td>Reason:</td>
            <td><input type="text" name="reason" maxlength="40" size="35" /></td>
        </tr>
    </table>
    <br /> <input type="submit" value="Insert Data" />

</form>
<br />
<input type="button" value="Return to Home"
       onclick="window.location.href='index.jsp'" />
</body>
</html>
