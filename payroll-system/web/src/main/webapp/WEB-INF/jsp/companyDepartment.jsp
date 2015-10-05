<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://www.labs.mimmin.com/inlineedit/jquery.inlineEdit.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" />

<script type="text/javascript">
var contexPath = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript">
$(function() {
	//alert("ready function");
	$("#department-form").dialog({

		autoOpen : false, // for opening box wothout ("open")
		buttons : { // for adding buttons on box
			"Save" : function() {
				//alert("update");
				updateDepartment();
				//$("#companyDepartmentForm").submit();
			},
			"Cancel" : function() {
				//alert("cancel");
				$(this).dialog("close");
			}
		},
		closeOnEscape : true, // for closing box ueing 'Esc' key
		closeText : 'hide',
		draggable : false,
		height : 300,
		minHeight : 100,
		maxHeight : 400,
		width : 500,
		minWidth : 200,
		maxWidth : 400,
		//show: "slide",
		//hide: "slide"
		show : {
			effect : 'drop',
			direction : "up"
		},
		hide : {
			effect : 'drop',
			direction : "down"
		},
		modal : false, // for disable background
	resizable : true,
		stack : false,
		title : "Edit Department",
		
	});
	$("#addDepartment-form").dialog({

		autoOpen : false, // for opening box wothout ("open")
		buttons : { // for adding buttons on box
			"Add" : function() {
				//alert("update");
				addDepartment();
				//$("#companyDepartmentForm").submit();
			},
			"Cancel" : function() {
				//alert("cancel");
				$(this).dialog("close");
			}
		},
		closeOnEscape : true, // for closing box ueing 'Esc' key
		closeText : 'hide',
		draggable : false,
		height : 300,
		minHeight : 100,
		maxHeight : 400,
		width : 500,
		minWidth : 200,
		maxWidth : 400,
		//show: "slide",
		//hide: "slide"
		show : {
			effect : 'drop',
			direction : "up"
		},
		hide : {
			effect : 'drop',
			direction : "down"
		},
		modal : false, // for disable background
		resizable : true,
		stack : false,
		title : "Add Department",

	});
	
	$("#add-department").button().click(function() {
		showAddForm();
	});
	
	
});
function showUpdateDepartment(departmentId, sno) {
	
	edit(departmentId, sno);
	$("#departmentMessage").html("");
}
function edit(departmentId, sno) {
// 	alert("edit  id : " + departmentId);
 	$.get(contexPath + "/company/department/showUpdateForm", {
	id : departmentId
 	}, function(response) {
// 	alert(response.deptName);
	$("#id").val(response.id),
	$("#sNo").val(sno),
	$("#deptId").val(response.deptId),
	 $("#deptName").val(response.deptName),
	 $("#description").val(response.description),
	 $("#status").val(response.status),
	 $("#department-form").dialog("open");
 	});
}
function updateDepartment() {
// 	alert("Id to update : "+$("#id").val());
				$.post( contexPath+"/company/department/update", {
					id : $("#id").val(),
					deptId : $("#deptId").val(),
					deptName : $("#deptName").val(),
					description : $("#description").val(),
					status : $("#status").val()
				}, function(response) {
// 						alert(response.status);
						if(response.status == 'success') {
							var id = "#departmentRow"+$("#id").val();
							var data = "<tr id='departmentRow"+$("#id").val()+"'><td>"+$("#sNo").val()+"</td><td>"+response.data.deptId+"</td><td>"+response.data.deptName+"</td><td>"+response.data.description+"</td><td><button type='button' onclick='showUpdateDepartment("+$("#id").val()+","+$("#sNo").val()+")'>Edit</button> <button type='button' onclick='deleteDepartment("+$("#id").val()+")'>Delete</button><br />";
							var radioCol = " <input type='radio' name='status"+response.data.id+"' id='status"+response.data.id+"' value='Active' checked='checked' onchange='changeStatus("+response.data.id+",value)'>Active <input type='radio' name='status"+response.data.id+"' id='status"+response.data.id+"' value='InActive' onchange='changeStatus("+response.data.id+",value)'>Inactive ";
							alert(response.data.status);
							if(response.data.status == 'INACTIVE') {
								radioCol = " <input type='radio' name='status"+response.data.id+"' id='status"+response.data.id+"' value='Active' onchange='changeStatus("+response.data.id+",value)'>Active <input type='radio' name='status"+response.data.id+"' id='status"+response.data.id+"' value='InActive' checked='checked' onchange='changeStatus("+response.data.id+",value)'>Inactive ";
							}
							data += radioCol+"</td>";
							$(id).replaceWith(data);
							$("#department-form").dialog("close");
							$("#departmentMessage").html(response.message);
						} else {
							$("#data-error").html(response.message);
						}
				});
			}
			
