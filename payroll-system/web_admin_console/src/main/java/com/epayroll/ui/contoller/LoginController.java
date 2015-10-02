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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.constant.PayrollUserType;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.exception.InvalidExistingPasswordException;
import com.epayroll.exception.MailNotSentException;
import com.epayroll.form.ForgotInfoStep2Form;
import com.epayroll.form.ForgotPasswordAndPinStep1Form;
import com.epayroll.form.ForgotUserIdStep1Form;
import com.epayroll.form.PinVerficationForm;
import com.epayroll.form.ResetPasswordForm;
import com.epayroll.form.ResetPinForm;
import com.epayroll.form.company.ChangePasswordForm;
import com.epayroll.form.company.UserForm;
import com.epayroll.service.MailService;
import com.epayroll.service.UserService;
import com.epayroll.service.company.CompanyUserService;
import com.epayroll.ui.contoller.helper.AdminUserControllerHelper;

@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private CompanyUserService companyUserService;

	@Autowired
	private UserService userService;

	@Autowired
	private AdminUserControllerHelper adminControllerHelper;

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/dashBoard", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) throws Exception {
		model.addAttribute("message", "Spring " + " Custom Form example");
		logger.info("User [ " + principal.getName() + " ] logged in successfully!");
		return "hello";
	}

	@RequestMapping(value = "/pinVerification", method = RequestMethod.POST)
	public String pinVerification(ModelMap model, HttpServletRequest request) throws Exception {
		logger.debug("/pinVerification....................");
		PinVerficationForm pinVerficationForm = new PinVerficationForm();
		pinVerficationForm.setUsername(request.getParameter("username"));
		pinVerficationForm.setPassword(request.getParameter("password"));
		model.addAttribute("pinVerficationForm", pinVerficationForm);
		return "pinVerification";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		if (request.getSession().getAttribute(PayrollUserType.USER_NOT_FOUND) != null) {
			model.addAttribute("errors",
					(String) request.getSession().getAttribute(PayrollUserType.USER_NOT_FOUND));
			request.getSession().removeAttribute(PayrollUserType.USER_NOT_FOUND);
		}
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
		return "accessDeny";
	}

	@RequestMapping(value = "/login/showChangePasswordForm/{userid}/{username}/{password}", method = RequestMethod.GET)
	public ModelAndView showChangePasswordForm(@PathVariable Long userid,
			@PathVariable String username, @PathVariable String password) {
		logger.debug(" >> in showChangePasswordForm ... userid:" + userid + ",username:" + username
				+ ",password:" + password);

		ModelAndView mav = new ModelAndView("change-password");

		User user = null;
		try {
			user = userService.getUser(userid);

			if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {

				ChangePasswordForm changePasswordForm = new ChangePasswordForm();
				changePasswordForm.setCompanyUserId(userid);
				mav.addObject("changePasswordForm", changePasswordForm);

			} else {
				logger.debug("Invalid URL or already used!");
				mav.addObject("error-message", "Invalid URL or already used!");
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Table not exixts !", e.fillInStackTrace());
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/login/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute ChangePasswordForm changePasswordForm,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("change-password");
		changePasswordForm.setIsFirstTime(true);

		if (bindingResult.hasErrors()) {
			mav.addObject("error", "Form Validation Falied.");
		} else {
			try {
				companyUserService.changePassword(changePasswordForm);

				mav.addObject("success-message", "Password Changed Successfully!");
				/*
				 * go to the Registration form
				 */
				UserForm userForm = new UserForm();
				userForm.setId(changePasswordForm.getCompanyUserId());
				mav.setViewName("user-registration");
				mav.addObject("states", userService.getStates());
				mav.addObject("securityQuestions", userService.getSecurityQuestions());
				mav.addObject("companyUserForm", userForm);
			} catch (InstanceNotFoundException e) {
				mav.addObject("error-message", e.getLocalizedMessage());
			} catch (InvalidExistingPasswordException e) {
				mav.addObject("error-message", e.getLocalizedMessage());
			}
		}
		return mav;
	}

	@RequestMapping(value = "/showForgotPasswordStep1", method = RequestMethod.GET)
	public ModelAndView showForgotPasswordStep1Form() {
		logger.debug(">> showForgotPasswordStep1Form");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("forgotPasswordAndPinStep1Form", new ForgotPasswordAndPinStep1Form());
		modelView.setViewName("forgotPasswordStep1");
		logger.debug("showForgotPasswordStep1Form >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotPasswordStep2", method = RequestMethod.POST)
	public ModelAndView showForgotPasswordStep2Form(
			@Valid @ModelAttribute ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> showForgotPasswordStep2Form");
		ModelAndView modelView = new ModelAndView("forgotPasswordStep1");
		String result = "";
		if (!bindingResult.hasErrors()) {

			try {
				ForgotInfoStep2Form forgotInfoStep2Form = userService
						.getForgotInfoStep2Form(forgotPasswordAndPinStep1Form);
				session.setAttribute("forgotPasswordAndPinStep1Form", forgotPasswordAndPinStep1Form);
				modelView.addObject("forgotInfoStep2Form", forgotInfoStep2Form);
				modelView.setViewName("forgotPasswordStep2");

			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
				result = "Invalid UserName,Try Again";
				logger.debug("Invalid UserName,Try Again");
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("showForgotPasswordStep2Form >>");
		return modelView;
	}

	// TODO set hidden variables in jsp(securityQuestionId1 and 2)

	@RequestMapping(value = "/showForgotPasswordStep3", method = RequestMethod.POST)
	public ModelAndView showForgotPasswordStep3(
			@Valid @ModelAttribute ForgotInfoStep2Form forgotInfoStep2Form,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> showForgotPasswordStep3");
		ModelAndView modelView = new ModelAndView("forgotPasswordStep2");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form = (ForgotPasswordAndPinStep1Form) session
						.getAttribute("forgotPasswordAndPinStep1Form");
				String link = "http://localhost:8080" + request.getContextPath()
						+ "/showResetPassword?userInfo="
						+ forgotPasswordAndPinStep1Form.getUserName();
				String email = userService.checkSecurityAnswersAndGetEmailForPassword(
						forgotPasswordAndPinStep1Form, forgotInfoStep2Form);
				if (email != null) {
					try {
						mailService.send(adminControllerHelper.prepareMailForPassword(
								forgotPasswordAndPinStep1Form, link, email));
					} catch (MailNotSentException e) {
						logger.error(e.getMessage());
						e.printStackTrace();
					}
					modelView.setViewName("forgotPasswordStep3");
					result = "Your credentials are correct,now you will get an email containing Password Reset Link.";
					logger.debug(result);
				} else {
					modelView.setViewName("forgotPasswordStep2");
					result = "Security Answers provided by you is not correct.";
					logger.debug(result);
				}
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			modelView.setViewName("forgotPasswordStep2");
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
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form = (ForgotPasswordAndPinStep1Form) session
					.getAttribute("forgotPasswordAndPinStep1Form");
			String link = "http://localhost:8080" + request.getContextPath()
					+ "/showResetPassword?userInfo=" + forgotPasswordAndPinStep1Form.getUserName();
			String email = (String) session.getAttribute("email");
			try {
				mailService.send(adminControllerHelper.prepareMailForPassword(
						forgotPasswordAndPinStep1Form, link, email));
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
		User user;
		try {
			user = userService.getUser(username);
			if (user.getForgetProcessInitiated().equals(true)) {
				session.setAttribute("user", user);
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
				User user = (User) session.getAttribute("user");
				if (userService.changePassword(user, resetPasswordForm)) {
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

	@RequestMapping(value = "/showForgotUserIdStep1", method = RequestMethod.GET)
	public ModelAndView showForgotUserIdStep1Form() {
		logger.debug(">> showForgotUserIdStep1Form");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("forgotUserIdStep1Form", new ForgotUserIdStep1Form());
		modelView.setViewName("forgotUserNameStep1");
		logger.debug("showForgotUserIdStep1Form >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotUserIdStep2", method = RequestMethod.POST)
	public ModelAndView showForgotUserIdStep2Form(
			@Valid @ModelAttribute ForgotUserIdStep1Form forgotUserIdStep1Form,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> showForgotUserIdStep2Form");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				ForgotInfoStep2Form forgotInfoStep2Form = userService
						.getForgotInfoStep2FormForUserId(forgotUserIdStep1Form);
				if (forgotInfoStep2Form != null) {
					session.setAttribute("forgotUserIdStep1Form", forgotUserIdStep1Form);
					modelView.addObject("forgotInfoStep2Form", forgotInfoStep2Form);
					modelView.setViewName("forgotUserNameStep2");
				} else {
					modelView.setViewName("forgotUserNameStep1");
					logger.debug("Invalid Email or Dob,Try Again");
					result = "Invalid Email or Dob,Try Again";
				}
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			modelView.setViewName("forgotUserNameStep1");
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("showForgotUserIdStep2Form >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotUserIdStep3", method = RequestMethod.POST)
	public ModelAndView showForgotUserIdStep3(
			@Valid @ModelAttribute ForgotInfoStep2Form forgotInfoStep2Form,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> showForgotUserIdStep3");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				ForgotUserIdStep1Form forgotUserIdStep1Form = (ForgotUserIdStep1Form) session
						.getAttribute("forgotUserIdStep1Form");
				String link = "http://localhost:8080" + request.getContextPath() + "/login";
				String userName = userService.checkSecurityAnswersAndGetUserIdForUserId(
						forgotUserIdStep1Form, forgotInfoStep2Form);
				if (userName != null) {
					try {
						mailService.send(adminControllerHelper.prepareMailForUserId(
								forgotUserIdStep1Form, link, userName));
						result = "Your credentials are correct,now you will get an email containing UserId retrieval Instructions.";
						modelView.setViewName("forgotUserNameStep3");
					} catch (MailNotSentException e) {
						logger.error("ERROR :: " + e.getMessage());
					}

				} else {
					modelView.setViewName("forgotUserNameStep2");
					result = "Security Answers provided by you is not correct.";
				}
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			modelView.setViewName("forgotUserNameStep2");
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error" + bindingResult.toString();
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
			ForgotUserIdStep1Form forgotUserIdStep1Form = (ForgotUserIdStep1Form) session
					.getAttribute("forgotUserIdStep1Form");
			String link = "http://localhost:8080" + request.getContextPath() + "/login";
			String userName = (String) session.getAttribute("userName");
			try {
				mailService.send(adminControllerHelper.prepareMailForUserId(forgotUserIdStep1Form,
						link, userName));
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

	@RequestMapping(value = "/showForgotPinStep1", method = RequestMethod.GET)
	public ModelAndView showForgotPinStep1Form() {
		logger.debug(">> showForgotPinStep1Form");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("forgotPasswordAndPinStep1Form", new ForgotPasswordAndPinStep1Form());
		modelView.setViewName("forgotPinStep1");
		logger.debug("showForgotPinStep1Form >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotPinStep2", method = RequestMethod.POST)
	public ModelAndView showForgotPinStep2Form(
			@Valid @ModelAttribute ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> showForgotPinStep2Form");
		ModelAndView modelView = new ModelAndView("forgotPinStep1");
		String result = "";
		if (!bindingResult.hasErrors()) {

			try {
				ForgotInfoStep2Form forgotInfoStep2Form = userService
						.getForgotInfoStep2Form(forgotPasswordAndPinStep1Form);
				session.setAttribute("forgotPasswordAndPinStep1Form", forgotPasswordAndPinStep1Form);
				modelView.addObject("forgotInfoStep2Form", forgotInfoStep2Form);
				modelView.setViewName("forgotPinStep2");

			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
				result = "Invalid UserName,Try Again";
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		modelView.addObject("result", result);
		logger.debug("showForgotPinStep2Form >>");
		return modelView;
	}

	// TODO set hidden variables in jsp(securityQuestionId1 and 2)

	@RequestMapping(value = "/showForgotPinStep3", method = RequestMethod.POST)
	public ModelAndView showForgotPinStep3(
			@Valid @ModelAttribute ForgotInfoStep2Form forgotInfoStep2Form,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> showForgotPinStep3");
		ModelAndView modelView = new ModelAndView("forgotPinStep2");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form = (ForgotPasswordAndPinStep1Form) session
						.getAttribute("forgotPasswordAndPinStep1Form");
				String link = "http://localhost:8080" + request.getContextPath()
						+ "/showResetPin?userInfo=" + forgotPasswordAndPinStep1Form.getUserName();
				String email = userService.checkSecurityAnswersAndGetEmailForPassword(
						forgotPasswordAndPinStep1Form, forgotInfoStep2Form);
				if (email != null) {
					try {
						mailService.send(adminControllerHelper.prepareMailForPin(
								forgotPasswordAndPinStep1Form, link, email));
					} catch (MailNotSentException e) {
						logger.error(e.getMessage());
						e.printStackTrace();
					}
					modelView.setViewName("forgotPinStep3");
					result = "Your credentials are correct,now you will get an email containing Pin Reset Link.";
					logger.debug(result);
				} else {
					result = "Security Answers provided by you is not correct.";
					logger.debug(result);
				}
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
				result = "Error" + e;
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
			result = "Validation Error" + bindingResult.toString();
		}
		modelView.addObject("result", result);
		logger.debug("showForgotPinStep3 >>");
		return modelView;
	}

	@RequestMapping(value = "/showResetPin", method = RequestMethod.GET)
	public ModelAndView showResetPinForm(@RequestParam("userInfo") String username,
			HttpSession session) {
		logger.debug(">> showResetPinForm");
		ModelAndView modelView = new ModelAndView("resetPin");
		User user;
		String result;
		try {
			user = userService.getUser(username);
			if (user.getForgetProcessInitiated().equals(true)) {
				session.setAttribute("user", user);
				modelView.addObject("resetPinForm", new ResetPinForm());
				modelView.setViewName("resetPin");
				result = "Success";
			} else {
				result = "Invalid URL.. Try Again";
			}
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			result = "Invalid UserName,Try Again";
		}
		modelView.addObject("result", result);
		logger.debug("showResetPinForm >>");
		return modelView;
	}

	@RequestMapping(value = "/resendEmailForPin", method = RequestMethod.GET)
	public ModelAndView resendEmailForPin(HttpSession session, HttpServletRequest request) {
		logger.debug(">> resendEmailForPin");
		ModelAndView modelView = new ModelAndView("forgotPinStep3");
		String result = "";
		try {
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form = (ForgotPasswordAndPinStep1Form) session
					.getAttribute("forgotPasswordAndPinStep1Form");
			String link = "http://localhost:8080" + request.getContextPath()
					+ "/showResetPin?userInfo=" + forgotPasswordAndPinStep1Form.getUserName();
			String email = (String) session.getAttribute("email");
			try {
				mailService.send(adminControllerHelper.prepareMailForPassword(
						forgotPasswordAndPinStep1Form, link, email));
			} catch (MailNotSentException e) {
				logger.error(e.getMessage());
			}
			result = "[ RESEND ] Your credentials are correct,now you will get an email containing Pin Reset Link.";
			logger.debug(result);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		modelView.addObject("result", result);
		logger.debug("resendEmailForPin >>");
		return modelView;
	}

	@RequestMapping(value = "/resetPin", method = RequestMethod.POST)
	public ModelAndView resetPin(@Valid @ModelAttribute ResetPinForm resetPinForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> resetPin");
		ModelAndView modelView = new ModelAndView("resetPin");
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				User user = (User) session.getAttribute("user");
				if (userService.changePin(user, resetPinForm)) {
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
		logger.debug("resetPin >>");
		return modelView;
	}

}