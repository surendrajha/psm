package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.EmployeePayrollEarning;

public interface PayrollEarningDao extends GenericDao<EmployeePayrollEarning, Long> {

	List<EmployeePayrollEarning> getEmployeePayrollEarnings(Long employeePayrollId);
}
