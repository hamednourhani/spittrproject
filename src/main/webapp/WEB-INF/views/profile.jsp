<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title><c:out value="${spitter.username}" /> Profile</title>
</head>
<body>
	<h1>Your Profile</h1>
	<c:out value="${spitter.username}" /><br/>
	<c:out value="${spitter.firstName}" />
	<c:out value="${spitter.lastName}" />
</body>
</html>