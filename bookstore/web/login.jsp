<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title>Random Login Form</title>
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
	<div class="help">At least 6 character</div>
	<label>Password <span>*</span></label>
	<input type="password" id="password" name="password" required="required">
	<div class="help">Use upper and lowercase lettes as well</div>
	<button type="submit">Login</button>
</form>


</body>
</html>