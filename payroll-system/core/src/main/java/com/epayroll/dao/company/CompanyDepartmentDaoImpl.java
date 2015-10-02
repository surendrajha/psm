package com.epayroll.dao.company;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDepartment;
import com.epayroll.exception.InstanceNotFoundException;

@Repository("CompanyDepartmentDao")
public class CompanyDepartmentDaoImpl extends GenericDaoImpl<CompanyDepartment, Long> implements
		CompanyDepartmentDao {
	private Logger logger = LoggerFactory.getLogger(CompanyDepartmentDaoImpl.class);

	// TODO While testing, also test by comparing company property with company
	// object directly rather than comparing ids
	@Override
	public Criteria getCriteriaForDepartmentList(Company company) {
		logger.debug(">> getCriteriaForDepartmentList");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("company.id", company.getId()));
		logger.debug("getCriteriaForDepartmentList >>");
		return criteria;
	}

	@Override
	public Long getPayrollCount(Long id) throws InstanceNotFoundException {
		logger.debug(">> getPayrollCount");
		String queryString = "select count(ep.id) from EmployeePayroll ep, CompanyDepartment cd Inner Join cd.employeeDepartmentAllocations eda where eda.employee=ep.employee and cd.id=?";
		Query query = getSession().createQuery(queryString);
		query.setLong(0, id);
		Long count = (Long) query.uniqueResult();
		logger.debug("Count=" + count);
		logger.debug("getPayrollCount >>");
		return count;
	}
}
