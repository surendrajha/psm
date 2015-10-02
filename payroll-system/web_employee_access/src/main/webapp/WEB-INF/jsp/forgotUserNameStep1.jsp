<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Forgot UserName</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
		<b>Enter Email and then click Next </b>
	</p>
	<hr>
	<form:form commandName="employeeAccessForgotUserIdStep1Form"
		action="${pageContext.request.contextPath}/showForgotUserIdStep2"
		method='POST'>

		<p>
			<label>Email</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='emailId' /> <form:errors path="emailId" class="validation-error"></form:errors>

		</p>
		
		<p>
			<input value="Next" type="submit" name="submit"> <input
				value="Clear" type="reset" name="reset">
		</p>
	</form:form>
</div>
<div class="clear"></div>
