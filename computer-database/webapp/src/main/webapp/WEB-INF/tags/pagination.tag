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
<%@ attribute name="field_order" required="false"
	type="java.lang.String" description=""%>
<%@ attribute name="order" required="false" type="java.lang.String"
	description=""%>

<div class="container text-center">
	<ul class="pagination">
		<c:if test="${page > 1}">
			<li><mylib:link target="dashboard" page="${page-1}"
					limit="${limit}" aria_label="Previous" search="${search}"
					field_order="${field_order}" order="${order}"
					body="<span aria-hidden='true'>&laquo;</span>" /></li>
			<li><mylib:link target="dashboard" page="${page-1}"
					limit="${limit}" search="${search}" field_order="${field_order}"
					order="${order}" body="${page-1}" /></li>
		</c:if>

		<c:choose>
			<c:when test="${page>maxPages}">
				<li><mylib:link target="dashboard" page="1" limit="${limit}"
						body="1" search="${search}" field_order="${field_order}"
						order="${order}" /></li>
			</c:when>
			<c:when test="${page+3 > maxPages}">
				<c:forEach var="i" begin="${page}" end="${maxPages}">
					<li><mylib:link target="dashboard" page="${i}"
							limit="${limit}" body="${i}" search="${search}"
							field_order="${field_order}" order="${order}" /></li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="i" begin="${page}" end="${page+3}">
					<li><mylib:link target="dashboard" page="${i}"
							limit="${limit}" body="${i}" search="${search}"
							field_order="${field_order}" order="${order}" /></li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<c:if test="${page < maxPages}">
			<li><mylib:link target="dashboard" page="${page+1}"
					limit="${limit}" aria_label="Next"
					body="<span aria-hidden='true'>&raquo</span>" search="${search}"
					field_order="${field_order}" order="${order}" /></li>
		</c:if>

	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">

		<c:choose>
			<c:when test="${page>maxPages}">
				<button type="button" class="btn btn-default"
					onclick="document.location.href='dashboard?page=1&limit=10&search=${search}&field_order=${field_order}&order=${order}'">10</button>
				<button type="button" class="btn btn-default"
					onclick="document.location.href='dashboard?page=1&limit=50&search=${search}&field_order=${field_order}&order=${order}'">50</button>
				<button type="button" class="btn btn-default"
					onclick="document.location.href='dashboard?page=1&limit=100&search=${search}&field_order=${field_order}&order=${order}'">100</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-default"
					onclick="document.location.href='dashboard?page=${page}&limit=10&search=${search}&field_order=${field_order}&order=${order}'">10</button>
				<button type="button" class="btn btn-default"
					onclick="document.location.href='dashboard?page=${page}&limit=50&search=${search}&field_order=${field_order}&order=${order}'">50</button>
				<button type="button" class="btn btn-default"
					onclick="document.location.href='dashboard?page=${page}&limit=100&search=${search}&field_order=${field_order}&order=${order}'">100</button>
			</c:otherwise>
		</c:choose>
	</div>
</div>