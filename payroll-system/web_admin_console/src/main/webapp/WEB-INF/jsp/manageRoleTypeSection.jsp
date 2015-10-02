<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function getSection(url, roleType) {
		document.getElementById("roleTypeSectionMapForm").action = url;
		document.getElementById("roleType").value = roleType;
		document.getElementById("roleTypeSectionMapForm").submit();
	}
</script>
</head>
<body>
	<h1>Manage Role Type Section Map</h1>
	<h4>Role Types</h4>
	<table border="1">
		<tr>
			<th>S.No.</th>
			<th>Role Type</th>
			<th></th>
		</tr>
		<c:forEach items="${roleTypeList}" var="roleType" varStatus="sno">
			<tr>
				<td>${sno.count}</td>
				<td>${roleType.name}</td>
				<td><button type="button"
						onclick="getSection('${pageContext.request.contextPath}/admin/getSections', '${roleType}')">Edit
						Sections</button></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<c:url value="/admin/manageSections" var="actionUrl" />
	<form:form action="${actionUrl}" method="post"
		modelAttribute="roleTypeSectionMapForm">
		<form:hidden path="roleType" />
		<h4>
			Define Sections<br /> Role Type : ${roleTypeSectionMapForm.roleType.name}
		</h4>
		<table border="1">
			<tr>
				<th>Menu</th>
				<th>Sub Menu</th>
				<th>Section</th>
				<th></th>
			</tr>
			<c:forEach items="${sectionList}" var="section" varStatus="row">
				<tr>
					<td>${section.subMenu.menu.name}</td>
					<td>${section.subMenu.name}</td>
					<td>${section.name}</td>
					<td><form:checkbox path="sections" value="${section}" /></td>
				</tr>
			</c:forEach>

		</table>
		<button type="submit">Save</button>
	</form:form>
</body>
</html>