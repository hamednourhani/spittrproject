
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>


	<h1>Welcome to Spittr</h1>
	<a href='<c:url value="/spittles"/>'>Spittles</a>
	<span> | </span>
	<st:url value="/spitter/register" var="registerUrl" htmlEscape="true"/>
	<a href="${registerUrl}">Register Page</a>
	<div>
	
		</div>
