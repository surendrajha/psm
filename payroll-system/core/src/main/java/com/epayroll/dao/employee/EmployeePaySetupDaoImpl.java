package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.dao.company.PayrollFrequencyDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeePaySetup;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.exception.InstanceNotFoundException;

@Repository
@SuppressWarnings("unchecked")
public class EmployeePaySetupDaoImpl extends GenericDaoImpl<EmployeePaySetup, Long> implements
		EmployeePaySetupDao {

	private Logger logger = LoggerFactory.getLogger(EmployeePaySetupDaoImpl.class);

	@Autowired
	private PayrollFrequencyDao payrollFrequencyDao;

	@Override
	public List<PayrollFrequency> getPayrollFrequencies(Company company)
			throws InstanceNotFoundException {
		logger.debug(" >> getPayrollFrequencies ... company:" + company.getId());
		List<PayrollFrequency> frequencies = payrollFrequencyDao.getPayrollFrequencies(company
				.getId());
		if (frequencies == null) {
			StringBuilder builder = new StringBuilder("[");
			builder.append("compayID:").append(company.getId()).append("]");
			throw new InstanceNotFoundException(builder.toString(), getEntityClass().getName());
		}
		return frequencies;
	}

	@Override
	public EmployeePaySetup getEmployeePaySetup(Employee employee) throws InstanceNotFoundException {
		logger.debug(" >> getEmployeePaySetup .. employee:" + employee.getId());
		EmployeePaySetup employeePaySetup = (EmployeePaySetup) getCriteria().add(
				Restrictions.eq("employee.id", employee.getId())).uniqueResult();
		if (employeePaySetup == null) {
			StringBuilder builder = new StringBuilder("[");
			builder.append("Employee ID:").append(employee.getId()).append("]");
			throw new InstanceNotFoundException(builder.toString(), getEntityClass().getName());
		}
		return employeePaySetup;
	}

	// Payroll SK
	/**
	 * Fetch the list of employees having type EMPLOYEE and current Pay
	 * frequency and Pay frequency type for that company.
	 */
	@Override
	public List<EmployeePaySetup> getEmployeePaySetups(Long payrollFreqId) {
		return getCriteria().add(Restrictions.eq("payrollFrequency.id", payrollFreqId)).list();
	}

}
