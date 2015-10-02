package com.epayroll.ui.contoller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Employee;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.exception.MailNotSentException;
import com.epayroll.form.ResetPasswordForm;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotPasswordStep1Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotPasswordStep2Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotUserIdStep1Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotUserIdStep2Form;
import com.epayroll.service.MailService;
import com.epayroll.service.employeeAccess.EmployeeAccessLoginService;
import com.epayroll.ui.contoller.helper.EmployeeAccessControllerHelper;

@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private EmployeeAccessLoginService employeeAccessLoginService;

	@Autowired
	private EmployeeAccessControllerHelper employeeAccessControllerHelper;

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) throws Exception {
		model.addAttribute("message", "Spring Security Custom Form example");
		logger.info("User [ " + principal.getName() + " ] logged in successfully!");

		// for employee test
		// AuthorizationUtil.getSession().setAttribute("employee",
		// employeeService.getEmployee(1L),
		// RequestAttributes.SCOPE_SESSION);

		// end
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		logger.info("User Logged Out successfully!");
		return "logoutReditect";
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		logger.debug("Access denied!");
		model.addAttribute("message", "You do not have permission to access this page!");
		return "accessDenied";
	}

	@RequestMapping(value = "/showForgotUserIdStep1", method = RequestMethod.GET)
	public ModelAndView showForgotUserIdStep1() {
		logger.debug(">> showForgotUserIdStep1");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("employeeAccessForgotUserIdStep1Form",
				new EmployeeAccessForgotUserIdStep1Form());
		modelView.setViewName("forgotUserNameStep1");
		logger.debug("showForgotUserIdStep1 >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotUserIdStep2", method = RequestMethod.POST)
	public ModelAndView showForgotUserIdStep2(
			@Valid @ModelAttribute EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> showForgotUserIdStep2");
		ModelAndView modelView = new ModelAndView("forgotUserNameStep1");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form = employeeAccessLoginService
						.getEmployeeAccessForgotUserIdStep2Form(employeeAccessForgotUserIdStep1Form);
				if (employeeAccessForgotUserIdStep2Form != null) {
					session.setAttribute("employeeAccessForgotUserIdStep1Form",
							employeeAccessForgotUserIdStep1Form);
					modelView.addObject("employeeAccessForgotUserIdStep2Form",
							employeeAccessForgotUserIdStep2Form);
					modelView.setViewName("forgotUserNameStep2");
				} else {
					logger.debug("Invalid Email,Try Again");
					result = "Invalid Email,Try Again";
				}
			} catch (InstanceNotFoundException e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("showForgotUserIdStep2 >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotUserIdStep3", method = RequestMethod.POST)
	public ModelAndView showForgotUserIdStep3(
			@Valid @ModelAttribute EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> showForgotUserIdStep3");
		ModelAndView modelView = new ModelAndView("forgotUserNameStep2");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form = (EmployeeAccessForgotUserIdStep1Form) session
						.getAttribute("employeeAccessForgotUserIdStep1Form");
				String link = "http://localhost:8080" + request.getContextPath() + "/login";
				String userName = employeeAccessLoginService
						.checkSecurityAnswersAndGetUserIdForUserId(
								employeeAccessForgotUserIdStep1Form,
								employeeAccessForgotUserIdStep2Form);
				session.setAttribute("userName", userName);
				if (userName != null) {
					try {
						mailService.send(employeeAccessControllerHelper.prepareMailForUserId(
								employeeAccessForgotUserIdStep1Form, link, userName));
						modelView.setViewName("forgotUserNameStep3");
						result = "Your credentials are correct,now you will get an email containing UserId retrieval Instructions.";
					} catch (MailNotSentException e) {
						logger.error("ERROR :: " + e.getMessage());
					}
				} else {
					result = "Security Answers provided by you is not correct.";
				}
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("showForgotUserIdStep3 >>");
		return modelView;
	}

	@RequestMapping(value = "/resendEmailForUserId", method = RequestMethod.GET)
	public ModelAndView resendEmailForUserId(HttpSession session, HttpServletRequest request) {
		logger.debug(">> resendEmailForUserId");
		ModelAndView modelView = new ModelAndView("forgotUserNameStep3");
		String result = "";
		try {
			EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form = (EmployeeAccessForgotUserIdStep1Form) session
					.getAttribute("employeeAccessForgotUserIdStep1Form");
			String link = "http://localhost:8080" + request.getContextPath() + "/login";
			String userName = (String) session.getAttribute("userName");
			try {
				mailService.send(employeeAccessControllerHelper.prepareMailForUserId(
						employeeAccessForgotUserIdStep1Form, link, userName));
			} catch (MailNotSentException e) {
				logger.error(e.getMessage());
			}
			result = "[ RESEND ] Your credentials are correct,now you will get an email containing UserId retrieval Instructions.";
			logger.debug(result);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		modelView.addObject("result", result);
		logger.debug("resendEmailForUserId >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotPasswordStep1", method = RequestMethod.GET)
	public ModelAndView showForgotPasswordStep1() {
		logger.debug(">> showForgotPasswordStep1");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("employeeAccessForgotPasswordStep1Form",
				new EmployeeAccessForgotPasswordStep1Form());
		modelView.setViewName("forgotPasswordStep1");
		logger.debug("showForgotPasswordStep1 >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotPasswordStep2", method = RequestMethod.POST)
	public ModelAndView showForgotPasswordStep2(
			@Valid @ModelAttribute EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> showForgotPasswordStep2");
		ModelAndView modelView = new ModelAndView("forgotPasswordStep1");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				EmployeeAccessForgotPasswordStep2Form employeeAccessForgotPasswordStep2Form = employeeAccessLoginService
						.getEmployeeAccessForgotPasswordStep2Form(employeeAccessForgotPasswordStep1Form);
				if (employeeAccessForgotPasswordStep2Form != null) {
					session.setAttribute("employeeAccessForgotPasswordStep1Form",
							employeeAccessForgotPasswordStep1Form);
					modelView.addObject("employeeAccessForgotPasswordStep2Form",
							employeeAccessForgotPasswordStep2Form);
					modelView.setViewName("forgotPasswordStep2");
				} else {
					logger.debug("Invalid Email,Try Again");
					result = "Invalid Email,Try Again";
				}
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("showForgotPasswordStep2 >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotPasswordStep3", method = RequestMethod.POST)
	public ModelAndView showForgotPasswordStep3(
			@Valid @ModelAttribute EmployeeAccessForgotPasswordStep2Form employeeAccessForgotPasswordStep2Form,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> showForgotPasswordStep3");
		ModelAndView modelView = new ModelAndView("forgotPasswordStep2");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form = (EmployeeAccessForgotPasswordStep1Form) session
						.getAttribute("employeeAccessForgotPasswordStep1Form");
				String link = "http://localhost:8080" + request.getContextPath()
						+ "/showResetPassword?userInfo="
						+ employeeAccessForgotPasswordStep1Form.getUserId();
				String emailId = employeeAccessLoginService
						.checkSecurityAnswersAndGetEmailForPassword(
								employeeAccessForgotPasswordStep1Form,
								employeeAccessForgotPasswordStep2Form);
				session.setAttribute("emailId", emailId);
				if (emailId != null) {
					try {
						mailService.send(employeeAccessControllerHelper.prepareMailForPassword(
								employeeAccessForgotPasswordStep1Form, link, emailId));
						modelView.setViewName("forgotPasswordStep3");
						result = "Your credentials are correct,now you will get an email containing Password retrieval Instructions.";
						logger.debug("Check If condition :: Your credentials are correct.");
					} catch (MailNotSentException e) {
						logger.error("ERROR :: " + e.getMessage());
					}

				} else {
					result = "Security Answers provided by you is not correct.";
					logger.debug("Check else condition :: Security Answers provided by you is not correct.");
				}
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("showForgotPasswordStep3 >>");
		return modelView;
	}

	@RequestMapping(value = "/resendEmailForPassword", method = RequestMethod.GET)
	public ModelAndView resendEmailForPassword(HttpSession session, HttpServletRequest request) {
		logger.debug(">> resendEmailForPassword");
		ModelAndView modelView = new ModelAndView("forgotPasswordStep3");
		String result = "";
		try {
			EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form = (EmployeeAccessForgotPasswordStep1Form) session
					.getAttribute("employeeAccessForgotPasswordStep1Form");
			String link = "http://localhost:8080" + request.getContextPath()
					+ "/showResetPassword?userInfo="
					+ employeeAccessForgotPasswordStep1Form.getUserId();
			String emailId = (String) session.getAttribute("emailId");
			try {
				mailService.send(employeeAccessControllerHelper.prepareMailForPassword(
						employeeAccessForgotPasswordStep1Form, link, emailId));
			} catch (MailNotSentException e) {
				logger.error(e.getMessage());
			}
			result = "[ RESEND ] Your credentials are correct,now you will get an email containing Password Reset Link.";
			logger.debug(result);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		modelView.addObject("result", result);
		logger.debug("resendEmailForPassword >>");
		return modelView;
	}

	@RequestMapping(value = "/showResetPassword", method = RequestMethod.GET)
	public ModelAndView showResetPasswordForm(@RequestParam("userInfo") String username,
			HttpSession session) {
		logger.debug(">> showResetPasswordForm");
		ModelAndView modelView = new ModelAndView("resetPassword");
		Employee employee;
		try {
			employee = employeeAccessLoginService.getEmployeeFromUserName(username);
			if (employee.getForgetProcessInitiated().equals(true)) {
				session.setAttribute("employee", employee);
				modelView.addObject("resetPasswordForm", new ResetPasswordForm());
				logger.debug("Check if condition :: Success");
			} else {
				modelView.addObject("result", "Invalid URL");
				logger.debug("Check else condition :: Invalid URL.. Try Again");
			}
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			logger.debug("Invalid UserName,Try Again");
		}
		logger.debug("showResetPasswordForm >>");
		return modelView;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(@Valid @ModelAttribute ResetPasswordForm resetPasswordForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> resetPassword");
		ModelAndView modelView = new ModelAndView("resetPassword");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				Employee employee = (Employee) session.getAttribute("employee");
				if (employeeAccessLoginService.changePassword(employee, resetPasswordForm)) {
					modelView.setViewName("login");
					result = "Success";
					logger.debug("Check if condition :: Success");
				} else {
					result = "error,Try Again";
					logger.debug("Check if condition :: error,Try Again");
				}
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("resetPassword >>");
		return modelView;
	}

}