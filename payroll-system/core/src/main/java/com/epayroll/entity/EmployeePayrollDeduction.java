package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.epayroll.entity.EmployeeDeduction.DeductionType;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;

/**
 * EmployeePayrollDeduction generated by hbm2java
 */
@Entity
@Table(name = "employee_payroll_deduction")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmployeePayrollDeduction implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8183285694329843798L;
	private Long id;
	private EmployeePayroll employeePayroll;
	private EmployeeDeduction employeeDeduction;
	private BigDecimal value;
	private Boolean isDeductionOverride;
	private DeductionValueType deductionOverrideType;
	private BigDecimal deductionOverrideValue;
	private BigDecimal calculatedDeduction;
	private BigDecimal calculatedY2d;
	private DeductionType deductionType;
	private DeductionValueType deductionValueType;

	public EmployeePayrollDeduction() {
	}

	public EmployeePayrollDeduction(EmployeePayroll employeePayroll,
			EmployeeDeduction employeeDeduction, DeductionValueType deductionOverrideType,
			DeductionType deductionType) {
		this.employeePayroll = employeePayroll;
		this.employeeDeduction = employeeDeduction;
		this.deductionOverrideType = deductionOverrideType;
		this.deductionType = deductionType;

	}

	public EmployeePayrollDeduction(EmployeePayroll employeePayroll,
			EmployeeDeduction employeeDeduction, BigDecimal value, Boolean isDeductionOverride,
			DeductionValueType deductionOverrideType, BigDecimal deductionOverrideValue,
			BigDecimal calculatedDeduction, BigDecimal calculatedY2d, DeductionType deductionType,
			DeductionValueType deductionValueType) {
		this.employeePayroll = employeePayroll;
		this.employeeDeduction = employeeDeduction;
		this.value = value;
		this.isDeductionOverride = isDeductionOverride;
		this.deductionOverrideType = deductionOverrideType;
		this.deductionOverrideValue = deductionOverrideValue;
		this.calculatedDeduction = calculatedDeduction;
		this.calculatedY2d = calculatedY2d;
		this.deductionType = deductionType;
		this.deductionValueType = deductionValueType;

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_payroll_id")
	public EmployeePayroll getEmployeePayroll() {
		return this.employeePayroll;
	}

	public void setEmployeePayroll(EmployeePayroll employeePayroll) {
		this.employeePayroll = employeePayroll;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_deduction_id")
	public EmployeeDeduction getEmployeeDeduction() {
		return this.employeeDeduction;
	}

	public void setEmployeeDeduction(EmployeeDeduction employeeDeduction) {
		this.employeeDeduction = employeeDeduction;
	}

	@Column(name = "value", precision = 10, scale = 2)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Column(name = "is_deduction_override")
	public Boolean getIsDeductionOverride() {
		return this.isDeductionOverride;
	}

	public void setIsDeductionOverride(Boolean isDeductionOverride) {
		this.isDeductionOverride = isDeductionOverride;
	}

	@Column(name = "deduction_override_type")
	public DeductionValueType getDeductionOverrideType() {
		return this.deductionOverrideType;
	}

	public void setDeductionOverrideType(DeductionValueType deductionOverrideType) {
		this.deductionOverrideType = deductionOverrideType;
	}

	@Column(name = "deduction_override_value", precision = 10, scale = 2)
	public BigDecimal getDeductionOverrideValue() {
		return this.deductionOverrideValue;
	}

	public void setDeductionOverrideValue(BigDecimal deductionOverrideValue) {
		this.deductionOverrideValue = deductionOverrideValue;
	}

	@Column(name = "calculated_deduction", precision = 10, scale = 2)
	public BigDecimal getCalculatedDeduction() {
		return this.calculatedDeduction;
	}

	public void setCalculatedDeduction(BigDecimal calculatedDeduction) {
		this.calculatedDeduction = calculatedDeduction;
	}

	@Column(name = "calculated_y2d", precision = 30)
	public BigDecimal getCalculatedY2d() {
		return calculatedY2d;
	}

	public void setCalculatedY2d(BigDecimal calculatedY2d) {
		this.calculatedY2d = calculatedY2d;
	}

	@Column(name = "deduction_value_type")
	public DeductionValueType getDeductionValueType() {
		return deductionValueType;
	}

	public void setDeductionValueType(DeductionValueType deductionValueType) {
		this.deductionValueType = deductionValueType;
	}

	@Column(name = "deduction_type")
	public DeductionType getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(DeductionType deductionType) {
		this.deductionType = deductionType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeePayrollDeduction [id=");
		builder.append(id);
		builder.append(", employeePayroll=");
		builder.append(employeePayroll.getId());
		builder.append(", employeeDeduction=");
		builder.append(employeeDeduction.getId());
		builder.append(", value=");
		builder.append(value);
		builder.append(", isDeductionOverride=");
		builder.append(isDeductionOverride);
		builder.append(", deductionOverrideType=");
		builder.append(deductionOverrideType);
		builder.append(", deductionOverrideValue=");
		builder.append(deductionOverrideValue);
		builder.append(", calculatedDeduction=");
		builder.append(calculatedDeduction);
		builder.append(", calculatedY2d=");
		builder.append(calculatedY2d);
		builder.append(", deductionValueType=");
		builder.append(deductionValueType);
		builder.append(", deductionType=");
		builder.append(deductionType);
		builder.append("]");
		return builder.toString();
	}

}
