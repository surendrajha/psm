package com.epayroll.model;

import java.util.List;

import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.EmployeeTax;

public class EmployeeW4DetailModel {
	private EmployeeTax employeeTax;
	private List<EmployeeAllowanceRecord> employeeAllowanceRecords;

	public EmployeeW4DetailModel(EmployeeTax employeeTax,
			List<EmployeeAllowanceRecord> employeeAllowanceRecords) {
		this.employeeTax = employeeTax;
		this.setEmployeeAllowanceRecords(employeeAllowanceRecords);
	}

	public EmployeeW4DetailModel() {
	}

	public EmployeeTax getEmployeeTax() {
		return employeeTax;
	}

	public void setEmployeeTax(EmployeeTax employeeTax) {
		this.employeeTax = employeeTax;
	}

	public List<EmployeeAllowanceRecord> getEmployeeAllowanceRecords() {
		return employeeAllowanceRecords;
	}

	public void setEmployeeAllowanceRecords(List<EmployeeAllowanceRecord> employeeAllowanceRecords) {
		this.employeeAllowanceRecords = employeeAllowanceRecords;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayStubModel [employeeTax=");
		builder.append(employeeTax);
		builder.append(", employeeAllowanceRecords=");
		builder.append(employeeAllowanceRecords);
		builder.append("]");
		return builder.toString();
	}

}
