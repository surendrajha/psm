<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<body>
	<h2>Section View Form</h2>
	<c:url value="/employee/addEmployeeSections" var="actionUrl" />
	<form:form method="POST" modelAttribute="employeeSectionForm" action="${actionUrl}">
		<c:forEach items="${sectionList}" var="sec" varStatus="varStatus">
			<form:checkbox path="empSections[${varStatus.index}].sectionId" value="${sec.id}" /> ${sec.sectionName}<br />
		</c:forEach>
		<td colspan="3"><input type="submit" name="submit" /></td>
	</form:form>
</body>
</html>