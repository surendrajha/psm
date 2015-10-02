<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.error {
	color: #D8000C;
	background-color: #FFBABA;
	/*background-image: url('error.png');*/
}
</style>
<%-- <script src='<c:url value="/js/jquery-1.9.0.js" />' ></script> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
<script>
	$(document).ready(function($) {
		$('#addMore').click(function() {
			var $tr = $('#empBankTb #empBankTr:last').clone();
			$tr.find("input,select").attr("name", function() {
				var parts = this.name.match(/(\D+)(\[)(\d+)(\])(\.)(\D+)$/);
				//alert("parts::" + parts[3]);
				return parts[1] + parts[2] + ++parts[3] + parts[4] + parts[5] + parts[6];
			}).attr("id", function() {
				var parts = this.id.match(/(\D+)(\d+)(\.)(\D+)$/);
				//alert("parts:"+parts);
				return parts[1] + ++parts[2] + parts[3] + parts[4];
			});
			var sno = parseInt($('#empBankTb #empBankTr:last #sno').text()) + 1;
			// append the new row to the table
			$('#empBankTb #empBankTr:last').after($tr);
			$('#empBankTb #empBankTr:last input').val("");
			$('#empBankTb #empBankTr:last #sno').html(sno);
			$('#empBankTb #empBankTr:last #deleteRow').remove();
		});
	});

	function deleteThis(actionUrl, formId, id, idValue) {
		var ans = confirm('Are you sure you want to delete?');
		if (ans) {
			document.getElementById(id).value = idValue;
			document.getElementById(formId).action = actionUrl;
			document.getElementById(formId).submit();
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<h2>Bank Info</h2>
	<c:url value="/employee/bank/addInfo" var="actionUrl" />
	<form:form action="${actionUrl}" method="post" modelAttribute="bankInfoForm">
		<form:errors path="*" cssClass="error" />
		<form:hidden path="employeeId" />
		<form:hidden path="id" />
		<table border="1" id="empBankTb">
			<tr>
				<th>S.No.</th>
				<th>Account Type</th>
				<th>Routing No.</th>
				<th>Account No.</th>
				<!-- 				<th>Confirm Account No.</th> -->
				<th>Bank</th>
				<th>Deposit Allocation</th>
				<th>Deposit Value</th>
				<th></th>
			</tr>
			<c:if test="${not empty bankInfoForm.employeeBankInfos}">
				<c:forEach items="${bankInfoForm.employeeBankInfos}" var="bankInfo" varStatus="status">
					<tr id="empBankTr">
						<td id="sno">${status.count}</td>
						<form:hidden path="employeeBankInfos[${status.index}].id" />
						<td><form:select path="employeeBankInfos[${status.index}].accountType"
								items="${accountTypeList}" itemLabel="name" /></td>
						<td><form:input path="employeeBankInfos[${status.index}].routingNumber" /><br /> <form:errors
								path="employeeBankInfos[${status.index}].routingNumber" cssClass="error" /></td>
						<td><form:input path="employeeBankInfos[${status.index}].accountNumber" /><br /> <form:errors
								path="employeeBankInfos[${status.index}].accountNumber" cssClass="error" /></td>
						<%-- 					<td><form:input path="employeeBankAccounts[${status.index}].accountNumber" /></td> --%>
						<td><form:input path="employeeBankInfos[${status.index}].bankName" /></td>
						<td><form:select path="employeeBankInfos[${status.index}].depositValueType"
								items="${depositTypeList}" itemLabel="name"></form:select></td>
						<td><form:input path="employeeBankInfos[${status.index}].depositValue" /></td>
						<td><button id="deleteRow" type="button"
								onclick="deleteThis('${pageContext.request.contextPath}/employee/bank/deleteInfo', 'bankInfoForm', 'id', '${bankInfo.id}')">Delete</button></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty bankInfoForm.employeeBankInfos}">
				<tr id="empBankTr">
					<td id="sno">1</td>
					<form:hidden path="employeeBankInfos[0].id" value="${bankInfo.id}" />
					<td><form:select path="employeeBankInfos[0].accountType" items="${accountTypeList}"
							itemLabel="name" /></td>
					<td><form:input path="employeeBankInfos[0].routingNumber"
							value="${bankInfo.routingNumber}" /></td>
					<td><form:input path="employeeBankInfos[0].accountNumber"
							value="${bankInfo.accountNumber}" /></td>
					<%-- 					<td><form:input path="employeeBankAccounts[${status.index}].accountNumber" /></td> --%>
					<td><form:input path="employeeBankInfos[0].bankName" value="${bankInfo.bankName}" /></td>
					<td><form:select path="employeeBankInfos[0].depositValueType" items="${depositTypeList}"
							itemLabel="name"></form:select></td>
					<td><form:input path="employeeBankInfos[0].depositValue" value="${bankInfo.depositValue}" /></td>
					<td></td>
				</tr>
			</c:if>
		</table>
		<form:button>Save</form:button>
		<button type="button" id="addMore">Add More</button>
	</form:form>
</body>
</html>