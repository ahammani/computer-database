<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id" required="false" type="java.lang.Integer"
	description=""%>

<div class="flag" align="right" style="margin-right:30px">
	<c:choose>
		<c:when test="${empty id}">
			<a href="${uri}?lang=en"><img src="static/fonts/en.png"
				alt="English" style="width: 35px; height: 30px"></a>
			<a href="${uri}?lang=fr"><img src="static/fonts/fr.png"
				alt="French" style="width: 35px; height: 30px"></a>
		</c:when>
		<c:otherwise>
		<a href="${uri}?lang=en&id=${id}"><img src="static/fonts/en.png"
				alt="English" style="width: 35px; height: 30px"></a>
			<a href="${uri}?lang=fr&id=${id}"><img src="static/fonts/fr.png"
				alt="French" style="width: 35px; height: 30px"></a>
		</c:otherwise>
	</c:choose>
</div>

