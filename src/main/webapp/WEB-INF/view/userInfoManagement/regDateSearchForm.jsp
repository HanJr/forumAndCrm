<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>User Search By Registered Date</title>
	</head>

	<body>
	
		<h2><spring:message code="regDateSearchFormTitle"/></h2>
		
		<spring:message code="dateFormatInstruction"/><br/><br/>
		
		<form:form action="processSearch" modelAttribute="searchCommand">
			
			<label>	
				<form:input path="from"/><spring:message code="from"/>
				<form:errors path="from"/>
			</label>
				
			<label>
				<form:input path="to"/><spring:message code="to"/>
				<form:errors path="to"/>
			</label>
				
			<input type="submit" value="search"/>
		</form:form>
		
		<c:if test="${!empty members}">
			<table>
				<tr>
					<th><spring:message code="search.id"/></th>
					<th><spring:message code="search.name"/></th>
					<th><spring:message code="search.email"/></th>
					<th><spring:message code="search.regDate"/></th>
				</tr>

			<c:forEach var="member" items="${members}">
				<tr>
					<td><a href="<c:url value="${request.getContextPath()}/detailInfo/${member.id}"/>">${member.id}</a></td>
					<td>${member.name}</td>
					<td>${member.email}</td>
					<td><tf:formatDateTime value="${member.registerDateTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
			
			</table>
		</c:if>
	</body>
</html>