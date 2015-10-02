package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Criteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeBankAccount;
import com.epayroll.exception.InstanceNotFoundException;

public interface EmployeeBankAccountDao extends GenericDao<EmployeeBankAccount, Long> {

	List<EmployeeBankAccount> getEmployeeBankAccounts(Employee employee)
			throws InstanceNotFoundException;

	Criteria getCriteriaForEmployeeBankAccounts(Long employeeId);

}
