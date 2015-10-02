<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<a href='<c:url value="/dashBoard"/>'>Dashboard</a>&nbsp;
<a href='<c:url value="/admin/showRoleTypes"/>'>Manage Role Type Sections</a>&nbsp;
<a href='<c:url value="/admin/showManagePermission"/>'>Manage Role Permissions</a>&nbsp;
<a href='<c:url value="/admin/showPayCycleForm"/>'>Manage Pay Cycle</a>&nbsp;
<a href='<c:url value="#"/>'></a>&nbsp;
<a href='<c:url value="#"/>'></a>&nbsp;
<sec:authorize access="isAnonymous()">
<a href='<c:url value="/login" />'>Login</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<a href='<c:url value="/loggedout" />'>Logout</a>
</sec:authorize>
