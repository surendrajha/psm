<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2>Download Report Using Jasper</h2>
	<c:url value="/state/cityReport?type=pdf" var="pdf" />
	<c:url value="/state/cityReport?type=xls" var="xls" />
	<c:url value="/state/cityReport?type=html" var="html" /> 
	
	<p><a href="${pdf}">PDF</a></p>
	<p><a href="${xls}">EXCEL</a></p>
	<p><a href="${html}">HTML</a></p>
</body>
</html>