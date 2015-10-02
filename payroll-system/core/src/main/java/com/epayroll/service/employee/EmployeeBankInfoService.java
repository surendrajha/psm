/**
 * 
 */
package com.epayroll.service.employee;

import java.util.List;

import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeBankAccount.AccountType;
import com.epayroll.entity.EmployeeBankAccount.DepositValueType;
import com.epayroll.exception.CommonException;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.EmployeeBankInfoForm;
import com.epayroll.model.employee.EmployeeBankInfo;

/**
 * @author Surendra Jha
 */
public interface EmployeeBankInfoService {

	void deleteEmployeeBankInfo(Long bankInfoId) throws InstanceNotFoundException;

	void saveEmployeeBankInfo(EmployeeBankInfoForm bankInfoForm, Employee employee)
			throws CommonException;

	AccountType[] getAccountTypes();

	DepositValueType[] getDepositAllocationTypes();

	List<EmployeeBankInfo> getEmployeeBankAccounts(Employee employee)
			throws InstanceNotFoundException;

}
