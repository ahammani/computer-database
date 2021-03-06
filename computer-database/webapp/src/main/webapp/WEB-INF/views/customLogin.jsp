<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!Doctype HTML>
<html>
<jsp:include page="/WEB-INF/views/import/head.jsp"></jsp:include>
<link href="static/css/signin.css" rel="stylesheet" media="screen">
<body>
	<jsp:include page="/WEB-INF/views/import/header.jsp"></jsp:include>
	<div id="login-box">
		<form class="form-signin" name="f"
			action="/webapp/j_spring_security_check" method="POST">
			<spring:message code="password" var="password" />
			<spring:message code="username" var="username" />
			<h2 class="form-signin-heading">
				<spring:message code="login" />
			</h2>

			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-user"></span>
					 <label for="inputEmail" class="sr-only">${username}</label>
				</span> 
				<input type="text" name="username" id="username"
					class="form-control" placeholder="${username}" required autofocus>
			</div>

			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-lock"></span>
					<label for="inputPassword" class="sr-only">${password}</label>
				</span>
				<input type="password" name="password" id="password" class="form-control"
					placeholder="${password}" required>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			</div>
				
				
			<c:if test="${auth == false}">
				<div>
					<font color="red"><spring:message code="error.auth.fail" /></font>
				</div>
			</c:if>
			<button id="login" class="btn btn-lg btn-primary btn-block"
				type="submit">
				<spring:message code="login" />
			</button>
		</form>
	</div>
	<script src="/webapp/js/jquery.min.js"></script>
	<script src="/webapp/js/bootstrap.min.js"></script>
</body>
</html>