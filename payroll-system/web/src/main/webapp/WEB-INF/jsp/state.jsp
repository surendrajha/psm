<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
<title>Payroll System</title>
</head>
<body>
	<h2>State</h2>
	<c:url value="/state/add" var="addUrl" />
	<form:form method="post" action="${addUrl}" commandName="state">
		<table>
			<tr>
				<td><form:label path="stateName">
						<spring:message code="label.name" />
					</form:label></td>
				<td><form:input path="stateName" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="<spring:message code="label.addState"/>" /></td>
			</tr>
		</table>
	</form:form>
	<sec:authorize access="hasAnyRole('US_STATE-has-VIEW','US_STATE-has-DELETE','US_STATE-has-UPDATE')">
		<h3>States</h3>
		<c:if test="${!empty stateList}">
			<table class="data" border="1">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach items="${stateList}" var="state">
					<tr>
						<td>${state.id}</td>
						<td>${state.stateName}</td>
						<sec:authorize access="hasRole('US_STATE-has-DELETE')">
							<td><a href="delete/${state.id}">delete</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</sec:authorize>
</body>
</html>