<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title> Login Form</title>
	<link rel="stylesheet" href="../styles/layout_login.css">
	<style>
		body{
			background-image:url("../images/index.jpg");
		}
	</style>
</head>
<body>

<form method="post" action="loginServlet">
	<header>Login</header>
	<label>Username <span>*</span></label>
	<input type="text" id="name" name="name" required="required">
	<label>Password <span>*</span></label>
	<input type="password" id="password" name="password" required="required">
	<button type="submit">Login</button>
</form>


</body>
</html>
