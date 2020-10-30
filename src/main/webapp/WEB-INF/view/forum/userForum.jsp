<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/static/css/style.css'/>" />
		<style>
			table, th, td {
				border: 1px solid black;
			}
		</style>
		<title>User Forum</title>
	</head>
	
	<body>
		
		<button onclick="moveToComposingPage('${pagination.currentPage}', '${pagination.currentBlock}')"><spring:message code="writeArticle"/></button>

		<c:if test="${not empty error}">
			<span class="error"><spring:message code="loginRequestMessage"/></span>
		</c:if>
		
		<table>
			<tr>
				<th><spring:message code="articleID"/></th>
				<th><spring:message code="title"/></th>
				<th><spring:message code="author"/></th>
				<th><spring:message code="viewCount"/></th>
				<th><spring:message code="createdDate"/></th>
			</tr>

			<c:forEach var="article" items="${articleList}">
				<tr>
					<td>${article.articleID}</td>
					<td><a href="<c:url value='/forum/viewArticle/${article.articleID}?currentPage=${pagination.currentPage}&currentBlock=${pagination.currentBlock}'/>">${article.title}</a></td>
					<td>${article.name}</td>
					<td>${article.viewNum}</td>
					<td><tags:formatDateTime value="${article.createdDate}"/></td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- Pagination -->
		<div>
			<ul class="pagination">
				<c:if test="${pagination.prev}">
					<li class="page-item prev">
						<a class="page-link" href="#" onClick="prev('${pagination.currentPage}', '${pagination.currentBlock}', '${pagination.displayingPagePerBlock}')">prev</a>
					</li>
				</c:if>
				
				<c:forEach begin="${pagination.startingPageNum}" end="${pagination.endPageNum}" var="index">
					<li class="page-item <c:out value="${pagination.currentPage == index ? 'active' : ''}"/> ">
						<a class="page-link" href="#" onClick="fn_pagination('${index}', '${pagination.currentBlock}')"> ${index} </a>
					</li>
				</c:forEach>
				
				<c:if test="${pagination.next}">
					<li class="page-item next">
						<a class="page-link" href="#" onClick="next('${pagination.currentPage}', '${pagination.currentBlock}', '${pagination.displayingPagePerBlock}')">next</a>
					</li>
				</c:if>
			</ul>
		</div>
		<!-- Pagination end -->
		
		<br/>
		<a href="<c:url value="/main"/>">Back to Main</a>
		
		<script type="text/javascript" src="<c:url value='/resources/static/js/button.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resources/static/js/pagination.js'/>"></script>
	</body>
</html>