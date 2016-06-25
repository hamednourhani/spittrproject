
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

	<st:htmlEscape defaultHtmlEscape="true"></st:htmlEscape>
	<h1>Your Profile</h1>
	<c:out value="${spitter.username}" /><br/>
	<c:out value="${spitter.firstName}" />
	<c:out value="${spitter.lastName}" />
