package com.epayroll.dao.company;

import org.hibernate.Criteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDepartment;
import com.epayroll.exception.InstanceNotFoundException;

public interface CompanyDepartmentDao extends GenericDao<CompanyDepartment, Long> {
	public Criteria getCriteriaForDepartmentList(Company company);

	Long getPayrollCount(Long id) throws InstanceNotFoundException;
}