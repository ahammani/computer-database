<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="maxPages" required="true" type="java.lang.Integer"
	description=""%>

<div class="container text-center">
	<ul class="pagination">

		<c:if test="${page > 1}">
			<li><mylib:link target="DashBoardServlet" page="1"
					limit="${limit}" aria_label="Previous"
					body="<span aria-hidden='true'>&laquo;</span>" /></li>
		</c:if>

		<c:choose>
			<c:when test="${page+3 > maxPages}">
				<c:forEach var="i" begin="${page}" end="${maxPages}">
					<li><mylib:link target="DashBoardServlet" page="${i}"
							limit="${limit}" body="${i}" /></li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="i" begin="${page}" end="${page+3}">
					<li><mylib:link target="DashBoardServlet" page="${i}"
							limit="${limit}" body="${i}" /></li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<c:if test="${page < maxPages}">
			<li><mylib:link target="DashBoardServlet" page="${page+1}"
					limit="${limit}" aria_label="Next"
					body="<span aria-hidden='true'>&raquo</span>" /></li>
		</c:if>

	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='DashBoardServlet?page=${page}&limit=10'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='DashBoardServlet?page=${page}&limit=50'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='DashBoardServlet?page=${page}&limit=100'">100</button>
	</div>
</div>