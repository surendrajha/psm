<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<a href='<c:url value="/dashBoard"/>'>Dashboard</a>
<!-- display the link when user has not NONE permission on US_STATE section -->
<sec:authorize access="!hasRole('US_STATE-has-NONE')">
&nbsp;
<a href='<c:url value="/state/getStates"/>'>State</a>
</sec:authorize>
&nbsp;
<a href='<c:url value="/company/showInfoForm"/>'>Company</a>
&nbsp;
<a href='<c:url value="/employee/bank/getInfo"/>'>Employee Bank Info</a>
&nbsp;
<a href='<c:url value="/employee/departmentAllocation"/>'>Department Allocation</a>
&nbsp;
<a href='<c:url value="/company/showManagePermission"/>'>Manage Permission</a>
&nbsp;
<a href='<c:url value="/company/earningAndDeduction/showList"/>'>Company Earning And Deduction</a>
&nbsp;
<a href='<c:url value="/company/department/showList"/>'>Company Department</a>
&nbsp;
<a href='<c:url value="/company/showPayrollFrequencyList"/>'>Company Payroll Schedule jquery
	page</a>
&nbsp;
<a href='<c:url value="/company/showPayrollFrequencyNormalList"/>'>Company Payroll Schedule</a>
&nbsp;
<a href='<c:url value="/employee/showEmployeeSections"/>'>Manage Access</a>
&nbsp;
<a href='<c:url value="/payroll/showNormalPayroll"/>'>Normal Payroll</a>
&nbsp;
<a href='<c:url value="/state/showCityReportForm"/>'>Jasper Report</a>
&nbsp;
<a href='<c:url value="/company/showWorkersCompensationList"/>'>Company Workers Compensation</a>
&nbsp;
<sec:authorize access="isAnonymous()">
	<a href='<c:url value="/login" />'>Login</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href='<c:url value="/loggedout" />'>Logout</a>
</sec:authorize>
