package com.epayroll.form.payroll;

import java.util.List;

import com.epayroll.model.payroll.EmployeeDeductionModel;
import com.epayroll.model.payroll.EmployeeTaxModel;

public class PayrollDeductionForm {

	private String employeeName;
	private Long employeePayrollId;
	private List<EmployeeTaxModel> employeeTaxes;
	private List<EmployeeDeductionModel> employeeDeductions;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getEmployeePayrollId() {
		return employeePayrollId;
	}

	public void setEmployeePayrollId(Long employeePayrollId) {
		this.employeePayrollId = employeePayrollId;
	}

	public List<EmployeeTaxModel> getEmployeeTaxes() {
		return employeeTaxes;
	}

	public void setEmployeeTaxes(List<EmployeeTaxModel> employeeTaxes) {
		this.employeeTaxes = employeeTaxes;
	}

	public List<EmployeeDeductionModel> getEmployeeDeductions() {
		return employeeDeductions;
	}

	public void setEmployeeDeductions(List<EmployeeDeductionModel> employeeDeductions) {
		this.employeeDeductions = employeeDeductions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollDeductionForm [employeeName=");
		builder.append(employeeName);
		builder.append(", employeePayrollId=");
		builder.append(employeePayrollId);
		builder.append(", employeeTaxes=");
		builder.append(employeeTaxes);
		builder.append(", employeeDeductions=");
		builder.append(employeeDeductions);
		builder.append("]");
		return builder.toString();
	}

}
