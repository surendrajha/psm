package com.epayroll.web;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.payroll.PayrollController;

public class RunPayrollControllerTest extends TestRoot {

	@Autowired
	private PayrollController controller;

	@Autowired
	private CompanyService companyService;

	@Ignore
	@Test
	public void showNormalPayroll() throws Exception {

		request.setMethod("POST");
		request.setRequestURI("/showNormalPayroll");
		ModelAndView mav = handlerAdapter.handle(request, response, controller);
		ModelAndViewAssert.assertViewName(mav, "runPayroll");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "pfList");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	@Ignore
	@Test
	public void showPayrollData() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showPayrollData");
		request.setParameter("payrollFrequencyId", "1");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);
		ModelAndViewAssert.assertViewName(mav, "runPayroll");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyEarningList");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "payRollForm");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Ignore
	// @Test
	// public void testShowEmployeePayrollData() throws Exception {
	// logger.debug("getting EMployee Data......");
	//
	// request.getSession().setAttribute("loggedInUser",
	// companyService.getCompanyUser(1L));
	// request.getSession().setAttribute("payFrequencyId", 1L);
	// request.setMethod("POST");
	// request.setRequestURI("/payroll/emppayrolldata");
	// ModelAndView mav = handlerAdapter.handle(request, response, controller);
	// ModelAndViewAssert.assertViewName(mav, "RunPayrollForm");
	// System.out.println(mav.getModel().get("empList"));
	// List<Employee> ls = (List<Employee>) mav.getModel().get("empList");
	// System.out.println(ls.get(0).getEmployeeEarnings());
	//
	// System.out.println(request.getSession().getAttribute("payFrequencyId"));
	// }

	// @Test
	// @SuppressWarnings("unchecked")
	// public void testShowEmployeePayrollData() throws Exception {
	//
	// request.getSession().setAttribute("loggedInUser",
	// companyService.getCompanyUser(1L));
	// request.setAttribute("pftId", 1L);
	// request.setMethod("POST");
	// request.setRequestURI("/emppayrolldata");
	// ModelAndView mav = handlerAdapter.handle(request, response, controller);
	// ModelAndViewAssert.assertViewName(mav, "RunPayrollForm");
	// logger.debug("Printing Payroll Dates>>>>>>>>>>>");
	// System.out.println(mav.getModel().get("payrollDates"));
	//
	// logger.debug("getting Employee Data......");
	// System.out.println(mav.getModel().get("empList"));
	// List<Employee> ls = (List<Employee>) mav.getModel().get("empList");
	// System.out.println(ls.get(0).getEmployeeEarnings());
	//
	// System.out.println(request.getSession().getAttribute("payFrequencyId"));
	// }

	@Ignore
	@Test
	public void getTaxesAndDeductions() throws Exception {

		request.setMethod("GET");
		request.setRequestURI("/getTaxesAndDeductions/64");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);

		ModelAndViewAssert.assertViewName(mav, "overrideTaxDeduction");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "taxOverrideOptions");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "deductionTypes");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("\nKey = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}

	// @Ignore
	@Test
	public void calculatePayroll() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/getPreview");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("\nKey = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}

}
