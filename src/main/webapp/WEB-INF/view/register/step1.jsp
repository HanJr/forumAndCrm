<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Terms of Service Agreement</title>
	</head>
	
	<body>
		<h1><spring:message code="term"/></h1>
		
		<p>
			By signing up, you agree to our Terms, Data Policy and Cookies Policy.
		</p>
		
		<form action="step2" method="post">
			<label>
				<input type="checkbox" name="agree" value="true"/> <spring:message code="term.agree"/>
			</label>
			
			<input type="submit" value="<spring:message code="next.btn"/>"/>
		</form>
	</body>
</html>