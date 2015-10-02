<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!-- <h3>Pay Frequency Type</h3> -->
<!-- <hr> -->
<!-- <p> -->
<!-- 	Please fill the mandatory fields which are marked with "<font -->
<!-- 		color='red'>*</font>" whenever you enter any record. -->
<!-- </p> -->
<!-- <table id="flex1" style="display: none"></table> -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scalable Sensing Service - administration</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/jquery/jquery-ui-1.9.1.custom.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css"
	media="screen" />

<!-- <script type="text/javascript" -->
<!-- 	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="http://www.labs.mimmin.com/inlineedit/jquery.inlineEdit.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" /> -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/flexigrid-1.1/js/flexigrid.js">
	
</script>
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath}/resources/flexigrid-1.1/js/flexigrid.pack.js"></script> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/flexigrid-1.1/css/flexigrid.css" />
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="${pageContext.request.contextPath}/resources/flexigrid-1.1/css/flexigrid.pack.css" /> --%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#payCycleTable').flexigrid({
			url : "/PayrollSystemAdminConsole/admin/getPayCycles",
			dataType : 'json',
			colModel : [ {
				display : 'S. No.',
				name : 'id',
				width : 50,
				sortable : false,
				align : 'left'
			}, {
				display : 'Pay Cycle',
				name : 'type',
				width : 200,
				sortable : true,
				align : 'left'
			}, {
				display : 'No. Of Days',
				name : 'noOfDays',
				width : 100,
				sortable : true,
				align : 'left'
			}, {
				display : 'Description',
				name : 'description',
				width : 300,
				sortable : true,
				align : 'left'
			} ],

			buttons : [ {
				name : 'Add',
				bclass : 'add',
				onpress : addPayCycle
			}, {
				name : 'Edit',
				bclass : 'edit',
				onpress : editPayCycle
			}, {
				name : 'Delete',
				bclass : 'delete',
				onpress : deletePayCycle
			}, {
				separator : true
			} ],
			searchitems : [ {
				display : 'Pay Cycle',
				name : 'payCycle'
			} ],
			sortname : "payCycle",
			sortorder : "asc",
			usepager : true,
			singleSelect : true,
			title : 'List of Pay Cycles',
			useRp : true,
			rp : 15,
			showTableToggleBtn : true,
			width : 650,
			height : 300
		});
	});

	function addPayCycle() {
		alert("addPayCycle");
	}

	function editPayCycle() {
		alert("editPayCycle");
	}

	function deletePayCycle() {
		alert("deletePayCycle");
	}

	$(function() {
		// Use this example, or...
		//$('a[@rel*=lightbox]').lightBox(); // Select all links that contains lightbox in the attribute rel
		// This, or...
		//$('#gallery a').lightBox(); // Select all links in object with gallery ID
		// This, or...
		//$('a.lightbox').lightBox(); // Select all links with lightbox class
		// This, or...
		$('a').lightBox(); // Select all links in the page
		// ... The possibility are many. Use your creative or choose one in the examples above
		//$("#edit").lightbox();
	});
</script>
</head>
<body>
	<center>
		<table id="payCycleTable" style="display: none">
		</table>
		<a href="#">Edit</a>
	</center>
</body>
</html>