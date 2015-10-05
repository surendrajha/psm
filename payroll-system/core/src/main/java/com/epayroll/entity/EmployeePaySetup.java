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

/**
 * PaySetupForm generated by hbm2java
 */
@Entity
@Table(name = "employee_pay_setup")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmployeePaySetup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4332771741857596910L;
	private Long id;
	private Employee employee;
	private BigDecimal salary;
	private BigDecimal regularHours;
	private BigDecimal otherRate;
	private BigDecimal hourlyRate;
	private PayType payType;
	private BigDecimal payRate;
	private PayrollFrequency payrollFrequency;

	public enum PayType {
		HOURLY("Hourly"), SALARY("Salary");
		private String name;

		private PayType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public EmployeePaySetup() {
	}

	public EmployeePaySetup(Employee employee) {
		this.employee = employee;
	}

	public EmployeePaySetup(Employee employee, BigDecimal salary, BigDecimal regularHours,
			BigDecimal otherRate, BigDecimal hourlyRate, PayType payType, BigDecimal payRate,
			PayrollFrequency payrollFrequency) {
		this.employee = employee;
		this.salary = salary;
		this.regularHours = regularHours;
		this.otherRate = otherRate;
		this.hourlyRate = hourlyRate;
		this.payType = payType;
		this.payRate = payRate;
		this.payrollFrequency = payrollFrequency;
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

	@Column(name = "salary", precision = 10, scale = 2)
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Column(name = "regular_hours", precision = 10, scale = 2)
	public BigDecimal getRegularHours() {
		return this.regularHours;
	}

	public void setRegularHours(BigDecimal regularHours) {
		this.regularHours = regularHours;
	}

	@Column(name = "other_rate", precision = 10, scale = 2)
	public BigDecimal getOtherRate() {
		return this.otherRate;
	}

	public void setOtherRate(BigDecimal otherRate) {
		this.otherRate = otherRate;
	}

	@Column(name = "hourly_rate", precision = 10, scale = 2)
	public BigDecimal getHourlyRate() {
		return this.hourlyRate;
	}

	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	@Column(name = "pay_type")
	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	@Column(name = "pay_rate", precision = 10, scale = 2)
	public BigDecimal getPayRate() {
		return this.payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payroll_frequency_id")
	public PayrollFrequency getPayrollFrequency() {
		return payrollFrequency;
	}

	public void setPayrollFrequency(PayrollFrequency payrollFrequency) {
		this.payrollFrequency = payrollFrequency;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaySetupForm [id=");
		builder.append(id);
		builder.append(", employee=");
		builder.append(employee.getId());
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", regularHours=");
		builder.append(regularHours);
		builder.append(", otherRate=");
		builder.append(otherRate);
		builder.append(", hourlyRate=");
		builder.append(hourlyRate);
		builder.append(", payType=");
		builder.append(payType);
		builder.append(", payRate=");
		builder.append(payRate);
		builder.append(", payrollFrequency=");
		builder.append(payrollFrequency.getId());
		builder.append("]");
		return builder.toString();
	}

}
