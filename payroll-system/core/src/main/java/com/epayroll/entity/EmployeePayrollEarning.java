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

import com.epayroll.entity.EmployeeEarning.EarningValueType;

/**
 * PayrollEarning generated by hbm2java
 */
@Entity
@Table(name = "employee_payroll_earning")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmployeePayrollEarning implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8616433967362966391L;
	private Long id;
	private EmployeeEarning employeeEarning;
	private EmployeePayroll employeePayroll;
	private EarningValueType earningValueType;
	private BigDecimal value;
	private BigDecimal calculatedEarning;
	private BigDecimal calculatedY2d;

	public EmployeePayrollEarning() {
	}

	public EmployeePayrollEarning(EmployeeEarning employeeEarning, EmployeePayroll employeePayroll,
			EarningValueType earningValueType, BigDecimal value, BigDecimal calculatedEarning,
			BigDecimal calculatedY2d) {
		super();
		this.employeeEarning = employeeEarning;
		this.employeePayroll = employeePayroll;
		this.earningValueType = earningValueType;
		this.value = value;
		this.calculatedEarning = calculatedEarning;
		this.calculatedY2d = calculatedY2d;
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
	@JoinColumn(name = "employee_earning_id")
	public EmployeeEarning getEmployeeEarning() {
		return this.employeeEarning;
	}

	public void setEmployeeEarning(EmployeeEarning employeeEarning) {
		this.employeeEarning = employeeEarning;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_payroll_id")
	public EmployeePayroll getEmployeePayroll() {
		return this.employeePayroll;
	}

	public void setEmployeePayroll(EmployeePayroll employeePayroll) {
		this.employeePayroll = employeePayroll;
	}

	@Column(name = "earning_value_type")
	public EarningValueType getEarningValueType() {
		return earningValueType;
	}

	public void setEarningValueType(EarningValueType earningValueType) {
		this.earningValueType = earningValueType;
	}

	@Column(name = "value", precision = 10, scale = 2)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Column(name = "calculated_earning", precision = 10, scale = 2)
	public BigDecimal getCalculatedEarning() {
		return calculatedEarning;
	}

	public void setCalculatedEarning(BigDecimal calculatedEarning) {
		this.calculatedEarning = calculatedEarning;
	}

	@Column(name = "calculated_y2d", precision = 10, scale = 2)
	public BigDecimal getCalculatedY2d() {
		return calculatedY2d;
	}

	public void setCalculatedY2d(BigDecimal calculatedY2d) {
		this.calculatedY2d = calculatedY2d;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollEarning [id=");
		builder.append(id);
		builder.append(", employeeEarning=");
		builder.append(employeeEarning.getId());
		builder.append(", employeePayroll=");
		builder.append(employeePayroll.getId());
		builder.append(", earningValueType=");
		builder.append(earningValueType);
		builder.append(", value=");
		builder.append(value);
		builder.append(", calculatedEarning=");
		builder.append(calculatedEarning);
		builder.append(", calculatedY2d=");
		builder.append(calculatedY2d);
		builder.append("]");
		return builder.toString();
	}

}
