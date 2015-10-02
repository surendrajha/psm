package com.epayroll.service.employeeAccess;

import java.util.List;

import com.epayroll.entity.Employee;
import com.epayroll.entity.Payroll;
import com.epayroll.form.employeeAccess.PayStubsForm;
import com.epayroll.model.employeeAccess.PayStubModel;

/**
 * @author Rajul Tiwari
 */

public interface EmployeeAccessPayStubsInfoService {

	List<Integer> getYearListForPayStubs(Long employeeId);

	List<Payroll> getPayrollCheckDateList(int year, Long employeeId);

	PayStubModel getPayStubModel(Employee employee, PayStubsForm payStubsForm);

}
