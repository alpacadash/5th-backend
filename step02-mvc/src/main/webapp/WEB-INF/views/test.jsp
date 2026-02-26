<%@page import="ch.qos.logback.core.net.SyslogOutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test JSP</title>
</head>
<body>
	<!-- HTML -->
	<%-- JSP주석 --%>
	<%! String id = "cloud"; %>
	<%= id %>
	<% String grade = "junior";
		System.out.println(grade);
	%>
	
	${2 > 3}
	<div>
		<h2>${student.sid}</h2>
		<p>${student.sname}</p>
		<p>${student.grade}</p>
	</div>
	
</body>
</html>