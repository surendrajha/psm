package com.epayroll.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.EmployeePayrollEarning;

@Repository
public class PayrollEarningDaoImpl extends GenericDaoImpl<EmployeePayrollEarning, Long> implements
		PayrollEarningDao {
	private Logger logger = LoggerFactory.getLogger(PayrollEarningDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeePayrollEarning> getEmployeePayrollEarnings(Long employeePayrollId) {
		logger.debug(">> getCriteriaForEmployeeEarning");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("employeePayroll.id", employeePayrollId));
		logger.debug("getCriteriaForEmployeeEarning >>");
		return criteria.list();
	}

}