function changeStatus(departmentId, status) {
	alert("changeStatus  id :: " + departmentId+" status :: "+ status);
	var ans = confirm('Are you sure you want to change Status?');
	if (ans) {
 	$.post(contexPath + "/company/department/updateStatus", {
	id : departmentId,
	status : status,
 	}, function(response) {
 		alert(response.status);
//  		if(response.status == 'success') {
// 			var id = "#departmentRow"+$("#id").val();
// 			var data = "id='departmentRow"+$("#id").val()+"'><td>"+$("#sNo").val()+"</td><td>"+response.data.deptId+"</td><td>"+response.data.deptName+"</td><td>"+response.data.description+"</td><td><button type='button' onclick='showUpdateDepartment("+$("#id").val()+","+$("#sNo").val()+")'>Edit</button> <button type='button' onclick='deleteDepartment("+$("#id").val()+")'>Delete</button></td>";
// 			$(id).replaceWith(data);
			$("#departmentMessage").html(response.message);
// 		} else {
// 			$("departmentMessage").html(response.message);
// 		}
});
	}
	else {
		return false;
	}
}
 		
function deleteDepartment(departmentId) {
// 	alert("Id to delete : "+departmentId);
	var ans = confirm('Are you sure you want to delete?');
	if (ans) {
				$.post( contexPath+"/company/department/delete", {
					id : departmentId,
				}, function(response) {
						if(response.status == 'success') {
							var id = "#departmentRow"+departmentId;
							$(id).replaceWith(" ");
							$("#departmentMessage").html(response.message);
						} else {
							$("#departmentMessage").html(response.message);
						}
				});
			}
	else {
		return false;
	}
 }	
function showAddForm() {
 	$.get(contexPath + "/company/department/showAddForm" ,{
 	}, function(serverResponse) {
		 $("#addDepartment-form").dialog("open");
 	});
}

function addDepartment() { 
	$.post( contexPath+"/company/department/add", {
		deptId : $("#addDeptId").val(),
		deptName : $("#addDeptName").val(),
		description : $("#addDescription").val(),
	}, function(response) {
			if(response.status == 'success') {
				var table = document.getElementById("departmentTable");
	            var rowCount = table.rows.length;
	            var row = table.insertRow(rowCount); 
	 			row.id = "departmentRow"+response.data;
	            var cell1 = row.insertCell(0);
	            cell1.innerHTML = rowCount;
	            var cell2 = row.insertCell(1);
	            cell2.innerHTML = $("#addDeptId").val();
	            var cell3 = row.insertCell(2);
	            cell3.innerHTML = $("#addDeptName").val();
	            var cell4 = row.insertCell(3);
	            cell4.innerHTML = $("#addDescription").val();
	            var cell5 = row.insertCell(4);
	            cell5.innerHTML = "<button type='button' onclick='showUpdateDepartment("+response.data+", "+rowCount+")'>Edit</button> <button type='button' onclick='deleteDepartment("+response.data+")'>Delete</button><br>"+
	            " <input type='radio' name='status"+response.data+"' id='status"+response.data+"' value='Active' checked='checked' onchange='changeStatus("+response.data+",value)'>Active <input type='radio' name='status"+response.data+"' id='status"+response.data+"' value='InActive' onchange='changeStatus("+response.data+",value)'>Inactive ";
				$("#addDepartment-form").dialog("close");
				$("#departmentMessage").html(response.message);
				
			} else {
				$("#add-data-error").html(response.message);
			}
	});
	
}



</script>

