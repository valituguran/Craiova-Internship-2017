<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="../styles/layout.css" type="text/css">
<title>Login Application</title>
</head>
<body>
<div class="wrapper row1">
	<header id="header" class="clear">
		<div id="hgroup">
			<h1><a href="#">BOOKSTORE</a></h1>
		</div>
	</header>
</div>
<div class="wrapper row2">
	<div id="container" class="clear">
		<!-- content body -->


		<div class="container">

			<form action="loginServlet" method="post">
				<h1>Login</h1>
				<fieldset>
					<label for="name"> Username:</label>
					<input type="text" id="name" name="name" required="required">

					<label for="password"> Password:</label>
					<input type="password" id="password" name="password" required="required">
				</fieldset>
				<button type="submit">Login</button>
			</form>
		</div><!-- container -->

	</div>
	<div class="wrapper row3">
		<footer id="footer" class="clear">
			<p class="fl_left">Copyright &copy; - All Rights Reserved - <a href="www.ymens.com">Ymens Homepage</a></p>
			<p class="fl_right"> Bookstore</p>
		</footer>
	</div>
</div>
</body>
</html>