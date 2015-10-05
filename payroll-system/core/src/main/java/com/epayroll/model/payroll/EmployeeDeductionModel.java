package com.epayroll.model.payroll;

import com.epayroll.entity.EmployeeDeduction.DeductionValueType;

public class EmployeeDeductionModel {

	private Long employeePayrollDeductionId;
	private String deductionName;
	private DeductionValueType deductionOverrideType;
	private Double amount;

	public String getDeductionName() {
		return deductionName;
	}

	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public DeductionValueType getDeductionOverrideType() {
		return deductionOverrideType;
	}

	public void setDeductionOverrideType(DeductionValueType deductionOverrideType) {
		this.deductionOverrideType = deductionOverrideType;
	}

	public Long getEmployeePayrollDeductionId() {
		return employeePayrollDeductionId;
	}

	public void setEmployeePayrollDeductionId(Long employeePayrollDeductionId) {
		this.employeePayrollDeductionId = employeePayrollDeductionId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDeductionModel [employeePayrollDeductionId=");
		builder.append(employeePayrollDeductionId);
		builder.append(", deductionName=");
		builder.append(deductionName);
		builder.append(", deductionOverrideType=");
		builder.append(deductionOverrideType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

}
