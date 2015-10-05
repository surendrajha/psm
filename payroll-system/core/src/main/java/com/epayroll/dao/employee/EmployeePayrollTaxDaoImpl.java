package com.epayroll.dao.employee;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.employee.TaxName;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeePayrollTax;

@Repository
public class EmployeePayrollTaxDaoImpl extends GenericDaoImpl<EmployeePayrollTax, Long> implements
		EmployeePayrollTaxDao {
	private Logger logger = LoggerFactory.getLogger(EmployeePayrollTaxDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeePayrollTax> getEmployeeTaxes(Long employeePayrollId) {
		logger.debug(">> getCriteriaForEmployeeTaxList");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("employeePayroll.id", employeePayrollId));
		logger.debug("getCriteriaForEmployeeTaxList >>");
		return criteria.list();
	}

	@Override
	public BigDecimal getCalculatedTaxY2D(Long employeeTaxId) {
		return (BigDecimal) getCriteria().add(Restrictions.eq("employeeTax.id", employeeTaxId))
				.setProjection(Projections.sum("calculatedTax")).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeePayrollTax> getEmployeePrimaryTaxes(Long employeePayrollId) {

		String hql = "SELECT ept FROM EmployeePayrollTax ept INNER JOIN ept.employeeTax et INNER JOIN et.taxType tt "
				+ "where (tt.taxName=:fit or SUBSTRING(tt.taxName, LENGTH(tt.taxName)-15)=:sit) and ept.employeePayroll.id=:pid";

		Query query = getSession().createQuery(hql);
		query.setParameter("fit", TaxName.FEDERAL_INCOME_TAX);
		query.setParameter("sit", TaxName.SIT);
		query.setParameter("pid", employeePayrollId);

		return query.list();
	}

	@Override
	public List<EmployeePayrollTax> getEmployeePayrollTaxs(Long employeeId, Long payrollId) {
		logger.debug(">> getEmployeePayrollTaxs");

		Query query = getSession()
				.createQuery(
						"select ept from EmployeePayrollTax ept "
								+ "where ept.employeePayroll.employee.id=? AND ept.employeePayroll.payroll.id=?");
		query.setLong(0, employeeId);
		query.setLong(1, payrollId);
		@SuppressWarnings("unchecked")
		List<EmployeePayrollTax> employeePayrollTaxs = query.list();
		logger.debug("employeePayrollTaxs :: " + employeePayrollTaxs);

		logger.debug("getEmployeePayrollTaxs >>");
		return employeePayrollTaxs;
	}
}
