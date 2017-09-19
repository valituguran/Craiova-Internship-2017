
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../styles/layout_login.css" type="text/css">
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
    <title>Title</title>
</head>
<body>
<%session.setAttribute("page", "products_admin.jsp");
String name = (String) session.getAttribute("type");
if (name.equalsIgnoreCase("admin")){%>
<div class="topnav">
    <a href="products_admin.jsp">Inapoi</a>
</div>
<%} else {%>
<div class="topnav">
    <a href="products_user.jsp">Inapoi</a>
</div>
<%}%>
<form action="addbookServlet" method="post" enctype="multipart/form-data">
    <header>Adauga o carte noua</header>
    <label for="name"> Nume:</label>
    <input type="text" id="name" name="name" required="required" />

    <label for="cnp">CNP Autor:</label>
    <input type="text" id="cnp" name="cnp" required="required" />

        <label for="isbn"> ISBN:</label>
        <input type="text" id="isbn" name="isbn" required="required" />

        <label for="price"> Pret: </label>
        <input type="text" id="price" name="price" required="required" />

        <label for="description"> Descriere carte:</label>
        <input type="text" id="description" name="description" required="required" />

        <p style="color:black;">Selecteaza imaginea cartii:</p><br>
    <input type="file" name="image" id="file" size="40" style="color:black;" required="required" />
    <button type="submit" >Add</button>
</form>
<script>



</script>
</body>
</html>
