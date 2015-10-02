package com.epayroll.dao.employee;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeePayrollDeduction;

/**
 * @author Rajul Tiwari
 */
public interface EmployeePayrollDeductionDao extends GenericDao<EmployeePayrollDeduction, Long> {

	List<EmployeePayrollDeduction> getEmployeeDeductions(Long employeePayrollId);

	BigDecimal getCalculatedDeductionY2D(Long employeeDeductionId);

	List<EmployeePayrollDeduction> getEmployeePayrollDeductions(Long employeeId, Long payrollId);

}