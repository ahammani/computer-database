<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="root" value="${req.getContextPath()}" />
<c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="test" value="${req.getQueryString() }"/>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${root}/dashboard"> Application -
			Computer Database </a>
			<spring:message code="title.language" /> : <a href="${uri}?lang=en&${test}"><spring:message code="dashboard.english" /></a>|<a href="${uri}?lang=fr&${test}"><spring:message code="dashboard.french" /></a>
	
	</div>
</header>