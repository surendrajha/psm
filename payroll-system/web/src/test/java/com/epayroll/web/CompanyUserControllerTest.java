package com.epayroll.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.form.ForgotPasswordAndPinStep1Form;
import com.epayroll.form.ForgotUserIdStep1Form;
import com.epayroll.form.company.UserForm;
import com.epayroll.service.UserService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyUserController;

public class CompanyUserControllerTest extends TestRoot {

	@Autowired
	private CompanyUserController companyUserController;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	// private HttpSession session;

	// @Before
	// public void setCompanyInSession() throws Exception {
	// System.out.println("before");
	// session = request.getSession();
	// session.setAttribute("company", companyService.findCompany(1L));
	// }
	//
	// @After
	// public void removeCompanyFromSession() {
	// System.out.println("after...");
	// session.removeAttribute("company");
	// session.invalidate();
	// }

	@Ignore
	@Test
	public void getManageUsersPage() throws Exception {

		request.setMethod("GET");
		request.setRequestURI("/showManageUsers");
		request.getSession().setAttribute("user", userService.getUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);

		ModelAndViewAssert.assertViewName(mav, "userProfilePage");
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "userProfile", UserForm.class);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "securityQues");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "states");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "userList");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Ignore
	@Test
	public void updateCompanyUserProfile() {
		System.out.println("start.......");
		try {
			request.setMethod("POST");
			request.setRequestURI("/updateCompanyUserProfile");

			request.setParameter("id", "4");
			request.setParameter("firstName", "SSSSSSSSSSSSSSSSS");
			request.setParameter("lastName", "LLLLLLLLL");
			request.setParameter("dob", "12/12/2012");
			request.setParameter("ssn", "121212121");
			request.setParameter("phone", "9595959595");
			request.setParameter("ext", "345353");
			request.setParameter("fax", "9595959595");
			request.setParameter("streetAddress", "SASASASAS SSA sddsd");
			request.setParameter("state", "2");
			request.setParameter("city", "city");
			request.setParameter("pinZip", "666666");
			request.setParameter("county", "county");

			ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);

			ModelAndViewAssert.assertViewName(mav, "company-user");

			for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()
						+ "\n");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("completed!");
	}

	@Ignore
	@Test
	public void addNewUser() throws Exception {

		// show addUser form
		// request.setMethod("GET");
		// request.setRequestURI("/showAddNewUserForm");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		//
		// ModelAndViewAssert.assertViewName(mav, "add-company-user");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "companyUserFrom",
		// AddCompanyUserFrom.class);
		// ModelAndViewAssert.assertModelAttributeAvailable(mav, "roles");

		// add new user
		request.setMethod("POST");
		request.setRequestURI("/addNewUser");
		request.setParameter("companyId", "1");
		request.setParameter("firstName", "Surendra");
		request.setParameter("lastName", "Jha");
		request.setParameter("email", "surendracc.in");
		request.setParameter("role", "2");

		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		// ModelAndViewAssert.assertViewName(mav, "redirect:/showManageUsers");

		// change password link hit
		// request.setMethod("GET");
		// request.setRequestURI("/login/showChangePasswordForm/5/VJS145241/REM5OVIwSE0=");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// loginController);
		// ModelAndViewAssert.assertViewName(mav, "change-password");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "changePasswordForm",
		// ChangePasswordForm.class);

		// change password form submit
		// request.setMethod("POST");
		// request.setRequestURI("/login/changePassword");
		// request.setParameter("companyUserId", "4");
		// request.setParameter("currentPassword", "AAAA");
		// request.setParameter("newPassword", "BBBB");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// loginController);
		// ModelAndViewAssert.assertViewName(mav, "user-registration");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "companyUserForm",
		// CompanyUserForm.class);
		// ModelAndViewAssert.assertModelAttributeAvailable(mav, "states");
		// ModelAndViewAssert.assertModelAttributeAvailable(mav,
		// "securityQuestionForm");

		// request.setMethod("POST");
		// request.setRequestURI("/registration");
		// request.setParameter("id", "4");
		// request.setParameter("dob", "10/25/1990");
		// request.setParameter("phone", "9595959595");
		// request.setParameter("streetAddress", "ssssss ssss ssss sss");
		// request.setParameter("city", "Bhopal");
		// request.setParameter("state", "1");
		// request.setParameter("county", "India");
		// request.setParameter("pinZip", "787878");
		// // set security QnA
		// request.setParameter("answer1", "mmmmmm");
		// request.setParameter("securityQues2", "6");
		// request.setParameter("answer2", "66666");
		// request.setParameter("securityQues3", "9");
		// request.setParameter("answer3", "99999");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "login-form");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	@Ignore
	@Test
	public void changeSecurityData() throws Exception {
		/*
		 * Changing user Name
		 */

		// 1. open form for otp generation
		// request.setMethod("POST");
		// request.setRequestURI("/showChangeSecurityInfoForm");
		// request.setParameter("companyUserId", "1");
		// request.setParameter("changeSecurityInfoType", "USERID");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "otp-form");

		// 2. generate otp and send to user
		// request.setMethod("POST");
		// request.setRequestURI("/generateOTP");
		// request.setParameter("companyUserId", "1");
		// request.setParameter("changeSecurityInfoType", "USERID");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "otp-form");

		// 3. verify the otp and open the form where user can change the
		// UserName
		// request.setMethod("POST");
		// request.setRequestURI("/verifyOTP");
		//
		// request.setParameter("companyUserId", "1");
		// request.setParameter("changeSecurityInfoType", "USERID");
		// request.setParameter("otp", "402287");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "change-userid");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "changeSecurityInfoForm",
		// CompanyUserSecurityInfoForm.class);

		// 4. change UserName
		// request.setMethod("POST");
		// request.setRequestURI("/changeSecurityInfo");
		//
		// request.setParameter("companyUserId", "1");
		// request.setParameter("changeSecurityInfoType", "USERID");
		// request.setParameter("currentPassword", "ssss");
		// request.setParameter("newUserName", "sk12345");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "company-user");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "changeSecurityInfoForm",
		// CompanyUserSecurityInfoForm.class);

		/*
		 * Changing Password
		 */

		// 1. open form for otp generation
		// same as above

		// 2. generate otp and send to user
		// same as above

		// 3. verify the otp and open the form where user can change the
		// Password
		// request.setMethod("POST");
		// request.setRequestURI("/verifyOTP");
		//
		// request.setParameter("companyUserId", "1");
		// request.setParameter("changeSecurityInfoType", "PASSWORD");
		// request.setParameter("otp", "111111");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "change-password");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "changeSecurityInfoForm",
		// CompanyUserSecurityInfoForm.class);

		// 4. change the Password
		// request.setMethod("POST");
		// request.setRequestURI("/changeSecurityInfo");
		//
		// request.setParameter("companyUserId", "1");
		// request.setParameter("changeSecurityInfoType", "PASSWORD");
		// request.setParameter("currentPassword", "aaaa");
		// request.setParameter("newPassword", "ssss");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "company-user");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "changeSecurityInfoForm",
		// CompanyUserSecurityInfoForm.class);

		/*
		 * Changing Pin
		 */

		// 1. open form for otp generation
		// same as above

		// 2. generate otp and send to user
		// same as above

		// 3. verify the otp and open the form where user can change the pin
		// same as above

		// 4. change the PIN
		// request.setMethod("POST");
		// request.setRequestURI("/changeSecurityInfo");
		//
		// request.setParameter("companyUserId", "1");
		// request.setParameter("changeSecurityInfoType", "PIN");
		// request.setParameter("currentPassword", "ssss");
		// request.setParameter("currentPin", "12345");
		// request.setParameter("newPin", "22222222");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "company-user");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "changeSecurityInfoForm",
		// CompanyUserSecurityInfoForm.class);

		/*
		 * Changing Email
		 */

		// 1. open form for otp generation
		// same as above

		// 2. generate otp and send to user
		// same as above

		// 3. verify the otp and open the form where user can change the Email
		// same as above

		// 4. change the Email
		request.setMethod("POST");
		request.setRequestURI("/changeSecurityInfo");

		request.setParameter("companyUserId", "1");
		request.setParameter("changeSecurityInfoType", "EMAIL");
		request.setParameter("currentPassword", "ssss");
		request.setParameter("newEmail", "aaa.com");

		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "company-user");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	@Ignore
	@Test
	public void changeSecurityQnA() throws Exception {

		// showUpdateSecurityQnAForm
		// request.setMethod("GET");
		// request.setRequestURI("/showUpdateSecurityQnAForm/1");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// controller);
		// ModelAndViewAssert.assertViewName(mav, "update-securityQnA");
		// ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav,
		// "securityQnAForm",
		// CompanyUserForm.class);
		// ModelAndViewAssert.assertModelAttributeAvailable(mav,
		// "securityQues");
		// ModelAndViewAssert.assertModelAttributeAvailable(mav,
		// "securityQnAList");

		// update the values
		request.setMethod("POST");
		request.setRequestURI("/updateSecurityQnA");

		request.setParameter("id", "1");
		request.setParameter("password", "ssss");
		request.setParameter("securityQues1", "");
		request.setParameter("answer1", "maa");
		request.setParameter("securityQues2", "6");
		request.setParameter("answer2", "aaaa");
		request.setParameter("securityQues3", "9");
		request.setParameter("answer3", "bbbb");

		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);

		ModelAndViewAssert.assertModelAttributeValue(mav, "message", "updated");
		ModelAndViewAssert.assertViewName(mav, "company-user");
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "securityQnAForm",
				UserForm.class);

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	@Ignore
	@Test
	public void updateUser() throws Exception {
		// showUpdateForm
		request.setMethod("GET");
		request.setRequestURI("/showUpdateUserForm/2");

		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "update-company-user");
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "companyUserFrom",
				UserForm.class);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "roles");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "states");

		// updateUser
		// request.setMethod("POST");
		// request.setRequestURI("/updateUser");
		//
		// request.setParameter("id", "2");
		// request.setParameter("role", "3");
		// request.setParameter("firstName", "SSSSSSSSSSSSSSSSS");
		// request.setParameter("lastName", "LLLLLLLLL");
		// // request.setParameter("dob", "12-12-12");
		// request.setParameter("ssn", "121212121");
		// request.setParameter("phone", "9595959595");
		// request.setParameter("ext", "345353");
		// request.setParameter("fax", "9595959595");
		// request.setParameter("streetAddress", "SASASASAS SSA sddsd");
		// request.setParameter("state", "2");
		// request.setParameter("city", "city");
		// request.setParameter("pinZip", "666666");
		// request.setParameter("county", "county");
		//
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// companyUserController);
		//
		// ModelAndViewAssert.assertViewName(mav, "redirect:showManageUsers");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	@Ignore
	@Test
	public void deleteuser() throws Exception {

		request.setMethod("POST");
		request.setRequestURI("/deleteUser/3");

		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "redirect:showManageUsers");
	}

	@Ignore
	@Test
	public void resendInvitation() throws Exception {

		request.setMethod("POST");
		request.setRequestURI("/resendInvitation/4");

		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	@Ignore
	@Test
	public void showForgotPasswordStep1Form() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showForgotPasswordStep1Form");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "forgotPasswordStep1Form");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "forgotPasswordAndPinStep1Form");
	}

	@Ignore
	@Test
	public void showForgotPasswordStep2Form() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showForgotPasswordStep2Form");

		// when userName matched (if condition)

		request.setParameter("userName", "Rajul");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "forgotInfoStep2Form");
		ModelAndViewAssert.assertViewName(mav, "forgotInfoStep2Form");

		// else

		// request.setParameter("userName", "bbbb");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// companyUserController);
		// ModelAndViewAssert.assertViewName(mav, "forgotPasswordStep1Form");
	}

	ForgotPasswordAndPinStep1Form getForgotPasswordAndPinStep1Form() {
		ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form = new ForgotPasswordAndPinStep1Form();
		forgotPasswordAndPinStep1Form.setUserName("Rajul");
		return forgotPasswordAndPinStep1Form;
	}

	@Ignore
	@Test
	public void showForgotPasswordStep3() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showForgotPasswordStep3");
		request.getSession().setAttribute("forgotPasswordAndPinStep1Form",
				this.getForgotPasswordAndPinStep1Form());
		request.setParameter("securityQuestionId1", "1");
		request.setParameter("securityQuestionId2", "3");
		request.setParameter("securityAnswer1", "Ans");
		request.setParameter("securityAnswer2", "Ans");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);

		// if setParameter are matched with database values

		ModelAndViewAssert.assertViewName(mav, "forgotPasswordStep3Page");

		// else

		// ModelAndViewAssert.assertViewName(mav, "forgotInfoStep2Form");
	}

	@Ignore
	@Test
	public void showResetPsasswordForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showResetPsasswordForm");
		request.setParameter("userInfo", "Rajul");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "resetPasswordForm");
	}

	@Ignore
	@Test
	public void resetPassword() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/resetPassword");
		request.getSession().setAttribute("user", userService.getUser("Rajul"));

		// when newPassword and confirmPassword are same (if condition)

		request.setParameter("newPassword", "bl");
		request.setParameter("confirmPassword", "bl");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "loginForm");

		// when newPassword and confirmPassword are different (else condition)

		// request.setParameter("newPassword", "a111");
		// request.setParameter("confirmPassword", "a11");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// companyUserController);
		// ModelAndViewAssert.assertViewName(mav, "resetPasswordForm");

	}

	@Ignore
	@Test
	public void showForgotUserIdStep1Form() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showForgotUserIdStep1Form");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "forgotUserIdStep1Form");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "forgotUserIdStep1Form");
	}

	@Ignore
	@Test
	public void showForgotUserIdStep2Form() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showForgotUserIdStep2Form");

		// when email and dob matched (if condition)

		request.setParameter("email", "rajul@i-techsoftware.com");
		request.setParameter("dob", "10/30/1988");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "forgotInfoStep2Form");
		ModelAndViewAssert.assertViewName(mav, "forgotInfoStep2Form");

		// else

		// request.setParameter("email", "rajul@i-techsoftware.com");
		// request.setParameter("dob", "23");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// companyUserController);
		// ModelAndViewAssert.assertViewName(mav, "forgotUserIdStep1Form");
	}

	ForgotUserIdStep1Form getForgotUserIdStep1Form() throws ParseException {
		ForgotUserIdStep1Form forgotUserIdStep1Form = new ForgotUserIdStep1Form();
		forgotUserIdStep1Form.setEmailId("rajul@i-techsoftware.com");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date d = simpleDateFormat.parse("10-30-1988");
		forgotUserIdStep1Form.setDob(d);
		return forgotUserIdStep1Form;
	}

	@Ignore
	@Test
	public void showForgotUserIdStep3() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showForgotUserIdStep3");
		request.getSession().setAttribute("forgotUserIdStep1Form", this.getForgotUserIdStep1Form());
		request.setParameter("securityQuestionId1", "1");
		request.setParameter("securityQuestionId2", "3");
		request.setParameter("securityAnswer1", "Ans");
		request.setParameter("securityAnswer2", "Ans13");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);

		// if setParameter are matched with database values
		ModelAndViewAssert.assertViewName(mav, "forgotUserIdStep3Page");

		// else
		// ModelAndViewAssert.assertViewName(mav, "forgotInfoStep2Form");
	}

	@Ignore
	@Test
	public void showForgotPinStep1Form() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showForgotPinStep1Form");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "forgotPinStep1Form");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "forgotPasswordAndPinStep1Form");
	}

	@Ignore
	@Test
	public void showForgotPinStep2Form() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showForgotPinStep2Form");

		// when userName matched (if condition)

		request.setParameter("userName", "Rajul");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "forgotInfoStep2Form");
		ModelAndViewAssert.assertViewName(mav, "forgotInfoStep2Form");

		// else

		// request.setParameter("userName", "bbbb");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// companyUserController);
		// ModelAndViewAssert.assertViewName(mav, "forgotPinStep1Form");
	}

	@Ignore
	@Test
	public void showForgotPinStep3() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showForgotPinStep3");
		request.getSession().setAttribute("forgotPasswordAndPinStep1Form",
				this.getForgotPasswordAndPinStep1Form());
		request.setParameter("securityQuestionId1", "1");
		request.setParameter("securityQuestionId2", "3");
		request.setParameter("securityAnswer1", "Ans");
		request.setParameter("securityAnswer2", "Ans13");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);

		// if setParameter are matched with database values

		ModelAndViewAssert.assertViewName(mav, "forgotPinStep3Page");

		// else

		// ModelAndViewAssert.assertViewName(mav, "forgotInfoStep2Form");
	}

	@Ignore
	@Test
	public void showResetPinForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showResetPinForm");
		request.setParameter("userInfo", "Rajul");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "resetPinForm");
	}

	@Ignore
	@Test
	public void resetPin() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/resetPin");
		request.getSession().setAttribute("user", userService.getUser("Rajul"));

		// when newPassword and confirmPassword are same (if condition)

		request.setParameter("newPassword", "bl");
		request.setParameter("confirmPassword", "bl");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "loginForm");

		// when newPassword and confirmPassword are different (else condition)

		// request.setParameter("newPassword", "a111");
		// request.setParameter("confirmPassword", "a11");
		// ModelAndView mav = handlerAdapter.handle(request, response,
		// companyUserController);
		// ModelAndViewAssert.assertViewName(mav, "resetPasswordForm");

	}

	// @Ignore
	@Test
	public void verifyPin() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/verifyPin");
		request.setParameter("userName", "Rajul");
		request.setParameter("pin", "dl");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
	}

	@Ignore
	@Test
	public void login() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/login");
		ModelAndView mav = handlerAdapter.handle(request, response, companyUserController);
		ModelAndViewAssert.assertViewName(mav, "companyDashBoardPage");
	}
}
