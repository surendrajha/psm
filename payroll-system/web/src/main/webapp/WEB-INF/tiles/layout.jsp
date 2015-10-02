<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="PRAGMA" content="NO-CACHE" />
<meta http-equiv="CACHE-CONTROL" content="NO-CACHE" />
<meta http-equiv="EXPIRES" content="0" />
<meta name="author" content="Itech Software Solutions">
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<table style="width: 80%;" border="1">
		<tr>
			<td><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="menu" /></td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="messagebar" /></td>
		</tr>
		<tr style="height: 600px;">
			<td valign="top"><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="bottom" /></td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>
</body>
</html>
