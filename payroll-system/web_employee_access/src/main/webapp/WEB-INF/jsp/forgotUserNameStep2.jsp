<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="signupsection1">


	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Forgot UserName</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
		<b>Enter Your Personal Details and then click Next </b>
	</p>
<hr>
${result}
	<form:form commandName="employeeAccessForgotUserIdStep2Form"
		action="${pageContext.request.contextPath}/showForgotUserIdStep3"
		method='POST'>
		<p>
			<label>Security Question 1</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='securityQuestion1' name='securityQuestion1'
				disabled="true"/> <form:errors path="securityQuestion1" class="validation-error" ></form:errors>

		</p>
		<p>
			<label>Security Answer 1</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='securityAnswer1' name='securityAnswer1' value='' /> <form:errors path="securityAnswer1" class="validation-error"></form:errors>

		</p>
		<p>
			<input value="Next" type="submit" name="submit"> <input
				value="Clear" type="reset" name="reset">
		</p>
	</form:form>
</div>
<div class="clear"></div>
