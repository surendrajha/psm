package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.AdminUser;

public interface AdminUserDao extends GenericDao<AdminUser, Long> {

	AdminUser getSystemAdminUser();

	List<AdminUser> getSystemUsersExceptAdmin();

	// List<CompanyUser> getCompanyUsersExceptAdmin(Company company);
	//
	// Integer deactivateCompanyUsers(Long companyId);
	//
	// Integer reactivateCompanyUsers(Long companyId);

}