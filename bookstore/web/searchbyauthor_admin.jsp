<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@page import="com.ymens.Book"%>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List"%>
<%@ page import="com.ymens.servlet.PaginationServlet" %>
<%@ page import="com.ymens.dao.SelectBooksDao" %>
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
	int noOfPages = PaginationServlet.noOfPages;
	int recordsPerPage = PaginationServlet.recordsPerPage;
%>
<%LinkedList list =(LinkedList) session.getAttribute("searchbyauthor");%>
<div id="mySidenav" class="sidenav">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	<a href="/mycontadminServlet"><%=realname%></a>
	<a href="/logoutServlet">Logout</a>
</div>
<div class="topnav">
	<div style="align:left;cursor:pointer;color:white;font-size: 20px;margin:30px;float:left" onclick="openNav()">&#9776;<%=realname%></div>
	<a href="/../shoppingcart_admin.jsp">Cart</a>
	<a href="addbook.jsp">Add books</a>
	<a href="register.jsp">Add users</a>
</div>
<div class="content">
	<div class="menu-vertical">
		<ul class="breadcrumb">
			<li><a href="products_admin.jsp">Home</a></li>
			<li><a href="#products">Books</a></li>
		</ul>
		<div class="form">
			<h4>Filter</h4>
			<form method="get" action="/searchbyauthoradminServlet" id="searchbyauthor">
				<h3>Search by author</h3><br>
				<input name="searchbyauthor" type="text" size="40" placeholder="Search..." required="required">
			</form>
			<form method="get" action="/searchbynameadminServlet" id="searchbyname">
				<h3>Search by name</h3><br>
				<input name="searchbyname" type="text" size="40" placeholder="Search..." required="required">
			</form>
		</div>
	</div>
</div>
<div class="products" id="products">
	<img class="logo" src="../images/logo.jpg">

	<div class="container">
		<%for( int i=(currentpage-1)*recordsPerPage; i<currentpage*recordsPerPage; i++){
			Book book = (Book) list.get(i);%>
		<div class="tab-content">
			<form method="get" action="/viewbookServlet" id="">
				<input type="hidden" name="pagetitle" value="index.jsp" class="title">
				<input name="title" class="title" type="submit" value="<%=book.getNume()%> ">
			</form>
			<div class="product">
				<img src="data:image/jpg;base64,<%=book.getImage()%>" />
				<p>Quantity: <input class="details" type="text" size="2" value="1" name="quantity"></p>
				<p>Price <%=book.getPrice()%> lei<input type="hidden" name="price" value="<%=book.getPrice()%>"></p>
				<button onclick="redirectLogin()"><input type="hidden" name="action" value="add">Buy</button>
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
			<input type="hidden" name="<%=noOfPages%>" id="noOfPages" value="noOfPages">
			<li>Page <input type="hidden" name="currentpage" id="current" value="<%=currentpage%>"><%=currentpage%>/<%=noOfPages+1%></li>
			<li> <input type="submit" onclick="pagination()" name="action" value="Next" id="next"></li>
		</ul>
	</form>
</div>
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

</script>
</body>

</html>


