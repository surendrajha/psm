package com.epayroll.service.employeeAccess;

import java.util.List;

import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeBankAccount;
import com.epayroll.entity.SecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employeeAccess.ChangeEmailForm;
import com.epayroll.form.employeeAccess.ChangePasswordForm;
import com.epayroll.form.employeeAccess.ChangeSecurityQnAForm;

/**
 * @author Rajul Tiwari
 */
public interface EmployeeAccessService {

	List<EmployeeBankAccount> getEmployeeBankAccountInfo(Long id);

	void verifyEmployeeAndChangePassword(ChangePasswordForm changePasswordForm)
			throws InstanceNotFoundException;

	ChangeEmailForm getChangeEmailForm(Employee employeeFromSession);

	void verifyEmployeeAndChangeEmail(ChangeEmailForm changeEmailForm)
			throws InstanceNotFoundException;

	ChangeSecurityQnAForm getChangeSecurityQnAForm(Employee employeeFromSession);

	void verifyEmployeeAndChangeSecurityQnA(ChangeSecurityQnAForm changeSecurityQnAForm)
			throws InstanceNotFoundException;

	List<SecurityQuestion> getSecurityQuestions();

}
