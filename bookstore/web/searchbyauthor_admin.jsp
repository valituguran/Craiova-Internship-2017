<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>

<%@ page import="com.ymens.servlet.PaginationServlet" %>
<%@ page import="com.ymens.spring.beans.Book"%>
<%@ page import="com.ymens.spring.dao.AuthorsDao" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<title>Bookstore</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../styles/style.css">
	<link src="../scripts/file.js">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
</head>
<body>
<%String realname;
	realname=(String)session.getAttribute("realname");%>
<%int currentpage = PaginationServlet.currentPage;
	String typelist = (String)session.getAttribute("typelist");
	List<String> listAuthors = (List)session.getAttribute("listAuthors");
	String nameAuthor;
	AuthorsDao author = new AuthorsDao();
	LinkedList<Book> list = (LinkedList<Book>)session.getAttribute(typelist);
	int recordsPerPage = PaginationServlet.recordsPerPage;
	int noOfProducts = list.size();
	int noOfPages;
	if(noOfProducts % recordsPerPage == 0) {
		noOfPages = noOfProducts / recordsPerPage ;
	}
	else{
		noOfPages = noOfProducts / recordsPerPage+1;
	}
%>

<div id="mySidenav" class="sidenav">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	<form method="get" action="/mycontadminServlet" >
		<input name="as"  type="submit" value="Detalii cont" required="required">
		<input name="type" type="hidden" value="accountdetails" required="required">
	</form>
	<form method="get" action="/mycontadminServlet" >
		<input name="as"  type="submit" value="Comenziile mele" required="required">
		<input name="type" type="hidden" value="myorders" required="required">
	</form>
	<form method="get" action="/logoutServlet" >
		<input name="logout" type="submit" value="Logout" required="required">
	</form>
</div>
<div class="topnav">
	<div style="align:left;cursor:pointer;color:white;font-size: 20px;margin:30px;float:left" onclick="openNav()">&#9776;<%=realname%></div>
	<a href="shoppingcart_admin.jsp">Cos de cumparaturi</a>
	<a href="addbook.jsp">Adauga o carte</a>
	<a href="register.jsp">Adauga un utilizator</a>
</div>
<div class="content">
	<div class="menu-vertical">
		<ul class="breadcrumb">
			<li><a href="products_admin.jsp">Prima pagina</a></li>
		</ul>
		<div class="form">
			<h4>Ordoneaza: </h4>
			<form method="get" action="/filterbypriceServlet" >
				<input name="filterasc" class="filter" type="submit" value="Pret crescator" required="required">
				<input type="hidden" name="typelist" value="filtersearchbyauthor" required="required">
			</form>
			<form method="get" action="/filterbypriceServlet" id="filterbyprice">
				<input name="filterdesc" class="filter" type="submit" value="Pret descrescator" required="required">
				<input type="hidden" name="typelist" value="filtersearchbyauthor" required="required">
			</form>
			<form method="get" action="/searchbyauthorServlet" id="searchbyauthor">
				<h3>Cautare dupa autor</h3><br>
				<input name="searchbyauthor" type="text" size="40" placeholder="Cauta..." required="required">
				<input type="hidden"  name="typelist" value="searchbyauthor" required="required">
			</form>
			<form method="get" action="/searchbynameServlet" id="searchbyname">
				<h3>Cautare dupa nume</h3><br>
				<input name="searchbyname" type="text" size="40" placeholder="Cauta..." required="required">
				<input type="hidden"  name="typelist" value="searchbyname" required="required">
			</form>
		</div>
	</div>
</div>
<div class="products" id="products">
	<img class="logo" src="../images/logo.jpg">

	<div class="container">
		<% if(list.size() == 0){%>
		<h3>Nu exista produse cu acest autor</h3>
		<a href="products_admin.jsp">Toate produsele</a>
		<%} else{%>
		<%for( int i=(currentpage-1)*recordsPerPage; i<currentpage*recordsPerPage && i<noOfProducts; i++){
			Book book = (Book) list.get(i);%>
		<div class="tab-content">
			<form method="get" action="/viewbookServlet" id="">
				<input type="hidden" name="pagetitle" value="index.jsp" class="title">
				<input name="title" class="title" type="submit" value="<%=book.getName()%> ">
			</form>
			<div class="product">
				<img src="data:image/jpg;base64,<%=book.getStrImage(book.getImage())%>" />
				<%nameAuthor = (String) listAuthors.get(i);%>
				<p><%=nameAuthor%></p>
				<form name="model" method="POST" action="/cartadminServlet">
					<input class="details" type="text" size="2" value="1" name="quantity">buc
					<p> Pret: <%=book.getPrice()%><input type="hidden" name="price" value="<%=book.getPrice()%>"></p>
					<button onclick="cart()"><input type="hidden" name="action" value="add">Adauga in cos</button>
				</form>
			</div>
		</div>
		<% } %>
	</div>
</div>
<div class="bottom">
	<form  method="POST" action="/paginationServlet">
		<input type="hidden" name="page" id="page" value="/searchbyauthor_admin.jsp">
		<ul class="pagination">
			<li> <input type="submit" onclick="pagination()" name="action" value="Prev" id="prev" ></li>
			<input type="hidden" name="noOfPages" id="noOfPages" value="<%=noOfPages%>">
			<li> <input type="hidden" name="currentpage" id="current" value="<%=currentpage%>"><%=currentpage%>/<%=noOfPages%></li>
			<li> <input type="submit" onclick="pagination()" name="action" value="Next" id="next"></li>
		</ul>
	</form>
</div>
<%}%>
<script>
	function openNav() {
		document.getElementById("mySidenav").style.width = "250px";
	}
	function closeNav() {
		document.getElementById("mySidenav").style.width = "0";
	}
	var current_page = document.getElementById("page");
	var records_per_page = 9;
	function prevPage()
	{
		if (current_page > 1) {
			current_page--;
			changePage(current_page);
		}
	}

	function nextPage()
	{
		if (current_page < numPages()) {
			current_page++;
			changePage(current_page);
		}
	}

	function changePage(page)
	{
		var btn_next = document.getElementById("btn_next");
		var btn_prev = document.getElementById("btn_prev");
		var listing_table = document.getElementById("listingTable");
		var page_span = document.getElementById("page");
		if (page < 1) page = 1;
		if (page > numPages()) page = numPages();
		listing_table.innerHTML = "";
		page_span.innerHTML = page;

		if (page == 1) {
			btn_prev.style.visibility = "hidden";
		} else {
			btn_prev.style.visibility = "visible";
		}

		if (page == numPages()) {
			btn_next.style.visibility = "hidden";
		} else {
			btn_next.style.visibility = "visible";
		}
	}

	function numPages()
	{
		return Math.ceil(objJson.length / records_per_page);
	}

	window.onload = function() {
		changePage(1);
	};
	function redirectLogin() {
		var txt;
		var r = confirm("Please login to continue!!");
		if (r == true) {
			window.location="login.jsp";
		} else {
			txt = "You pressed Cancel!";
		}
		document.getElementById("demo").innerHTML = txt;
	}

</script>
</body>

</html>


