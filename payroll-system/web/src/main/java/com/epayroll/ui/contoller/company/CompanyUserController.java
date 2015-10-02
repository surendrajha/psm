package com.epayroll.ui.contoller.company;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.constant.company.SecurityInfoType;
import com.epayroll.entity.Company;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.exception.MailNotSentException;
import com.epayroll.form.ForgotInfoStep2Form;
import com.epayroll.form.ForgotPasswordAndPinStep1Form;
import com.epayroll.form.ForgotUserIdStep1Form;
import com.epayroll.form.GenerateOTPForm;
import com.epayroll.form.PinVerficationForm;
import com.epayroll.form.ResetPasswordForm;
import com.epayroll.form.ResetPinForm;
import com.epayroll.form.company.ChangeSecurityQnAForm;
import com.epayroll.form.company.ChangeUserEmailForm;
import com.epayroll.form.company.ChangeUserIDForm;
import com.epayroll.form.company.ChangeUserPasswordForm;
import com.epayroll.form.company.ChangeUserPinForm;
import com.epayroll.form.company.UserAddForm;
import com.epayroll.form.company.UserForm;
import com.epayroll.service.MailService;
import com.epayroll.service.UserService;
import com.epayroll.service.company.CompanyUserService;
import com.epayroll.spring.authorization.AuthorizationUtil;
import com.epayroll.ui.contoller.helper.CompanyUserControllerHelper;
import com.epayroll.utils.RandomNumberUtils;

@Controller
@RequestMapping("/company/user")
public class CompanyUserController {

	public Logger logger = LoggerFactory.getLogger(CompanyUserController.class);

	private Company getCompany(HttpSession session) {
		return (Company) session.getAttribute("company");
	}

	@Autowired
	private CompanyUserService companyUserService;

	@Autowired
	private CompanyUserControllerHelper companyUserControllerHelper;

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@RequestMapping("/showManageUsers")
	public ModelAndView showManageUsers(HttpSession session) {
		ModelAndView mav = new ModelAndView("userProfilePage");
		// User user = (User) session.getAttribute("user");
		User user = AuthorizationUtil.getLoginUser();
		Company company = AuthorizationUtil.getLoginCompany();
		try {
			mav.addObject("userProfile", userService.getUserProfile(user.getId()));
			mav.addObject("securityQues", userService.getSecurityQuestions());
			mav.addObject("states", userService.getStates());
			mav.addObject("userList", userService.getComapanyUsersExceptAdmin(company));

		} catch (Exception e) {
			logger.error("Error::", e);
			e.printStackTrace();
		}
		return mav;
	}

