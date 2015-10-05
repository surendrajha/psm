package com.epayroll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.EmployeePayrollEarning;

@Repository
@SuppressWarnings("unchecked")
public class EmployeePayrollEarningDaoImpl extends GenericDaoImpl<EmployeePayrollEarning, Long>
		implements EmployeePayrollEarningDao {
	private Logger logger = LoggerFactory.getLogger(EmployeePayrollEarningDaoImpl.class);

	@Override
	public List<EmployeePayrollEarning> getEmployeePayrollEarnings(Long employeePayrollId) {
		logger.debug(">> getEmployeePayrollEarnings");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("employeePayroll.id", employeePayrollId)).addOrder(
				Order.asc("employeeEarning.id"));
		logger.debug("getEmployeePayrollEarnings >>");
		return criteria.list();
	}

	@Override
	public BigDecimal getCalculatedEarningY2D(Long employeeEarningId) {
		return (BigDecimal) getCriteria().setProjection(Projections.sum("calculatedEarning"))
				.uniqueResult();
	}

	// Payroll SK
	@Override
	public List<EmployeePayrollEarning> getEmployeePayrollEarningList(Long employeePayrollId) {
		return getCriteria().createAlias("employeeEarning", "ee")
				.add(Restrictions.eq("employeePayroll.id", employeePayrollId))
				.addOrder(Order.asc("ee.companyEarning.id")).list();
	}

	@Override
	public List<EmployeePayrollEarning> getEmployeePayrollEarnings(Long employeeId, Long payrollId) {
		logger.debug(">> getEmployeePayrollEarning");

		Query query = getSession()
				.createQuery(
						"select epe from EmployeePayrollEarning epe "
								+ "where epe.employeePayroll.employee.id=? AND epe.employeePayroll.payroll.id=?");
		query.setLong(0, employeeId);
		query.setLong(1, payrollId);
		List<EmployeePayrollEarning> employeePayrollEarnings = query.list();
		logger.debug("employeePayrollEarning :: " + employeePayrollEarnings);
		logger.debug("getEmployeePayrollEarning >>");
		return employeePayrollEarnings;
	}

}
