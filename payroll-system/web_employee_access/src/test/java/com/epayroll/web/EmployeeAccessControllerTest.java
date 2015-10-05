package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.employee.access.EmployeeAccessLoginController;

/**
 * 
 * @author Uma
 * 
 */
public class EmployeeAccessControllerTest extends TestRoot {

	@Autowired
	private EmployeeAccessLoginController employeeLoginAccessController;

	@Autowired
	private CompanyService companyService;

	@Test
	public void showEmployeeList() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/checkTemporaryPassword");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.setParameter("password", "abc");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "registrationView");
	}

	// @Test
	public void showRegistrationForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showRegistrationForm/abc");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "registrationView");
	}

	// @Test
	public void checkAvailability() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/checkAvailability/abc23333/1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "registrationView");
	}

	// @Test
	public void registration() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/registration");
		request.setParameter("employeeId", "1");
		request.setParameter("userId", "abc");
		request.setParameter("password", "abc");
		request.setParameter("reEnteredPassword", "abc");
		request.setParameter("securityQuestionId", "6");
		request.setParameter("answer", "new Answer");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "loginView");
	}

	// @Test
	public void checkLogin() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/checkLogin");
		request.setParameter("userName", "abc");
		request.setParameter("password", "abc");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "employeeDashboard");
	}

	// @Test
	public void showEmployeeAccessForgotUserIdStep2Form() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showEmployeeAccessForgotUserIdStep2Form");
		request.setParameter("emailId", "uma@i-techsoftware.com");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "employeeAccessForgotUserIdStep2Form");
	}

	// @Test
	public void showEmployeeAccessForgotUserIdStep3Form() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showEmployeeAccessForgotUserIdStep3Form");
		request.setParameter("securityAnswer1", "new Answer");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "showEmployeeAccessSuccessForm");
	}

	// @Test
	public void showEmployeeAccessForgotPasswordStep2Form() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showEmployeeAccessForgotPasswordStep2Form");
		request.setParameter("userId", "a");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "employeeAccessForgotPasswordStep2Form");
	}

	// @Test
	public void showEmployeeAccessForgotPasswordStep3Form() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showEmployeeAccessForgotPasswordStep3Form");
		request.setParameter("securityAnswer1", "new Answer");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "showEmployeeAccessSuccessForm");
	}

	// @Test
	public void resendMail() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/resendMail");
		request.setParameter("emailId", "uma@i-techsoftware.com");
		request.setParameter("userName", "a");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeLoginAccessController);
		ModelAndViewAssert.assertViewName(mav, "showEmployeeAccessSuccessForm");
	}
}