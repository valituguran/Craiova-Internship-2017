<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome <%=session.getAttribute("name")%></title>
</head>
<body>
<h3>Login successful!!!</h3>
<h4>
    Hello,admin
    <%=session.getAttribute("name")%></h4>
<table>

    <tr>
        <td><a href="register.jsp">Register</a></td>
    </tr>
</table>

</body>
</html>