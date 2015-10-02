package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FilingStatus generated by hbm2java
 */
@Entity
@Table(name = "filing_status")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class FilingStatus implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3565409005394970448L;
	private Long id;
	private String status;
	private String description;
	private Set<FederalStateTaxRate> federalStateTaxRates = new HashSet<FederalStateTaxRate>(0);
	private Set<EmployeeW4Detail> employeeW4Details = new HashSet<EmployeeW4Detail>(0);
	private Set<StandardDeductionRate> standardDeductionRates = new HashSet<StandardDeductionRate>(
			0);

	public FilingStatus() {
	}

	public FilingStatus(String status, String description,
			Set<FederalStateTaxRate> federalStateTaxRates, Set<EmployeeW4Detail> employeeW4Details,
			Set<StandardDeductionRate> standardDeductionRates) {
		this.status = status;
		this.description = description;
		this.federalStateTaxRates = federalStateTaxRates;
		this.setEmployeeW4Details(employeeW4Details);
		this.standardDeductionRates = standardDeductionRates;
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

	@Column(name = "status", length = 45, unique = true)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "filingStatus")
	public Set<FederalStateTaxRate> getFederalStateTaxRates() {
		return this.federalStateTaxRates;
	}

	public void setFederalStateTaxRates(Set<FederalStateTaxRate> federalStateTaxRates) {
		this.federalStateTaxRates = federalStateTaxRates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "filingStatus", cascade = CascadeType.ALL)
	public Set<EmployeeW4Detail> getEmployeeW4Details() {
		return employeeW4Details;
	}

	public void setEmployeeW4Details(Set<EmployeeW4Detail> employeeW4Details) {
		this.employeeW4Details = employeeW4Details;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "filingStatus")
	public Set<StandardDeductionRate> getStandardDeductionRates() {
		return this.standardDeductionRates;
	}

	public void setStandardDeductionRates(Set<StandardDeductionRate> standardDeductionRates) {
		this.standardDeductionRates = standardDeductionRates;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilingStatus [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}