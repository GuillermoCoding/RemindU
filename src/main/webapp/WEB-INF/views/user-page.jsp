<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>       
<!DOCTYPE html>

<html>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
                rel="stylesheet">
<head>
<title>RemindU</title>
</head>
<body>
<div class="container">
<h2 id ="heading">Welcome ${user.name}</h2>

	<table class="table table-bordered">
	<caption>Reminders</caption>
	<thead>
		<tr>
			<th>Description</th>
			<th>Target Date</th>
			<th>Is Completed?</th>
			<th>Delete?</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${user.reminders}" var="reminder">

			<tr>
				<td> ${reminder.desc}</td>
				<td> ${reminder.targetDate}</td>
				<td> ${reminder.isDone}</td>
				<td> <a class="btn btn-danger" href="delete-reminder?id=${reminder.id}">Delete</a></td>

			</tr>
			
		</c:forEach>
		
	</tbody>	

	</table>
	<a class="btn btn-info " href="/">Go back</a>
	<a class="btn btn-info pull-right" href="add-reminder">Add Reminder</a>
	

</div>

</body>




</html>