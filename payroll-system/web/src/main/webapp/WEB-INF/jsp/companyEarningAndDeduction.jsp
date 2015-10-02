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
 function updateDeduction() {
//alert("Id to update : "+$("#id").val());
			$.post( contexPath+"/company/earningAndDeduction/updateCompanyDeductionDisplayName", {
				id : $("#id").val(),
				deductionCategoryName : $("#deductionCategoryName").val(),
				deductionName : $("#deductionName").val(),
				description : $("#description").val(),
				displayName : $("#displayName").val()
			}, function(response) {
					//alert(response.status);
					if(response.status == 'success') {
						var id = "#deductionRow"+$("#id").val();
						//var data = "<tr id='deductionRow'"+$("#id").val()+"><td>"+$("#sNo").val()+"</td><td>"+$("#deductionCategoryName").val()+"</td><td>"+$("#deductionName").val()+"</td><td>"+$("#description").val()+"</td><td>"+$("#displayName").val()+"</td><td><button type='button' onclick='showUpdateDeduction("+$("#id").val()+")'>Edit</button> <button type='button'>Delete</button></td>";
						var data = "<tr id='deductionRow'"+$("#id").val()+"><td>"+$("#sNo").val()+"</td><td>"+response.data.deduction.deductionCategory.category+"</td><td>"+response.data.deduction.deductionName+"</td><td>"+response.data.deduction.deductionCategory.description+"</td><td>"+response.data.displayName+"</td><td><button type='button' onclick='showUpdateDeduction("+$("#id").val()+")'>Edit</button> <button type='button'>Delete</button></td>";
						$(id).replaceWith(data);
						$("#deduction-form").dialog("close");
						$("#deductionMessage").html(response.message);
					} else {
						$("#data-error").html(response.message);
					}
			});
			
		}

 function deleteDeduction(deductionId) {
	//alert("Id to delete : "+deductionId);
	var ans = confirm('Are you sure you want to delete?');
	if (ans) {
				$.post( contexPath+"/company/earningAndDeduction/deleteDeduction", {
					id : deductionId,
				}, function(response) {
						//alert(response.status);
						if(response.status == 'success') {
							var id = "#deductionRow"+deductionId;
							$(id).replaceWith(" ");
							$("#deductionMessage").html(response.message);
						} else {
							$("#deductionMessage").html(response.message);
						}
				});
				
			}
	else {
		return false;
	}
 }
 
 function addDeduction() { 
	$.post( contexPath+"/company/earningAndDeduction/addDeduction", {
		//id : $("#id").val(),
		deductionId : $("#deductionId").val(),
		displayName : $("#addDisplayName").val()
	}, function(response) {
		//	alert(response.status);
			if(response.status == 'success') {
				
				var table = document.getElementById("deductionTable");
				 
	            var rowCount = table.rows.length;
	            var row = table.insertRow(rowCount); 
	 			row.id = "deductionRow"+response.data;
	            var cell1 = row.insertCell(0);
	            cell1.innerHTML = rowCount;
	            var cell2 = row.insertCell(1);
	            cell2.innerHTML = $("#deductionCategoryId").val();
	            var cell3 = row.insertCell(2);
	            cell3.innerHTML = $("#deductionId").val();
	            var cell4 = row.insertCell(3);
	            cell4.innerHTML = $("#addDescription").val();
	            var cell5 = row.insertCell(4);
	            cell5.innerHTML = $("#addDisplayName").val();
	            var cell6 = row.insertCell(5);
	            cell6.innerHTML = "<button type='button' onclick='showUpdateDeduction("+response.data+", "+rowCount+")'>Edit</button> <button type='button' onclick='deleteDeduction("+response.data+")'>Delete</button>";
	            
// 	            var editButton = document.createElement("input");
// 	            editButton.type = "button";
// 	            editButton.value="Edit";
// 	            editButton.onclick = showUpdateDeduction($("#deductionId").val(), rowCount);
	            
// 	            var deleteButton = document.createElement("input");
// 	            deleteButton.type = "button";
// 	            deleteButton.value="Delete";
// 	            deleteButton.onclick = "deleteDeduction(${deduction.id})";

// 	            cell6.appendChild(editButton);
// 	            cell6.appendChild(deleteButton);
				
				$("#addDeduction-form").dialog("close");
				$("#deductionMessage").html(response.message);
				
			} else {
				$("#add-data-error").html(response.message);
			}
	});
	
}
$(function() {
	//alert("ready function");
	$("#deduction-form").dialog({

		autoOpen : false, // for opening box wothout ("open")
		buttons : { // for adding buttons on box
			"Update" : function() {
				//alert("update");
				updateDeduction();
				//$("#companyDeductionForm").submit();
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
	$("#addDeduction-form").dialog({

		autoOpen : false, // for opening box wothout ("open")
		buttons : { // for adding buttons on box
			"Add" : function() {
				//alert("update");
				addDeduction();
				//$("#companyDeductionForm").submit();
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
		title : "Add Deduction",

	});

	$("#add-deduction").button().click(function() {
		showAddForm();
		
	});
	
});

function showAddForm() {
 	$.get(contexPath + "/company/earningAndDeduction/showAddDeductionForm", {
 	}, function(serverResponse) {
 		//alert("response: " + serverResponse.status);
 		dataString = "<select id=deductionCategoryId  name='deductionCategoryId' onchange='getDeductionList(this.value);'><option value=''></option>";
 		for ( var i = 0; i < serverResponse.data.length; i++) {
			dataString += "<option value='" + serverResponse.data[i].id + "'>"
					+ serverResponse.data[i].category + "</option>";
		}
		dataString += "</select>";
		$("#deductionCategoryId").replaceWith(dataString);
		 $("#addDeduction-form").dialog("open");
 	});
}

function getDeductionList(deductionCategoryId) {
	//alert("deductionCategoryId : " + deductionCategoryId);
	$.post(contexPath+"/company/earningAndDeduction/getDeductions", {
		deductionCategoryId : deductionCategoryId
	}, function(serverResponse) {
		if (serverResponse.status == 'success') {
			
			dataString = "<select id='deductionId' name='deductionId' onchange='getDeductionDescription(this.value);'><option value=''></option>";
			for ( var i = 0; i < serverResponse.data.length; i++) {
				dataString += "<option value='" + serverResponse.data[i].id + "'>"
						+ serverResponse.data[i].deductionName + "</option>";
			}
			dataString += "</select>";
			
			$("#deductionId").replaceWith(dataString); 
		} else {
			//alert("Some error occurred : " + serverResponse.message);
			$("#add-data-error").html(serverResponse.message);
		}
	});
}

function getDeductionDescription(deductionId){
	//alert("deductionId : " + deductionId);
	$.post(contexPath+"/company/earningAndDeduction/getDeductionDescription", {
		deductionId : deductionId
	}, function(serverResponse) {
		if (serverResponse.status == 'success') {
			//alert("description : " + serverResponse.data.description);
			desString="	<textarea id='addDescription' name='addDescription' disabled='disabled'>"+serverResponse.data.description+"</textarea>"; 
				$("#addDescription").replaceWith(desString); 
			} else {
				//alert("Some error occurred : " + serverResponse.message);
				$("#add-data-error").html(serverResponse.message);
			}			
		});

}

function showUpdateDeduction(deductionId, sno) {
	
	edit(deductionId, sno);
	$("#deductionMessage").html("");
}
function edit(deductionId, sno) {
	//alert("edit  id : " + deductionId);
 	$.get(contexPath + "/company/earningAndDeduction/showUpdateDeductionForm", {
	id : deductionId
 	}, function(response) {
 		
	//alert(response.displayName);
	$("#id").val(response.id),
	$("#sNo").val(sno),
	$("#deductionCategoryName").val(response.deductionCategoryName),
	 $("#deductionName").val(response.deductionName),
	 $("#description").val(response.description),
      $("#displayName").val(response.displayName);
	 $("#deduction-form").dialog("open");
 	});
}
</script>
<div id="page">
	<div id="content">
		<div class="box">
			<h3>Company Earnings And Deductions</h3>
			<hr>

			<c:if test="${!empty companyEarningList}">
				<h4>Earnings</h4>
				<table class="data" border="1">
					<tr>
						<th>S. No.</th>
						<th>Type of Earning</th>
						<th>Earning</th>
						<th>Description</th>
						<th>Display Name</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${companyEarningList}" var="earning"
						varStatus="sno">
						<tr>
							<td>${sno.count}</td>
							<td>${earning.earning.earningCategory.category}</td>
							<td>${earning.earning.earningName}</td>
							<td>${earning.earning.description}</td>
							<td>${earning.displayName}</td>
							<td><button type="button" onclick="">Edit</button>
								<button type="button" onclick="">Delete</button> <input
								type="button" onclick=""></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>

			<c:if test="${!empty companyDeductionList}">
				<h4>Deduction</h4>
				<div id="deductionMessage"></div>
				<table class="dataUsers" border="1" id="deductionTable">
					<tr>
						<th>S. No.</th>
						<th>Type of Deduction</th>
						<th>Deduction</th>
						<th>Description</th>
						<th>Display Name</th>
						<th><button type="button" id="add-deduction">Add New
								Deduction</button></th>
					</tr>
					<c:forEach items="${companyDeductionList}" var="deduction"
						varStatus="sno">
						<tr id="deductionRow${deduction.id}">
							<td>${sno.count}</td>
							<td>${deduction.deduction.deductionCategory.category}</td>
							<td>${deduction.deduction.deductionName}</td>
							<td>${deduction.deduction.description}</td>
							<td>${deduction.displayName}</td>
							<td><button type="button"
									onclick="showUpdateDeduction(${deduction.id}, ${sno.count})">Edit</button>
								<button type="button" onclick="deleteDeduction(${deduction.id})">Delete</button></td>
						</tr>
					</c:forEach>
				</table>
				<div id="deduction-form" style="border: 1;">
					<div class="error" id="data-error"></div>
					<form:form modelAttribute="companyDeductionForm">
						<form:hidden path="id" />
						<input type="hidden" id="sNo" />
	Type Of Deduction: <form:input path="deductionCategoryName"
							disabled="true" />
						<br />
		 Deduction: <form:input path="deductionName" disabled="true" />
						<br />
	Description: <form:textarea path="description"  disabled="true"/>
						<br />
	DisplayName : <form:input path="displayName" />
						<br />
					</form:form>
				</div>
				<div id="addDeduction-form" style="border: 1;">
					<div class="error" id="add-data-error"></div>
					<form:form modelAttribute="companyDeductionForm">
						<form:hidden path="id" />
						<input type="hidden" id="sNo" />
	Type Of Deduction: <form:select path="deductionCategoryId">
							<form:option value="" label="" />
							<form:options items="${deductionCategoryList}"
								itemLabel="category" itemValue="id"
								onchange="getDeductionList(this.value)" />
						</form:select>
						<br />
		 Deduction: <form:select path="deductionId">
							<form:option value="" label="" />
							<form:options items="${deductionList}" itemLabel="deductionName"
								itemValue="id" />
						</form:select>
						<br />
	Description: <textarea name="addDescription" id="addDescription"
							disabled="disabled"></textarea>
						<br />
	DisplayName : <input type="text" name="addDisplayName"
							id="addDisplayName" />

						<br />
					</form:form>
				</div>
			</c:if>

		</div>

		<br class="clearfix" />
	</div>

	<br class="clearfix" />

</div>
