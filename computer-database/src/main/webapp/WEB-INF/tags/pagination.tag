<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="maxPages" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="search" required="false" type="java.lang.String"
	description=""%>

<div class="container text-center">
	<ul class="pagination">

		<c:if test="${page > 1}">
			<li><mylib:link target="DashboardServlet" page="${page-1}"
					limit="${limit}" aria_label="Previous" search="${search}"
					body="<span aria-hidden='true'>&laquo;</span>" /></li>
		</c:if>

		<c:choose>
			<c:when test="${page+3 > maxPages}">
				<c:forEach var="i" begin="${page}" end="${maxPages}">
					<li><mylib:link target="DashboardServlet" page="${i}"
							limit="${limit}" body="${i}" search="${search}" /></li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="i" begin="${page}" end="${page+3}">
					<li><mylib:link target="DashboardServlet" page="${i}"
							limit="${limit}" body="${i}" search="${search}"/></li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<c:if test="${page < maxPages}">
			<li><mylib:link target="DashboardServlet" page="${page+1}"
					limit="${limit}" aria_label="Next"
					body="<span aria-hidden='true'>&raquo</span>" search="${search}"/></li>
		</c:if>

	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='DashboardServlet?page=${page}&limit=10&search=${search}'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='DashboardServlet?page=${page}&limit=50&search=${search}'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='DashboardServlet?page=${page}&limit=100&search=${search}'">100</button>
	</div>
</div>