<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<c:import url="import/head.jsp" />
<body>
	<c:import url="import/header.jsp" />
	<spring:message code="title.language" /> : <a href="${uri}?lang=en&id=${computer.id}"><spring:message code="dashboard.english" /></a>|<a href="${uri}?lang=fr&id=${computer.id}"><spring:message code="dashboard.french" /></a>
	
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1><spring:message code="title.editComputer"/></h1>

					<form  id="addComputerForm" action="editComputer" method="POST">
						<input type="hidden" value="${computer.id}" name="id" />
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="form.computerName"/></label> 
								<input
									type="text" class="form-control" id="name"
									placeholder=<spring:message code="form.computerName"/>  name="name" value="${computer.name}"/>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="form.introduced"/></label>
								 <input
									type="date" class="form-control" id="introduced"
									placeholder=<spring:message code="form.introduced"/> name="introduced" value="${computer.introduced}"/>
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="form.discontinued"/></label> <input
									type="date" class="form-control" id="discontinued"
									placeholder=<spring:message code="form.discontinued"/> name="discontinued"
									value="${computer.discontinued}"/>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="form.companyName"/></label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="${computer.companyId}">${computer.companyName}</option>
									<option value="0">--</option>
									<c:forEach items="${companies}" var="company">
										<c:if test="${company.name != computer.companyName }">
											<option value="${company.id}">${company.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value=<spring:message code="form.edit"/> class="btn btn-primary">
							<spring:message code="form.or"/> <a href="dashboard" class="btn btn-default"><spring:message code="form.cancel"/></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/jquery.validate.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/dashboard.js"></script>
	<script src="static/js/addComputer.js"></script>
</body>
</html>