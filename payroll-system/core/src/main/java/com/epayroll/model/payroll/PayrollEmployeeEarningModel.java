package com.epayroll.model.payroll;

import com.epayroll.entity.EmployeeEarning.EarningValueType;

public class PayrollEmployeeEarningModel {

	private Long employeePayrollEarningId;
	private Long employeeEarningId;
	private Double value;
	private EarningValueType earningValueType;

	public Long getEmployeeEarningId() {
		return employeeEarningId;
	}

	public void setEmployeeEarningId(Long employeeEarningId) {
		this.employeeEarningId = employeeEarningId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Long getEmployeePayrollEarningId() {
		return employeePayrollEarningId;
	}

	public void setEmployeePayrollEarningId(Long employeePayrollEarningId) {
		this.employeePayrollEarningId = employeePayrollEarningId;
	}

	public EarningValueType getEarningValueType() {
		return earningValueType;
	}

	public void setEarningValueType(EarningValueType earningValueType) {
		this.earningValueType = earningValueType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollEmployeeEarningModel [employeePayrollEarningId=");
		builder.append(employeePayrollEarningId);
		builder.append(", employeeEarningId=");
		builder.append(employeeEarningId);
		builder.append(", value=");
		builder.append(value);
		builder.append(", earningValueType=");
		builder.append(earningValueType);
		builder.append("]");
		return builder.toString();
	}

}
