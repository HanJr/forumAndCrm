<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/static/css/style.css'/>"/>
		<title>Article Viewer</title>
	</head>
	
	<body>
		<h1>${article.title}</h1>
		
		${article.name} | <spring:message code="viewCount"/> ${article.viewNum} | <spring:message code="createdDate"/> ${article.createdDate}
		
		<c:if test="${not empty authInfo && authInfo.id == article.memberId}">
			<script>var id = ${article.articleID}</script>
			<button id="deleteArticleButton">delete</button>
			<button onclick="editArticle('${currentPage}', '${currentBlock}')">edit</button>
		</c:if>
		<button onclick="backToList('${currentPage}', '${currentBlock}')">Back to list</button>
		
		<hr id="articleContentHr"/>
		
		<p>
			${article.articleContents}
		</p>
	</body>
	
	<script src="<c:url value='/resources/static/js/button.js'/>"></script>
</html>