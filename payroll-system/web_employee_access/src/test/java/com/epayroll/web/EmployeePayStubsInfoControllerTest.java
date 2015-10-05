package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.employee.EmployeeService;
import com.epayroll.ui.contoller.employee.access.EmployeePayStubsInfoController;

/**
 * @author Rajul Tiwari
 */

public class EmployeePayStubsInfoControllerTest extends TestRoot {

	@Autowired
	private EmployeePayStubsInfoController employeePayStubsInfoController;

	@Autowired
	private EmployeeService employeeService;

	@Ignore
	@Test
	public void showPayStubsForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showPayStubsForm");
		request.getSession().setAttribute("employee", employeeService.getEmployee(5L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeePayStubsInfoController);
		ModelAndViewAssert.assertViewName(mav, "payStub");
	}

	@Ignore
	@Test
	public void getCheckDateList() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/getCheckDateList");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("year", "2013");
		ModelAndView mav = handlerAdapter.handle(request, response, employeePayStubsInfoController);
		ModelAndViewAssert.assertViewName(mav, "payStub");
	}

	@Ignore
	@Test
	public void showPayStubsDetail() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showPayStubsDetail");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("year", "2013");
		request.setParameter("checkDate", "01/10/2013");
		request.setParameter("startDate", "01/19/2013");
		request.setParameter("endDate", "01/29/2013");
		request.setParameter("payrollId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeePayStubsInfoController);
		ModelAndViewAssert.assertViewName(mav, "payStub");
	}

}