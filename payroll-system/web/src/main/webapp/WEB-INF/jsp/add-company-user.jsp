
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form method="post" action="addNewUser" commandName="companyUserAddForm">
		<table>
			<tr>
				<td>firstName</td>
				<td><form:input path="firstName" />
					<form:errors path="firstName" /></td>
			</tr>
			<tr>
				<td>lastName</td>
				<td><form:input path="lastName" />
					<form:errors path="lastName" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" />
					<form:errors path="email" /></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><form:input path="role" />
					<form:errors path="role" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>