
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<st:url value="/" var="homeUrl" scope="application"/>
<st:url value="/images/spittr-logo.png" var="spitterLogo" scope="application"/>
<a href="${homeUrl}">
	<img alt="Spitter Logo" src="${spitterLogo}">
</a>