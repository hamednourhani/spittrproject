<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Spittr Home</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/style.css"/>'>
</head>
<body>
	<h1>Welcome to Spittr</h1>
	<a href='<c:url value="/spittles"/>'>Spittles</a>
	<span> | </span>
	<a href='<c:url value="/spitter/register"/>'>Register</a>
</body>
</html>