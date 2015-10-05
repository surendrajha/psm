<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h1>Manage System Data --> Federal Tax --> Medicare & Social Security</h1>
	<c:url value="/adminConsole/federalTax/addUpdateMedicareSocialSecurity" var="actionUrl" />

	<form:form action="${actionUrl}" method="post" modelAttribute="medicareSocialSecurityForm">

		<table width="50%" border="1">
			<tr>
				<th>S. No.</th>
				<th>Tax Name</th>
				<th>Employee Contribution</th>
				<th>Employer Contribution</th>
			</tr>
			<c:forEach items="${medicareSocialSecurityForm.medicareSocialSecurityModels}" varStatus="row"
				var="medicareSocialSecurityModel">
				<tr>
					<td>${row.count}</td>
					<td><form:hidden path="medicareSocialSecurityModels[${row.index}].taxTypeId" /> <form:hidden
							path="medicareSocialSecurityModels[${row.index}].federalStateTaxRateId" /> <form:hidden
							path="medicareSocialSecurityModels[${row.index}].taxName" />
						${medicareSocialSecurityModel.taxName}</td>
					<td><form:input path="medicareSocialSecurityModels[${row.index}].employeeContribution" /></td>
					<td><form:input path="medicareSocialSecurityModels[${row.index}].companyContribution" /></td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="2"><button>Save</button></th>
		</table>

	</form:form>
</body>
</html>