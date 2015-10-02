package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.employee.EmployeePaymentCheckInfoService;
import com.epayroll.service.employee.EmployeeService;
import com.epayroll.ui.contoller.employee.EmployeePaymentCheckInfoController;

/**
 * @author Rajul Tiwari
 */

public class EmployeePaymentCheckInfoControllerTest extends TestRoot {

	@Autowired
	private EmployeePaymentCheckInfoController employeePaymentCheckInfoController;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeePaymentCheckInfoService employeePaymentCheckInfoService;

	// @Test
	public void showEmployeeList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showPaymentCheckInfoForm");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeePaymentCheckInfoController);
		ModelAndViewAssert.assertViewName(mav, "payCheckInfoPage");
	}

	// @Test
	public void showPaymentCheckInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showPaymentCheckInfo");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.getSession().setAttribute(
				"payDateList",
				employeePaymentCheckInfoService
						.getPayDates(employeeService.getEmployee(1L).getId()));
		// set parameters value of requested object
		request.setParameter("fromPayDate", "01/10/2013");
		request.setParameter("toPayDate", "01/15/2013");
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeePaymentCheckInfoController);
		ModelAndViewAssert.assertViewName(mav, "payCheckInfoPage");
	}

	@Test
	public void showPaymentDetails() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showPaymentDetails/1");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeePaymentCheckInfoController);
		ModelAndViewAssert.assertViewName(mav, "paymentDetailsPage");
	}
}