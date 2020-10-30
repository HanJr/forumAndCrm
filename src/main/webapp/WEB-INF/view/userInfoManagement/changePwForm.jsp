<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Change Password</title>
	</head>

	<body>
		<form:form action="processPwChange" modelAttribute="changePwRequest">
			<label>
				<spring:message code="currentPw"/>
				<form:input path="currentPw"/>
				<form:errors path="currentPw"/>
			</label>
			<br/>
			<label>
				<spring:message code="newPw"/>
				<form:input path="newPw"/>
				<form:errors path="newPw"/>
			</label>
			<br/>
			<label>
				<spring:message code="confirmNewPw"/>
				<form:input path="confirmNewPw"/>
				<form:errors path="confirmNewPw"/>
			</label>	
			<br/>
			<br/>
			<form:errors/>
			
			<input type="submit" value="submit"/>
		</form:form>
	
	</body>
</html>