<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
	<h2 style="text-decoration: underline; color: #217BBE;">Company
		Department List</h2>
	<br>
	</p>
	<div
		style="border: solid; border-width: 1px; padding: 10px; border-radius: 5px 5px 5px 5px;">
		<p style="margin: 0px 0px 5px 0px; text-align: left;">
			&nbsp;&nbsp;&nbsp;This page contains List of Departments owned by
			company. From here company owner can add, update, activate,
			deactivate and delete his company’s departments. When company owner
			clicks on Add New Department Link then Add New Department Facebox
			will open from where company owner can add a new department.
			Similarly when he clicks on Edit Button the Edit Department Facebox
			will open from where he can edit department’s information.</p>
		<hr>
		<c:if test="${!empty companyDepartmentList}">
			<table style="width: 100%;">
				<tr>
					<td align="left"><h3
							style="color: #217BBE; border-color: black;">Department List</h3></td>
					<td align="right"><h4>
							<button type="button" id="add-department">Add New
								Department</button>
						</h4></td>
				</tr>
			</table>
			<br>
			<div id="departmentMessage"></div>
			<table
				style="border: 1px solid; width: 100%; text-align: center; border-radius: 5px 5px 5px 5px;"
				border="1" class="dataUsers" id="departmentTable">
				<tr>
					<th><h4>S.No.</h4></th>
					<th><h4>ID</h4></th>
					<th><h4>Name</h4></th>
					<th><h4>Description</h4></th>
					<th></th>
				</tr>
				<c:forEach items="${companyDepartmentList}" var="department"
					varStatus="sno">
					<tr id="departmentRow${department.id}">
						<td>${sno.count}</td>
						<td>${department.deptId}</td>
						<td>${department.deptName}</td>
						<td>${department.description}</td>
						<td><button type="button"
								onclick="showUpdateDepartment(${department.id}, ${sno.count})">Edit</button>
							<button type="button"
								onclick="deleteDepartment(${department.id})">Delete</button> <br>

							<%-- 							<c:forEach items="${departmentStatusList}" var="deptStatus" varStatus="row"> --%>
							<%-- 								<c:if test="${department.status eq deptStatus}"> --%>
							<%-- 									<input type="radio" name="status${department.id}" --%>
							<%-- 										id="status${department.id}" value="${deptStatus}" --%>
							<!-- 										checked="checked" --> <%-- 										onchange="changeStatus(${department.id},value)">${deptStatus} --%>
							<%-- 								</c:if> --%> <%-- 								<c:if test="${department.status ne deptStatus}"> --%>
							<%-- 									<input type="radio" name="status${department.id}" --%>
							<%-- 										id="status${department.id}" value="${deptStatus}" --%>
							<%-- 										onchange="changeStatus(${department.id},value)">${deptStatus} --%>
							<%-- 								</c:if> --%> <%-- 							</c:forEach>  --%> <c:if
								test="${department.status.name eq 'Active'}">
								<input type="radio" name="status${department.id}"
									id="status${department.id}" value="${department.status}"
									checked="checked"
									onchange="changeStatus(${department.id},value)">Active 
							 								<input type="radio" name="status${department.id}"
									id="status${department.id}" value="InActive"
									onchange="changeStatus(${department.id},value)">Inactive 
							 							</c:if> <c:if test="${department.status.name eq 'InActive'}">
								<input type="radio" name="status${department.id}"
									id="status${department.id}" value="Active"
									onchange="changeStatus(${department.id},value)">Active 
							 							<input type="radio" name="status${department.id}"
									id="status${department.id}" value="InActive" checked="checked"
									onchange="changeStatus(${department.id},value)">Inactive
							 							</c:if></td>
					</tr>
				</c:forEach>
			</table>
			<div id="department-form" style="border: 1;">
				<div class="error" id="data-error"></div>
				<form>
					<input type="hidden" id="sNo" /> <input type="hidden" id="id" />
										<input type="hidden" id="status" /> 
					<p style="margin: 0px 0px 5px 0px; text-align: left;">
					<p>Review and Update the Information and Click on Save Button.</p>
					</p>
					<hr>
					<br>
					<table
						style="border: 1px solid; width: 100%; text-align: left; border-radius: 5px 5px 5px 5px;">
						<tr>
							<td>Department Id:</td>
							<td><input type="text" name="deptId" id="deptId" /></td>
						</tr>
						<tr>
							<td>Department Name :</td>
							<td><input type="text" name="deptName" id="deptName" /></td>
						</tr>
						<tr>
							<td>Description :</td>
							<td><textarea type="text" name="description"
									id="description"></textarea></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</table>
					<br>
					<hr>
				</form>
			</div>

			<div id="addDepartment-form" style="border: 1;">
				<div class="error" id="add-data-error"></div>
				<form>
					<p style="margin: 0px 0px 5px 0px; text-align: left;">
						<!-- 					<h4 style="text-decoration: underline; color: #217BBE;">Edit -->
						<!-- 						Department</h4> -->
						<!-- 					<br> -->
					<p>Add new Department.</p>
					</p>
					<hr>
					<br> <input type="hidden" id="sNo" /> <input type="hidden"
						id="id" />
					<table
						style="border: 1px solid; width: 100%; text-align: left; border-radius: 5px 5px 5px 5px;">
						<tr>
							<td>Department Id:</td>
							<td><input type="text" name="addDeptId" id="addDeptId" /></td>
						</tr>
						<tr>
							<td>Department Name :</td>
							<td><input type="text" name="addDeptName" id="addDeptName" /></td>
						</tr>
						<tr>
							<td>Description :</td>
							<td><textarea type="text" name="addDescription"
									id="addDescription"></textarea></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</table>
					<br>
					<hr>
				</form>
			</div>

		</c:if>
	</div>
</div>
<div class="clear"></div>
