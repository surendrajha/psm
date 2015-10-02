<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<h2 style="font-weight: normal">
	<span style="color: #ca3b2b;">Workers Compensation</span>
</h2>
<script type="text/javascript">
	$(function() {
		//alert("ready function");
		$("#compensation-form").dialog({

			autoOpen : false, // for opening box wothout ("open")
			buttons : { // for adding buttons on box
				"Update" : function() {
					//alert("update");
					updateCompensation();
				},
				"Cancel" : function() {
					//alert("cancel");
					$(this).dialog("close");
				}
			},
			closeOnEscape : true, // for closing box ueing 'Esc' key
			closeText : 'hide',
			draggable : false,
			height : 400,
			minHeight : 100,
			maxHeight : 400,
			width : 500,
			minWidth : 200,
			maxWidth : 600,
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
			title : "Update Deduction",

		});
		$("#addCompensation-form").dialog({
			autoOpen : false, // for opening box wothout ("open")
			buttons : { // for adding buttons on box
				"Add" : function() {
				addWorkersCompensation();
				},
				"Cancel" : function() {
					$(this).dialog("close");
				}
			},
			closeOnEscape : true, // for closing box ueing 'Esc' key
			closeText : 'hide',
			draggable : false,
			height : 400,
			minHeight : 100,
			maxHeight : 400,
			width : 500,
			minWidth : 200,
			maxWidth : 600,
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
			title : "Add New Compensation",

		});

		$("#add-compensation").button().click(function() {
			//alert("inside add-compensation");
			showAddForm();

		});

	});
	function showAddForm() {
		$.get(contexPath + "/company/showWorkersCompensationAddForm", {}, function(serverResponse) {
			//alert("response: " + serverResponse.data.length);
			dataString = "<select id=stateId  name='stateId'><option value=''></option>";
			for ( var i = 0; i < serverResponse.data.length; i++) {
				dataString += "<option value='" + serverResponse.data[i].id + "'>"
						+ serverResponse.data[i].stateName + "</option>";
			}
			dataString += "</select>";
			$("#stateId").replaceWith(dataString);
			$("#code").val('');
			$("#description").val('');
					$("#addCompensation-form").dialog("open");
		});
	}
	function addWorkersCompensation() {
				$.post( contexPath+"/company/addWorkersCompensation", {
					//id : $("#id").val(),
					stateId : $("#stateId").val(),
					code : $("#code").val(),
					description : $("#description").val(),
				}, function(response) {
//					alert(response.status);
					if(response.status == 'success') {
						
						var table = document.getElementById("compensationTable");
						 
			            var rowCount = table.rows.length;
			            var row = table.insertRow(rowCount); 
			 			row.id = "compensationRow"+response.data;
			            var cell1 = row.insertCell(0);
			            cell1.innerHTML = rowCount;
			            var cell2 = row.insertCell(1);
			            cell2.innerHTML = $("#code").val();
			            var cell3 = row.insertCell(2);
			            cell3.innerHTML = $("#stateId").val();
			            var cell4 = row.insertCell(3);
			            cell4.innerHTML = $("#description").val();
			            var cell5 = row.insertCell(4);
			            cell5.innerHTML = $("#rate").val();
			            var cell6 = row.insertCell(5);
			            cell6.innerHTML = "<button type='button' onclick='edit("+response.data+", "+rowCount+")'>Edit</button> <button type='button' onclick='deleteCompensation("+response.data+")'>Delete</button>";
						$("#addCompensation-form").dialog("close");
					}
				});
	}
	function edit(companyWorkersCompensationId, sno) {
		//alert("edit  id : " + deductionId);
		//alert("sno::"+sno);
	 	$.get(contexPath + "/company/showWorkersCompensationForm", {
	 		companyWorkersCompensationId : companyWorkersCompensationId
	 	}, function(response) {
		dataString = "<select id=updateStateId  name='updateStateId'><option value=''></option>";
		for ( var i = 0; i < response.data.usStates.length; i++) {
			dataString += "<option value='" + response.data.usStates[i].id + "'>"
					+ response.data.usStates[i].stateName + "</option>";
		}
		dataString += "</select>";
		$("#updateStateId").replaceWith(dataString);
		$("#deductionId").replaceWith(dataString); 
		$("#id").val(response.data.companyWorkersCompensationForm.id),
		$("#sNo").val(sno),
		$("#updateStateId").val(response.data.companyWorkersCompensationForm.stateId);
		$("#updatecode").val(response.data.companyWorkersCompensationForm.code),
		 $("#updatedescription").val(response.data.companyWorkersCompensationForm.description),
		 $("#compensation-form").dialog("open");
	 	});
	}
	function updateCompensation() {
					$.post( contexPath+"/company/updateWorkersCompensation", {
						id : $("#id").val(),
						code : $("#updatecode").val(),
						stateId : $("#updateStateId").val(),
						description : $("#updatedescription").val(),
					}, function(response) {
						//	alert(response);
								var id = "#compensationRow"+$("#id").val();
								//alert(id); 
								//alert($("#updateStateId").val()); 
								var data = "<tr id=compensationRow"+$("#id").val()+"><td>"+$("#sNo").val()+"</td><td>"+response.data.code+"</td><td>"+$("#updateStateId").val()+"</td><td>"+response.data.description+"</td><td>"+response.data.rate+"</td><td><button type='button' onclick='edit("+$("#id").val()+","+$("#sNo").val()+")'>Edit</button> <button type='button' onclick='deleteCompensation("+$("#id").val()+")'>Delete</button></td>";
							//	alert("compensationRow"+$("#id").val());
								$(id).replaceWith(data);
								$("#compensation-form").dialog("close");
							
					});
					
				}
	function deleteCompensation(companyWorkersCompensationId) {
		//alert("Id to delete : "+deductionId);
		var ans = confirm('Are you sure you want to delete?');
		if (ans) {
					$.post( contexPath+"/company/deleteWorkersCompensation", {
						id : companyWorkersCompensationId,
					}, function(response) {
							alert(response.status);
							if(response.status == 'success') {
								var id = "#compensationRow"+companyWorkersCompensationId;
								//alert(id);
								$(id).replaceWith('');
							} 
					});
					
				}
		else {
			return false;
		}
	 }
