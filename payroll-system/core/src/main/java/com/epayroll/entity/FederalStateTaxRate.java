package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FederalStateTaxRate generated by hbm2java
 */
@Entity
@Table(name = "federal_state_tax_rate")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class FederalStateTaxRate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6293641053753457950L;
	private Long id;
	private TaxType taxType;
	private FilingStatus filingStatus;
	private BigDecimal minValue;
	private BigDecimal maxValue;
	private BigDecimal employeeRate;
	private BigDecimal companyRate;
	private BigDecimal maximumFutaCredit;
	private BigDecimal ceiling;
	private BigDecimal calculatedSlabAmount;
	private Date dateOfEntry;
	private Date wef;
	private Date releaseDate;

	public FederalStateTaxRate() {
	}

	public FederalStateTaxRate(TaxType taxType, FilingStatus filingStatus) {
		this.taxType = taxType;
		this.filingStatus = filingStatus;
	}

	public FederalStateTaxRate(TaxType taxType, FilingStatus filingStatus, BigDecimal minValue,
			BigDecimal maxValue, BigDecimal employeeRate, BigDecimal companyRate,
			BigDecimal maximumFutaCredit, BigDecimal ceiling, BigDecimal calculatedSlabAmount,
			Date dateOfEntry, Date wef, Date releaseDate) {
		this.taxType = taxType;
		this.filingStatus = filingStatus;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.employeeRate = employeeRate;
		this.companyRate = companyRate;
		this.maximumFutaCredit = maximumFutaCredit;
		this.ceiling = ceiling;
		this.calculatedSlabAmount = calculatedSlabAmount;
		this.dateOfEntry = dateOfEntry;
		this.wef = wef;
		this.releaseDate = releaseDate;
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
	@JoinColumn(name = "tax_type_id")
	public TaxType getTaxType() {
		return this.taxType;
	}

	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filing_status_id")
	public FilingStatus getFilingStatus() {
		return this.filingStatus;
	}

	public void setFilingStatus(FilingStatus filingStatus) {
		this.filingStatus = filingStatus;
	}

	@Column(name = "min_value", precision = 10, scale = 2)
	public BigDecimal getMinValue() {
		return this.minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	@Column(name = "max_value", precision = 10, scale = 2)
	public BigDecimal getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	@Column(name = "employee_rate", precision = 10, scale = 2)
	public BigDecimal getEmployeeRate() {
		return this.employeeRate;
	}

	public void setEmployeeRate(BigDecimal employeeRate) {
		this.employeeRate = employeeRate;
	}

	@Column(name = "company_rate", precision = 10, scale = 2)
	public BigDecimal getCompanyRate() {
		return companyRate;
	}

	public void setCompanyRate(BigDecimal companyRate) {
		this.companyRate = companyRate;
	}

	@Column(name = "maximum_futa_credit", precision = 10, scale = 2)
	public BigDecimal getMaximumFutaCredit() {
		return maximumFutaCredit;
	}

	public void setMaximumFutaCredit(BigDecimal maximumFutaCredit) {
		this.maximumFutaCredit = maximumFutaCredit;
	}

	@Column(name = "ceiling", precision = 10, scale = 2)
	public BigDecimal getCeiling() {
		return this.ceiling;
	}

	public void setCeiling(BigDecimal ceiling) {
		this.ceiling = ceiling;
	}

	@Column(name = "calculated_slab_amount", precision = 10, scale = 2)
	public BigDecimal getCalculatedSlabAmount() {
		return this.calculatedSlabAmount;
	}

	public void setCalculatedSlabAmount(BigDecimal calculatedSlabAmount) {
		this.calculatedSlabAmount = calculatedSlabAmount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_entry", length = 19)
	public Date getDateOfEntry() {
		return this.dateOfEntry;
	}

	public void setDateOfEntry(Date dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "wef", length = 19)
	public Date getWef() {
		return this.wef;
	}

	public void setWef(Date wef) {
		this.wef = wef;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_date", length = 19)
	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FederalStateTaxRate [id=");
		builder.append(id);
		builder.append(", taxType=");
		builder.append(taxType.getId());
		builder.append(", filingStatus=");
		builder.append(filingStatus);
		builder.append(", minValue=");
		builder.append(minValue);
		builder.append(", maxValue=");
		builder.append(maxValue);
		builder.append(", employeeRate=");
		builder.append(employeeRate);
		builder.append(", companyRate=");
		builder.append(companyRate);
		builder.append(", maximumFutaCredit=");
		builder.append(maximumFutaCredit);
		builder.append(", ceiling=");
		builder.append(ceiling);
		builder.append(", calculatedSlabAmount=");
		builder.append(calculatedSlabAmount);
		builder.append(", dateOfEntry=");
		builder.append(dateOfEntry);
		builder.append(", wef=");
		builder.append(wef);
		builder.append(", releaseDate=");
		builder.append(releaseDate);
		builder.append("]");
		return builder.toString();
	}

}
