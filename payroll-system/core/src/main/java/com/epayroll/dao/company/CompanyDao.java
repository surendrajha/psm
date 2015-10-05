package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.form.employeeAccess.CompanyForm;

public interface CompanyDao extends GenericDao<Company, Long> {

	List<Object[]> getSearchedCompanyList(CompanyForm companyForm);

	public Integer deactivateCompany(Long companyId);

	Integer reactivateCompany(Long companyId);

	Company getCompany(Long userId);

}