package com.epayroll.form.employee;

import java.util.List;

public class EmployeeDepartmentAllocationForm {

	private List<EmployeeDeptAllocation> allocations;

	public List<EmployeeDeptAllocation> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<EmployeeDeptAllocation> allocations) {
		this.allocations = allocations;
	}
}
