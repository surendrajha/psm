package com.epayroll.service.employee;

import java.util.List;

import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeePaySetup.PayType;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.ContractorPaySetupForm;
import com.epayroll.form.employee.EmployeePaySetupForm;

public interface EmployeePaySetupService {

	EmployeePaySetupForm getEmployeePaySetup(Employee employee);

	ContractorPaySetupForm getContractorPaySetup(Employee employee);

	void updateEmployeePaySetup(EmployeePaySetupForm employeePaySetupForm, Employee employee);

	void updateContractorPaySetup(ContractorPaySetupForm contractorPaySetupForm, Employee employee);

	PayType[] getPayTypes();

	List<PayrollFrequency> getPayCycles(Employee employee) throws InstanceNotFoundException;

}
