package com.epayroll.service.company;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.UsStateDao;
import com.epayroll.dao.company.CompanyUserDao;
import com.epayroll.dao.company.RoleDao;
import com.epayroll.dao.company.UserDao;
import com.epayroll.dao.company.UserSecurityQuestionDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.Role;
import com.epayroll.entity.User;
import com.epayroll.entity.UserSecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.exception.InvalidExistingPasswordException;
import com.epayroll.form.company.ChangePasswordForm;
import com.epayroll.utils.RandomNumberUtils;

@Service
public class CompanyUserServiceImpl implements CompanyUserService {

	private Logger logger = LoggerFactory.getLogger(CompanyUserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Autowired
	private CompanyUserDao companyUserDao;

	@Autowired
	private UserSecurityQuestionDao companySecurityQuestionDao;

	@Autowired
	private UsStateDao usStateDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public List<UserSecurityQuestion> getCompanyUserSecurityQnAs(Company company)
			throws InstanceNotFoundException {
		CompanyUser companyUser = companyUserDao.getCompanyAdminUser(company);
		User user = companyUser.getUser();
		return getCompanyUserSecurityQnAs(user.getId());
	}

	/**
	 * @param user
	 * @return
	 */

	@Override
	@Transactional
	public void changePassword(ChangePasswordForm changePasswordForm)
			throws InstanceNotFoundException, InvalidExistingPasswordException {
		User user = getUser(changePasswordForm.getCompanyUserId());
		if (changePasswordForm.getIsFirstTime()
				&& user.getPassword().equals(
						RandomNumberUtils.encode(changePasswordForm.getCurrentPassword()))) {
			user.setPassword(RandomNumberUtils.encode(changePasswordForm.getNewPassword()));
			userDao.update(user);
		} else if (user.getPassword().equals(
				RandomNumberUtils.encode(changePasswordForm.getCurrentPassword()))) {
			user.setPassword(RandomNumberUtils.encode(changePasswordForm.getNewPassword()));
			userDao.update(user);
		} else {
			System.out.println("Existing Password is not valid!");
			throw new InvalidExistingPasswordException("Existing Password is not valid!");
		}
	}

	@Override
	public User getUser(Long userId) throws InstanceNotFoundException {
		return userDao.find(userId);
	}

	@Override
	@Transactional
	public List<UserSecurityQuestion> getCompanyUserSecurityQnAs(Long userId) {
		return companySecurityQuestionDao.getUserSecurityQnAs(userId);
	}

	@Override
	@Transactional
	public List<Role> getRoles() {
		return roleDao.getCompanyRolesExceptAdmin();
	}

}
