<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function getReplyForm(url, toPerson, subject) {
	document.getElementById("messageCenterForm").action = url;
	document.getElementById("toPerson").value = toPerson;
	document.getElementById("subject").value = subject;
	document.getElementById("messageCenterForm").submit();
}
</script>
</head>
<body>
	<h1>Message Center</h1>
	<div>
		<a href='<c:url value="/employee/access/showSendMessageForm"/>'>Compose</a><br />&nbsp;&nbsp;<a
			href='<c:url value="/employee/access/getInboxMessage"/>'>Inbox</a><br />&nbsp;&nbsp;<a
			href='<c:url value="/employee/access/getSentMessage"/>'>Sent</a><br />
	</div>
	<c:if test="${not empty inboxList}">
		<div>
			<p>From: &nbsp;&nbsp;&nbsp;&nbsp; Subject: &nbsp;&nbsp;&nbsp;&nbsp; Received: &nbsp;&nbsp;</p>
			<c:forEach items="${inboxList}" var="msg">
				<p>${msg.fromPerson.firstName} ${msg.fromPerson.lastName} &nbsp;&nbsp;&nbsp;<a href='<c:url value="/employee/access/getMessageDetail/${msg.id}/INBOX"/>'>${msg.subject}</a> &nbsp;&nbsp;&nbsp;
					${msg.receivedAt}</p>
			</c:forEach>
		</div>
	</c:if>
	<c:url value="/employee/access/sendMessage" var="actionUrl" />
		<form:form action="${actionUrl}" method="post" modelAttribute="messageCenterForm">

<c:if test="${empty personList}">
	<form:hidden path="toPerson"/>
	<form:hidden path="subject"/>
	</c:if>
	<c:if test="${not empty personList}">
			<div>
			
				<p>
					TO ::
					<form:select path="toPersionId" items="${personList}" itemLabel="name" itemValue="persionId" />
				</p>
					
				<p>
					Subject ::
					<form:input path="subject" size="50" />
				</p>
				<p>
					MESSAGE ::
					<form:textarea path="messageText" rows="10" />
				</p>
			</div>
			<button type="submit">Submit</button>
</c:if>
	</form:form>
	<c:if test="${not empty sentList}">
		<div>
			<p>TO: &nbsp;&nbsp;&nbsp;&nbsp; Subject: &nbsp;&nbsp;&nbsp;&nbsp; Sent On: &nbsp;&nbsp;</p>
			<c:forEach items="${sentList}" var="msg">
				<p>${msg.toPerson.firstName} ${msg.toPerson.lastName} &nbsp;&nbsp;&nbsp; <a href='<c:url value="/employee/access/getMessageDetail/${msg.personMessage.id}/OUTBOX"/>'> ${msg.personMessage.subject}</a>
					&nbsp;&nbsp;&nbsp; ${msg.personMessage.receivedAt}</p>
			</c:forEach>
		</div>
	</c:if>
	
	<c:if test="${not empty messageDetail}">
	<div style="width:300px;border:2px solid blue;">
	<p>From: <b>${messageDetail.fromPerson.firstName}&nbsp; ${messageDetail.fromPerson.lastName}&nbsp;[${messageDetail.fromPerson.email}] </b> </p>
	<p>Subject: <b>${messageDetail.subject}</b> </p>
	<p>Sent : <b>${messageDetail.receivedAt}</b> </p>
	<p>Message :</p>
	<div style="width:300px;border:1px solid blue;">
	<p>&nbsp;&nbsp;&nbsp;${messageDetail.message}</p>
	</div>
	<button type="button" onclick="getReplyForm('${pageContext.request.contextPath}/employee/access/replyTo', '${messageDetail.fromPerson.id}','RE:${messageDetail.subject}')">Reply</button>
	
	</div>
	</c:if>
	
</body>
</html>