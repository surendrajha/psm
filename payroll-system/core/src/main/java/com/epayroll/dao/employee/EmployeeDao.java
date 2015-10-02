package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Criteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeeSection;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.ContractorSearchForm;
import com.epayroll.form.employee.EmployeeSearchForm;

/**
 * @author Rajul Tiwari
 */
public interface EmployeeDao extends GenericDao<Employee, Long> {

	List<Object[]> getEmployeeList(Long companyId, Type employeeType);

	List<Object[]> getSearchedEmployees(EmployeeSearchForm employeeSearchForm, Long companyId);

	List<Object[]> getSearchedContractor(ContractorSearchForm constractorSearchForm, Long companyId);

	List<String> getCities();

	List<EmployeeSection> getEmployeeSectionList(Long companyId);

	Employee getEmployee(String userName) throws InstanceNotFoundException;

	Criteria getCriteriaForEmployee(Long employeeId, String currentPassword);

	Long getEmployeeStateId(Long employeeId);

	// Employee Access
	Employee checkTemporaryPassword(Long companyId, String password);

	Employee checkUserId(String userName);

	Employee checkLogin(String userName, String password);

	Employee getEmployeeForSentEmailFromEmailId(String emailId) throws InstanceNotFoundException;

	Employee getEmployeeForSentEmailFromUserName(String userName);

	List<Long> getPayrollEmployees(Long payrollId);

}