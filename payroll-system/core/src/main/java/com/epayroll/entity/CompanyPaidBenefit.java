package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CompanyPaidBenefit generated by hbm2java
 */
@Entity
@Table(name = "company_paid_benefit")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class CompanyPaidBenefit implements java.io.Serializable {

	/**
	 * 
	 */
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

	public enum AccrualMode {
		PAY_HOURLY("Pay Hourly"), FIXED("Fixed");
		private String name;

		private AccrualMode(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum RollOverHours {
		ALL_HOURS("All Hours"), SOME_HOURS("Some Hours"), ZERO_HOURS("Zero Hours");
		private String name;

		private RollOverHours(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private static final long serialVersionUID = 11788795314107170L;
	private Long id;
	private Company company;
	private CompanyEarning companyEarning;
	private Status status;
	private AccrualMode accrualMode;
	private Float accrualRate;
	private RollOverHours rolloverHours;
	private Float noOfHours;
	private Boolean accrueHoursOnLeave;

	public CompanyPaidBenefit() {
	}

	public CompanyPaidBenefit(Company company, CompanyEarning companyEarning) {
		this.company = company;
		this.companyEarning = companyEarning;
	}

	public CompanyPaidBenefit(Company company, CompanyEarning companyEarning, Status status,
			AccrualMode accrualMode, Float accrualRate, RollOverHours rolloverHours,
			Float noOfHours, Boolean accrueHoursOnLeave) {
		this.company = company;
		this.companyEarning = companyEarning;
		this.status = status;
		this.accrualMode = accrualMode;
		this.accrualRate = accrualRate;
		this.rolloverHours = rolloverHours;
		this.noOfHours = noOfHours;
		this.accrueHoursOnLeave = accrueHoursOnLeave;
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
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_earning_id")
	public CompanyEarning getCompanyEarning() {
		return this.companyEarning;
	}

	public void setCompanyEarning(CompanyEarning companyEarning) {
		this.companyEarning = companyEarning;
	}

	@Column(name = "status", length = 20)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "accrual_mode", length = 45)
	public AccrualMode getAccrualMode() {
		return this.accrualMode;
	}

	public void setAccrualMode(AccrualMode accrualMode) {
		this.accrualMode = accrualMode;
	}

	@Column(name = "accrual_rate", precision = 12, scale = 0)
	public Float getAccrualRate() {
		return this.accrualRate;
	}

	public void setAccrualRate(Float accrualRate) {
		this.accrualRate = accrualRate;
	}

	@Column(name = "rollover_hours")
	public RollOverHours getRolloverHours() {
		return this.rolloverHours;
	}

	public void setRolloverHours(RollOverHours rolloverHours) {
		this.rolloverHours = rolloverHours;
	}

	@Column(name = "no_of_hours", precision = 12, scale = 0)
	public Float getNoOfHours() {
		return this.noOfHours;
	}

	public void setNoOfHours(Float noOfHours) {
		this.noOfHours = noOfHours;
	}

	@Column(name = "accrue_hours_on_leave")
	public Boolean getAccrueHoursOnLeave() {
		return this.accrueHoursOnLeave;
	}

	public void setAccrueHoursOnLeave(Boolean accrueHoursOnLeave) {
		this.accrueHoursOnLeave = accrueHoursOnLeave;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyPaidBenefit [id=");
		builder.append(id);
		builder.append(", company=");
		builder.append(company.getId());
		builder.append(", companyEarning=");
		builder.append(companyEarning.getId());
		builder.append(", status=");
		builder.append(status);
		builder.append(", accrualMode=");
		builder.append(accrualMode);
		builder.append(", accrualRate=");
		builder.append(accrualRate);
		builder.append(", rolloverHours=");
		builder.append(rolloverHours);
		builder.append(", noOfHours=");
		builder.append(noOfHours);
		builder.append(", accrueHoursOnLeave=");
		builder.append(accrueHoursOnLeave);
		builder.append("]");
		return builder.toString();
	}

}
