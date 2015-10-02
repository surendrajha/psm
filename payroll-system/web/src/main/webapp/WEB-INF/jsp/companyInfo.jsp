<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.css" />

<script type="text/javascript">
	//var jq = jQuery.noConflict();
	function getCountyAndCityList(stateId, idName, contextName) {
		$
				.post(
						contextName + "/common/getCounty",
						{
							usStateId : stateId
						},
						function(serverResponse) {
							var countyId = idName + "CountyId";
							if (serverResponse.status == 'success') {
								dataString = "<select id=" + countyId + " name=" + countyId + " class='"+idName+"'><option value=''></option>";
								for ( var i = 0; i < serverResponse.data.length; i++) {
									dataString += "<option value='" + serverResponse.data[i].id + "'>"
											+ serverResponse.data[i].countyName + "</option>";
								}
								dataString += "</select>";
								$("#" + countyId).replaceWith(dataString);
							} else {
								alert("Some error occurred : " + serverResponse.message);
							}
							getCityList(stateId, idName, contextName);
						});
	}

	function getCityList(stateId, idName, contextName) {
		$
				.post(
						contextName + "/common/getCity",
						{
							usStateId : stateId
						},
						function(serverResponse) {
							if (serverResponse.status == 'success') {
								id = idName + "CityId";
								dataString = "<select id=" + id + " name=" + id + " class='"+idName+"'><option value=''></option>";
								for ( var i = 0; i < serverResponse.data.length; i++) {
									dataString += "<option value='" + serverResponse.data[i].id + "'>"
											+ serverResponse.data[i].cityName + "</option>";
								}
								dataString += "</select>";
								id = "#" + idName + "CityId";
								$(id).replaceWith(dataString);
							} else {
								alert("Some error occurred : " + serverResponse.message);
							}
						});
	}

	function collectFormData(fields) {
		var data = {};
		for ( var i = 0; i < fields.length; i++) {
			var $item = $(fields[i]);
			data[$item.attr('name')] = $item.val();
		}
		return data;
	}

	function saveInfo(className, formName, url) {
		var $form = $('#' + formName);
		var $inputs = $form.find('.' + className);
		var data = collectFormData($inputs);

		$.post(url, data, function(serverResponse) {
			var divs = $form.find('div');
			for ( var i = 0; i < divs.length; i++) {
				$(divs[i]).html('');
			}

			var messageId = "#" + className + "Message";
			if (serverResponse.status == 'success') {
				$("#companyId").val(serverResponse.data);
				$(messageId).html(serverResponse.message)
			} else {
				$(messageId).html(serverResponse.message)
				for ( var i = 0; i < serverResponse.errorMessages.length; i++) {
					var item = serverResponse.errorMessages[i];
					$form.find('#' + item.fieldName + "Error").html(item.message);
				}
			}
		});
	}
