package com.epayroll.ui.contoller.admin.console;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.constant.company.SecurityInfoType;
import com.epayroll.entity.Role;
import com.epayroll.entity.UsState;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.GenerateOTPForm;
import com.epayroll.form.company.ChangeSecurityQnAForm;
import com.epayroll.form.company.ChangeUserEmailForm;
import com.epayroll.form.company.ChangeUserIDForm;
import com.epayroll.form.company.ChangeUserPasswordForm;
import com.epayroll.form.company.ChangeUserPinForm;
import com.epayroll.form.company.UserAddForm;
import com.epayroll.form.company.UserForm;
import com.epayroll.service.MailService;
import com.epayroll.service.UserService;
import com.epayroll.service.adminConsole.AdminUserService;
import com.epayroll.ui.contoller.helper.AdminUserControllerHelper;
import com.epayroll.utils.RandomNumberUtils;

@Controller
@RequestMapping("/company/user")
public class AdminUserController {

	public Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AdminUserControllerHelper adminUserControllerHelper;

	@Autowired
	private MailService mailService;

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping("/showAdminManageUsers")
	public ModelAndView showAdminManageUsers(HttpSession session) {
		logger.debug(">>showAdminManageUsers");
		ModelAndView mav = new ModelAndView("adminUserProfilePage");
		User user = (User) session.getAttribute("user");
		try {
			mav.addObject("userProfile", userService.getUserProfile(user.getId()));
			mav.addObject("securityQues", userService.getSecurityQuestions());
			mav.addObject("states", userService.getStates());
			logger.debug("showAdminManageUsers>>");
		} catch (Exception e) {
			logger.error("Error::", e);
			e.printStackTrace();
		}
		return mav;

	}

