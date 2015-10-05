<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Reset Pin</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
		<b>Enter Your New Pin and Confirm Pin then click Submit</b>
	</p>
<hr>
	<form:form commandName="resetPinForm"
		action="${pageContext.request.contextPath}/resetPin"
		method='POST'>

		<p>
			<label>New Pin</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='newPin' name='newPin' value='' /> <form:errors path="newPin" class="validation-error"></form:errors>

		</p>
		<p>
			<label>Confirm Pin</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='confirmPin' name='confirmPin' value='' /> <form:errors path="confirmPin" class="validation-error"></form:errors>
		</p>
		<p>
			<input value="Submit" type="submit" name="submit"> <input
				value="Clear" type="reset" name="reset">
		</p>
	</form:form>
</div>
<div class="clear"></div>
