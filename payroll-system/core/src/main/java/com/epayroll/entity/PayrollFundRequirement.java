package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * PayrollFundRequirement generated by hbm2java
 */
@Entity
@Table(name = "payroll_fund_requirement")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PayrollFundRequirement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8601614983612883246L;
	private Long id;
	private BigDecimal directDepositAmount;
	private BigDecimal companyTax;
	private BigDecimal employeeTax;
	private BigDecimal checkAmount;
	private BigDecimal employeeDeduction;
	private BigDecimal vjsFee;
	private BigDecimal companyDeduction;
	private Payroll payroll;

	public PayrollFundRequirement() {
	}

	public PayrollFundRequirement(BigDecimal directDepositAmount, BigDecimal companyTax,
			BigDecimal employeeTax, BigDecimal checkAmount, BigDecimal employeeDeduction,
			BigDecimal vjsFee, BigDecimal companyDeduction, Payroll payroll) {
		this.directDepositAmount = directDepositAmount;
		this.companyTax = companyTax;
		this.employeeTax = employeeTax;
		this.checkAmount = checkAmount;
		this.employeeDeduction = employeeDeduction;
		this.vjsFee = vjsFee;
		this.companyDeduction = companyDeduction;
		this.payroll = payroll;
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

	@Column(name = "direct_deposit_amount", precision = 10, scale = 2)
	public BigDecimal getDirectDepositAmount() {
		return this.directDepositAmount;
	}

	public void setDirectDepositAmount(BigDecimal directDepositAmount) {
		this.directDepositAmount = directDepositAmount;
	}

	@Column(name = "company_tax", precision = 10, scale = 2)
	public BigDecimal getCompanyTax() {
		return this.companyTax;
	}

	public void setCompanyTax(BigDecimal companyTax) {
		this.companyTax = companyTax;
	}

	@Column(name = "employee_tax", precision = 10, scale = 2)
	public BigDecimal getEmployeeTax() {
		return this.employeeTax;
	}

	public void setEmployeeTax(BigDecimal employeeTax) {
		this.employeeTax = employeeTax;
	}

	@Column(name = "check_amount", precision = 10, scale = 2)
	public BigDecimal getCheckAmount() {
		return this.checkAmount;
	}

	public void setCheckAmount(BigDecimal checkAmount) {
		this.checkAmount = checkAmount;
	}

	@Column(name = "employee_deduction", precision = 10, scale = 2)
	public BigDecimal getEmployeeDeduction() {
		return this.employeeDeduction;
	}

	public void setEmployeeDeduction(BigDecimal employeeDeduction) {
		this.employeeDeduction = employeeDeduction;
	}

	@Column(name = "vjs_fee", precision = 10, scale = 2)
	public BigDecimal getVjsFee() {
		return this.vjsFee;
	}

	public void setVjsFee(BigDecimal vjsFee) {
		this.vjsFee = vjsFee;
	}

	@Column(name = "company_deduction", precision = 10, scale = 2)
	public BigDecimal getCompanyDeduction() {
		return this.companyDeduction;
	}

	public void setCompanyDeduction(BigDecimal companyDeduction) {
		this.companyDeduction = companyDeduction;
	}

	@OneToOne
	public Payroll getPayroll() {
		return this.payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollFundRequirement [id=");
		builder.append(id);
		builder.append(", directDepositAmount=");
		builder.append(directDepositAmount);
		builder.append(", companyTax=");
		builder.append(companyTax);
		builder.append(", Payroll ID=");
		builder.append(payroll.getId());
		builder.append(", employeeTax=");
		builder.append(employeeTax);
		builder.append(", checkAmount=");
		builder.append(checkAmount);
		builder.append(", employeeDeduction=");
		builder.append(employeeDeduction);
		builder.append(", vjsFee=");
		builder.append(vjsFee);
		builder.append(", companyDeduction=");
		builder.append(companyDeduction);
		builder.append("]");
		return builder.toString();
	}

}
