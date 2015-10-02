<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.css" />
<script type="text/javascript">
	$(function() {
		document.getElementById('edit-payrollSchedule').style.visibility = 'hidden';
	});
</script>
<script type="text/javascript">
	var obj = {};
	function Visible(payFrequencyTypeList) {
		document.getElementById('addpayrollFrequency').style.visibility = 'visible';
	}
	function NotVisible(payFrequencyTypeList) {
		document.getElementById('addpayrollFrequency').style.visibility = 'hidden';
	}
	function getEditEndDate() {
		var startDate = $("#editPeriodStartDate").val();
		var frequencyTypeId = $("#frequencyTypeId").val();
		alert("startDate::" + startDate);
		alert("frequencyTypeId::" + frequencyTypeId);
		$.post("${pageContext.request.contextPath}/company/getEndDate", {
			periodStartDate : startDate,
			frequencyTypeId : frequencyTypeId
		}, function(serverResponse) {
			alert(serverResponse.data);
			$("#editPeriodEndDate").val(serverResponse.data);

		});
	}
	function getAddEndDate() {
		var startDate = $("#periodStartDate").val();
		var frequencyTypeId = $("#frequencyTypeId").val();
		alert("startDate::" + startDate);
		alert("frequencyTypeId::" + frequencyTypeId);
		$.post("${pageContext.request.contextPath}/company/getEndDate", {
			periodStartDate : startDate,
			frequencyTypeId : frequencyTypeId
		}, function(serverResponse) {
			alert(serverResponse.data);
			$("#periodEndDate").val(serverResponse.data);

		});
	}
	function setValue(FrequencyTypeId) {
		$("#frequencyTypeId").val(FrequencyTypeId)
	}

	function addfrequency() {
		alert("addfrequency()");
		document.getElementById('edit-payrollSchedule').style.visibility = 'hidden';
		$("#periodStartDate").val(''), $("#periodEndDate").val(''), $("#checkDate").val('');
		document.getElementById('addpayrollFrequency').style.visibility = 'visible';

	}
	function edit(payrollFrequencyId, payFrequencyTypeId, type) {
		alert("payFrequencyTypeId :" + payFrequencyTypeId);
		$
				.get(
						"${pageContext.request.contextPath}/company/showPayrollFrequencyForm",
						{
							payrollFrequencyId : payrollFrequencyId
						},
						function(response) {
							alert(response.data.payrollFrequency.id);
							$("#id").val(response.data.payrollFrequency.id), $("#payFrequencyType")
									.val(type), $("#frequencyTypeId").val(payFrequencyTypeId), $(
									"#editPeriodStartDate").val(
									response.data.payrollFrequency.periodStartDate), $(
									"#editPeriodEndDate").val(
									response.data.payrollFrequency.periodEndDate),
									$("#editCheckDate").val(
											response.data.payrollFrequency.checkDate), $(
											"#editholidayCheckDateOptionList").val(
											response.data.holidayOptions);
							var scheduleList = response.data.payrollSchedules;
							obj.scheduleList = scheduleList;
							alert(obj.scheduleList.length);
							var header = "<h4>Payroll Schedule List</h4> <table class='data' border='1'><tr><th>Period Start Date</th><th>Period End Date</th><th>Check Date</th><th>Approved On</th><th>Status</th><th></th></tr>";
							var listSize = obj.scheduleList.length;
							for ( var i = 0; i < listSize; i++) {
								header += "<tr id='payrollScheduleRow"+i+"'><td>"
										+ obj.scheduleList[i].periodStartDate + " </td> <td>"
										+ obj.scheduleList[i].periodEndDate + "</td> <td>"
										+ obj.scheduleList[i].checkDate + "</td> <td>"
										+ obj.scheduleList[i].approvedOn
										+ " </td> <td class='status'>" + obj.scheduleList[i].status
										+ "</td>";
								if (obj.scheduleList[i].status == 'DELETED') {
									header += "<td><button type='button' onclick='restore("
											+ obj.scheduleList[i].id + "," + i + "," + listSize
											+ ")'>Restore</button></td></tr>";
								} else {
									header += "<td><button type='button' onclick='deleteSchedule("
											+ obj.scheduleList[i].id + "," + i + "," + listSize
											+ ")'>Delete</button></td></tr>";
								}
							}
							$("#list").replaceWith(header);
							document.getElementById('edit-payrollSchedule').style.visibility = 'visible';
							$('#payrollScheduleList').hide();
						});
	}
	function deletefrequency(payrollFrequencyId) {
		alert("payrollFrequencyId :" + payrollFrequencyId);
		$.get("${pageContext.request.contextPath}/company/deletePayRollFrequency", {
			payrollFrequencyId : payrollFrequencyId
		}, function(response) {
			if (response.status == 'success') {
				var id = "#payrollFrequencyRow" + payrollFrequencyId;
				$(id).replaceWith(" ");
				$('#listUpperList').hide();
				document.getElementById('addpayrollFrequency').style.visibility = 'hidden';
				document.getElementById('edit-payrollSchedule').style.visibility = 'hidden';
			}
		});
	}

	function deleteSchedule(payrollScheduleId, sNo, rowSize) {
		alert("payrollScheduleId :" + payrollScheduleId);
		$.get("${pageContext.request.contextPath}/company/deletePayRollschedule", {
			payrollScheduleId : payrollScheduleId
		}, function(response) {
			alert(response.data.status);
			if (response.status == 'success') {
				alert(response.status);
				var id = "#payrollScheduleRow" + sNo;
				var curStatus = $(id).find(".status").html();
				var data = "<tr id='payrollScheduleRow"+sNo+"'><td>"
						+ response.data.periodStartDate + " </td><td>"
						+ response.data.periodEndDate + " </td><td>" + response.data.checkDate
						+ " </td><td>" + response.data.approvedOn + "</td><td class='status'>"
						+ response.data.status + "</td><td><button type='button' onclick='restore("
						+ payrollScheduleId + "," + sNo + "," + rowSize
						+ ")'>Restore</button></td>";
				$(id).replaceWith(data);
				if (curStatus == 'CURRENT') {
					for ( var j = 1; j <= rowSize; j++) {
						var nextrow = sNo + j;
						var id = "#payrollScheduleRow" + nextrow;
						var statusValue = $(id).find(".status").html();
						if (statusValue == 'FUTURE') {
							$(id).find(".status").html('CURRENT');
							break;
						}
					}
				}
			}
		});
	}
	function restore(payrollScheduleId, Sno, rowSize) {
		alert("payrollScheduleId :" + payrollScheduleId);
		$.get("${pageContext.request.contextPath}/company/restore", {
			payrollScheduleId : payrollScheduleId
		},
				function(response) {
					alert(response.data.status);
					if (response.status == 'success') {
						var restoreId = "#payrollScheduleRow" + Sno;
						var restoredData = "<tr id='payrollScheduleRow"+Sno+"'><td>"
								+ response.data.periodStartDate + " </td><td>"
								+ response.data.periodEndDate + " </td><td>"
								+ response.data.checkDate + "</td><td>" + response.data.approvedOn
								+ "</td><td class='status'>" + response.data.status
								+ "</td><td><button type='button' onclick='deleteSchedule("
								+ payrollScheduleId + "," + Sno + "," + rowSize
								+ ")'>Delete</button></td>";
						$(restoreId).replaceWith(restoredData);
						var updatedStatus = response.data.status;
						if (updatedStatus == 'CURRENT') {
							for ( var j = 1; j <= rowSize; j++) {
								var nextrow = Sno + j;
								var id = "#payrollScheduleRow" + nextrow;
								var statusValue = $(id).find(".status").html();
								if (statusValue == 'CURRENT') {
									$(id).find(".status").html('FUTURE');
									break;
								}
							}
						}
					}
				});
	}
