package com.epayroll.service.payroll;

import java.util.List;

import com.epayroll.entity.CompanyEarning;
import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.entity.Payroll;
import com.epayroll.entity.PayrollCompany;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollSchedule;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.payroll.PayRollForm;
import com.epayroll.form.payroll.PayrollDeductionForm;
import com.epayroll.form.payroll.PayrollPreviewForm;

public interface PayrollService {

	List<EmployeeTax> getEmployeePrimaryTaxList(Long employeeId);

	List<PayrollFrequency> getPayrollFrequencies(Long companyId);

	List<CompanyEarning> getCompanyEarnings(Long companyId);

	PayRollForm getPayRollForm(Long companyId, PayRollForm payRollForm);

	void updatePayrollData(PayRollForm payRollForm);

	void deletePayroll(Long payrollId);

	TaxOverrideType[] getTaxOverrideTypes();

	DeductionValueType[] getDeductionOverrideTypes();

	PayrollDeductionForm getTaxAndDeductionFromPayroll(Long employeePayrollId);

	void updateTaxAndDeductions(PayrollDeductionForm deductionForm);

	List<EmployeeAllowanceRecord> getEmployeeAllowanceRecord(Long employeeId);

	PayrollPreviewForm calculatePayroll(Long payrollId, Long payrollFrequencyId)
			throws InstanceNotFoundException;

	Integer setCheckStubMessage(Long payrollId, String checkStubMessage);

	void submitForApproval(Long payrollId, PayrollSchedule payrollSchedule, String link);

	List<Payroll> getPayrollList(Long companyId);

	List<EmployeePayroll> getEmployeePayrollList(Long payrollId);

	List<PayrollCompany> getPayrollCompanyList();

}
