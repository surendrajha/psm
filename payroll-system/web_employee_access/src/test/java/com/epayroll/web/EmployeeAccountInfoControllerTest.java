package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.employee.EmployeeService;
import com.epayroll.ui.contoller.employee.access.EmployeeAccountInfoController;

/**
 * @author Rajul Tiwari
 */

public class EmployeeAccountInfoControllerTest extends TestRoot {

	@Autowired
	private EmployeeAccountInfoController employeeAccountInfoController;

	@Autowired
	private EmployeeService employeeService;

	// @Ignore
	@Test
	public void showEmployeeInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeInfoForm");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeAccountInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");
	}

	// @Ignore
	@Test
	public void showChangePasswordForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showChangePasswordForm");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeAccountInfoController);
		ModelAndViewAssert.assertViewName(mav, "changePasswordForm");
	}

	@Ignore
	@Test
	public void changePassword() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/changePassword");
		// set parameters value of requested object
		request.setParameter("currentPassword", "rajul");
		request.setParameter("newPassword", "ra");
		request.setParameter("confirmNewPassword", "raj");
		request.setParameter("id", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeAccountInfoController);
		// ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");
		ModelAndViewAssert.assertViewName(mav, "changePasswordForm");

	}

	@Ignore
	@Test
	public void showChangeEmailForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showChangeEmailForm");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeAccountInfoController);
		ModelAndViewAssert.assertViewName(mav, "changeEmailForm");
	}

	@Ignore
	@Test
	public void changeEmail() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/changeEmail");
		// set parameters value of requested object
		request.setParameter("password", "rajul");
		request.setParameter("newEmail", "rajul");
		request.setParameter("confirmNewEmail", "rajul");
		request.setParameter("id", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeAccountInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");

	}

	@Ignore
	@Test
	public void showChangeSecurityQnAForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showChangeSecurityQnAForm");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeAccountInfoController);
		ModelAndViewAssert.assertViewName(mav, "changeSecurityQnAForm");
	}

	@Ignore
	@Test
	public void changeSecurityQnA() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/changeSecurityQnA");
		// set parameters value of requested object
		request.setParameter("password", "rajul");
		request.setParameter("securityQuestion", "what is your nick name");
		request.setParameter("securityAnswer", "rajul");
		request.setParameter("id", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeAccountInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");

	}

}