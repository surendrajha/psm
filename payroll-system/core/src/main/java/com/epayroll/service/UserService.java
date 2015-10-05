package com.epayroll.service;

import java.util.Date;
import java.util.List;

import com.epayroll.entity.AdminUser;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.UsState;
import com.epayroll.entity.User;
import com.epayroll.entity.UserSecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
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
import com.epayroll.model.SecurityQuestions;

/**
 * @author Rajul Tiwari
 */
public interface UserService {

	// Forgot and Reset Password
	User getUser(String userName) throws InstanceNotFoundException;

	ForgotInfoStep2Form getForgotInfoStep2Form(
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form)
			throws InstanceNotFoundException;

	String checkSecurityAnswersAndGetEmailForPassword(
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form,
			ForgotInfoStep2Form forgotInfoStep2Form) throws InstanceNotFoundException;

	Boolean changePassword(User user, ResetPasswordForm resetPasswordForm);

	// Reset Pin
	boolean changePin(User user, ResetPinForm resetPinForm);

	boolean verifyPin(PinVerficationForm pinVerficationForm);

	// Forgot UserId
	User getUser(String email, Date dob) throws InstanceNotFoundException;

	String checkSecurityAnswersAndGetUserIdForUserId(ForgotUserIdStep1Form forgotUserIdStep1Form,
			ForgotInfoStep2Form forgotInfoStep2Form) throws InstanceNotFoundException;

	ForgotInfoStep2Form getForgotInfoStep2FormForUserId(ForgotUserIdStep1Form forgotUserIdStep1Form)
			throws InstanceNotFoundException;

	//

	UserForm getUserProfile(Long userId) throws InstanceNotFoundException;

	SecurityQuestions getSecurityQuestions();

	List<UsState> getStates();

	List<UserForm> getComapanyUsersExceptAdmin(Company company);

	void updateAdminUserProfile(UserForm userForm) throws InstanceNotFoundException;

	GenerateOTPForm generateOtp(GenerateOTPForm otpForm) throws InstanceNotFoundException;

	User getUser(Long userId) throws InstanceNotFoundException;

	Boolean verifyOTP(GenerateOTPForm otpForm) throws InstanceNotFoundException;

	Boolean changeUserID(ChangeUserIDForm changeUserIDForm) throws InstanceNotFoundException;

	Boolean changeUserPassword(ChangeUserPasswordForm changeUserPasswordForm)
			throws InstanceNotFoundException;

	Boolean changeUserPin(ChangeUserPinForm changeUserPinForm) throws InstanceNotFoundException;

	Boolean changeUserEmail(ChangeUserEmailForm changeUserEmailForm)
			throws InstanceNotFoundException;

	Boolean isUserNameAvailable(String userName) throws InstanceNotFoundException;

	List<UserSecurityQuestion> getUserSecurityQnAs(Long companyUserId);

	Boolean updateUserSequrityQnA(ChangeSecurityQnAForm changeSecurityQnAForm)
			throws InstanceNotFoundException;

	Long addUser(UserAddForm userAddForm) throws Exception;

	void registerUser(UserForm userForm) throws InstanceNotFoundException;

	UserForm getCompanyUserForm(Long companyUserId) throws InstanceNotFoundException;

	UserForm getAdminUserForm(Long adminUserId) throws InstanceNotFoundException;

	CompanyUser getCompanyUser(Long companyUserId) throws InstanceNotFoundException;

	void updateUser(UserForm userForm) throws InstanceNotFoundException;

	void deleteUser(Long companyUserId) throws InstanceNotFoundException;

	UserForm resetLoginCredential(Long userId) throws InstanceNotFoundException;

	List<UserForm> getAdminUsersExceptAdmin();

	AdminUser getAdminUser(Long adminUserId) throws InstanceNotFoundException;

}
