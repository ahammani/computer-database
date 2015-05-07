<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="root" value="${req.getContextPath()}" />

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${root}/dashboard"> Application -
			Computer Database </a>
	</div>
</header>