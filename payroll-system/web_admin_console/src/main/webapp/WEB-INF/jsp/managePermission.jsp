<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function getPermission(url, roleId, roleName) {
		//alert(url + "," + roleId + "," + roleName);
		//window.location.href = url;
		document.getElementById("managePermissionForm").action = url;
		document.getElementById("roleId").value = roleId;
		document.getElementById("roleName").value = roleName;
		document.getElementById("managePermissionForm").submit();
	}
</script>

<script type="text/javascript">
	function processAllRows() {
		//alert("lastRow : " + document.getElementById("lastRowNo").value);
		rowSize = document.getElementById("listSize").value;
		firstRowIndex = document.getElementById("lastRowNo").value;
		for ( var i = firstRowIndex; i < rowSize+firstRowIndex; i++) {
			noneId = "managePermissions" + i + ".permissionTypes1";
			viewId = "managePermissions" + i + ".permissionTypes2";
			insertId = "managePermissions" + i + ".permissionTypes3";
			updateId = "managePermissions" + i + ".permissionTypes4";
			deleteId = "managePermissions" + i + ".permissionTypes5";
			if(document.getElementById(noneId).checked) {
				processRows(i, 'NONE');
			}
			if(document.getElementById(viewId).checked) {
				processRows(i, 'VIEW');
			}
			if(document.getElementById(insertId).checked) {
				processRows(i, 'INSERT');
			}
			if(document.getElementById(updateId).checked) {
				processRows(i, 'UPDATE');
			}
			if(document.getElementById(deleteId).checked) {
				processRows(i, 'DELETE');
			}
		}
	}

	function processRows(rowNo, value) {
		//alert("lastRow : " + document.getElementById("lastRowNo").value);
		//alert("checked : " + rowNo + " value : " + value);
		noneId = "managePermissions" + rowNo + ".permissionTypes1";
		viewId = "managePermissions" + rowNo + ".permissionTypes2";
		insertId = "managePermissions" + rowNo + ".permissionTypes3";
		updateId = "managePermissions" + rowNo + ".permissionTypes4";
		deleteId = "managePermissions" + rowNo + ".permissionTypes5";
		switch (value) {
		case 'NONE':
			if (document.getElementById(noneId).checked) {
				//document.getElementById(viewId).checked = false;
				document.getElementById(viewId).disabled = true;
				//document.getElementById(insertId).checked = false;
				document.getElementById(insertId).disabled = true;
				//document.getElementById(updateId).checked = false;
				document.getElementById(updateId).disabled = true;
				//document.getElementById(deleteId).checked = false;
				document.getElementById(deleteId).disabled = true;
			} else {
				document.getElementById(viewId).disabled = false;
				document.getElementById(insertId).disabled = false;
				document.getElementById(updateId).disabled = false;
				document.getElementById(deleteId).disabled = false;
			}
			break;

		case 'VIEW':
			if (document.getElementById(viewId).checked) {
				//document.getElementById(noneId).checked = false;
				document.getElementById(noneId).disabled = true;
				//document.getElementById(insertId).checked = false;
				document.getElementById(insertId).disabled = true;
				//document.getElementById(updateId).checked = false;
				document.getElementById(updateId).disabled = true;
				//document.getElementById(deleteId).checked = false;
				document.getElementById(deleteId).disabled = true;
			} else {
				document.getElementById(noneId).disabled = false;
				document.getElementById(insertId).disabled = false;
				document.getElementById(updateId).disabled = false;
				document.getElementById(deleteId).disabled = false;
			}
			break;
		case 'INSERT':
			if (document.getElementById(insertId).checked) {
				//document.getElementById(noneId).checked = false;
				document.getElementById(noneId).disabled = true;
				//document.getElementById(viewId).checked = false;
				document.getElementById(viewId).disabled = true;

			} else {
				document.getElementById(noneId).disabled = false;
				document.getElementById(viewId).disabled = false;
			}
			break;

		case 'UPDATE':
			if (document.getElementById(updateId).checked) {
				//document.getElementById(noneId).checked = false;
				document.getElementById(noneId).disabled = true;
				//document.getElementById(viewId).checked = false;
				document.getElementById(viewId).disabled = true;
			} else {
				document.getElementById(noneId).disabled = false;
				document.getElementById(viewId).disabled = false;
			}
			break;
		case 'DELETE':
			if (document.getElementById(deleteId).checked) {
				//document.getElementById(noneId).checked = false;
				document.getElementById(noneId).disabled = true;
				//document.getElementById(viewId).checked = false;
				document.getElementById(viewId).disabled = true;
			} else {
				document.getElementById(noneId).disabled = false;
				document.getElementById(viewId).disabled = false;
			}
			break;
		}
	}
</script>
</head>
<body onload="processAllRows()">
	<h1>Company Manage Permissions</h1>
	<h4>Manage Roles</h4>
	<table border="1">
		<tr>
			<th>S.No.</th>
			<th>Roles</th>
			<th>Descriptions</th>
			<th></th>
		</tr>
		<c:forEach items="${roleList}" var="role" varStatus="sno">
			<tr>
				<td>${sno.count}</td>
				<td>${role.roleName}</td>
				<td>${role.roleDescription}</td>
				<td><button type="button"
						onclick="getPermission('${pageContext.request.contextPath}/admin/getPermissions', '${role.id}','${role.roleName}')">Edit
						Permissions</button></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<c:url value="/admin/managePermissions" var="actionUrl" />
	<form:form action="${actionUrl}" method="post"
		modelAttribute="managePermissionForm">
		<form:hidden path="roleId" />
		<form:hidden path="roleName" />
		<form:hidden path="listSize" />
		<c:if test="${not empty managePermissionForm.managePermissions}">
			<h4>
				Define Permissions<br /> Role Name :
				${managePermissionForm.roleName}
			</h4>
			<table border="1">
				<c:forEach items="${subMenuList}" var="subMenu">
					<tr>
						<th colspan="2" align="left">${subMenu.name}</th>
					</tr>
					<c:forEach items="${managePermissionForm.managePermissions}"
						var="sectionModel" varStatus="row">
						<c:if test="${subMenu eq sectionModel.section.subMenu}">
							<tr>
								<form:hidden path="managePermissions[${row.index}].section" />
								<td>${sectionModel.section.name}</td>
								<td><form:checkboxes items="${permissionTypeList}"
										path="managePermissions[${row.index}].permissionTypes"
										itemLabel="name"
										onclick="processRows('${row.index}', this.value)" /><input
									type="hidden" id="lastRowNo" value="${row.index}" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</c:forEach>
			</table>
			<button type="submit">Save</button>
		</c:if>
	</form:form>
</body>
</html>