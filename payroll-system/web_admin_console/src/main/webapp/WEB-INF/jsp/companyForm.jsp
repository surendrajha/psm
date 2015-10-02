<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	
	<h2>Company Legal info</h2>
	<table class="data" border="1">
		<tr>
			<th>companyId</th>
			<th>legalName</th>
			<th>streetAddress</th>
			<th>city</th>
			<th>state</th>
			<th>county</th>
		</tr>
		<tr>
			<td>${legalInfoForm.companyId}</td>
			<td>${legalInfoForm.legalName}</td>
			<td>${legalInfoForm.streetAddress}</td>
			<td>${legalInfoForm.city}</td>
			<td>${legalInfoForm.state}</td>
			<td>${legalInfoForm.county}</td>
		</tr>
	</table>
	<h3>Session Object value:</h3>
	${msg}
</body>
</html>