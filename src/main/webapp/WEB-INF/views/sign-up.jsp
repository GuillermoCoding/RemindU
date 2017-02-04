<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html>

<html>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
                rel="stylesheet">
<head>
<title>RemindU Sign Up</title>
</head>
<body>
<div class="container">
<h2>Sign Up</h2>
	<form:form method="POST" modelAttribute="user">
	<fieldset class="form-group">
	<form:label path="name">First name :</form:label>
		<form:input path="name" type="text" class="form-control" required="required"></form:input>
		
	<form:label path="lastName">Last name :</form:label>
		<form:input path="lastName" type="text" class="form-control" required="required"></form:input>
		
	<form:label path="email">Email:</form:label>
		<form:input path="email" type="email" class="form-control" required="required"></form:input>
		
	<form:label path="userName">Username:</form:label>
		<form:input path="userName" type="text" class="form-control" required="required"></form:input>
		<form:errors path="userName" cssClass="text-warning"/>
		
	<form:label path="password">Password</form:label>
		<form:input path="password" type="password" class="form-control" required="required"></form:input>
		<form:errors path="password" cssClass="text-warning"/>
	</fieldset>
		<input type="submit" class="btn btn-info"value="create account"></>
		
	</form:form>
<font color="red">${emailError}</font><br> <font color="red">${userNameError}</font>
</div>
</body>




</html>