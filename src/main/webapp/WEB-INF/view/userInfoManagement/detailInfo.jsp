<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>User Detailed Information</title>
	</head>
	<body>
	
		<h2>User Detailed Information</h2>
	
		<spring:message code="id"/>: ${member.id}
		<br/><br/>
		<spring:message code="email"/>: ${member.email} 
		<br/><br/>
		<spring:message code="name"/>: ${member.name}
		<br/><br/>
		<spring:message code="registeredDate"/>: <tf:formatDateTime value="${member.registerDateTime}" />
	
	</body>
</html>