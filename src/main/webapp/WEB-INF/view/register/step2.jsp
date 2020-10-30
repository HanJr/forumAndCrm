<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Register Form</title>
	</head>
	
	<body>
		<h2><spring:message code="register"/></h2>
		
		<form:form action="step3" modelAttribute="registerRequest">
			
			<label>
				<spring:message code="email"/>:
				<form:input path="email"/>
				<form:errors path="email"/>
			</label>
			<br/>
			<label>
				<spring:message code="name"/>:
				<form:input path="name"/>
				<form:errors path="name"/>
			</label>
			<br/>
			<label>
				<spring:message code="password"/>:
				<form:password path="password"/>
				<form:errors path="password"/>
			</label>
			<br/>
			<label>
				<spring:message code="password.confirm"/>:
				<form:password path="confirmPassword"/>
				<form:errors path="confirmPassword"/>
			</label>			
			<br/>
			<input type="submit" value="<spring:message code="submit"/>">
		</form:form>
	</body>
</html>