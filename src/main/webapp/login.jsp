<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="login" method="post">
		${SPRING_SECURITY_LAST_EXCEPTION.message}
		<label>Username</label>
		<input type="text" name="username" />
		
		<label>Password</label>
		<input type="password" name="password" />
		
		<input type="submit" name="submit" />
		
	</form>
</body>
</html>