</script>
<div id="page">
	<div id="content">
		<div class="box">
			<h3>Company Info</h3>
			<hr>
			<p>
				Please fill the mandatory fields which are marked with "<font color='red'>*</font>" whenever you
				enter any record.
			</p>
			<form:form action="${pageContext.request.contextPath}/company/addUpdateLegalInfo"
				commandName="legalInfoForm">
				<form:hidden path="companyId" />
				<table>
					<tr>
						<td colspan="4"><h4>Legal Information</h4>
							<hr /> <br />
							<div id="legalMessage"></div></td>
					</tr>

					<tr>
						<td colspan="4">Name<br> <form:input path="legalName" cssClass="legal" /><br />
							<div id="legalNameError">
								<form:errors path="legalName"></form:errors>
							</div></td>
					</tr>

					<tr>
						<td colspan="4">Address (500 Characters Max.)<br> <form:textarea
								path="legalStreetAddress" cssClass="legal" /></td>
					</tr>
					<tr>
						<td>State <br> <form:select path="legalStateId" cssClass="legal"
								onchange="getCountyAndCityList(this.value, 'legal', '${pageContext.request.contextPath}')">
								<form:option value="" label="" />
								<form:options items="${states}" itemLabel="stateName" itemValue="id" />
							</form:select><br />
							<div id="legalStateIdError">
								<form:errors path="legalStateId"></form:errors>
							</div>
						</td>
						<td>County <br> <form:select path="legalCountyId" cssClass="legal">
								<form:option value="" label="" />
								<form:options items="${counties}" itemLabel="countyName" itemValue="id" />
							</form:select><br />
							<div id="legalCountyIdError">
								<form:errors path="legalCountyId"></form:errors>
							</div>
						</td>
						<td>City <br> <form:select path="legalCityId" cssClass="legal">
								<form:option value="" label="" />
								<form:options items="${cities}" itemLabel="cityName" itemValue="id" />
							</form:select> <br />
							<div id="legalCityIdError">
								<form:errors path="legalCityId"></form:errors>
							</div></td>

						<td>Zip Code <br> <form:input path="legalPinZip" cssClass="legal" /><br />
							<div id="legalPinZipError">
								<form:errors path="legalPinZip"></form:errors>
							</div></td>
					</tr>

					<tr>
						<td><button type="button"
								onclick="saveInfo('legal', 'legalInfoForm', '${pageContext.request.contextPath}/company/addUpdateLegalInfo')">Save</button></td>
					</tr>
				</table>
			</form:form>


			<form:form action="${pageContext.request.contextPath}/company/addOrUpdateBusinessInfo"
				commandName="businessInfoForm">
				<table>
					<tr>
						<td colspan="4"><h4>Business Information</h4>
							<hr /> <br />
							<div id="businessMessage"></div></td>
					</tr>

					<tr>
						<td>Name<br> <form:input path="businessName" cssClass="business" />
							<div id="businessNameError">
								<form:errors path="businessName"></form:errors>
							</div></td>
						<td>Phone<br> <form:input path="businessPhone" cssClass="business" />
							<div id="businessPhoneError">
								<form:errors path="businessPhone"></form:errors>
							</div></td>
						<td>Fax<br> <form:input path="businessFax" cssClass="business" />
							<div id="businessFaxError">
								<form:errors path="businessFax"></form:errors>
							</div></td>
						<td>Website<br> <form:input path="webAddress" cssClass="business" />
							<div id="webAddressError">
								<form:errors path="webAddress"></form:errors>
							</div></td>
					</tr>

					<tr>
						<td colspan="4">Address (500 Characters Max.)<br> <form:textarea
								path="businessStreetAddress" /></td>
					</tr>
					<tr>
						<td>State <br> <form:select path="businessStateId" cssClass="business"
								onchange="getCountyAndCityList(this.value, 'business', '${pageContext.request.contextPath}')">
								<form:option value="" label="" />
								<form:options items="${states}" itemLabel="stateName" itemValue="id" />
							</form:select>
							<div id="businessStateIdError">
								<form:errors path="businessStateId"></form:errors>
							</div></td>
						<td>County <br> <form:select path="businessCountyId" cssClass="business">
								<form:option value="" label="" />
								<form:options items="${counties}" itemLabel="countyName" itemValue="id" />
							</form:select>
							<div id="businessCountyIdError">
								<form:errors path="businessCountyId"></form:errors>
							</div></td>
						<td>City <br> <form:select path="businessCityId" cssClass="business">
								<form:option value="" label="" />
								<form:options items="${cities}" itemLabel="cityName" itemValue="id" />
							</form:select>
							<div id="businessCityIdError">
								<form:errors path="businessCityId"></form:errors>
							</div></td>

						<td>Zip Code <br> <form:input path="businessPinZip" />
							<div id="businessPinZipError">
								<form:errors path="businessPinZip"></form:errors>
							</div></td>
					</tr>

					<tr>
						<td><button type="button"
								onclick="saveInfo('business', 'businessInfoForm', '${pageContext.request.contextPath}/company/addOrUpdateBusinessInfo')">Save</button></td>
					</tr>
				</table>
			</form:form>



			<form:form action="${pageContext.request.contextPath}/company/addOrUpdateShippingInfo"
				commandName="shippingInfoForm">
				<%-- 				<form:hidden path="actionUrl" /> --%>
				<table>
					<tr>
						<td colspan="4"><h4>Shipping Information</h4>
							<hr /><br />
							<div id="shippingMessage"></div></td>
					</tr>
					<tr>
						<td colspan="4">Address (500 Characters Max.)<br> <form:textarea
								path="shippingStreetAddress"  cssClass="shipping" /><div id="shippingStreetAddressError">
								<form:errors path="shippingStreetAddress"></form:errors>
							</div></td>
					</tr>
					<tr>
						<td>State <br> <form:select path="shippingStateId" cssClass="shipping" 
								onchange="getCountyAndCityList(this.value, 'shipping','${pageContext.request.contextPath}')">
								<form:option value="" label="" />
								<form:options items="${states}" itemLabel="stateName" itemValue="id" />
							</form:select><div id="shippingStateIdError">
								<form:errors path="shippingStateId"></form:errors>
							</div></td>
						<td>County <br> <form:select path="shippingCountyId" cssClass="shipping" >
								<form:option value="" label="" />
								<form:options items="${counties}" itemLabel="countyName" itemValue="id" />
							</form:select><div id="shippingCountyIdError">
								<form:errors path="shippingCountyId"></form:errors>
							</div></td>
						<td>City <br> <form:select path="shippingCityId" cssClass="shipping" >
								<form:option value="" label="" />
								<form:options items="${cities}" itemLabel="cityName" itemValue="id" />
							</form:select><div id="shippingCityIdError">
								<form:errors path="shippingCityId"></form:errors>
							</div></td>

						<td>Zip Code <br> <form:input path="shippingPinZip" cssClass="shipping" /><div id="shippingPinZipError">
								<form:errors path="shippingPinZip"></form:errors>
							</div></td>
					</tr>

					<tr>
					<td><button type="button"
								onclick="saveInfo('shipping', 'shippingInfoForm', '${pageContext.request.contextPath}/company/addOrUpdateShippingInfo')">Save</button></td>
					</tr>
				</table>
			</form:form>
			<form:form action="${pageContext.request.contextPath}/company/addOrUpdateAuthorizedSignatoryContact"
				commandName="authorizedSignatoryContactForm">
				<%-- 				<form:hidden path="actionUrl" /> --%>
				<table>
					<tr>
						<td colspan="4"><h4>Authorized Signatory Contact</h4>
							<hr /><br />
							<div id="authorizedSignatoryContactMessage"></div></td>
					</tr>
					<tr>
						<td>First Name<br> <form:input path="firstName" cssClass="authorizedSignatoryContact" />
							<div id="firstNameError">
								<form:errors path="firstName"></form:errors>
							</div></td>
						<td>Last Name<br> <form:input path="lastName" cssClass="authorizedSignatoryContact" />
							<div id="lastNameError">
								<form:errors path="lastName"></form:errors>
							</div></td>
						<td>Designation<br> <form:input path="designation" cssClass="authorizedSignatoryContact" />
							<div id="designationError">
								<form:errors path="designation"></form:errors>
							</div></td>
						<td>Email<br> <form:input path="email" cssClass="authorizedSignatoryContact" />
							<div id="emailError">
								<form:errors path="email"></form:errors>
							</div></td>
					</tr>
					<tr>
						<td>Phone<br> <form:input path="phone" cssClass="authorizedSignatoryContact" />
							<div id="phoneError">
								<form:errors path="phone"></form:errors>
							</div></td>
						<td>Ext<br> <form:input path="ext" cssClass="authorizedSignatoryContact" />
							<div id="extError">
								<form:errors path="ext"></form:errors>
							</div></td>
						<td>Fax<br> <form:input path="fax" cssClass="authorizedSignatoryContact" />
							<div id="faxError">
								<form:errors path="fax"></form:errors>
							</div></td>
					</tr>

					<tr>
					<td><button type="button"
								onclick="saveInfo('authorizedSignatoryContact', 'authorizedSignatoryContactForm', '${pageContext.request.contextPath}/company/addOrUpdateAuthorizedSignatoryContact')">Save</button></td>
					</tr>
				</table>
			</form:form>
