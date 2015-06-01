<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:import url="import/head.jsp" />
<body>
	<c:import url="import/header.jsp" />
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${pages.maxComputers}
				<spring:message code="dashboard.computersFound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">
						<input type="hidden" name="field_order"
							value='${pages.field_order}' />
						<input type="hidden" name="limit" value="${pages.limit}" />
						<input type="hidden" name="order" value="${pages.order}" />
						<input type="search" id="searchbox" name="search" class="form-control"
							placeholder=<spring:message code="form.searchName"/> />
						<input type="submit" id="searchsubmit" value=<spring:message code="form.filterByName"/>
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right align-right">
					<sec:authorize access="hasRole('ROLE_USER')">
						<a class="btn btn-success" id="addComputer" href="addComputer">
							<spring:message	code="button.addComputer" />
						</a>
					</sec:authorize>

					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<a class="btn btn-default " id="editComputer" href="#"
							onclick="$.fn.toggleEditMode();">
							<spring:message	code="button.deleteComputer" />
						</a>
					</sec:authorize>
				</div>
			</div>
		</div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		
			<form id="deleteForm" action="deleteComputer" method="POST">
				<input type="hidden" name="selection" value="">
			</form>
		</sec:authorize>
		
		<div class="container" style="margin-top: 10px;">
			<div class="label label-default pull-right">page:${pages.page}</div>
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="form.computerName" /> 
						<mylib:link	body="<span class='glyphicon glyphicon-chevron-up'></span>" order="asc" field_order="computer.name"
								limit="${pages.limit}" page="${pages.page}" target="dashboard"></mylib:link>
						<mylib:link body="<span class='glyphicon glyphicon-chevron-down'></span>" order="desc" field_order="computer.name"
								limit="${pages.limit}" page="${pages.page}" target="dashboard"></mylib:link>
						</th>
						<th><spring:message code="form.introduced" /> <mylib:link
								body="<span class='glyphicon glyphicon-chevron-up'></span>" order="asc" field_order="computer.introduced"
								limit="${pages.limit}" page="${pages.page}" target="dashboard"></mylib:link>
							<mylib:link body="<span class='glyphicon glyphicon-chevron-down'></span>" order="desc"
								field_order="computer.introduced" limit="${pages.limit}"
								page="${pages.page}" target="dashboard"></mylib:link></th>

						<!-- Table header for Discontinued Date -->
						<th><spring:message code="form.discontinued" /> <mylib:link
								body="<span class='glyphicon glyphicon-chevron-up'></span>" order="asc" field_order="computer.discontinued"
								limit="${pages.limit}" page="${pages.page}" target="dashboard"></mylib:link>
							<mylib:link body="<span class='glyphicon glyphicon-chevron-down'></span>" order="desc"
								field_order="computer.discontinued" limit="${pages.limit}"
								page="${pages.page}" target="dashboard"></mylib:link></th>
						<!-- Table header for Company -->
						<th><spring:message code="form.companyName" /> <mylib:link
								body="<span class='glyphicon glyphicon-chevron-up'></span>" order="asc" field_order="name"
								limit="${pages.limit}" page="${pages.page}" target="dashboard"></mylib:link>
							<mylib:link body="<span class='glyphicon glyphicon-chevron-down'></span>" order="desc" field_order="name"
								limit="${pages.limit}" page="${pages.page}" target="dashboard"></mylib:link>
						</th>

					</tr>
				</thead>

				<!-- Browse attribute computers -->
				<c:forEach items="${pages.computers}" var="computer">
					<tbody id="results">
						<tr>
						<sec:authorize var="loggedIn" access="isAuthenticated()" />
						<sec:authorize var="anonymous" access="isAnonymous()" />
						<sec:authorize var="admin" access="hasRole('ROLE_ADMIN')" />
						<c:choose>
						<c:when test="${admin}">
							<td class="editMode">
								<input type="checkbox" name="cb" class="cb" value="${computer.id}">
							</td>
							
							<td><a href="editComputer?id=${computer.id}" onclick="">
									<c:out value="${computer.name}" />
							</a></td>
						</c:when>
						<c:otherwise>
						<td class="editMode"></td>
							<td>
								<c:out value="${computer.name}" />
							</td>
						
						</c:otherwise>
						</c:choose>
								<td><c:out value="${computer.introduced}" /></td>
								<td><c:out value="${computer.discontinued}" /></td>
								<td><c:out value="${computer.companyName}" /></td>
					</tbody>
				</c:forEach>


			</table>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<mylib:pagination page="${pages.page}" limit="${pages.limit}"
			maxPages="${pages.maxPages}" search="${pages.search}"
			field_order="${pages.field_order}" order="${pages.order}" />
	</footer>

	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootbox.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var button_view = "<spring:message code='button.cancel'/>";
		var button_edit = "<spring:message code='button.deleteComputer'/>";
		var alert_message = "<spring:message code='delete.message'/>";
		var lang= "<spring:message code='lang'/>";
	</script>
	<script src="static/js/dashboard.js"></script>

</body>
</html>