	@RequestMapping(value = "/updateAdminUserProfile", method = RequestMethod.POST)
	public ModelAndView updateAdminUserProfile(@Valid @ModelAttribute UserForm userForm,
			BindingResult result) {
		logger.debug(">>updateAdminUserProfile");
		ModelAndView mav = new ModelAndView("company-user");
		if (!result.hasErrors()) {
			try {
				userService.updateAdminUserProfile(userForm);
				logger.debug("updateAdminUserProfile>>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mav;
	}

	/*
	 * Changing security Info [BEGIN]
	 */
	@RequestMapping(value = "/showAdminUserChangeSecurityInfoForm", method = RequestMethod.POST)
	public ModelAndView showAdminUserChangeSecurityInfoForm(
			@ModelAttribute GenerateOTPForm generateOTPForm) {
		ModelAndView mav = new ModelAndView("otp-form");
		logger.debug("generateOTPForm::::" + generateOTPForm);
		// Setting changeSecurityInfoType and userId
		mav.addObject("otpForm", generateOTPForm);

		return mav;
	}

	@RequestMapping(value = "/generateOTP", method = RequestMethod.POST)
	public ModelAndView generateOTP(@ModelAttribute GenerateOTPForm otpForm) {
		ModelAndView mav = new ModelAndView("otp-form");
		try {
			otpForm = userService.generateOtp(otpForm);
			// send mail to user with OTP
			mailService.send(adminUserControllerHelper.prepareMail(otpForm));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
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

	@RequestMapping(value = "/changeAdminUserID", method = RequestMethod.POST)
	public ModelAndView changeAdminUserID(@ModelAttribute ChangeUserIDForm changeUserIDForm) {

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

	@RequestMapping(value = "/changeAdminUserPassword", method = RequestMethod.POST)
	public ModelAndView changeAdminUserPassword(
			@ModelAttribute ChangeUserPasswordForm changeUserPasswordForm) {

		ModelAndView mav = new ModelAndView("change-password");
		try {
			if (userService.changeUserPassword(changeUserPasswordForm)) {
				mav.addObject("success-message", "Password changed successfully!!");
				mav.setViewName("admin-user");
			} else {
				mav.addObject("error-message", "Password doesn't match!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/changeAdminUserPin", method = RequestMethod.POST)
	public ModelAndView changeAdminUserPin(@ModelAttribute ChangeUserPinForm changeUserPinForm) {

		ModelAndView mav = new ModelAndView("change-pin");
		try {
			if (userService.changeUserPin(changeUserPinForm)) {
				mav.addObject("success-message", "Security PIN changed successfully!!");
				mav.setViewName("admin-user");
			} else {
				mav.addObject("error-message", "Password doesn't match!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/changeAdminUserEmail", method = RequestMethod.POST)
	public ModelAndView changeAdminUserEmail(@ModelAttribute ChangeUserEmailForm changeUserEmailForm) {

		ModelAndView mav = new ModelAndView("change-email");
		try {
			if (userService.changeUserEmail(changeUserEmailForm)) {
				mav.addObject("success-message", "Email changed successfully!!");
				mav.setViewName("admin-user");
			} else {
				mav.addObject("error-message", "Password doesn't match!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/checkUserAvailability/{userName}", method = RequestMethod.POST)
	public ModelAndView checkUserAvailability(@PathVariable String userName) {
		String message = "";
		ModelAndView mav = new ModelAndView("adminUser");
		try {
			if (userService.isUserNameAvailable(userName)) {
				message = "Available!";
				logger.debug("message" + message);

			} else {
				message = "Not Available!";
				logger.debug("message" + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
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

	@RequestMapping("/showUsersList")
	public ModelAndView showUsersList(HttpSession session) {
		logger.debug(">> show Users List");
		ModelAndView mav = new ModelAndView("showUsersListPage");
		try {
			List<UserForm> userForms = userService.getAdminUsersExceptAdmin();
			mav.addObject("userList", userForms);
			logger.error("userList::", userForms);
		} catch (Exception e) {
			logger.error("Error::", e);
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping("/showAddNewUserForm")
	public ModelAndView showAddNewUserForm(HttpSession session) {
		ModelAndView mav = new ModelAndView("add-admin-user");
		UserAddForm userAddForm = new UserAddForm();
		List<Role> roles = adminUserService.getRoles();
		mav.addObject("roles", roles);
		logger.debug("roles::::" + roles);
		mav.addObject("adminUserAddForm", userAddForm);
		return mav;
	}

	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public ModelAndView addNewUser(@ModelAttribute @Valid UserAddForm userAddForm,
			BindingResult result) {
		logger.debug("userAddForm::::" + userAddForm);
		ModelAndView mav = new ModelAndView("add-user");
		if (!result.hasErrors()) {
			System.out.println("validated");
			try {
				// Generate user name and password, and setting it to user
				// form
				userAddForm.setUserName(RandomNumberUtils.getUserName());
				userAddForm.setPassword(RandomNumberUtils.getTempPassword());

				Long id = userService.addUser(userAddForm);
				logger.debug("id:::::" + id);
				if (id > 0) {
					userAddForm.setUserId(id);
					mailService.send(adminUserControllerHelper.prepareMail(userAddForm));
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

	@RequestMapping("/showUpdateUserForm/{adminUserId}")
	public ModelAndView showUpdateUserForm(@PathVariable Long adminUserId) {
		ModelAndView mav = new ModelAndView("update-admin-user");
		try {
			UserForm userFrom = userService.getAdminUserForm(adminUserId);
			logger.debug("userFrom:::" + userFrom);
			List<Role> roles = adminUserService.getRoles();
			mav.addObject("roles", roles);
			logger.debug("roles:::" + roles);
			List<UsState> usStates = userService.getStates();
			mav.addObject("states", usStates);
			logger.debug("usStates::::" + usStates);
			mav.addObject("userFrom", userFrom);
		} catch (InstanceNotFoundException e) {
			System.out.println("Instance Exception aaa : " + e);
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return mav;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute UserForm userFrom) {
		ModelAndView mav = new ModelAndView("update-admin-user");
		try {
			userService.updateUser(userFrom);
			mav.setViewName("redirect:showManageUsers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/deleteAdminUser/{userId}", method = RequestMethod.POST)
	public ModelAndView deleteAdminUser(@PathVariable Long userId) {
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
			mailService.send(adminUserControllerHelper.prepareMail(userForm));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

}