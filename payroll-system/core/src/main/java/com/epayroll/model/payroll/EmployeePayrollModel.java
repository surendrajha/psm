package com.epayroll.model.payroll;

import com.epayroll.entity.Employee.Type;

public class EmployeePayrollModel {

	private Double grossAmount;
	private String employeeName;
	private Double employeeTax;
	private Double employeeDeduction;
	private Double netDirectDepositAmount;
	private Double netCheckAmount;
	private Double employerTax;
	private Double employerDeduction;
	private Type employeeType;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Double getEmployeeTax() {
		return employeeTax;
	}

	public void setEmployeeTax(Double employeeTax) {
		this.employeeTax = employeeTax;
	}

	public Double getEmployeeDeduction() {
		return employeeDeduction;
	}

	public void setEmployeeDeduction(Double employeeDeduction) {
		this.employeeDeduction = employeeDeduction;
	}

	public Double getNetDirectDepositAmount() {
		return netDirectDepositAmount;
	}

	public void setNetDirectDepositAmount(Double netDirectDepositAmount) {
		this.netDirectDepositAmount = netDirectDepositAmount;
	}

	public Double getNetCheckAmount() {
		return netCheckAmount;
	}

	public void setNetCheckAmount(Double netCheckAmount) {
		this.netCheckAmount = netCheckAmount;
	}

	public Double getEmployerTax() {
		return employerTax;
	}

	public void setEmployerTax(Double employerTax) {
		this.employerTax = employerTax;
	}

	public Double getEmployerDeduction() {
		return employerDeduction;
	}

	public void setEmployerDeduction(Double employerDeduction) {
		this.employerDeduction = employerDeduction;
	}

	public Type getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(Type employeeType) {
		this.employeeType = employeeType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeePayrollModel [employeeName=");
		builder.append(employeeName);
		builder.append(", grossAmount=");
		builder.append(grossAmount);
		builder.append(", employeeTax=");
		builder.append(employeeTax);
		builder.append(", employeeDeduction=");
		builder.append(employeeDeduction);
		builder.append(", netDirectDepositAmount=");
		builder.append(netDirectDepositAmount);
		builder.append(", netCheckAmount=");
		builder.append(netCheckAmount);
		builder.append(", employerTax=");
		builder.append(employerTax);
		builder.append(", employerDeduction=");
		builder.append(employerDeduction);
		builder.append(", employeeType=");
		builder.append(employeeType);
		builder.append("]");
		return builder.toString();
	}

}
