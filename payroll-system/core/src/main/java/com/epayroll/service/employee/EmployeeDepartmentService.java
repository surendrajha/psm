/**
 * 
 */
package com.epayroll.service.employee;

import java.util.List;

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDepartment;
import com.epayroll.entity.Employee;
import com.epayroll.form.employee.EmployeeDepartmentAllocationForm;
import com.epayroll.model.employee.DepartmentAllocation;

/**
 * @author Surendra Jha
 */
public interface EmployeeDepartmentService {

	List<CompanyDepartment> getCompanyDepartments(Company company);

	List<DepartmentAllocation> getEmployeeDepartmentAllocations(Employee employee);

	void saveEmployeeDepartmentAllocations(EmployeeDepartmentAllocationForm allocationForm,
			Employee employee);

}
