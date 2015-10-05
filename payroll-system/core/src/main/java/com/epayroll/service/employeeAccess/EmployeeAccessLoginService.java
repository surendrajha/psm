package com.epayroll.service.employeeAccess;

import java.util.List;

import com.epayroll.entity.Employee;
import com.epayroll.entity.SecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ResetPasswordForm;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotPasswordStep1Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotPasswordStep2Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotUserIdStep1Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotUserIdStep2Form;
import com.epayroll.form.employeeAccess.EmployeeAccessLoginForm;
import com.epayroll.form.employeeAccess.EmployeeAccessRegistrationForm;

/**
 * @author Rajul Tiwari
 */
public interface EmployeeAccessLoginService {

	// Employee Access
	boolean checkTemporaryPassword(Long companyId, String password);

	Employee checkUserId(String userName);

	List<SecurityQuestion> getSecurityQuestions();

	Long registration(EmployeeAccessRegistrationForm employeeAccessRegistrationForm)
			throws InstanceNotFoundException;

	Employee checkLogin(EmployeeAccessLoginForm employeeAccessLoginForm)
			throws InstanceNotFoundException;

	EmployeeAccessForgotUserIdStep2Form getEmployeeAccessForgotUserIdStep2Form(
			EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form)
			throws InstanceNotFoundException;

	String checkSecurityAnswersAndGetUserIdForUserId(
			EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form,
			EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form)
			throws InstanceNotFoundException;

	EmployeeAccessForgotPasswordStep2Form getEmployeeAccessForgotPasswordStep2Form(
			EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form)
			throws InstanceNotFoundException;

	String checkSecurityAnswersAndGetEmailForPassword(
			EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form,
			EmployeeAccessForgotPasswordStep2Form employeeAccessForgotPasswordStep2Form)
			throws InstanceNotFoundException;

	Employee getEmployeeFromUserName(String userName) throws InstanceNotFoundException;

	Employee getEmployeeFromEmailId(String emailId) throws InstanceNotFoundException;

	Boolean changePassword(Employee employee, ResetPasswordForm resetPasswordForm);

}
