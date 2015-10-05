package com.epayroll.dao;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.entity.EmployeePayrollEarning;

public interface EmployeePayrollEarningDao extends GenericDao<EmployeePayrollEarning, Long> {

	// List<PayrollEarning> getEmployeeEarningList(Long employeePayrollId);
	List<EmployeePayrollEarning> getEmployeePayrollEarnings(Long employeePayrollId);

	BigDecimal getCalculatedEarningY2D(Long employeeEarningId);

	List<EmployeePayrollEarning> getEmployeePayrollEarningList(Long employeePayrollId);

	List<EmployeePayrollEarning> getEmployeePayrollEarnings(Long employeeId, Long payrollId);

}
