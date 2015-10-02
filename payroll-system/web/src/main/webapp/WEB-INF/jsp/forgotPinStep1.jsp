<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Forgot Pin</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;"><b>Enter UserName and then click Next </b></p>
	
	<form:form commandName="forgotPasswordAndPinStep1Form"
		action="${pageContext.request.contextPath}/showForgotPinStep2" method='POST'>
		
		<p>
			<label>UserName</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='userName' name='userName' value='' /> <form:errors path="userName" class="validation-error"></form:errors>
			
		</p>

		<p>
			<input value="Next" type="submit" name="submit"> <input value="Clear" type="reset" name="reset">
		</p>
	</form:form>
</div>
<div class="clear"></div>
