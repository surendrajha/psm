<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container_contents">

	<div class="post" id="post-244">
		<div class="entry">
			<h2 style="font-weight: normal; margin: 10px 0px 20px 20px;">
				<span style="color: #ca3b2b;">E Payroll Services</span>
			</h2>
			<div style="margin-bottom: 5px; display: block;">
				<p style="text-align: left;">
					<img class="size-full wp-image-252 alignleft" alt="mobile_phone-42"
						src="${pageContext.request.contextPath}/resources/styles/mobile_phone-42.gif"
						width="42" height="42"><span
						style="margin-top: 15px; display: block; color: #000000;">Anytime
						| Anywhere | Access</span>
				</p>
			</div>
			<div style="margin-bottom: 5px; display: block;">
				<p style="text-align: left;">
					<img class=" wp-image-253 alignleft" alt="dollar"
						src="${pageContext.request.contextPath}/resources/styles/dollar.png"
						width="40" height="50"><span
						style="margin-top: 15px; display: block; color: #000000;">Affordable
						&amp; Save money</span>
				</p>
			</div>
			<div style="margin-bottom: 5px; display: block;">
				<p>
					<img class=" wp-image-254 alignleft" alt="phone"
						src="${pageContext.request.contextPath}/resources/styles/phone.png"
						width="58" height="65"><span
						style="margin-top: 15px; display: block; color: #000000;">Exceptional
						Customer Service</span>
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
<c:if test="${not empty error}">
		<div class="errorblock" style="color: red">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<p style="margin: 0px 0px 5px 0px; text-align: left;">
	<h4>Employer Login</h4>
	</p>
	<p style="margin: 0px 0px 5px 0px; text-align: left;"><b>Sign In to
		Employer Login</b></p>
	<br />
	<form name="f" action="<c:url value='/check_login' />" method='POST'>
		<p>
			<label>Username</label>&nbsp;<input type='text' name='username'><br><a href="${pageContext.request.contextPath}/showForgotUserIdStep1">Forgot Username?</a>
		</p>
		<br />
		<p>
			<label>Password</label>&nbsp;<input type='password' name='password'><br><a href="${pageContext.request.contextPath}/showForgotPasswordStep1">Forgot Password?</a>
		</p>
		<p>
			<input value="Sign In" type="submit" name="submit"> <input value="Clear" type="reset" name="reset">
		</p>
	</form>
</div>
<div class="clear"></div>
