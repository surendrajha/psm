package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeDepartmentAllocation;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeDepartmentAllocationDaoImpl extends
		GenericDaoImpl<EmployeeDepartmentAllocation, Long> implements
		EmployeeDepartmentAllocationDao {

	private Logger logger = LoggerFactory.getLogger(EmployeeDepartmentAllocationDaoImpl.class);

	@Override
	public List<EmployeeDepartmentAllocation> getEmployeeDepartmentAllocations(Employee employee) {

		logger.debug(" >> getEmployeeDepartmentAllocations,, employeeID:" + employee.getId());
		List<EmployeeDepartmentAllocation> allocations = getCriteria().add(
				Restrictions.eq("employee.id", employee.getId())).list();
		logger.debug("allocations::" + allocations);
		return allocations;
	}
}
