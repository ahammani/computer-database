<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id" required="false" type="java.lang.Integer"
	description=""%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="navbar-right">
	<c:choose>
		<c:when test="${empty id}">
			<a href="${uri}?lang=en"><img src="static/fonts/en.png"
				alt="English" style="width: 35px; height: 30px"></a>
			<a href="${uri}?lang=fr"><img src="static/fonts/fr.png"
				alt="Français" style="width: 35px; height: 30px"></a>
		</c:when>
		<c:otherwise>
			<a href="${uri}?lang=en&id=${id}"><img src="static/fonts/en.png"
				alt="English" style="width: 35px; height: 30px"></a>
			<a href="${uri}?lang=fr&id=${id}"><img src="static/fonts/fr.png"
				alt="Français" style="width: 35px; height: 30px"></a>
		</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${pageContext.request.userPrincipal.name != null}">
		<c:url var="logoutUrl" value="/logout" />
		<form action="${logoutUrl}" method="post">
			<input type="submit" class="btn btn-primary btn-block"
				value="<spring:message code="logout" />" />
		</form>
	</c:when>
	<c:otherwise>
	<c:url var="loginUrl" value="/customLogin" />
		<form action="${loginUrl}" method="get">
			<input type="submit" class="btn btn-primary "
				value="<spring:message code="login" />" />
		</form>
	</c:otherwise>
	</c:choose>
</div>

