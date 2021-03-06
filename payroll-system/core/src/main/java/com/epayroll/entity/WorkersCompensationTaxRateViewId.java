package com.epayroll.entity;

// Generated Jan 8, 2013 4:53:46 AM by Hibernate Tools 3.6.0

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WorkersCompensationTaxRateViewId generated by hbm2java
 */
@Embeddable
public class WorkersCompensationTaxRateViewId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2871382623070921141L;
	private String code;
	private String description;
	private BigDecimal rate;
	private long companyId;
	private String stateName;

	public WorkersCompensationTaxRateViewId() {
	}

	public WorkersCompensationTaxRateViewId(String code, long companyId, String stateName) {
		this.code = code;
		this.companyId = companyId;
		this.stateName = stateName;
	}

	public WorkersCompensationTaxRateViewId(String code, String description, BigDecimal rate,
			long companyId, String stateName) {
		this.code = code;
		this.description = description;
		this.rate = rate;
		this.companyId = companyId;
		this.stateName = stateName;
	}

	@Column(name = "code", length = 45)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "rate", precision = 7, scale = 3)
	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Column(name = "id")
	public long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "state_name", length = 45)
	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof WorkersCompensationTaxRateViewId)) {
			return false;
		}
		WorkersCompensationTaxRateViewId castOther = (WorkersCompensationTaxRateViewId) other;

		return ((this.getCode() == castOther.getCode()) || (this.getCode() != null
				&& castOther.getCode() != null && this.getCode().equals(castOther.getCode())))
				&& ((this.getDescription() == castOther.getDescription()) || (this.getDescription() != null
						&& castOther.getDescription() != null && this.getDescription().equals(
						castOther.getDescription())))
				&& ((this.getRate() == castOther.getRate()) || (this.getRate() != null
						&& castOther.getRate() != null && this.getRate()
						.equals(castOther.getRate())))
				&& (this.getCompanyId() == castOther.getCompanyId())
				&& ((this.getStateName() == castOther.getStateName()) || (this.getStateName() != null
						&& castOther.getStateName() != null && this.getStateName().equals(
						castOther.getStateName())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCode() == null ? 0 : this.getCode().hashCode());
		result = 37 * result + (getDescription() == null ? 0 : this.getDescription().hashCode());
		result = 37 * result + (getRate() == null ? 0 : this.getRate().hashCode());
		result = 37 * result + (int) this.getCompanyId();
		result = 37 * result + (getStateName() == null ? 0 : this.getStateName().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "WorkersCompensationTaxRateViewId [code=" + code + ", description=" + description
				+ ", rate=" + rate + ", companyId=" + companyId + ", stateName=" + stateName + "]";
	}

}
