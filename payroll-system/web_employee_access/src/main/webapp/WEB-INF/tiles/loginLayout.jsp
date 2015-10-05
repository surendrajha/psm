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
<!-- <html xmlns="http://www.w3.org/1999/xhtml" lang="en-US"> -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e payroll services » Employer Login</title>
<meta name="generator" content="WordPress 3.5.1">
<!-- leave this for stats -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/style.css"
	type="text/css" media="screen">
<link rel="alternate" type="application/rss+xml"
	title="e payroll services RSS Feed"
	href="http://payroll.sweans.org/feed/">
<link href="${pageContext.request.contextPath}/resources/styles/css.css" rel="stylesheet" type="text/css">
<link rel="pingback" href="http://payroll.sweans.org/xmlrpc.php">
<style type="text/css">
</style>
</head>
<body onload='document.f.username.focus();'>
	<div id="wrapper_login">
		<div id="site_top">
			<div id="logo">
				<h1>
					<a href="http://payroll.sweans.org/"><img
						src="${pageContext.request.contextPath}/resources/styles/VORGO_logo.png"></a>
				</h1>
				<div id="site_top_ryt">
					<p>New to Epayroll?</p>
					<a href="#"><span id="red_login">Create An Account</span></a>
				</div>
			</div>
		</div>

		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>

	</div>
	<div style="width: 1000px; margin: 0 auto; margin-top: 10px;">
		<a href="">Legal Terms </a>| <a href="#">Help</a> | © EPayroll
	</div>
</body>
<!-- </html> -->