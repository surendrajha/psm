package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;

/**
 * EmployeeTax generated by hbm2java
 */
@Entity
@Table(name = "employee_w4_detail")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmployeeW4Detail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1041641064505372515L;

	private Long id;
	private FilingStatus filingStatus;
	private EmployeeTax employeeTax;
	private BigDecimal additionalWithholding;
	private Status status;
	private Date asOnDate;
	private BigDecimal totalTaxLiability;
	private TaxOverrideType taxOverrideType;
	private BigDecimal taxOverrideValue;
	private Boolean overrideFromCompany;

	private Set<EmployeeAllowanceRecord> employeeAllowanceRecords = new HashSet<EmployeeAllowanceRecord>(
			0);

	public enum Status {
		ACTIVE("Active"), INACTIVE("InActive");
		private String name;

		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public EmployeeW4Detail() {
	}

	public EmployeeW4Detail(FilingStatus filingStatus, EmployeeTax employeeTax) {
		this.filingStatus = filingStatus;
		this.setEmployeeTax(employeeTax);
	}

	public EmployeeW4Detail(FilingStatus filingStatus, EmployeeTax employeeTax,
			BigDecimal additionalWithholding, Status status, Date asOnDate,
			BigDecimal totalTaxLiability, TaxOverrideType taxOverrideType,
			BigDecimal taxOverrideValue, Boolean overrideFromCompany,
			Set<EmployeeAllowanceRecord> employeeAllowanceRecords) {
		super();
		this.filingStatus = filingStatus;
		this.employeeTax = employeeTax;
		this.additionalWithholding = additionalWithholding;
		this.status = status;
		this.asOnDate = asOnDate;
		this.setTotalTaxLiability(totalTaxLiability);
		this.taxOverrideType = taxOverrideType;
		this.taxOverrideValue = taxOverrideValue;
		this.overrideFromCompany = overrideFromCompany;
		this.employeeAllowanceRecords = employeeAllowanceRecords;
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
	@JoinColumn(name = "filing_status_id")
	public FilingStatus getFilingStatus() {
		return this.filingStatus;
	}

	public void setFilingStatus(FilingStatus filingStatus) {
		this.filingStatus = filingStatus;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_tax_id")
	public EmployeeTax getEmployeeTax() {
		return employeeTax;
	}

	public void setEmployeeTax(EmployeeTax employeeTax) {
		this.employeeTax = employeeTax;
	}

	@Column(name = "additional_withholding", precision = 10, scale = 2)
	public BigDecimal getAdditionalWithholding() {
		return this.additionalWithholding;
	}

	public void setAdditionalWithholding(BigDecimal additionalWithholding) {
		this.additionalWithholding = additionalWithholding;
	}

	@Column(name = "status", length = 45)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "as_on_date", length = 19)
	public Date getAsOnDate() {
		return this.asOnDate;
	}

	public void setAsOnDate(Date asOnDate) {
		this.asOnDate = asOnDate;
	}

	@Column(name = "total_tax_liability", precision = 10, scale = 2)
	public BigDecimal getTotalTaxLiability() {
		return totalTaxLiability;
	}

	public void setTotalTaxLiability(BigDecimal totalTaxLiability) {
		this.totalTaxLiability = totalTaxLiability;
	}

	@Column(name = "tax_override_type")
	public TaxOverrideType getTaxOverrideType() {
		return this.taxOverrideType;
	}

	public void setTaxOverrideType(TaxOverrideType taxOverrideType) {
		this.taxOverrideType = taxOverrideType;
	}

	@Column(name = "tax_override_value", precision = 10, scale = 2)
	public BigDecimal getTaxOverrideValue() {
		return this.taxOverrideValue;
	}

	public void setTaxOverrideValue(BigDecimal taxOverrideValue) {
		this.taxOverrideValue = taxOverrideValue;
	}

	@Column(name = "override_from_company")
	public Boolean getOverrideFromCompany() {
		return overrideFromCompany;
	}

	public void setOverrideFromCompany(Boolean overrideFromCompany) {
		this.overrideFromCompany = overrideFromCompany;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeW4Detail", cascade = CascadeType.ALL)
	public Set<EmployeeAllowanceRecord> getEmployeeAllowanceRecords() {
		return employeeAllowanceRecords;
	}

	public void setEmployeeAllowanceRecords(Set<EmployeeAllowanceRecord> employeeAllowanceRecords) {
		this.employeeAllowanceRecords = employeeAllowanceRecords;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeW4Detail [id=");
		builder.append(id);
		builder.append(", filingStatus=");
		builder.append(filingStatus.getId());
		builder.append(", employeeTax=");
		builder.append(employeeTax.getId());
		builder.append(", additionalWithholding=");
		builder.append(additionalWithholding);
		builder.append(", status=");
		builder.append(status);
		builder.append(", asOnDate=");
		builder.append(asOnDate);
		builder.append(", totalTaxLiability=");
		builder.append(totalTaxLiability);
		builder.append(", taxOverrideType=");
		builder.append(taxOverrideType);
		builder.append(", taxOverrideValue=");
		builder.append(taxOverrideValue);
		builder.append(", overrideFromCompany=");
		builder.append(overrideFromCompany);
		builder.append("]");
		return builder.toString();
	}

}