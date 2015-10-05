package com.epayroll.web;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.form.company.UserForm;
import com.epayroll.service.UserService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.admin.console.AdminUserController;

public class AdminUserControllerTest extends TestRoot {

	@Autowired
	private AdminUserController adminUserController;

	@Autowired
	private UserService userService;

	// @Ignore
	// @Test
	public void showAdminManageUsers() throws Exception {

		request.setMethod("GET");
		request.setRequestURI("/showAdminManageUsers");
		request.getSession().setAttribute("user", userService.getUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);

		ModelAndViewAssert.assertViewName(mav, "adminUserProfilePage");
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "userProfile", UserForm.class);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "securityQues");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "states");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Test
	public void updateAdminUserProfile() {
		System.out.println("start.......");
		try {
			request.setMethod("POST");
			request.setRequestURI("/updateAdminUserProfile");

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

			ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);

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

	// @Test
	public void showAdminUserChangeSecurityInfoForm() throws Exception {
		System.out.println("start.......");

		request.setMethod("POST");
		request.setRequestURI("/showAdminUserChangeSecurityInfoForm");
		request.setParameter("userId", "1");
		request.setParameter("changeSecurityInfoType", "USERID");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "otp-form");
		System.out.println("completed!");
	}

	// @Test
	public void generateOTP() throws Exception {
		System.out.println("start.......");

		request.setMethod("POST");
		request.setRequestURI("/generateOTP");
		request.setParameter("userId", "1");
		request.setParameter("changeSecurityInfoType", "USERID");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "otp-form");
		System.out.println("completed!");
	}

	// @Test
	public void verifyOTP() throws Exception {
		System.out.println("start.......");

		request.setMethod("POST");
		request.setRequestURI("/verifyOTP");
		request.setParameter("userId", "1");
		request.setParameter("changeSecurityInfoType", "USERID");
		request.setParameter("otp", "218551");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "change-userid");
		System.out.println("completed!");
	}

	// @Test
	public void changeAdminUserID() throws Exception {
		System.out.println("start.......");
		request.setMethod("POST");
		request.setRequestURI("/changeAdminUserID");
		request.setParameter("userId", "1");
		request.setParameter("currentPassword", "pankaj");
		request.setParameter("newUserName", "pankajp");
		request.setParameter("confirmNewUserName", "pankajp");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "company-user");
		System.out.println("completed!");
	}

	// @Test
	public void changeAdminUserPassword() throws Exception {
		System.out.println("start.......");
		request.setMethod("POST");
		request.setRequestURI("/changeAdminUserPassword");
		request.setParameter("userId", "1");
		request.setParameter("currentPassword", "pankajp");
		request.setParameter("newPassword", "pankajp");
		request.setParameter("confirmNewPassword", "pankajp");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "admin-user");
		System.out.println("completed!");
	}

	// @Test
	public void changeAdminUserPin() throws Exception {
		System.out.println("start.......");
		request.setMethod("POST");
		request.setRequestURI("/changeAdminUserPin");
		request.setParameter("userId", "1");
		request.setParameter("currentPassword", "pankajp");
		request.setParameter("currentPin", "0001");
		request.setParameter("newPin", "0004");
		request.setParameter("confirmNewPin", "0004");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "admin-user");
		System.out.println("completed!");
	}

	// @Test
	public void changeAdminUserEmail() throws Exception {
		System.out.println("start.......");
		request.setMethod("POST");
		request.setRequestURI("/changeAdminUserEmail");
		request.setParameter("userId", "1");
		request.setParameter("currentPassword", "pankajp");
		request.setParameter("currentEmail", "uma@i-techsoftware.com");
		request.setParameter("newEmail", "pankaj@i-techsoftware.com");
		request.setParameter("confirmNewEmail", "pankaj@i-techsoftware.com");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "admin-user");
		System.out.println("completed!");
	}

	// @Test
	public void checkUserAvailability() throws Exception {
		System.out.println("start.......");
		request.setMethod("POST");
		request.setRequestURI("/checkUserAvailability/uma");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "adminUser");
		System.out.println("completed!");
	}

	// @Test
	public void showUpdateSecurityQnAForm() throws Exception {
		System.out.println("start.......");
		request.setRequestURI("/showUpdateSecurityQnAForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "update-securityQnA");
		System.out.println("completed!");
	}

	// @Test
	public void updateSecurityQnA() throws Exception {
		System.out.println("start.......");
		request.setMethod("POST");
		request.setRequestURI("/updateSecurityQnA");
		request.setParameter("userId", "1");
		request.setParameter("currentPassword", "pankajp");
		request.setParameter("securityQues3", "2");
		request.setParameter("answer3", "ans 2 updated");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "update-securityQnA");
		System.out.println("completed!");
	}

	/*
	 * ADDing New User [BEGIN]
	 */

	// @Test
	public void showUsersList() throws Exception {
		System.out.println("start.......");
		request.setRequestURI("/showUsersList");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "showUsersListPage");
		System.out.println("completed!");
	}

	// @Test
	public void showAddNewUserForm() throws Exception {
		System.out.println("start.......");
		request.setRequestURI("/showAddNewUserForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "add-admin-user");
		System.out.println("completed!");
	}

	// @Test
	public void addNewUser() throws Exception {
		// add new user
		request.setMethod("POST");
		request.setRequestURI("/addNewUser");
		request.setParameter("firstName", "uma");
		request.setParameter("lastName", "Saraswat");
		request.setParameter("email", "uma@i-techsoftware.com");
		request.setParameter("role", "2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "add-user");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Test
	public void registration() throws Exception {
		// add new user
		request.setMethod("POST");
		request.setRequestURI("/registration");
		request.setParameter("id", "2");
		request.setParameter("dob", "10/25/1990");
		request.setParameter("phone", "9595959595");
		request.setParameter("streetAddress", "ssssss ssss ssss sss");
		request.setParameter("city", "Bhopal");
		request.setParameter("state", "1");
		request.setParameter("county", "India");
		request.setParameter("pinZip", "787878");
		// set security QnA
		request.setParameter("answer1", "mmmmmm");
		request.setParameter("securityQues2", "2");
		request.setParameter("answer2", "66666");
		request.setParameter("securityQues3", "3");
		request.setParameter("answer3", "99999");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "login-form");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Test
	public void showUpdateUserForm() throws Exception {
		// showUpdateForm
		request.setMethod("GET");
		request.setRequestURI("/showUpdateUserForm/3");

		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "update-admin-user");
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "userFrom", UserForm.class);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "roles");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "states");
	}

	// @Test
	public void updateUser() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateUser");
		request.setParameter("id", "3");
		request.setParameter("role", "3");
		request.setParameter("firstName", "SSSSSSSSSSSSSSSSS");
		request.setParameter("lastName", "LLLLLLLLL");
		request.setParameter("dob", "12/12/12");
		request.setParameter("ssn", "121212121");
		request.setParameter("phone", "9595959595");
		request.setParameter("ext", "345353");
		request.setParameter("fax", "9595959595");
		request.setParameter("streetAddress", "SASASASAS SSA sddsd");
		request.setParameter("state", "2");
		request.setParameter("city", "city");
		request.setParameter("pinZip", "666666");
		request.setParameter("county", "county");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "redirect:showManageUsers");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Test
	public void deleteAdminUser() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/deleteAdminUser/10");
		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);
		ModelAndViewAssert.assertViewName(mav, "redirect:showManageUsers");
	}

	@Test
	public void resendInvitation() throws Exception {

		request.setMethod("POST");
		request.setRequestURI("/resendInvitation/8");

		ModelAndView mav = handlerAdapter.handle(request, response, adminUserController);

	}

}
