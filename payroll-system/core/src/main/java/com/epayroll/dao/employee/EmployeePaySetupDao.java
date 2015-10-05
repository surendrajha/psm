package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeePaySetup;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.exception.InstanceNotFoundException;

public interface EmployeePaySetupDao extends GenericDao<EmployeePaySetup, Long> {

	List<PayrollFrequency> getPayrollFrequencies(Company company) throws InstanceNotFoundException;

	EmployeePaySetup getEmployeePaySetup(Employee employee) throws InstanceNotFoundException;

	List<EmployeePaySetup> getEmployeePaySetups(Long payrollFreqId);

}
