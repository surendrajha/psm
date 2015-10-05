package com.epayroll.service.company;

import java.util.List;

import com.epayroll.entity.Company;
import com.epayroll.entity.Role;
import com.epayroll.entity.User;
import com.epayroll.entity.UserSecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.exception.InvalidExistingPasswordException;
import com.epayroll.form.company.ChangePasswordForm;

/**
 * @author Surendra Jha
 */
public interface CompanyUserService {

	List<UserSecurityQuestion> getCompanyUserSecurityQnAs(Company company)
			throws InstanceNotFoundException;

	void changePassword(ChangePasswordForm changePasswordForm) throws InstanceNotFoundException,
			InvalidExistingPasswordException;

	User getUser(Long userId) throws InstanceNotFoundException;

	List<UserSecurityQuestion> getCompanyUserSecurityQnAs(Long companyUserId);

	List<Role> getRoles();
}
