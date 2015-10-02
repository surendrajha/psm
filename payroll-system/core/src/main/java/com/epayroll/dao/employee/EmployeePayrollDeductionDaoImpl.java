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

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeePayrollDeduction;

@Repository
public class EmployeePayrollDeductionDaoImpl extends GenericDaoImpl<EmployeePayrollDeduction, Long>
		implements EmployeePayrollDeductionDao {
	private Logger logger = LoggerFactory.getLogger(EmployeePayrollDeductionDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeePayrollDeduction> getEmployeeDeductions(Long employeePayrollId) {
		logger.debug(">> getCriteriaForEmployeeDeduction");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("employeePayroll.id", employeePayrollId));
		logger.debug("getCriteriaForEmployeeDeduction >>");
		return criteria.list();
	}

	@Override
	public BigDecimal getCalculatedDeductionY2D(Long employeeDeductionId) {
		return (BigDecimal) getCriteria()
				.add(Restrictions.eq("employeeDeduction.id", employeeDeductionId))
				.setProjection(Projections.sum("calculatedDeduction")).uniqueResult();
	}

	@Override
	public List<EmployeePayrollDeduction> getEmployeePayrollDeductions(Long employeeId,
			Long payrollId) {
		logger.debug(">> getEmployeePayrollDeductions");

		Query query = getSession()
				.createQuery(
						"select epd from EmployeePayrollDeduction epd "
								+ "where epd.employeePayroll.employee.id=? AND epd.employeePayroll.payroll.id=?");
		query.setLong(0, employeeId);
		query.setLong(1, payrollId);
		@SuppressWarnings("unchecked")
		List<EmployeePayrollDeduction> employeePayrollDeductions = query.list();
		logger.debug("employeePayrollDeductions :: " + employeePayrollDeductions);

		logger.debug("getEmployeePayrollDeductions >>");
		return employeePayrollDeductions;
	}

}
