<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="import/head.jsp" />
<body>

	<c:import url="import/header.jsp" />
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="title.addComputer"/></h1>
					<form:form modelAttribute="computerDTO"  action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<spring:message code="form.computerName" var="computerName"/>
								<label for="name">
									<spring:message code="form.computerName"/>
								</label>
								<form:input path="name" type="text" class="form-control" cssClass="form-control" id="name"
									name="name" placeholder="${computerName}"/>
                            	<form:errors path="name"  cssStyle="color: #ff0000;" cssClass="has-error" />
							</div>
							<div class="form-group">
								<spring:message code="form.introduced" var="introduced"/>
								<label for="introduced">
									<spring:message code="form.introduced"/>
								</label> 
								<form:input path="introduced"
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="${introduced}"/>
									<form:errors path="introduced"  cssStyle="color: #ff0000;" cssClass="has-error" />
							</div>
							<div class="form-group">
							<spring:message code="form.discontinued" var="discontinued"/>
								<label  for="discontinued">
									<spring:message code="form.discontinued"/>
								</label> 
								<form:input path="discontinued"
									type="date" class="form-control" id="discontinued"
									name="discontinued"
									placeholder="${discontinued}"/>
									<form:errors path="discontinued"  cssStyle="color: #ff0000;" cssClass="has-error" />
							</div>
							<div class="form-group">
								<spring:message code="form.companyName" var="companyName"/>
								<label for="companyId">
									<spring:message code="form.companyName"/>
								</label> 
								<select	class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value=<spring:message code="form.add"/> class="btn btn-primary">
							<spring:message code="form.or"/> <a href="dashboard" class="btn btn-default"><spring:message code="form.cancel"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	
	<script src="static/js/jquery.min.js"></script>
<!-- 	<script src="static/js/jquery.validate.min.js"></script> -->
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/dashboard.js"></script>
	<script src="static/js/addComputer.js"></script>
</body>
</html>