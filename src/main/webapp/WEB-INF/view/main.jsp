<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Main</title>
	</head>

	<body>
		<h2>Welcome to Han's Spring Practice Website</h2>
			
		<c:if test="${empty authInfo}">
			<a href="<c:url value='/register/step1'/>">Create a New Account!</a><br/>
			<a href="<c:url value='/login/loginForm'/>">Login</a>
		</c:if>

		<c:if test="${!empty authInfo}">
			<a href="<c:url value='/forum/userForum'/>">User Forum</a><br/>
			<a href="<c:url value='/changePwForm'/>">Change Password</a><br/>
			<a href="<c:url value='/logout'/>">Log Out</a>
		</c:if>
				
	</body>
</html>