
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

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
 