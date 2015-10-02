<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<h1>Employee Override</h1>
	<c:url value="/payroll/saveOrUpdateTaxesAndDeductions" var="actionUrl" />
	<form:form action="${actionUrl}" modelAttribute="payrollDeductionForm">
	Employee Name : ${payrollDeductionForm.employeeName}
		<form:hidden path="employeePayrollId" />
		<br />
		<br />
		<table border="1">
			<tr>
				<th>Jurisdiction</th>
				<th>Override Options</th>
				<th>Amount</th>
			</tr>
			<c:forEach items="${payrollDeductionForm.employeeTaxes}" var="empTax" varStatus="varStatus">
				<tr>
					<form:hidden path="employeeTaxes[${varStatus.index}].employeePayrollTaxId" />
					<form:hidden path="employeeTaxes[${varStatus.index}].employeeTaxId" />
					<td>${empTax.taxName}</td>
					<td><form:select path="employeeTaxes[${varStatus.index}].taxOverrideType"
							items="${taxOverrideOptions}" itemLabel="name"></form:select>
					<td><form:input path="employeeTaxes[${varStatus.index}].amount" /></td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<br />
		<table border="1">
			<tr>
				<th>Deduction</th>
				<th>Type</th>
				<th>Amount</th>
			</tr>
			<c:forEach items="${payrollDeductionForm.employeeDeductions}" var="empDedc" varStatus="varStatus">
				<tr>
					<form:hidden path="employeeDeductions[${varStatus.index}].employeePayrollDeductionId" />
					<form:hidden path="employeeDeductions[${varStatus.index}].employeeDeductionId" />
					<td>${empDedc.deductionName}</td>
					<td><form:select path="employeeDeductions[${varStatus.index}].deductionOverrideType"
							items="${deductionTypes}" itemLabel="name"></form:select></td>
					<td><form:input path="employeeDeductions[${varStatus.index}].amount" /></td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<br />
		<button type="submit">Save</button>
	</form:form>
</body>
</html>