</script>
<div id="page">
	<div id="content">
		<div class="box">
			<c:if test="${!empty companyWorkerCompensations}">
				<h4>Compensation List</h4>
				<table border="1" id="compensationTable">

					<tr>
						<th>S. No.</th>
						<th>Code</th>
						<th>State</th>
						<th>Description</th>
						<th>Rate</th>
						<th><button type="button"
							id="add-compensation">Add New Workers CompenSation</button></th>
					</tr>
					<c:forEach items="${companyWorkerCompensations}" var="compensation" varStatus="sno">
						<tr id="compensationRow${compensation.id}">
							<td>${sno.count}</td>
							<td>${compensation.code}</td>
							<td>${compensation.usState.stateName}</td>
							<td>${compensation.description}</td>
							<td>
							<c:forEach items="${workersCompensationTaxRates}" var="compensationTaxRate">
							<c:if test="${compensationTaxRate.companyWorkerCompensation.id==compensation.id}">
							${compensationTaxRate.rate}
							</c:if>
							</c:forEach>
							</td>
							<td><button type="button" onclick="edit('${compensation.id}','${sno.count}')">Edit</button>
								<button type="button" onclick="deleteCompensation('${compensation.id}')">Delete</button></td>
						</tr>
					</c:forEach>
				</table>
								<div id="compensation-form" style="border: 1;">
									<div class="error" id="data-error"></div>
									<form:form modelAttribute="companyWorkersCompensationForm">
										<form:hidden path="id" />
										<input type="hidden" id="sNo" />
					Code: <input type="text" id="updatecode" />
										<br />
						  State: <select id="updateStateId">
</select> 
										<br />
					Description: <textarea id="updatedescription" name="updatedescription"></textarea>
										<br />
									</form:form>
								</div>


				<div id="addCompensation-form" style="border: 1;">
					<form:form modelAttribute="companyWorkersCompensationForm">
						<form:hidden path="id" />
						<input type="hidden" id="sNo" />
	Code: <form:input path="code" />
						<br />
		  State: <form:select path="stateId">
							<form:option value="" label="" />
							<form:options items="${usStatesList}" itemLabel="stateName" itemValue="id" />
						</form:select>
						<br />
	Description: <form:textarea path="description"/>
						<br />
					</form:form>
				</div>
			</c:if>

		</div>
	</div>
</div>
