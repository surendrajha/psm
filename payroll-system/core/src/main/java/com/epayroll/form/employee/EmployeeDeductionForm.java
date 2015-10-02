package com.epayroll.form.employee;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.entity.Deduction;
import com.epayroll.entity.DeductionCategory;
import com.epayroll.entity.EmployeeDeduction.DeductionType;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;

/**
 * @author Uma
 */
public class EmployeeDeductionForm {
	private Long id;
	private Long employeeId;
	private Long employeeDeductionId;
	private List<DeductionCategory> deductionCategory;
	private List<Deduction> deductions;
	private DeductionValueType deductionValueType;
	private DeductionType deductionType;
	private BigDecimal value;
	private BigDecimal targetValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getEmployeeDeductionId() {
		return employeeDeductionId;
	}

	public void setEmployeeDeductionId(Long employeeDeductionId) {
		this.employeeDeductionId = employeeDeductionId;
	}

	public List<DeductionCategory> getDeductionCategory() {
		return deductionCategory;
	}

	public void setDeductionCategory(List<DeductionCategory> deductionCategory) {
		this.deductionCategory = deductionCategory;
	}

	public List<Deduction> getDeductions() {
		return deductions;
	}

	public void setDeductions(List<Deduction> deductions) {
		this.deductions = deductions;
	}

	public DeductionValueType getDeductionValueType() {
		return deductionValueType;
	}

	public void setDeductionValueType(DeductionValueType deductionValueType) {
		this.deductionValueType = deductionValueType;
	}

	public DeductionType getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(DeductionType deductionType) {
		this.deductionType = deductionType;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDeductionForm [id=");
		builder.append(id);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", employeeDeductionId=");
		builder.append(employeeDeductionId);
		builder.append(", deductionCategory=");
		builder.append(deductionCategory);
		builder.append(", deductions=");
		builder.append(deductions);
		builder.append(", deductionValueType=");
		builder.append(deductionValueType);
		builder.append(", deductionType=");
		builder.append(deductionType);
		builder.append(", value=");
		builder.append(value);
		builder.append(", targetValue=");
		builder.append(targetValue);
		builder.append("]");
		return builder.toString();
	}
}
