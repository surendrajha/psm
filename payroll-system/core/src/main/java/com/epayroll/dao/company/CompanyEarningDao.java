package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.exception.InstanceNotFoundException;

public interface CompanyEarningDao extends GenericDao<CompanyEarning, Long> {

	List<CompanyEarning> getCompanyEarnings(Company company, String type);

	Long getPayrollCount(Long id) throws InstanceNotFoundException;

	List<CompanyEarning> getCompanyEarnings(Long companyId);

	List<CompanyEarning> getCompanyEarningsByEarningId(Long earningId);

}