	// @RequestMapping(value = "/showAdminProfileForm/{companyUserId}", method =
	// RequestMethod.POST)
	// public ModelAndView showAdminProfileForm(@PathVariable Long
	// companyUserId) {
	// ModelAndView mav = new ModelAndView("update-company-admin-user");
	// try {
	// mav.addObject("companyUser",
	// companyUserService.getAdminUserProfile(companyUserId));
	// } catch (Exception e) {
	// System.out.println("ER::" + e.getLocalizedMessage());
	// }
	// return mav;
	// }
	@RequestMapping(value = "/updateCompanyUserProfile", method = RequestMethod.POST)
	public ModelAndView updateCompanyUserProfile(@Valid @ModelAttribute UserForm userForm,
			BindingResult result) {
		ModelAndView mav = new ModelAndView("company-user");
		if (!result.hasErrors()) {
			try {
				userService.updateAdminUserProfile(userForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mav;
	}

	/*
	 * Changing security Info [BEGIN]
	 */
	@RequestMapping(value = "/showChangeSecurityInfoForm", method = RequestMethod.POST)
	public ModelAndView showChangeSecurityInfoForm(@ModelAttribute GenerateOTPForm generateOTPForm) {
		ModelAndView mav = new ModelAndView("otp-form");
		// Setting changeSecurityInfoType and userId
		mav.addObject("otpForm", generateOTPForm);

		return mav;
	}

	@RequestMapping(value = "/generateOTP", method = RequestMethod.POST)
	public @ResponseBody
	String generateOTP(@ModelAttribute GenerateOTPForm otpForm) {
		try {
			otpForm = userService.generateOtp(otpForm);
			// send mail to user with OTP
			mailService.send(companyUserControllerHelper.prepareMail(otpForm));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	@RequestMapping(value = "/verifyOTP", method = RequestMethod.POST)
	public ModelAndView verifyOTP(@ModelAttribute GenerateOTPForm otpForm) {

		ModelAndView mav = new ModelAndView("otp-form");

		try {
			if (userService.verifyOTP(otpForm)) {

				switch (otpForm.getChangeSecurityInfoType()) {
				case SecurityInfoType.USERID:
					mav.setViewName("change-userid");
					ChangeUserIDForm changeUserIDForm = new ChangeUserIDForm();
					changeUserIDForm.setUserId(otpForm.getUserId());
					changeUserIDForm.setCurrentUserName(userService.getUser(otpForm.getUserId())
							.getUserName());
					mav.addObject("changeCompanyUserIDForm", changeUserIDForm);
					break;

				case SecurityInfoType.PASSWORD:
					mav.setViewName("change-password");
					ChangeUserPasswordForm changeUserPasswordForm = new ChangeUserPasswordForm();
					mav.addObject("changeCompanyUserPasswordForm", changeUserPasswordForm);
					break;

				case SecurityInfoType.PIN:
					mav.setViewName("change-pin");
					ChangeUserPinForm changeUserPinForm = new ChangeUserPinForm();
					mav.addObject("changeCompanyUserPinForm", changeUserPinForm);
					break;

				case SecurityInfoType.EMAIL:
					mav.setViewName("change-email");
					ChangeUserEmailForm changeUserEmailForm = new ChangeUserEmailForm();
					changeUserEmailForm.setCurrentEmail(otpForm.getEmail());
					mav.addObject("changeCompanyUserEmailForm", changeUserEmailForm);
					break;

				default:
					break;
				}
			} else {
				mav.addObject("error-message", "OTP doesn't match!");
			}
		} catch (Exception e) {
			System.out.println("ER::" + e.getLocalizedMessage());
		}

		return mav;
	}

	@RequestMapping(value = "/changeCompanyUserID", method = RequestMethod.POST)
	public ModelAndView changeCompanyUserID(@ModelAttribute ChangeUserIDForm changeUserIDForm) {

		ModelAndView mav = new ModelAndView("change-userid");
		try {
			if (userService.changeUserID(changeUserIDForm)) {
				mav.addObject("success-message", "User ID changed successfully!!");
				mav.setViewName("company-user");
			} else {
				mav.addObject("error-message", "Password doesn't match!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/changeCompanyUserPassword", method = RequestMethod.POST)
	public ModelAndView changeCompanyUserPassword(
			@ModelAttribute ChangeUserPasswordForm changeUserPasswordForm) {

		ModelAndView mav = new ModelAndView("change-password");
		try {
			if (userService.changeUserPassword(changeUserPasswordForm)) {
				mav.addObject("success-message", "Password changed successfully!!");
				mav.setViewName("company-user");
			} else {
				mav.addObject("error-message", "Password doesn't match!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/changeCompanyUserPin", method = RequestMethod.POST)
	public ModelAndView changeCompanyUserPin(@ModelAttribute ChangeUserPinForm changeUserPinForm) {

		ModelAndView mav = new ModelAndView("change-pin");
		try {
			if (userService.changeUserPin(changeUserPinForm)) {
				mav.addObject("success-message", "Security PIN changed successfully!!");
				mav.setViewName("company-user");
			} else {
				mav.addObject("error-message", "Password doesn't match!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/changeCompanyUserEmail", method = RequestMethod.POST)
	public ModelAndView changeCompanyUserEmail(
			@ModelAttribute ChangeUserEmailForm changeUserEmailForm) {

		ModelAndView mav = new ModelAndView("change-email");
		try {
			if (userService.changeUserEmail(changeUserEmailForm)) {
				mav.addObject("success-message", "Email changed successfully!!");
				mav.setViewName("company-user");
			} else {
				mav.addObject("error-message", "Password doesn't match!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/checkUserAvailability", method = RequestMethod.POST)
	public @ResponseBody
	String checkUserAvailability(@RequestParam String userName) {
		String message = "";
		try {
			if (userService.isUserNameAvailable(userName)) {
				message = "Available!";
			} else {
				message = "Not Available!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	/*
	 * Changing security Info [END]
	 */

	@RequestMapping("/showUpdateSecurityQnAForm/{userId}")
	public ModelAndView showUpdateSecurityQnAForm(@PathVariable Long userId) {
		ModelAndView mav = new ModelAndView("update-securityQnA");
		ChangeSecurityQnAForm changeSecurityQnAForm = new ChangeSecurityQnAForm();
		changeSecurityQnAForm.setUserId(userId);
		try {
			mav.addObject("securityQues", userService.getSecurityQuestions());
			mav.addObject("securityQnAList", userService.getUserSecurityQnAs(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("securityQnAForm", changeSecurityQnAForm);
		return mav;
	}

	@RequestMapping(value = "/updateSecurityQnA", method = RequestMethod.POST)
	public ModelAndView updateSecurityQnA(
			@ModelAttribute ChangeSecurityQnAForm changeSecurityQnAForm) {
		ModelAndView mav = new ModelAndView("update-securityQnA");
		try {
			if (userService.updateUserSequrityQnA(changeSecurityQnAForm)) {
				mav.addObject("message", "updated");
			} else {
				mav.addObject("message", "Current password doesn't match!");
				mav.setViewName("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("changeCompanyUserSecurityQnAForm", changeSecurityQnAForm);
		return mav;
	}

	/*
	 * ADDing New User [BEGIN]
	 */
	@RequestMapping("/showAddNewUserForm")
	public ModelAndView showAddNewUserForm(HttpSession session) {
		ModelAndView mav = new ModelAndView("add-company-user");
		UserAddForm companyUserFrom = new UserAddForm();
		companyUserFrom.setCompanyId(getCompany(session).getId());
		mav.addObject("roles", companyUserService.getRoles());
		mav.addObject("companyUserAddForm", companyUserFrom);
		return mav;
	}

	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public ModelAndView addNewUser(@ModelAttribute @Valid UserAddForm userAddForm,
			BindingResult result) {

		ModelAndView mav = new ModelAndView("add-company-user");
		if (!result.hasErrors()) {
			System.out.println("validated");
			try {
				// Generate user name and password, and setting it to user
				// form
				userAddForm.setUserName(RandomNumberUtils.getUserName());
				userAddForm.setPassword(RandomNumberUtils.getTempPassword());

				Long id = userService.addUser(userAddForm);
				if (id > 0) {
					userAddForm.setUserId(id);
					mailService.send(companyUserControllerHelper.prepareMail(userAddForm));
					mav.setViewName("redirect:/showManageUsers");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// mav.addObject("companyUserFrom", companyUserFrom);
		return mav;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(@ModelAttribute UserForm userFrom) {
		ModelAndView mav = new ModelAndView("user-registration");
		try {
			userService.registerUser(userFrom);
			mav.setViewName("login-form");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/*
	 * ADDing New User [END]
	 */

	@RequestMapping("/showUpdateUserForm/{companyUserId}")
	public ModelAndView showUpdateUserForm(@PathVariable Long companyUserId) {
		ModelAndView mav = new ModelAndView("update-company-user");
		try {
			UserForm userFrom = userService.getCompanyUserForm(companyUserId);
			mav.addObject("roles", companyUserService.getRoles());
			mav.addObject("states", userService.getStates());
			mav.addObject("companyUserFrom", userFrom);
		} catch (InstanceNotFoundException e) {
			System.out.println("Instance Exception aaa : " + e);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return mav;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute UserForm userFrom) {
		ModelAndView mav = new ModelAndView("update-company-user");
		try {
			userService.updateUser(userFrom);
			mav.setViewName("redirect:showManageUsers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.POST)
	public ModelAndView deleteUser(@PathVariable Long userId) {
		ModelAndView mav = new ModelAndView();
		try {
			userService.deleteUser(userId);
			mav.setViewName("redirect:showManageUsers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/resendInvitation/{userId}", method = RequestMethod.POST)
	public @ResponseBody
	String resendInvitation(@PathVariable Long userId) {
		try {
			UserForm userForm = userService.resetLoginCredential(userId);
			/*
			 * send mail to user with reset username and password
			 */
			mailService.send(companyUserControllerHelper.prepareMail(userForm));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

	@RequestMapping(value = "/showForgotPasswordStep1Form", method = RequestMethod.GET)
	public ModelAndView showForgotPasswordStep1Form() {
		logger.debug(">> showForgotPasswordStep1Form");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("forgotPasswordAndPinStep1Form", new ForgotPasswordAndPinStep1Form());
		modelView.setViewName("forgotPasswordStep1");
		logger.debug("showForgotPasswordStep1Form >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotPasswordStep2Form", method = RequestMethod.POST)
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
						+ "/showResetPasswordForm?userInfo="
						+ forgotPasswordAndPinStep1Form.getUserName();
				String email = userService.checkSecurityAnswersAndGetEmailForPassword(
						forgotPasswordAndPinStep1Form, forgotInfoStep2Form);
				if (email != null) {
					try {
						mailService.send(companyUserControllerHelper.prepareMailForPassword(
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

	@RequestMapping(value = "/showResetPasswordForm", method = RequestMethod.GET)
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

	@RequestMapping(value = "/showForgotUserIdStep1Form", method = RequestMethod.GET)
	public ModelAndView showForgotUserIdStep1Form() {
		logger.debug(">> showForgotUserIdStep1Form");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("forgotUserIdStep1Form", new ForgotUserIdStep1Form());
		modelView.setViewName("forgotUserNameStep1");
		logger.debug("showForgotUserIdStep1Form >>");
		return modelView;
	}

	@RequestMapping(value = "/showForgotUserIdStep2Form", method = RequestMethod.POST)
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
						mailService.send(companyUserControllerHelper.prepareMailForUserId(
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
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("showForgotUserIdStep3 >>");
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
						mailService.send(companyUserControllerHelper.prepareMailForPin(
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

	/**
	 * showLoginForm Method not completed......(testing...)
	 */
	@RequestMapping(value = "/showLoginForm", method = RequestMethod.GET)
	public ModelAndView showLoginForm() {
		logger.debug(">> showLoginForm");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("loginForm");
		modelView.setViewName("loginForm");
		logger.debug("showLoginForm >>");
		return modelView;
	}

	/**
	 * login Method not completed......(testing...)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login() {
		logger.debug(">> login");
		ModelAndView modelView = new ModelAndView();
		// User companyUser;
		// try {
		// companyUser = companyUserService.getCompanyUser(1L);
		// userControllerHelper.getSortedMessages(companyUser.getSystemMessages());
		// userControllerHelper.getSortedTaskes(companyUser.getSystemTasks());
		// logger.debug("Messages::"
		// +
		// userControllerHelper.getSortedMessages(companyUser.getSystemMessages()));
		// logger.debug("Taskes::"
		// +
		// userControllerHelper.getSortedTaskes(companyUser.getSystemTasks()));
		// logger.debug("DONE::::");
		// modelView.setViewName("companyDashBoardPage");
		// } catch (InstanceNotFoundException e) {
		// e.printStackTrace();
		// }
		logger.debug("login >>");
		return modelView;

	}

	// when userName and password verified then pinVerificationForm open
	// set userName in hidden variable and count = 0

	@RequestMapping(value = "/verifyPin", method = RequestMethod.POST)
	public ModelAndView verifyPin(@Valid @ModelAttribute PinVerficationForm pinVerficationForm,
			BindingResult bindingResult) {
		logger.debug(">> verifyPin");
		ModelAndView modelView = new ModelAndView("pinVerificationForm");
		if (!bindingResult.hasErrors()) {
			try {
				if (userService.verifyPin(pinVerficationForm)) {
					modelView.setViewName("userHome");
					logger.debug("TESTING ::: 'SUCCESS' Pin Verified....");
				}
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
				// TODO user have to remove from session
				modelView.setViewName("loginForm");
				logger.debug("TESTING ::: Please try again ....");
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("pinVerification >>");
		return modelView;
	}
}