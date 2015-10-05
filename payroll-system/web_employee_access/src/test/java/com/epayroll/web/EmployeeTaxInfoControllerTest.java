package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.employee.EmployeeService;
import com.epayroll.ui.contoller.employee.access.EmployeeTaxInfoController;

/**
 * @author Rajul Tiwari
 */

public class EmployeeTaxInfoControllerTest extends TestRoot {

	@Autowired
	private EmployeeTaxInfoController employeeTaxInfoController;

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void showList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showList");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter
				.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxListPage");
	}

}