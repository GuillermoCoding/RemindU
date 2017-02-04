<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html>

<html>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
                rel="stylesheet">
<head>
<title>RemindU</title>
</head>
<body>
<div class="container">
<h2>Add a Reminder</h2>
	<form:form method="POST" modelAttribute="reminder">
	<fieldset class="form-group">
	<form:label path="desc">Description:</form:label>
	<form:input path="desc" type="text" class="form-control" required="required"></form:input><br>
	<form:input path="targetDate" type="date"></form:input> <br>
	</fieldset>
		<a class="btn btn-info" href="user-page">Go back</a>
		<input type="submit" class="btn btn-info pull-right"value="Add Reminder"></>
		
	</form:form>
	
</div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>


</body>




</html>