<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Select Details</title>
</head>
<body>

<form action="./SelectEmployee" method="get">
    Select: <select name="select">
    <option>all</option>
    <option>by_id</option>
</select>
    <table>
        <tr>
            <td>Enter ID:</td>
            <td><input type="text" name="id" maxlength="6" size="7" /></td>
        </tr>
    </table>
    <br /> <input type="submit" value="View Data" />
</form>
<br />
<input type="button" value="Return to Home"
       onclick="window.location.href='index.jsp'" />

</body>

</html>