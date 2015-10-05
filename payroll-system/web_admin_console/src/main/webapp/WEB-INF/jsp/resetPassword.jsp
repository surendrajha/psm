<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Reset Password</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
		<b>Enter Your New Password and Confirm Password then click Submit</b>
	</p>
<hr>
	<form:form commandName="resetPasswordForm"
		action="${pageContext.request.contextPath}/resetPassword"
		method='POST'>

		<p>
			<label>New Password</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='newPassword' name='newPassword' value='' /> <form:errors path="newPassword" class="validation-error"></form:errors>

		</p>
		<p>
			<label>Confirm Password</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='confirmPassword' name='confirmPassword' value='' /> <form:errors path="confirmPassword" class="validation-error"></form:errors>

		</p>
		<p>
			<input value="Submit" type="submit" name="submit"> <input
				value="Clear" type="reset" name="reset">
		</p>
	</form:form>
</div>
<div class="clear"></div>
