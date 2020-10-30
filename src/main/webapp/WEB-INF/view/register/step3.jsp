<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>	
		<title>Register Complete</title>
	</head>

	<body>
		<p>Register Complete</p>
		<p><spring:message code="register.done" arguments="${registerRequest.name}"/><p>
		
		<p><a href="<c:url value='/main'/>">[Go Back to Main]</a></p>
	</body>
</html>