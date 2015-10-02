<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<body>
	<h3>Message : ${message}</h3>
	<h3>
		Username :
		<sec:authentication property="principal.username" />
	</h3>
</body>
</html>