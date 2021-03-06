package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
 * EmployeeEarning generated by hbm2java
 */
@Entity
@Table(name = "employee_earning")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmployeeEarning implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5061002643049755167L;
	private Long id;
	private Employee employee;
	private CompanyEarning companyEarning;
	private EarningValueType earningValueType;
	private BigDecimal value;
	private Set<EmployeePayrollEarning> employeePayrollEarnings = new HashSet<EmployeePayrollEarning>(
			0);

	public enum EarningValueType {
		AMOUNT("Amount"), HOURS("Hours");
		private String name;

		private EarningValueType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public EmployeeEarning() {
	}

	public EmployeeEarning(Employee employee, CompanyEarning companyEarning,
			EarningValueType earningValueType) {
		this.employee = employee;
		this.companyEarning = companyEarning;
		this.earningValueType = earningValueType;
	}

	public EmployeeEarning(Employee employee, CompanyEarning companyEarning,
			EarningValueType earningValueType, BigDecimal value,
			Set<EmployeePayrollEarning> employeePayrollEarnings) {
		this.employee = employee;
		this.companyEarning = companyEarning;
		this.earningValueType = earningValueType;
		this.value = value;
		this.employeePayrollEarnings = employeePayrollEarnings;
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_earning_id")
	public CompanyEarning getCompanyEarning() {
		return this.companyEarning;
	}

	public void setCompanyEarning(CompanyEarning companyEarning) {
		this.companyEarning = companyEarning;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeEarning")
	public Set<EmployeePayrollEarning> getPayrollEarnings() {
		return this.employeePayrollEarnings;
	}

	public void setPayrollEarnings(Set<EmployeePayrollEarning> employeePayrollEarnings) {
		this.employeePayrollEarnings = employeePayrollEarnings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeEarning [id=");
		builder.append(id);
		builder.append(", employee=");
		builder.append(employee.getId());
		builder.append(", companyEarning=");
		builder.append(companyEarning.getId());
		builder.append(", earningValueType=");
		builder.append(earningValueType);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
