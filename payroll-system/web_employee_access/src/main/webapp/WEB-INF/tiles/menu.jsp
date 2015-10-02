<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<a href='<c:url value="/welcome"/>'>Welcome</a>&nbsp;
<a href='<c:url value="/employeeAccess/messageCenter/getInboxMessage"/>'>Message Center</a>&nbsp;
<a href='<c:url value="/employeeAccess/payStub/showPayStubsForm"/>'>View/Download Pay Stubs</a>&nbsp;
<a href='<c:url value="#"/>'></a>&nbsp;
<a href='<c:url value="#"/>'></a>&nbsp;
<a href='<c:url value="#"/>'></a>&nbsp;
<a href='<c:url value="#"/>'></a>&nbsp;
<sec:authorize access="isAnonymous()">
<a href='<c:url value="/login" />'>Login</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<a href='<c:url value="/loggedout" />'>Logout</a>
</sec:authorize>
