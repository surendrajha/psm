<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2>Company user info</h2>
	<table class="data" border="1">
		<tr>
			<td>${adminUserProfile.companyId}</td>
			<td>${adminUserProfile.firstName}</td>
			<td>${adminUserProfile.lastName}</td>
			<td>${adminUserProfile.city}</td>
			<td>${adminUserProfile.state}</td>
			<td>${adminUserProfile.county}</td>
		</tr>
	</table>
	<h3>Session Object value:</h3>
	${obj}
	<c:if test="${!empty states}">
		<table class="data" border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>
			</tr>
			<c:forEach items="${states}" var="state">
				<tr>
					<td>${state.id}</td>
					<td>${state.stateName}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>