</script>
<div id="page">
	<div id="content">
		<div class="box">
			<c:if test="${!empty PayrollFrequencyList}">
				<h4>Pay Frequency List</h4>
				<table class="data" border="1">
					<tr>
						<th>S. No.</th>
						<th>Frequency</th>
						<th>Period Start Date</th>
						<th>Period End Date</th>
						<th>Check Date</th>
						<th>If CheckDate falls on holiday<br /> Select Which working day <br /> Should be the
							Check Date
						</th>
						<th><button type="button" id="add-frequency" onclick="addfrequency()">Add New
								Frequency</button></th>
					</tr>
					<c:forEach items="${PayrollFrequencyList}" var="payfrequency" varStatus="sno">
						<tr id="payrollFrequencyRow${payfrequency.id}">
							<td>${sno.count}</td>
							<td>${payfrequency.payFrequencyType.type}</td>
							<td><fmt:formatDate value="${payfrequency.periodStartDate}" pattern="MM/dd/yyyy" /></td>
							<td><fmt:formatDate value="${payfrequency.periodEndDate}" pattern="MM/dd/yyyy" /></td>
							<td><fmt:formatDate value="${payfrequency.checkDate}" pattern="MM/dd/yyyy" /></td>
							<td>${payfrequency.holidayCheckDateOption}</td>
							<td><button type="button"
									onclick="edit('${payfrequency.id}','${payfrequency.payFrequencyType.id}','${payfrequency.payFrequencyType.type}')">Edit</button>
								<button type="button" onclick="deletefrequency('${payfrequency.id}')">Delete</button></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<div id="addpayrollFrequency" style="border: 1;">
				<div class="error" id="data-error"></div>
				<form:form modelAttribute="payrollFrequency"
					action="${pageContext.request.contextPath}/company/addPayrollFrequency">
					<input type="hidden" id="frequencyTypeId" />
					<table>
						<tr>
							<td>Select Frequency Type :<br> <form:select path="payFrequencyType.id">
									<form:option value="" label="" />
									<form:options items="${payFrequencyTypeList}" itemLabel="type" itemValue="id"
										onclick='setValue(this.value)' />
								</form:select></td>
							<td>CheckDate: <br> <form:input path="checkDate" /> <br /></td>
							<td>Period Start Date:<br> <form:input path="periodStartDate"
									onchange="getAddEndDate()" /> <br /></td>
							<td>Period End Date : <br> <form:input path="periodEndDate" /> <br /></td>
							<td>HolidayCheckDateOption :<br> <form:select path="holidayCheckDateOption">
									<form:options items="${holidayCheckDateOptionList}" />
								</form:select>
							</td>
							<td><button type="submit">Save</button></td>
						</tr>
					</table>
				</form:form>
			</div>
			<c:if test="${empty PayrollFrequencyList}">
				<script>
					Visible();
				</script>
			</c:if>
			<c:if test="${!empty PayrollFrequencyList}">
				<script>
					NotVisible();
				</script>
			</c:if>
			<div id="edit-payrollSchedule">
				<form:form modelAttribute="companyPayrollFrequencyForm"
					action="${pageContext.request.contextPath}/company/updatePayrollFrequency">
					<form:hidden path="id" />
					<input type="hidden" id="frequencyTypeId" />
					<table>
						<tr>
							<td>Select Frequency Type :<br> <input type="text" name="payFrequencyType"
								id="payFrequencyType" ,cssClass="payFrequency" disabled="disabled" /> <br />
							</td>
							<td>CheckDate: <br> <input type="text" name="editCheckDate" id="editCheckDate"
								cssClass="payFrequency" /> <br /></td>
							<td>Period Start Date:<br> <input type="text" name="editPeriodStartDate"
								id="editPeriodStartDate" cssClass="payFrequency" onchange="getEditEndDate()" /> <br /></td>
							<td>Period End Date : <br> <input type="text" name="editPeriodEndDate"
								id="editPeriodEndDate" cssClass="payFrequency" /> <br /></td>
							<td>HolidayCheckDateOption :<br> <form:select path="holidayCheckDateOption"
									cssClass="payFrequency">
									<form:options items="${holidayCheckDateOptionList}" />
								</form:select>
							</td>
							<td><button type="submit">Go</button></td>
						</tr>
					</table>
				</form:form>
			</div>
			<div id="listUpperList">
				<div id="list"></div>
			</div>

			<div id="payrollScheduleList">
				<c:if test="${!empty payrollScheduleList}">
					<h4>Payroll Schedule List</h4>
					<table class="data" border="1">
						<tr>

							<th>Period Start Date</th>
							<th>Period End Date</th>
							<th>Check Date</th>
							<th>Approved On</th>
							<th>Status</th>
							<th></th>
						</tr>
						<c:forEach items="${payrollScheduleList}" var="payrollSchedule" varStatus="sno">
							<tr id="payrollScheduleRow${payrollSchedule.id}${sno.count}">
								<td><fmt:formatDate value="${payrollSchedule.periodStartDate}" pattern="MM/dd/yyyy" /></td>
								<td><fmt:formatDate value="${payrollSchedule.periodEndDate}" pattern="MM/dd/yyyy" /></td>
								<td><fmt:formatDate value="${payrollSchedule.checkDate}" pattern="MM/dd/yyyy" /></td>
								<td>${payrollSchedule.approvedOn}</td>
								<td>${payrollSchedule.status}</td>
								<td>
									<button type="button" onclick="deleteSchedule('${payrollSchedule.id}','${sno.count}')">Delete</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>

		</div>
	</div>
</div>
