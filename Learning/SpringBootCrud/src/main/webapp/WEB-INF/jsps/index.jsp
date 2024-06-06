<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hello</h1>

	<span>${msg}</span>
		<form:form action="reg" modelAttribute="user">
		<form:hidden path="id"/>
		<form:label path="name">Name</form:label>
		<form:input path="name"/>
		<form:label path="email">Email</form:label>
		<form:input path="email"/>
		<form:label path="phone">Phone</form:label>
		<form:input path="phone"/>
		<input type="submit">
		</form:form>
		<a href="display">view all users</a>

</body>
</html>