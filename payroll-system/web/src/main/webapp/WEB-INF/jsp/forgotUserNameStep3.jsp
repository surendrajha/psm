<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
function resendEmail(contextName) {
	window.location=contextName+"/resendEmailForUserId";
}
function login(contextName) {
	window.location=contextName+"/login";
}

//-->
</script>

<div class="signupsection1">
	<p style="margin: 0px 0px 5px 0px; text-align: center;">
	<h4>Forgot UserName</h4>
	</p>
	<hr>
	<br>
	<p> ${result}</p>
	<br><br>
	<button onclick="resendEmail('${pageContext.request.contextPath}' );">Resend Email</button>
	<button onclick="login('${pageContext.request.contextPath}' );">Login</button> 
</div>
<div class="clear"></div>

