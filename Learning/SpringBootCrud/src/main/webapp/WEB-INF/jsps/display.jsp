<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		
		<table>
		<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Email</th>
		<th>Phone</th>
		</tr>
		
		<c:forEach var="dt" items="${data}">
		
		<tr>
		<td>${dt.getId()}</td>
		<td>${dt.getName() }</td>
		<td>${dt.getEmail()}</td>
		<td>${dt.getPhone()}</td>
		<td><a href="delete?did=${dt.getId()}">delete</a></td>
		<td><a href="edit?eid=${dt.getId()}">edit</a></td>
		</tr>
		</c:forEach>
		
		</table>
</body>
</html>