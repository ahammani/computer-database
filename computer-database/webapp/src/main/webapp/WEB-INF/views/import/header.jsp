<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="root" value="${req.getContextPath()}" />
<c:set var="uri"
	value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="test2" value="${req.getQueryString() }" />
<c:set var="test" value="${req.setAttribute('lang', '')}" />

<c:url value="${uri}" var="myURL">
	<c:param name="lang" value="" />
</c:url>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${root}/dashboard"> Application -
			Computer Database </a>
		<mylib:flag id="${computer.id}" />


		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<c:url var="logoutUrl" value="/logout" />
			<form action="${logoutUrl}" method="post">
				<input type="submit" value="Log out" /> 
<%-- 				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
			</form>

		</c:if>

	</div>
</header>