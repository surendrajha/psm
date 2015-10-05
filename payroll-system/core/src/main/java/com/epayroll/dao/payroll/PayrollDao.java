package com.epayroll.dao.payroll;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.Payroll;

public interface PayrollDao extends GenericDao<Payroll, Long> {

	Long getPayrollScheduleCount(Long payrollScheduleID);

	Long getPayrollFrequencyCount(Long payrollFrequencyId);

	List<Employee> getEmployees(Long companyId, Long payFrequencyId, Type employeeType);

	Payroll getPayrollRecord(Long companyId, Long payrollScheduleId);

	List getemployeePrimaryTaxList(Long employeeId);

	Payroll getPayroll(Long companyId, Long payrollScheduleId);

	Integer setCheckStubMessage(Long payrollId, String checkStubMessage);

	// List<Date> getpayDates(Long employeeId);

	List<Payroll> getPayrollList(Long companyId);

}