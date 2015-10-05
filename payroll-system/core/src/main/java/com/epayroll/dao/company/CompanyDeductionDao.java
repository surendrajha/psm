package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Criteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.exception.InstanceNotFoundException;

public interface CompanyDeductionDao extends GenericDao<CompanyDeduction, Long> {
	public Criteria getCriteriaForDeductionList(Company company);

	public CompanyDeduction getworkersCompensationDeduction(Long companyId, String dectionName);

	Long getEmployeePayrollDeductionCount(Long id) throws InstanceNotFoundException;

	Long getCompanyPayrollDeductionCount(Long id) throws InstanceNotFoundException;

	List<CompanyDeduction> getCompanyDeductions(Long deductionId);

	public List<Company> getCompanyList();

	public List<CompanyDeduction> getCompanyDeductionList(Long companyId);
}