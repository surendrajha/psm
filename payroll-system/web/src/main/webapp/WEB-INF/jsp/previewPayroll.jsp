<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<h1>Preview Payroll</h1>
	<c:url value="/payroll/approve" var="actionUrl" />
	<form:form action="${actionUrl}" modelAttribute="payrollPreviewForm">

		<button type="button">Print Payroll Preview</button>&nbsp;<button type="button">View
			Detailed Report</button>&nbsp;<button type="button">Add/Edit Detailed Report</button>
		<form:hidden path="payrollId" />
		<form:hidden path="payrollFrequencyId" />
		<br />
		<br />

		<table border="1">
			<tr>
				<th>S.No.</th>
				<th>Employee</th>
				<th>Gross Amount</th>
				<th>Employee Tax</th>
				<th>Employee Deduction</th>
				<th>Net DIrect Deposit Amount</th>
				<th>Net Check Amount</th>
				<th>Employer Tax</th>
				<th>Employer Deduction</th>
			</tr>
			<c:forEach items="${payrollPreviewForm.employeePayrollModels}" var="empPayroll"
				varStatus="varStatus">
				<c:if test="${empPayroll.employeeType eq 'EMPLOYEE'}">
					<tr>
						<td>${varStatus.count}</td>
						<td>${empPayroll.employeeName}</td>
						<td>${empPayroll.grossAmount}</td>
						<td>${empPayroll.employeeTax}</td>
						<td>${empPayroll.employeeDeduction}</td>
						<td>${empPayroll.netDirectDepositAmount}</td>
						<td>${empPayroll.netCheckAmount}</td>
						<td>${empPayroll.employerTax}</td>
						<td>${empPayroll.employerDeduction}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<br />
		<br />

		<table border="1">
			<tr>
				<th>S.No.</th>
				<th>Employee</th>
				<th>Gross Amount</th>
				<th>Contractor Deduction</th>
				<th>Net DIrect Deposit Amount</th>
				<th>Net Check Amount</th>
				<th>Employer Tax</th>
				<th>Employer Deduction</th>
			</tr>
			<c:forEach items="${payrollPreviewForm.employeePayrollModels}" var="empPayroll"
				varStatus="varStatus">
				<c:if test="${empPayroll.employeeType eq 'CONTRACTOR'}">
					<tr>
						<td>${varStatus.count}</td>
						<td>${empPayroll.employeeName}</td>
						<td>${empPayroll.grossAmount}</td>
						<td>${empPayroll.employeeDeduction}</td>
						<td>${empPayroll.netDirectDepositAmount}</td>
						<td>${empPayroll.netCheckAmount}</td>
						<td>${empPayroll.employerTax}</td>
						<td>${empPayroll.employerDeduction}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>

		<table border="1">

			<tr>
				<td>Direct Deposit Total</td>
				<td>${payrollPreviewForm.directDepositTotal}</td>
			</tr>
			<tr>
				<td>Employee Tax Total</td>
				<td>${payrollPreviewForm.employeeTaxTotal}</td>
			</tr>
			<tr>
				<td>Employer Tax Total</td>
				<td>${payrollPreviewForm.employerTaxTotal}</td>
			</tr>
			<tr>
				<td>VJS Processing Fees</td>
				<td>${payrollPreviewForm.vjsProcessingFees}</td>
			</tr>
			<tr>
				<td>Total Amount to be Transferred Electronically</td>
				<td>${payrollPreviewForm.totalAmountTransferElectronically}</td>
			</tr>
			<tr>
				<td>Check Amount Total</td>
				<td>${payrollPreviewForm.checkAmountTotal}</td>
			</tr>
			<tr>
				<td>Employee/Contractor Deduction Total</td>
				<td>${payrollPreviewForm.employeeContractorDeductionTotal}</td>
			</tr>
			<tr>
				<td>Employer Deduction Total</td>
				<td>${payrollPreviewForm.employerDeductionTotal}</td>
			</tr>
			<tr>
				<td>Grand Total</td>
				<td>${payrollPreviewForm.grandTotal}</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>


		<br />
		<br />
		<div align="right">
			<button type="button" onclick="">Preview</button>
			<button type="button"
				onclick="submitThis('${pageContext.request.contextPath}/payroll/saveAndProcessLater')">Save&amp;Process&nbsp;Later</button>
		</div>
	</form:form>
</body>
</html>