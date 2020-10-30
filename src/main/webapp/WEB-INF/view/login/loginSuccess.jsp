<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Authenticated Successfully</title>
	</head>

	<body>
		<spring:message code="authentication.done" arguments="${name}"/>
		
		<br/>
		<a href = "<c:url value="/main"/>">[Go to Main]</a>		
	</body>
</html>