package com.epayroll.ui.contoller.employee.access;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.Employee;
import com.epayroll.entity.SecurityQuestion;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.TemporaryPasswordForm;
import com.epayroll.form.employeeAccess.EmployeeAccessLoginForm;
import com.epayroll.form.employeeAccess.EmployeeAccessRegistrationForm;
import com.epayroll.service.MailService;
import com.epayroll.service.employee.MessageCenterService;
import com.epayroll.service.employeeAccess.EmployeeAccessLoginService;
import com.epayroll.spring.authorization.EmployeeAccessAuthorizationUtils;
import com.epayroll.ui.contoller.helper.EmployeeAccessControllerHelper;

/**
 * @author Uma
 */
@RequestMapping("/employeeAccess")
@Controller
public class EmployeeAccessLoginController {
	@Autowired
	private EmployeeAccessLoginService employeeAccessLoginService;

	@Autowired
	private MailService mailService;

	@Autowired
	private MessageCenterService messageCenterService;

	@Autowired
	private EmployeeAccessControllerHelper employeeAccessControllerHelper;

	private Logger logger = LoggerFactory.getLogger(EmployeeAccessLoginController.class);

	private Company getCompanyFromSession(HttpSession session) {
		logger.debug(">> getCompanyFromSession");
		User user = (User) session.getAttribute("loggedInUser");
		Company company = new Company();
		if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
			Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
			if (iterator.hasNext()) {
				company = iterator.next().getCompany();
			}
		}
		logger.debug("getCompanyFromSession >>");
		return company;
	}

	@RequestMapping(value = "/checkTemporaryPassword", method = RequestMethod.POST)
	public ModelAndView checkTemporaryPassword(
			@ModelAttribute TemporaryPasswordForm teporaryPasswordForm, HttpSession session) {
		logger.debug(" >> Check Temporary Password");
		ModelAndView modelAndView = new ModelAndView();
		Company company = getCompanyFromSession(session);
		if (employeeAccessLoginService.checkTemporaryPassword(company.getId(),
				teporaryPasswordForm.getPassword())) {
			logger.debug("Correct");
			modelAndView.setViewName("registrationView");
		} else {
			logger.debug("InCorrect");
			modelAndView.setViewName("teporaryPasswordFormView");
		}
		logger.debug("Check Temporary Password >>");
		return modelAndView;
	}

	// user id coming from email
	@RequestMapping(value = "/showRegistrationForm/{userId}", method = RequestMethod.GET)
	public ModelAndView showRegistrationForm(@PathVariable String userId) {
		logger.debug(" >> showRegistrationForm");
		ModelAndView modelAndView = new ModelAndView("registrationView");
		Employee employee = employeeAccessLoginService.checkUserId(userId);
		if (employee != null) {
			EmployeeAccessRegistrationForm accessRegistrationForm = new EmployeeAccessRegistrationForm();
			accessRegistrationForm.setEmployeeId(employee.getId());
			List<SecurityQuestion> securityQuestions = employeeAccessLoginService
					.getSecurityQuestions();
			logger.debug("securityQuestions::::::" + securityQuestions);
			modelAndView.addObject("securityQuestions" + securityQuestions);
			modelAndView.addObject("accessRegistrationForm" + accessRegistrationForm);
		} else {
			logger.debug("incorrectLink");
		}
		logger.debug("showRegistrationForm >>");
		return modelAndView;
	}

	// employee id is coming from showRegistrationForm
	@RequestMapping(value = "/checkAvailability/{userId}/{employeeId}", method = RequestMethod.GET)
	public ModelAndView checkAvailability(@PathVariable String userId, @PathVariable Long employeeId) {
		logger.debug(" >> checkAvailability ");
		ModelAndView modelAndView = new ModelAndView("registrationView");
		Employee employee = employeeAccessLoginService.checkUserId(userId);
		if (employee == null) {
			logger.debug(" not available");
		} else {
			logger.debug("already available");
		}
		EmployeeAccessRegistrationForm accessRegistrationForm = new EmployeeAccessRegistrationForm();
		accessRegistrationForm.setEmployeeId(employeeId);
		List<SecurityQuestion> securityQuestions = employeeAccessLoginService
				.getSecurityQuestions();
		modelAndView.addObject("securityQuestions" + securityQuestions);
		modelAndView.addObject("employeeAccessForm" + accessRegistrationForm);
		logger.debug("Check Temporary Password >>");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView Registration(
			@Valid @ModelAttribute EmployeeAccessRegistrationForm employeeAccessRegistrationForm,
			BindingResult bindingResult) {
		logger.debug(" >> Registration");
		logger.debug(" employeeAccessRegistrationForm..." + employeeAccessRegistrationForm);
		ModelAndView modelAndView = new ModelAndView("loginView");
		try {
			if (!bindingResult.hasErrors()) {
				Long employeeId = employeeAccessLoginService
						.registration(employeeAccessRegistrationForm);
				employeeAccessRegistrationForm.setEmployeeId(employeeId);
				modelAndView.addObject("employeeAccessRegistrationForm"
						+ employeeAccessRegistrationForm);
			} else {
				logger.debug("validation error");
			}
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		logger.debug("Registration>>");
		return modelAndView;
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView checkLogin(@ModelAttribute EmployeeAccessLoginForm employeeAccessLoginForm) {
		logger.debug(" >> Registration");
		ModelAndView modelAndView = new ModelAndView("loginView");
		try {
			Employee employee = employeeAccessLoginService.checkLogin(employeeAccessLoginForm);
			if (employee != null) {
				logger.debug("Login Successfull");
				modelAndView.setViewName("employeeDashboard");
			} else {
				logger.debug("Incorrect userId and password");
			}
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		logger.debug("Registration>>");
		return modelAndView;
	}

	// @RequestMapping(value = "/showEmployeeAccessForgotUserIdStep1Form",
	// method = RequestMethod.GET)
	// public ModelAndView showEmployeeAccessForgotUserIdStep1Form() {
	// logger.debug(">> showEmployeeAccessForgotUserIdStep1Form");
	// ModelAndView modelView = new ModelAndView();
	// modelView.addObject("employeeAccessForgotUserIdStep1Form",
	// new EmployeeAccessForgotUserIdStep1Form());
	// modelView.setViewName("employeeAccessForgotUserIdStep1Form");
	// logger.debug("showEmployeeAccessForgotUserIdStep1Form >>");
	// return modelView;
	// }
	//
	// @RequestMapping(value = "/showEmployeeAccessForgotPasswordStep1Form",
	// method = RequestMethod.GET)
	// public ModelAndView showEmployeeAccessForgotPasswordStep1Form() {
	// logger.debug(">> showEmployeeAccessForgotPasswordStep1Form");
	// ModelAndView modelView = new ModelAndView();
	// modelView.addObject("employeeAccessForgotPasswordStep1Form",
	// new EmployeeAccessForgotPasswordStep1Form());
	// modelView.setViewName("employeeAccessForgotPasswordStep1Form");
	// logger.debug("showEmployeeAccessForgotPasswordStep1Form >>");
	// return modelView;
	// }
	//
	// @RequestMapping(value = "/showEmployeeAccessForgotUserIdStep2Form",
	// method = RequestMethod.POST)
	// public ModelAndView showEmployeeAccessForgotUserIdStep2Form(
	// @Valid @ModelAttribute EmployeeAccessForgotUserIdStep1Form
	// employeeAccessForgotUserIdStep1Form,
	// BindingResult bindingResult, HttpSession session) {
	// logger.debug(">> showEmployeeAccessForgotUserIdStep2Form");
	// ModelAndView modelView = new ModelAndView();
	// String result = "";
	// if (!bindingResult.hasErrors()) {
	// try {
	// EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form =
	// employeeAccessLoginService
	// .getEmployeeAccessForgotUserIdStep2Form(employeeAccessForgotUserIdStep1Form);
	// if (employeeAccessForgotUserIdStep2Form != null) {
	// session.setAttribute("employeeAccessForgotUserIdStep1Form",
	// employeeAccessForgotUserIdStep1Form);
	// modelView.addObject("employeeAccessForgotUserIdStep2Form",
	// employeeAccessForgotUserIdStep2Form);
	// modelView.setViewName("employeeAccessForgotUserIdStep2Form");
	// logger.debug("Security Question 1::"
	// + employeeAccessForgotUserIdStep2Form.getSecurityQuestion1());
	// } else {
	// modelView.setViewName("employeeAccessForgotUserIdStep1Form");
	// logger.debug("Invalid Email,Try Again");
	// result = "Invalid Email,Try Again";
	// }
	// } catch (InstanceNotFoundException e) {
	// e.printStackTrace();
	// logger.error(e.getMessage());
	// }
	// } else {
	// modelView.setViewName("employeeAccessForgotUserIdStep1Form");
	// logger.error("Validation Error:::" + bindingResult.toString());
	// result = "Validation Error";
	// }
	// modelView.addObject("result", result);
	// logger.debug("showEmployeeAccessForgotUserIdStep2Form >>");
	// return modelView;
	// }
	//
	// @RequestMapping(value = "/showEmployeeAccessForgotPasswordStep2Form",
	// method = RequestMethod.POST)
	// public ModelAndView showEmployeeAccessForgotPasswordStep2Form(
	// @Valid @ModelAttribute EmployeeAccessForgotPasswordStep1Form
	// employeeAccessForgotPasswordStep1Form,
	// BindingResult bindingResult, HttpSession session) {
	// logger.debug(">> showEmployeeAccessForgotPasswordStep2Form");
	// ModelAndView modelView = new ModelAndView();
	// String result = "";
	// if (!bindingResult.hasErrors()) {
	// try {
	// EmployeeAccessForgotPasswordStep2Form
	// employeeAccessForgotPasswordStep2Form = employeeAccessLoginService
	// .getEmployeeAccessForgotPasswordStep2Form(employeeAccessForgotPasswordStep1Form);
	// if (employeeAccessForgotPasswordStep2Form != null) {
	// session.setAttribute("employeeAccessForgotPasswordStep1Form",
	// employeeAccessForgotPasswordStep1Form);
	// modelView.addObject("employeeAccessForgotPasswordStep2Form",
	// employeeAccessForgotPasswordStep2Form);
	// modelView.setViewName("employeeAccessForgotPasswordStep2Form");
	// logger.debug("Security Question 1::"
	// + employeeAccessForgotPasswordStep2Form.getSecurityQuestion1());
	// } else {
	// modelView.setViewName("employeeAccessForgotPasswordStep1Form");
	// logger.debug("Invalid Email,Try Again");
	// result = "Invalid Email,Try Again";
	// }
	// } catch (InstanceNotFoundException e) {
	// e.printStackTrace();
	// logger.error(e.getMessage());
	// }
	// } else {
	// modelView.setViewName("employeeAccessForgotPasswordStep1Form");
	// logger.error("Validation Error:::" + bindingResult.toString());
	// result = "Validation Error";
	// }
	// modelView.addObject("result", result);
	// logger.debug("showEmployeeAccessForgotPasswordStep2Form >>");
	// return modelView;
	// }
	//
	// @RequestMapping(value = "/showEmployeeAccessForgotUserIdStep3Form",
	// method = RequestMethod.POST)
	// public ModelAndView showEmployeeAccessForgotUserIdStep3Form(
	// @Valid @ModelAttribute EmployeeAccessForgotUserIdStep2Form
	// employeeAccessForgotUserIdStep2Form,
	// BindingResult bindingResult, HttpSession session, HttpServletRequest
	// request) {
	// logger.debug(">> showEmployeeAccessForgotUserIdStep3Form");
	// ModelAndView modelView = new ModelAndView();
	// String result = "";
	// if (!bindingResult.hasErrors()) {
	// try {
	// EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form =
	// (EmployeeAccessForgotUserIdStep1Form) session
	// .getAttribute("employeeAccessForgotUserIdStep1Form");
	// String link = "http://localhost:8080" + request.getContextPath()
	// + "/employeeAccess/showLoginForm.html";
	// String userName = employeeAccessLoginService
	// .checkSecurityAnswersAndGetUserIdForUserId(
	// employeeAccessForgotUserIdStep1Form,
	// employeeAccessForgotUserIdStep2Form);
	// if (userName != null) {
	// try {
	// mailService.send(employeeAccessControllerHelper.prepareMailForUserId(
	// employeeAccessForgotUserIdStep1Form, link, userName));
	// modelView.setViewName("showEmployeeAccessSuccessForm");
	// modelView
	// .addObject("result",
	// "Your credentials are correct,now you will get an email containing UserId retrieval Instructions.");
	// logger.debug("Check If condition :: Your credentials are correct.");
	// EmployeeAccessSuccessForm employeeAccessSuccessForm = new
	// EmployeeAccessSuccessForm();
	// employeeAccessSuccessForm.setEmailId(employeeAccessForgotUserIdStep1Form
	// .getEmailId());
	// employeeAccessSuccessForm.setUserName(userName);
	// modelView.addObject("showEmployeeAccessSuccessForm",
	// employeeAccessSuccessForm);
	// } catch (MailNotSentException e) {
	// logger.error(e.getMessage());
	// e.printStackTrace();
	// }
	//
	// } else {
	// modelView.setViewName("employeeAccessForgotUserIdStep2Form");
	// modelView.addObject("result",
	// "Security Answers provided by you is not correct.");
	// logger.debug("Check else condition :: Security Answers provided by you is not correct.");
	// }
	// } catch (InstanceNotFoundException e) {
	// e.printStackTrace();
	// logger.error(e.getMessage());
	// }
	// } else {
	// modelView.setViewName("employeeAccessForgotUserIdStep2Form");
	// logger.error("Validation Error:::" + bindingResult.toString());
	// result = "Validation Error";
	// }
	// modelView.addObject("result", result);
	// logger.debug("showEmployeeAccessForgotUserIdStep3Form >>");
	// return modelView;
	// }
	//
	// @RequestMapping(value = "/showEmployeeAccessForgotPasswordStep3Form",
	// method = RequestMethod.POST)
	// public ModelAndView showEmployeeAccessForgotPasswordStep3Form(
	// @Valid @ModelAttribute EmployeeAccessForgotPasswordStep2Form
	// employeeAccessForgotPasswordStep2Form,
	// BindingResult bindingResult, HttpSession session, HttpServletRequest
	// request) {
	// logger.debug(">> showEmployeeAccessForgotPasswordStep3Form");
	// ModelAndView modelView = new ModelAndView();
	// String result = "";
	// if (!bindingResult.hasErrors()) {
	// try {
	// EmployeeAccessForgotPasswordStep1Form
	// employeeAccessForgotPasswordStep1Form =
	// (EmployeeAccessForgotPasswordStep1Form) session
	// .getAttribute("employeeAccessForgotPasswordStep1Form");
	// String link = "http://localhost:8080" + request.getContextPath()
	// + "/employeeAccess/showLoginForm.html";
	// String emailId = employeeAccessLoginService
	// .checkSecurityAnswersAndGetUserIdForEmailId(
	// employeeAccessForgotPasswordStep1Form,
	// employeeAccessForgotPasswordStep2Form);
	// if (emailId != null) {
	// try {
	// mailService.send(employeeAccessControllerHelper.prepareMailForUserId(
	// employeeAccessForgotPasswordStep1Form, link, emailId));
	// modelView.setViewName("showEmployeeAccessSuccessForm");
	// modelView
	// .addObject(
	// "result",
	// "Your credentials are correct,now you will get an email containing Password retrieval Instructions.");
	// logger.debug("Check If condition :: Your credentials are correct.");
	// EmployeeAccessSuccessForm employeeAccessSuccessForm = new
	// EmployeeAccessSuccessForm();
	// employeeAccessSuccessForm.setEmailId(emailId);
	// employeeAccessSuccessForm.setUserName(employeeAccessForgotPasswordStep1Form
	// .getUserId());
	// modelView.addObject("showEmployeeAccessSuccessForm",
	// employeeAccessSuccessForm);
	// } catch (MailNotSentException e) {
	// logger.error(e.getMessage());
	// e.printStackTrace();
	// }
	//
	// } else {
	// modelView.setViewName("employeeAccessForgotPasswordStep2Form");
	// modelView.addObject("result",
	// "Security Answers provided by you is not correct.");
	// logger.debug("Check else condition :: Security Answers provided by you is not correct.");
	// }
	// } catch (InstanceNotFoundException e) {
	// e.printStackTrace();
	// logger.error(e.getMessage());
	// }
	// } else {
	// modelView.setViewName("employeeAccessForgotPasswordStep2Form");
	// logger.error("Validation Error:::" + bindingResult.toString());
	// result = "Validation Error";
	// }
	// modelView.addObject("result", result);
	// logger.debug("showEmployeeAccessForgotPasswordStep3Form >>");
	// return modelView;
	// }

	@RequestMapping(value = "/showLoginForm", method = RequestMethod.GET)
	public ModelAndView showLoginForm() {
		logger.debug(">> showLoginForm");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("employeeAccessLoginForm");
		modelView.setViewName("employeeAccessLoginForm");
		logger.debug("showLoginForm >>");
		return modelView;
	}

	// @RequestMapping(value = "/resendMail", method = RequestMethod.POST)
	// public ModelAndView resendMail(
	// @Valid @ModelAttribute EmployeeAccessSuccessForm
	// employeeAccessSuccessForm,
	// BindingResult bindingResult, HttpSession session, HttpServletRequest
	// request) {
	// logger.debug(">> resendMail");
	// ModelAndView modelView = new
	// ModelAndView("showEmployeeAccessSuccessForm");
	// String result = "";
	//
	// String link = "http://localhost:8080" + request.getContextPath()
	// + "/employeeAccess/showLoginForm.html";
	// try {
	// mailService.send(employeeAccessControllerHelper.prepareMailForUserId(
	// employeeAccessSuccessForm.getEmailId(), link,
	// employeeAccessSuccessForm.getUserName()));
	// modelView
	// .addObject("result",
	// "Your credentials are correct,now you will get an email containing UserId retrieval Instructions.");
	// logger.debug("Check If condition :: Your credentials are correct.");
	// modelView.addObject("showEmployeeAccessSuccessForm",
	// employeeAccessSuccessForm);
	// } catch (MailNotSentException e) {
	// logger.error(e.getMessage());
	// e.printStackTrace();
	// }
	// modelView.addObject("result", result);
	// logger.debug("resendMail >>");
	// return modelView;
	// }

	@RequestMapping(value = "/employeeDashboard", method = RequestMethod.POST)
	public ModelAndView employeeDashboard(
			@Valid @ModelAttribute EmployeeAccessLoginForm employeeAccessLoginForm,
			BindingResult bindingResult, Principal principal) {
		logger.debug(">> employeeDashboard");
		ModelAndView modelView = new ModelAndView("employeeDeshboardForm");
		// messageCenterService.getReceivedMessages(((Employee)
		// principal).getPerson().getId());
		messageCenterService.getReceivedMessages(EmployeeAccessAuthorizationUtils.getLoginUser()
				.getPerson().getId());
		logger.debug("employeeDashboard >>");
		return modelView;
	}

}
