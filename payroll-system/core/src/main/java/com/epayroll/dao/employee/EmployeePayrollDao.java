package com.epayroll.dao.employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.Payroll;
import com.epayroll.form.employee.PaymentCheckInfoForm;

/**
 * @author Rajul Tiwari
 */
public interface EmployeePayrollDao extends GenericDao<EmployeePayroll, Long> {
	List<Date> getpayDates(Long employeeId);

	List<EmployeePayroll> getPaymentes(Long employeeId);

	List<EmployeePayroll> getSearchedPaymentes(Long employeeId,
			PaymentCheckInfoForm paymentCheckInfoForm);

	List<Payroll> getPayrollCheckDateList(Long employeeId, Integer year);

	List<EmployeePayroll> getEmployeePayroll(Long payrollId);

	BigDecimal getGrossSalaryPaidY2D(Long employeeId);

	public List<EmployeePayroll> getEmployeePayrollValues(Long employeeId, Long payrollId);

}