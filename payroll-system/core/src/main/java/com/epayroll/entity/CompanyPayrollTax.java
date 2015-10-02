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
 * CompanyPayrollTax generated by hbm2java
 */
@Entity
@Table(name = "company_payroll_tax")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class CompanyPayrollTax implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3999672007896460790L;
	private long id;
	private EmployeePayroll employeePayroll;
	private CompanyTax companyTax;
	private BigDecimal taxCalculated;

	public CompanyPayrollTax() {
	}

	public CompanyPayrollTax(long id, EmployeePayroll employeePayroll, CompanyTax companyTax) {
		this.id = id;
		this.employeePayroll = employeePayroll;
		this.companyTax = companyTax;
	}

	public CompanyPayrollTax(long id, EmployeePayroll employeePayroll, CompanyTax companyTax,
			BigDecimal taxCalculated) {
		this.id = id;
		this.employeePayroll = employeePayroll;
		this.companyTax = companyTax;
		this.taxCalculated = taxCalculated;
	}

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = IDENTITY)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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
	@JoinColumn(name = "company_tax_id")
	public CompanyTax getCompanyTax() {
		return this.companyTax;
	}

	public void setCompanyTax(CompanyTax companyTax) {
		this.companyTax = companyTax;
	}

	@Column(name = "tax_calculated", precision = 10, scale = 2)
	public BigDecimal getTaxCalculated() {
		return this.taxCalculated;
	}

	public void setTaxCalculated(BigDecimal taxCalculated) {
		this.taxCalculated = taxCalculated;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyPayrollTax [id=");
		builder.append(id);
		builder.append(", employeePayroll=");
		builder.append(employeePayroll.getId());
		builder.append(", companyTax=");
		builder.append(companyTax.getId());
		builder.append(", taxCalculated=");
		builder.append(taxCalculated);
		builder.append("]");
		return builder.toString();
	}

}