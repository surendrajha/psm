package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeDepartmentAllocation;

public interface EmployeeDepartmentAllocationDao extends
		GenericDao<EmployeeDepartmentAllocation, Long> {

	List<EmployeeDepartmentAllocation> getEmployeeDepartmentAllocations(Employee employee);

}
