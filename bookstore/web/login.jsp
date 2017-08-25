<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title> Login Form</title>
	<link rel="stylesheet" href="../styles/layout_login.css">
	<style>

	</style>
</head>
<body>

<form method="post" action="loginServlet">
	<header>Login</header>
	<label>Username <span>*</span></label>
	<input type="text" id="name" name="name" required="required">
	<label>Parola <span>*</span></label>
	<input type="password" id="password" name="password" required="required">
	<button type="submit">Continua</button>
</form>


</body>
</html>
