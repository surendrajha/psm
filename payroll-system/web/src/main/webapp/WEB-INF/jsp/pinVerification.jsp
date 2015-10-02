<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
	function cancelLogin(contextName) {
		window.location = contextName + "/logout";
	}
//-->
</script>

<div class="container_contents">
	<div class="post" id="post-244">
		<div class="entry">
			<h2 style="font-weight: normal; margin: 10px 0px 20px 20px;">
				<span style="color: #ca3b2b;">E Payroll Services</span>
			</h2>
			<div style="margin-bottom: 5px; display: block;">
				<p style="text-align: left;">
					<img class="size-full wp-image-252 alignleft" alt="mobile_phone-42"
						src="${pageContext.request.contextPath}/resources/styles/mobile_phone-42.gif" width="42"
						height="42"><span style="margin-top: 15px; display: block; color: #000000;">Anytime
						| Anywhere | Access</span>
				</p>
			</div>
			<div style="margin-bottom: 5px; display: block;">
				<p style="text-align: left;">
					<img class=" wp-image-253 alignleft" alt="dollar"
						src="${pageContext.request.contextPath}/resources/styles/dollar.png" width="40" height="50"><span
						style="margin-top: 15px; display: block; color: #000000;">Affordable &amp; Save money</span>
				</p>
			</div>
			<div style="margin-bottom: 5px; display: block;">
				<p>
					<img class=" wp-image-254 alignleft" alt="phone"
						src="${pageContext.request.contextPath}/resources/styles/phone.png" width="58" height="65"><span
						style="margin-top: 15px; display: block; color: #000000;">Exceptional Customer Service</span>
				</p>
			</div>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>
				<span style="color: #000000;">Call us now on 1-800-888-8888</span>
			</p>
			<div class="info"></div>
		</div>
	</div>
</div>
<div class="signupsection">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Pin Verification</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
		<b>Enter Pin to go to Dash Board</b>
	</p>
	<c:url value="/check_login" var="ac"></c:url>
	<form:form commandName="pinVerficationForm" action="${ac}" method="POST">
		<form:hidden path="username" />
		<form:hidden path="password" />
		<p>
			<label>Pin</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form:password path='pin' name='pin' value='' />
			<form:errors path="pin" class="validation-error"></form:errors>
			<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="${pageContext.request.contextPath}/showForgotPinStep1">Forgot Pin?</a>
		</p>

		<p>
			<input value="Verify" type="submit" name="submit"> <input value="Cancel" type="button"
				name="cancel" onclick="cancelLogin('${pageContext.request.contextPath}');">
		</p>
	</form:form>
</div>
<div class="clear"></div>
