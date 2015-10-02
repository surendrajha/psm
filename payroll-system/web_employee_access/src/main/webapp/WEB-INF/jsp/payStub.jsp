<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h3>View/Download Pay Stubs</h3>
	<div>This page will display the Employee€™'s Pay Stub links of
		current year in chronological order. Once employee clicks on any link
		then â€œPay Stub Details€ will be displayed just below the link as shown
		below. If the employee wishes to see the pay stubs for the previous
		year then he can select the year and click GO€ and it will display
		the pay stub for the selected year</div>
	</p>
	<hr>
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
		<b>Select Year To view the Pay Stub</b>
	</p>
	<form:form commandName="payStubsForm"
		action="${pageContext.request.contextPath}/getCheckDateList"
		method='POST'>
		<div>
			<table id='main_table'>
				<tr>
					<td><form:select path="year">
								<form:option value="" label="" />
								<form:options items="${yearList}" />
							</form:select> <br />
							<div id="yearError">
								<form:errors path="year"></form:errors>
							</div>
						</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
		</div>

		<p>
<!-- 			<label>New Password</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<%-- 			<form:input path='newPassword' name='newPassword' value='' /> --%>
<%-- 			<form:errors path="newPassword" class="validation-error"></form:errors> --%>

		</p>
		<p>
<!-- 			<label>Confirm Password</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<%-- 			<form:input path='confirmPassword' name='confirmPassword' value='' /> --%>
<%-- 			<form:errors path="confirmPassword" class="validation-error"></form:errors> --%>

		</p>
		<p>
			<input value="Submit" type="submit" name="submit"> <input
				value="Clear" type="reset" name="reset">
		</p>
	</form:form>
</div>
<div class="clear"></div>
