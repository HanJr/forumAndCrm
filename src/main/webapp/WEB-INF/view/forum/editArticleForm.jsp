<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/static/css/style.css'/>" />
		<title>Edit Article</title>
	</head>
	
	
	<body>
		<h2><spring:message code="editArticlePageTitle"/></h2>
		
		<form:form action="editArticle" modelAttribute="article">
			<form:hidden path="articleID"/>
			<form:hidden path="viewNum"/>
		
			<label for="articleTitle"><spring:message code="title"/></label><br/>
			<form:input path="title" id="articleTitle" class="typeText"/>
			<form:errors path="title"/>
			<br/><br/>
			
			<label for="articleAuthor"><spring:message code="name"/></label><br/>
			<form:input path="name" readonly="true" id="articleAuthor" class="typeText" />
			<br/><br/>
			
			<label for="articleBody"><spring:message code="body"/></label><br/>
			<form:textarea path="articleContents" id="articleBody" rows="15"/>
			<form:errors path="articleContents"/>
			<br/>
			
			<input type="submit" value="<spring:message code='article.register'/>">
		</form:form>
	</body>
</html>