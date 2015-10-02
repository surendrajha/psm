<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function getPayrollData() {
		document.getElementById("payRollForm").submit();
	}

	function deleteThis(id, url) {
		if (confirm("Are you sure want to delete?") == true) {
			document.getElementById("payRollForm").action = url;
			document.getElementById("payRollForm").payrollId = id;
			document.getElementById("payRollForm").submit();
		}
	}

	function submitThis(url) {
		document.getElementById("payRollForm").action = url;
		document.getElementById("payRollForm").submit();
	}

	function overrideTaxDeduction(url) {
		alert(url);
		window.location.href = url;
	}
	function preview(url) {
		document.getElementById("payRollForm").action = url;
		document.getElementById("payRollForm").submit();
	}
</script>
</head>
<body>
	<h1>Normal Payroll</h1>
	<c:url value="/payroll/showPayrollData" var="actionUrl" />
	<form:form action="${actionUrl}" modelAttribute="payRollForm">
	Select Pay Frequency for This payroll : <form:select path="payrollFrequencyId" items="${pfList}"
			itemValue="id" itemLabel="payFrequencyType.type"></form:select>
		<button type="button" onclick="getPayrollData()">GO</button>
		<form:hidden path="noOfEarning" />
		<form:hidden path="payrollId" />
		<form:hidden path="noOfEmployee" />
		<br />
		<br />
		Start Date: ${payRollForm.startDate}
		End Date: ${payRollForm.endDate}
		Check Date: ${payRollForm.checkDate}
		<button type="button"
			onclick="deleteThis('${payRollForm.payrollId}','${pageContext.request.contextPath}/payroll/delete')">Delete
			This Payroll</button>
		<br />
		<br />
		Enter Payroll for Employee
		<table border="1">
			<tr>
				<th>S.No.</th>
				<th>Employee</th>
				<th>Disable Direct Deposit</th>
				<th>Salary</th>
				<th>Hourly Rate</th>
				<th>Regular Hours</th>
				<c:forEach items="${companyEarningList}" var="compEarng">
					<th>${compEarng.displayName}</th>
				</c:forEach>
			</tr>
			<c:forEach items="${payRollForm.payrollEmployees}" var="emp" varStatus="varStatus">
				<c:if test="${emp.employeeType eq 'EMPLOYEE'}">
					<tr>
						<form:hidden path="payrollEmployees[${varStatus.index}].employeeId" />
						<form:hidden path="payrollEmployees[${varStatus.index}].employeePayrollId" />
						<td><a href="#"
							onclick="overrideTaxDeduction('${pageContext.request.contextPath}/payroll/getTaxesAndDeductions/${emp.employeePayrollId}')">${varStatus.count}</a></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].employeeName" readonly="true" /></td>
						<td><form:checkbox path="payrollEmployees[${varStatus.index}].directDepositStatus" /></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].salary" /></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].hourlyRate" /></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].regularHours" /></td>
						<c:forEach begin="0" end="${payRollForm.noOfEarning-1}" step="1" var="index">
							<form:hidden
								path="payrollEmployees[${varStatus.index}].payrollEmployeeEarnings[${index}].employeePayrollEarningId" />
							<form:hidden path="payrollEmployees[${varStatus.index}].payrollEmployeeEarnings[${index}].id" />
							<td><form:input
									path="payrollEmployees[${varStatus.index}].payrollEmployeeEarnings[${index}].value" /></td>
							<form:hidden
								path="payrollEmployees[${varStatus.index}].payrollEmployeeEarnings[${index}].earningValueType" />
						</c:forEach>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<br />
		<br />
		Enter Payroll for Contractors
		<table border="1">
			<tr>
				<th>S.No.</th>
				<th>Contractor</th>
				<th>Disable Direct Deposit</th>
				<th>Fixed Pay</th>
				<th>Hourly Rate</th>
				<th>Total Hours</th>
			</tr>
			<c:forEach items="${payRollForm.payrollEmployees}" var="emp" varStatus="varStatus">
				<c:if test="${emp.employeeType eq 'CONTRACTOR'}">
					<tr>
						<form:hidden path="payrollEmployees[${varStatus.index}].employeeId" />
						<form:hidden path="payrollEmployees[${varStatus.index}].employeePayrollId" />
						<td><a href="#"
							onclick="overrideTaxDeduction('${pageContext.request.contextPath}/payroll/getTaxesAndDeductions/${emp.employeePayrollId}')">${varStatus.count}</a></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].employeeName" readonly="true" /></td>
						<td><form:checkbox path="payrollEmployees[${varStatus.index}].directDepositStatus" /></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].salary" /></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].hourlyRate" /></td>
						<td><form:input path="payrollEmployees[${varStatus.index}].regularHours" /></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<br />
		<br />
		<div align="right">
			<button type="button" onclick="preview('${pageContext.request.contextPath}/payroll/getPreview')">Preview</button>
			<button type="button"
				onclick="submitThis('${pageContext.request.contextPath}/payroll/saveAndProcessLater')">Save&amp;Process&nbsp;Later</button>
		</div>
	</form:form>
</body>
</html>