<form:form action="${pageContext.request.contextPath}/company/addOrUpdatePrimaryContact"
				commandName="primaryContactForm">
				<%-- 				<form:hidden path="actionUrl" /> --%>
				<table>
					<tr>
						<td colspan="4"><h4>Primary Contact</h4>
							<hr /><br />
							<div id="primaryContactMessage"></div></td>
					</tr>
					<tr>
						<td>First Name<br> <form:input path="firstName" cssClass="primaryContact" />
							<div id="firstNameError">
								<form:errors path="firstName"></form:errors>
							</div></td>
						<td>Last Name<br> <form:input path="lastName" cssClass="primaryContact" />
							<div id="lastNameError">
								<form:errors path="lastName"></form:errors>
							</div></td>
						<td>Designation<br> <form:input path="designation" cssClass="primaryContact" />
							<div id="designationError">
								<form:errors path="designation"></form:errors>
							</div></td>
						<td>Email<br> <form:input path="email" cssClass="primaryContact" />
							<div id="emailError">
								<form:errors path="email"></form:errors>
							</div></td>
					</tr>
					<tr>
						<td>Phone<br> <form:input path="phone" cssClass="primaryContact" />
							<div id="phoneError">
								<form:errors path="phone"></form:errors>
							</div></td>
						<td>Ext<br> <form:input path="ext" cssClass="primaryContact" />
							<div id="extError">
								<form:errors path="ext"></form:errors>
							</div></td>
						<td>Fax<br> <form:input path="fax" cssClass="primaryContact" />
							<div id="faxError">
								<form:errors path="fax"></form:errors>
							</div></td>
					</tr>

					<tr>
					<td><button type="button"
								onclick="saveInfo('primaryContact', 'primaryContactForm', '${pageContext.request.contextPath}/company/addOrUpdatePrimaryContact')">Save</button></td>
					</tr>
				</table>
			</form:form>
			<form:form action="${pageContext.request.contextPath}/company/addOrUpdateBillingContact"
				commandName="billingContactForm">
				<%-- 				<form:hidden path="actionUrl" /> --%>
				<table>
					<tr>
						<td colspan="4"><h4>Billing Contact</h4>
							<hr /><br />
							<div id="billingContactMessage"></div></td>
					</tr>
					<tr>
						<td>First Name<br> <form:input path="firstName" cssClass="billingContact" />
							<div id="firstNameError">
								<form:errors path="firstName"></form:errors>
							</div></td>
						<td>Last Name<br> <form:input path="lastName" cssClass="billingContact" />
							<div id="lastNameError">
								<form:errors path="lastName"></form:errors>
							</div></td>
						<td>Fax<br> <form:input path="fax" cssClass="billingContact" />
							<div id="faxError">
								<form:errors path="fax"></form:errors>
							</div></td>
						<td>Email<br> <form:input path="email" cssClass="billingContact" />
							<div id="emailError">
								<form:errors path="email"></form:errors>
							</div></td>
					</tr>
					<tr>
						<td>Phone<br> <form:input path="phone" cssClass="billingContact" />
							<div id="phoneError">
								<form:errors path="phone"></form:errors>
							</div></td>
						<td>Ext<br> <form:input path="ext" cssClass="billingContact" />
							<div id="extError">
								<form:errors path="ext"></form:errors>
							</div></td>
					</tr>

					<tr>
					<td><button type="button"
								onclick="saveInfo('billingContact', 'billingContactForm', '${pageContext.request.contextPath}/company/addOrUpdateBillingContact')">Save</button></td>
					</tr>
				</table>
			</form:form>
		</div>

		<br class="clearfix" />
	</div>

	<br class="clearfix" />

</div>
