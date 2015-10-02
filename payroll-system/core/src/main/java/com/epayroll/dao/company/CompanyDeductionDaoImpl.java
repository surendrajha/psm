package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.exception.InstanceNotFoundException;

@Repository
public class CompanyDeductionDaoImpl extends GenericDaoImpl<CompanyDeduction, Long> implements
		CompanyDeductionDao {
	private Logger logger = LoggerFactory.getLogger(CompanyDeductionDaoImpl.class);

	// TODO While testing, also test by comparing company property with company
	// object directly rather than comparing ids
	@Override
	public Criteria getCriteriaForDeductionList(Company company) {
		logger.debug(">> getCriteriaForDeductionList");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("company.id", company.getId()));
		logger.debug("getCriteriaForDeductionList >>");
		return criteria;
	}

	@Override
	public CompanyDeduction getworkersCompensationDeduction(Long companyId, String deductionName) {

		Query query = getSession()
				.createQuery(
						"select cd from CompanyDeduction cd inner join cd.deduction cdd where cdd.deductionName=? and cd.company.id=?");
		query.setParameter(0, deductionName);
		query.setParameter(1, companyId);
		CompanyDeduction companyDeduction = (CompanyDeduction) query.uniqueResult();
		return companyDeduction;
	}

	@Override
	public Long getEmployeePayrollDeductionCount(Long id) throws InstanceNotFoundException {
		logger.debug(">> getEmployeePayrollDeductionCount");
		String queryString = "select count(eepd.id) from EmployeePayrollDeduction eepd inner join eepd.employeeDeduction ed inner join ed.companyDeduction cd where cd.id=?";
		Query query = getSession().createQuery(queryString);
		query.setLong(0, id);
		Long count = (Long) query.uniqueResult();
		logger.debug("Count=" + count);
		logger.debug("getEmployeePayrollDeductionCount >>");
		return count;
	}

	@Override
	public Long getCompanyPayrollDeductionCount(Long id) throws InstanceNotFoundException {
		logger.debug(">> getCompanyPayrollDeductionCount");
		String queryString = "select count(erpd.id) from CompanyPayrollDeduction erpd inner join erpd.companyDeduction  cd where cd.id=?";
		Query query = getSession().createQuery(queryString);
		query.setLong(0, id);
		Long count = (Long) query.uniqueResult();
		logger.debug("Count=" + count);
		logger.debug("getCompanyPayrollDeductionCount >>");
		return count;
	}

	@Override
	public List<CompanyDeduction> getCompanyDeductions(Long deductionId) {
		Query query = getSession().createQuery(
				"select cd from CompanyDeduction cd where cd.deduction.id=?");
		query.setParameter(0, deductionId);
		@SuppressWarnings("unchecked")
		List<CompanyDeduction> companyDeductions = query.list();
		return companyDeductions;
	}

	@Override
	public List<Company> getCompanyList() {
		Query query = getSession().createQuery(
				"select distinct(cd.company) from CompanyDeduction cd ");
		@SuppressWarnings("unchecked")
		List<Company> companies = query.list();
		return companies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDeduction> getCompanyDeductionList(Long companyId) {
		return getCriteria().add(Restrictions.eq("company.id", companyId)).list();
	}

}
