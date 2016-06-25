<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256" session="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Spittr</title>
<st:url value="/css/style.css" var="mainStyleUrl" />
<link rel="stylesheet" type="text/css" href="${mainStyleUrl}" />
</head>
<body>
	
	<div class="header">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div class="content">
		<tiles:insertAttribute name="body"/>
	</div>
	
	<div class="footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>