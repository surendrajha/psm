package com.epayroll.dao.employee;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeePayrollTax;

/**
 * @author Rajul Tiwari
 */
public interface EmployeePayrollTaxDao extends GenericDao<EmployeePayrollTax, Long> {

	List<EmployeePayrollTax> getEmployeeTaxes(Long employeePayrollId);

	BigDecimal getCalculatedTaxY2D(Long employeeTaxId);

	List<EmployeePayrollTax> getEmployeePrimaryTaxes(Long employeePayrollId);

	List<EmployeePayrollTax> getEmployeePayrollTaxs(Long employeeId, Long payrollId);

}