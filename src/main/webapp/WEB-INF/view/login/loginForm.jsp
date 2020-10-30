<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	<title>Login Form</title>
	</head>
<body>
	<h2>Login Form</h2>

	<form:form action="authenticate" method="post" modelAttribute="login">	
		<label>
			<spring:message code="email"/>:<form:input path="email"/><form:errors path="email"/>
		</label>
		<br/>
	
		<label>
			<spring:message code="password"/>:<form:input path="password"/><form:errors path="password"/>
		</label>

		<label>
			<spring:message code="rememberEmail"/>:<form:checkbox path="rememberEmail"/>
		</label>
		
		<br/>
		<form:errors path=""/>
		<br/>
		<input type="submit" value="Login"/>
	</form:form>		
</body>
</html>