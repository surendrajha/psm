<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<c:url value="/employee/saveDepartmentAllocation" var="actionUrl" />
	<form:form modelAttribute="allocationForm" action="${actionUrl}" method="post">
		<h2>Company Legal info</h2>
		<table class="data" border="1">
			<tr>
				<th>S No</th>
				<th>Code</th>
				<th>Description</th>
				<th>Allocation</th>
			</tr>
			<c:forEach items="${allocationList}" var="allocation" varStatus="status">
				<tr>
					<form:hidden path="allocations[${status.index}].id" value="${allocation.id}" />
					<form:hidden path="allocations[${status.index}].deptId" value="${allocation.deptId}" />
					<td>${status.count}</td>
					<td>${allocation.departmentCode}</td>
					<td>${allocation.departmentDesciption}</td>
					<td><form:input path="allocations[${status.index}].allocationPercentage"
							value="${allocation.allocationPercentage}" /></td>
				</tr>
			</c:forEach>
		</table>
		<form:button>Save</form:button>
	</form:form>
</body>
</html>