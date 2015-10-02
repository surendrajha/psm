package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;

public interface CompanyUserDao extends GenericDao<CompanyUser, Long> {

	CompanyUser getCompanyAdminUser(Company company);

	List<CompanyUser> getCompanyUsersExceptAdmin(Company company);

	Integer deactivateCompanyUsers(Long companyId);

	Integer reactivateCompanyUsers(Long companyId);

}