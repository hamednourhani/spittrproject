<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
href='<c:url value="/css/style.css"/>'>
</head>
<body>
	<h1>Register</h1>
	<sf:form method="POST" commandName="spitter">
		<sf:errors path="*" element="div" cssClass="errors"/>
		First Name: <sf:input path="firstName" cssErrorClass="error" /><br/>
		<sf:errors path="firstName"/><br/>
		Last Name: <sf:input path="lastName"  cssErrorClass="error"/><br/>
		<sf:errors path="lastName"/><br/>
		Email: <sf:input path="email"  cssErrorClass="error"/><br/>
		<sf:errors path="email"/><br/>
		Username: <sf:input path="username" cssErrorClass="error"/><br/>
		<sf:errors path="username"/><br/>
		Password: <sf:password path="password" cssErrorClass="error"/><br/>
		<sf:errors path="password" /><br/>
		<input type="submit" value="Register" />
	</sf:form>

</body>
</html>