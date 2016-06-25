<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Single Spittle</title>
</head>
<body>
	<div class="spittleView">
		<div class="spittleMessage"><c:out value="${spittle.message}" /></div>
		<div>
			<span class="spittleTime"><c:out value="${spittle.time}" /></span>
		</div>
	</div>
</body>
</html>