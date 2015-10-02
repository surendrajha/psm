<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Forgot Password</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;"><b>Enter UserName and then click Next </b></p>
	
	<form:form commandName="employeeAccessForgotPasswordStep1Form"
		action="${pageContext.request.contextPath}/showForgotPasswordStep2" method='POST'>
		
		<p>
			<label>UserName</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:input path='userId' name='userId' value='' /> <form:errors path="userId" class="validation-error"></form:errors>
							
			
		</p>

		<p>
			<input value="Next" type="submit" name="submit"> <input value="Clear" type="reset" name="reset">
		</p>
	</form:form>
</div>
<div class="clear"></div>
