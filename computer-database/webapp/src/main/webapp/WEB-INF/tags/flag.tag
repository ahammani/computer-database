<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id" required="false" type="java.lang.Integer"
	description=""%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="navbar-right">
	<c:choose>
		<c:when test="${empty id}">
			<a href="${uri}?lang=en"><img src="static/img/en.png"
				alt="English" style="width: 35px; height: 30px"></a>
			<a href="${uri}?lang=fr"><img src="static/img/fr.png"
				alt="Français" style="width: 35px; height: 30px"></a>
		</c:when>
		<c:otherwise>
			<a href="${uri}?lang=en&id=${id}"><img src="static/img/en.png"
				alt="English" style="width: 35px; height: 30px"></a>
			<a href="${uri}?lang=fr&id=${id}"><img src="static/img/fr.png"
				alt="Français" style="width: 35px; height: 30px"></a>
		</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${pageContext.request.userPrincipal.name != null}">
		<c:url var="logoutUrl" value="/logout" />
		<form action="${logoutUrl}" method="post">
			<button type="submit" class="btn btn-danger btn-block">
			<span class="glyphicon glyphicon-log-out"> </span>
			<spring:message code="logout" />
			</button>
			
		</form>
	</c:when>
	<c:otherwise>
	<c:url var="loginUrl" value="/customLogin" />
		<form action="${loginUrl}" method="get">
			<button type="submit" class ="btn btn-primary" >
				<span class="glyphicon glyphicon-user"></span>
				<spring:message code="login" />
			</button>
		</form>
	</c:otherwise>
	</c:choose>
</div>

