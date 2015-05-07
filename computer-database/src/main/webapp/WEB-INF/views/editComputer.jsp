<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<c:import url="import/head.jsp" />
<body>
	<c:import url="import/header.jsp" />

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<form  id="addComputerForm" action="editComputer" method="POST">
						<input type="hidden" value="${computer.id}" name="id" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									placeholder="Computer name"  name="computerName" value="${computer.name}">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									placeholder="Introduced date" name="introduced" value="${computer.introduced}">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									placeholder="Discontinued date" name="discontinued"
									value="${computer.discontinued}">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="${computer.company_id}">${computer.company_name}</option>
									<option value="0">--</option>
									<c:forEach items="${companies}" var="company">
										<c:if test="${company.name != computer.company_name }">
											<option value="${company.id}">${company.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
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