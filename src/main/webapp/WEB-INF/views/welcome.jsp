<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
    
<!DOCTYPE html>

<html>


<head>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
                rel="stylesheet">
<title>RemindYou Welcome Page</title>
</head>
<body>
<div class="container">
<h2>Welcome to RemindU</h2>

	<form:form method="POST" action="/login">
	<fieldset>
	<label>Username:</label>
	<input name="userName" type="text" class="form-control" required="required"></input> <br>
	
	<label>Password:</label>
	<input name="password"type="password" class="form-control" required="required"></input> <br>
	</fieldset>
	<input type="submit" class="btn btn-info" value="Login"> 	<font color="red">${loginError}</font>
	
	</form:form>
	<br>

<label>Dont have an account? &nbsp </label><a  href="sign-up">Sign Up</a>
</div>
</body>




</html>