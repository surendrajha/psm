package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EmployeeDeduction generated by hbm2java
 */
@Entity
@Table(name = "employee_deduction")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmployeeDeduction implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8404813672703043781L;
	private Long id;
	private Employee employee;
	private DeductionValueType deductionValueType;
	private CompanyDeduction companyDeduction;
	private DeductionType deductionType;
	private BigDecimal value;
	private BigDecimal targetValue;
	private BigDecimal amountpaid;
	private Status status;
	private Set<EmployeePayrollDeduction> employeePayrollDeductions = new HashSet<EmployeePayrollDeduction>(
			0);

	public enum Status {
		NOT_APPLICABLE("Status Not Applicable"), TARGET_VALUE_REACHED("Target Value Reached  "), DEDUCTION_APPLICABLE(
				"Deduction Applicable");
		private String name;

		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum DeductionValueType {
		PERCENTAGE("Percentage"), AMOUNT("Amount");
		private String name;

		private DeductionValueType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum DeductionType {
		PRE_TAX("Pre Tax"), POST_TAX("Post Tax"), NON_TAX("Non Tax");
		private String name;

		private DeductionType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public EmployeeDeduction() {
	}

	public EmployeeDeduction(Employee employee, DeductionValueType deductionValueType,
			CompanyDeduction companyDeduction, DeductionType deductionType, Status status) {
		this.employee = employee;
		this.deductionValueType = deductionValueType;
		this.companyDeduction = companyDeduction;
		this.deductionType = deductionType;
		this.status = status;
	}

	public EmployeeDeduction(Employee employee, DeductionValueType deductionValueType,
			CompanyDeduction companyDeduction, DeductionType deductionType, BigDecimal value,
			BigDecimal targetValue, Set<EmployeePayrollDeduction> employeePayrollDeductions,
			Status status, BigDecimal amountPaid) {
		this.employee = employee;
		this.deductionValueType = deductionValueType;
		this.companyDeduction = companyDeduction;
		this.deductionType = deductionType;
		this.value = value;
		this.targetValue = targetValue;
		this.employeePayrollDeductions = employeePayrollDeductions;
		this.status = status;
		this.amountpaid = amountPaid;
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
	@JoinColumn(name = "employee_id")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "deduction_value_type")
	public DeductionValueType getDeductionValueType() {
		return this.deductionValueType;
	}

	public void setDeductionValueType(DeductionValueType deductionValueType) {
		this.deductionValueType = deductionValueType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_deduction_id")
	public CompanyDeduction getCompanyDeduction() {
		return this.companyDeduction;
	}

	public void setCompanyDeduction(CompanyDeduction companyDeduction) {
		this.companyDeduction = companyDeduction;
	}

	@Column(name = "deduction_type")
	public DeductionType getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(DeductionType deductionType) {
		this.deductionType = deductionType;
	}

	@Column(name = "value", precision = 10, scale = 2)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Column(name = "target_value", precision = 10, scale = 2)
	public BigDecimal getTargetValue() {
		return this.targetValue;
	}

	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeDeduction")
	public Set<EmployeePayrollDeduction> getEmployeePayrollDeductions() {
		return this.employeePayrollDeductions;
	}

	public void setEmployeePayrollDeductions(Set<EmployeePayrollDeduction> employeePayrollDeductions) {
		this.employeePayrollDeductions = employeePayrollDeductions;
	}

	@Column(name = "amount_paid", precision = 10, scale = 2)
	public BigDecimal getAmountpaid() {
		return amountpaid;
	}

	public void setAmountpaid(BigDecimal amountpaid) {
		this.amountpaid = amountpaid;
	}

	@Column(name = "status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDeduction [id=");
		builder.append(id);
		builder.append(", employee=");
		builder.append(employee.getId());
		builder.append(", deductionValueType=");
		builder.append(deductionValueType);
		builder.append(", companyDeduction=");
		builder.append(companyDeduction.getId());
		builder.append(", deductionType=");
		builder.append(deductionType);
		builder.append(", value=");
		builder.append(value);
		builder.append(", targetValue=");
		builder.append(targetValue);
		builder.append(", amountPaid=");
		builder.append(amountpaid);
		builder.append(", Status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
