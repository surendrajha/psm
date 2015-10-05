package com.epayroll.service.employee;

import java.util.Date;
import java.util.List;

import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayrollDeduction;
import com.epayroll.entity.EmployeePayrollEarning;
import com.epayroll.entity.EmployeePayrollTax;
import com.epayroll.form.employee.PaymentCheckInfoForm;

/**
 * @author Rajul Tiwari
 */
public interface EmployeePaymentCheckInfoService {

	List<Date> getPayDates(Long employeeId);

	List<EmployeePayroll> getPaymentes(Long employeeId);

	List<EmployeePayroll> getSearchedPaymentes(Long employeeId,
			PaymentCheckInfoForm paymentCheckInfoForm);

	List<EmployeePayrollEarning> getEmployeeEarningList(Long employeePayrollId);

	List<EmployeePayrollTax> getEmployeeTaxList(Long employeePayrollId);

	List<EmployeePayrollDeduction> getEmployeeDeductionList(Long employeePayrollId);
}
