<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.css" />

<script type="text/javascript">
	var obj = {};
	function Visible(payFrequencyTypeList) {
		document.getElementById('addpayrollFrequency').style.visibility = 'visible';
	}
	function NotVisible(payFrequencyTypeList) {
		document.getElementById('addpayrollFrequency').style.visibility = 'hidden';
	}
	function visibleEditForm(payFrequencyTypeList) {
		document.getElementById('edit-payrollSchedule').style.visibility = 'visible';
	}
	function notVisibleEditForm(payFrequencyTypeList) {
		document.getElementById('edit-payrollSchedule').style.visibility = 'hidden';
	}
	function notVisibleSchedules() {
		document.getElementById('scheduleList').style.visibility = 'hidden';
	}
	
	function getEditEndDate(frequencyTypeId) {
		var startDate = $("#editPeriodStartDate").val();
		//alert("startDate::" + startDate);
		//alert("frequencyTypeId::" + frequencyTypeId);
		$.post("${pageContext.request.contextPath}/company/getEndDate", {
			periodStartDate : startDate,
			payFrequencyType : frequencyTypeId
		}, function(serverResponse) {
			//alert(serverResponse.data);
			$("#editPeriodEndDate").val(serverResponse.data);

		});
	}
	function getAddEndDate() {
		var startDate = $("#periodStartDate").val();
		var frequencyTypeId = $("#frequencyTypeId").val();
		//alert("startDate::" + startDate);
		//alert("frequencyTypeId::" + frequencyTypeId);
		$.post("${pageContext.request.contextPath}/company/getEndDate", {
			periodStartDate : startDate,
			payFrequencyType : frequencyTypeId
		}, function(serverResponse) {
			//alert(serverResponse.data);
			$("#periodEndDate").val(serverResponse.data);

		});
	}
	function setValue(FrequencyTypeId) {
		$("#frequencyTypeId").val(FrequencyTypeId);
	}

	function addfrequency() {
		//alert("addfrequency()");
		document.getElementById('edit-payrollSchedule').style.visibility = 'hidden';
		$("#periodStartDate").val(''), $("#periodEndDate").val(''), $("#checkDate").val('');
		$("#payrollScheduleList").hide();
		document.getElementById('addpayrollFrequency').style.visibility = 'visible';

	}

	function deleteSchedule(payrollScheduleId, sNo, rowSize) {
		//alert("sNo"+sNo);
		//alert("payrollScheduleId :" + payrollScheduleId);
		$.get("${pageContext.request.contextPath}/company/deletePayRollschedule", {
			payrollScheduleId : payrollScheduleId
		}, function(response) {
			//alert(response.data.status);
			if (response.status == 'success') {
				//alert(response.status);
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
					//alert("rowSize"+rowSize);
					for ( var j = 1; j <= rowSize; j++) {
						var nextrow = sNo + j;
					//	alert("nextrow"+nextrow);
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
		//alert("payrollScheduleId :" + payrollScheduleId);
		$.get("${pageContext.request.contextPath}/company/restore", {
			payrollScheduleId : payrollScheduleId
		},
				function(response) {
					//alert(response.data.status);
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
	function getPreviousPayrollScheduleList(startDate, endDate,sno) {
		$
				.get(
						"${pageContext.request.contextPath}/company/getPreviousPayrollScheduleList",
						{
							perioStartDate :startDate,
							periodEndDate :endDate,
							
						},
						function(response) {
							//alert(response.data);
							var schedules = response.data;
// 							obj.schedules = schedules;
// 							alert(obj.schedules.length);
							for(var j=1;j<=6;j++){
								var id="#scheduleList"+j; 
								$(id).html('');	
							}
							var header = " <div id='scheduleList"+sno+"'><table class='data' border='1'><tr><th>Date Of Process</th><th>Check Date</th><th>Period Start Date</th><th>Period End Date</th><th>Frequency</th><th>Approved On</th><th>Status</th></tr>";
							//var listSize = obj.schedules.length;
							var listSize = schedules.length;
							//alert(schedules.length);
							for ( var i = 0; i < listSize; i++) {
								header += "<tr><td>"
										+  " </td> <td>"
										+ schedules[i].checkDate + "</td> <td>"
										+ schedules[i].periodStartDate + "</td> <td>"
										+ schedules[i].periodEndDate+ "</td> <td>"
										//+ schedules[i].payrollFrequency.payFrequencyType.type
										+ "</td> <td>"
										+schedules[i].approvedOn+ "</td> <td>"
										+schedules[i].status+ "</td> </tr>";
							}
							header+="</table></div>";
							var id="#scheduleList"+sno; 
							$(id).html(header);
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
							<td>${payfrequency.payFrequencyType.name}</td>
							<td><fmt:formatDate value="${payfrequency.periodStartDate}" pattern="MM/dd/yyyy" /></td>
							<td><fmt:formatDate value="${payfrequency.periodEndDate}" pattern="MM/dd/yyyy" /></td>
							<td><fmt:formatDate value="${payfrequency.checkDate}" pattern="MM/dd/yyyy" /></td>
							<td>${payfrequency.holidayCheckDateOption}</td>
							<td><a
								href="${pageContext.request.contextPath}/company/showPayrollFrequencyFormNormal?payrollFrequencyId=${payfrequency.id}">Edit</a>
								<a
								href="${pageContext.request.contextPath}/company/deletePayRollFrequencyNormal?payrollFrequencyId=${payfrequency.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<div id="addpayrollFrequency" style="border: 1;">
				<div class="error" id="data-error"></div>
				<h4>Add New Frequency</h4>
				<form:form modelAttribute="payrollFrequency"
					action="${pageContext.request.contextPath}/company/addPayrollFrequencyNormal">
					<input type="hidden" id="frequencyTypeId" />
					<table>
						<tr>
							<td>Select Frequency Type :<br> <form:select path="payFrequencyType">
									<form:option value="" label="" />
									<form:options items="${payFrequencyTypeList}" itemLabel="name" 
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
				<a href="${pageContext.request.contextPath}/company/viewPreviousPayrollScheduleList?page=0">view
					Previous Schedule List</a>
				<br />
				<script>
					NotVisible();
				</script>
			</c:if>
			<div id="edit-payrollSchedule">
				<form:form modelAttribute="companyPayrollFrequencyForm"
					action="${pageContext.request.contextPath}/company/updatePayrollFrequencyNormal">
					<form:hidden path="id" />
					<form:hidden path="payFrequencyType" />
					<input type="hidden" id=frequencyTypeId value="${frequencyTypeId}" />
					<h4>Payroll Schedule List of ${payFrequencyTypeName}</h4>
					<table>
						<tr>
							<td>Select Frequency Type :<br> <input type="text" name="payFrequencyType"
								id="payFrequencyType" disabled="disabled" value="${payFrequencyTypeName}" /> <br />
							</td>
							<td>CheckDate: <br> <input type="text" name="editCheckDate" id="editCheckDate"
								value="${companyPayrollFrequencyForm.editCheckDate}" /> <br /></td>
							<td>Period Start Date:<br> <input type="text" name="editPeriodStartDate"
								id="editPeriodStartDate" onchange="getEditEndDate('${frequencyTypeId}')"
								value="${companyPayrollFrequencyForm.editPeriodStartDate}" /> <br /></td>
							<td>Period End Date : <br> <input type="text" name="editPeriodEndDate"
								id="editPeriodEndDate" value="${companyPayrollFrequencyForm.editPeriodEndDate}" /> <br /></td>
							<td>HolidayCheckDateOption :<br> <form:select path="holidayCheckDateOption">
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
			<c:if test="${empty payrollScheduleList}">
				<script>
				//alert("calling visible edit form");
				notVisibleEditForm();
				</script>
			</c:if>
			<div id="payrollScheduleList">
				<c:if test="${!empty payrollScheduleList}">
					<script>
				//alert("calling visible edit form");
				visibleEditForm();
				</script>
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
							<tr id="payrollScheduleRow${sno.count}">
								<td><fmt:formatDate value="${payrollSchedule.periodStartDate}" pattern="MM/dd/yyyy" /></td>
								<td><fmt:formatDate value="${payrollSchedule.periodEndDate}" pattern="MM/dd/yyyy" /></td>
								<td><fmt:formatDate value="${payrollSchedule.checkDate}" pattern="MM/dd/yyyy" /></td>
								<td>${payrollSchedule.approvedOn}</td>
								<td class='status'>${payrollSchedule.status}</td>
								<c:if test="${payrollSchedule.status=='DELETED'}">
									<td><button type='button'
											onclick="restore('${payrollSchedule.id}',${sno.count},'${fn:length(payrollScheduleList)}')">Restore</button></td>
								</c:if>
								<c:if test="${payrollSchedule.status=='CURRENT' or  payrollSchedule.status=='FUTURE'}">
									<td>
										<button type="button"
											onclick="deleteSchedule('${payrollSchedule.id}',${sno.count},'${fn:length(payrollScheduleList)}')">Delete</button>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<c:if test="${!empty previousPayrollScheduleList}">
				<!-- Paging Details -->

				<c:forEach items="${previousPayrollScheduleList.payrollSchedules}" var="previousSchedules"
					varStatus="sNo">

					<button type="button"
						onclick="getPreviousPayrollScheduleList('${previousSchedules.periodStartDate}','${previousSchedules.periodEndDate}',${sNo.count})">Schedule
						For Period ${previousSchedules.periodStartDate} to ${previousSchedules.periodEndDate}</button>
					<br />
					<div id="upperscheduleList${sNo.count}">
						<div id="scheduleList${sNo.count}"></div>
					</div>
				</c:forEach>
				<c:if
					test="${fn:length(previousPayrollScheduleList.payrollSchedules) ne previousPayrollScheduleList.navInfo.rowCount}">
					<table style="border: 1px solid; width: 100%; text-align: right;">
						<tr>
							<td colspan="5" align="right">
								<div align="right">
									<c:if test="${!previousPayrollScheduleList.navInfo.firstPage }">
										<a href="<c:url value="/company/viewPreviousPayrollScheduleList?page=0" />">First</a>&nbsp; | 
									</c:if>
									<c:forEach var="i" items="${previousPayrollScheduleList.navInfo.indexList}">
										<c:choose>
											<c:when test="${i != previousPayrollScheduleList.navInfo.currentPage}">
												<a href="<c:url value="/company/viewPreviousPayrollScheduleList?page=${i}" />">${i+1}</a>&nbsp; | 
										</c:when>
											<c:otherwise>
												<b>${i+1}</b>&nbsp; | 
										</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${!previousPayrollScheduleList.navInfo.lastPage}">
										<a
											href="<c:url value="/company/viewPreviousPayrollScheduleList?page=${previousPayrollScheduleList.navInfo.pageCount - 1}" />">Last</a>
									</c:if>
								</div>
							</td>
						</tr>
					</table>
				</c:if>
			</c:if>
		</div>
	</div>
</div>


