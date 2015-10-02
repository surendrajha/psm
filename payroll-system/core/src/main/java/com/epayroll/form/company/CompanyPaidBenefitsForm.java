package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotEmpty;

import com.epayroll.entity.CompanyPaidBenefit.AccrualMode;
import com.epayroll.entity.CompanyPaidBenefit.RollOverHours;
import com.epayroll.entity.CompanyPaidBenefit.Status;

public class CompanyPaidBenefitsForm {

	private Long id;
	private Long companyEarningId;
	private AccrualMode accrualMode;
	@NotEmpty
	private String rate;
	private RollOverHours carryForwardHours;
	private String noOfHours;
	private Boolean isHoursAccrueOnLeave;
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyEarningId() {
		return companyEarningId;
	}

	public void setCompanyEarningId(Long companyEarningId) {
		this.companyEarningId = companyEarningId;
	}

	public AccrualMode getAccrualMode() {
		return accrualMode;
	}

	public void setAccrualMode(AccrualMode accrualMode) {
		this.accrualMode = accrualMode;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public RollOverHours getCarryForwardHours() {
		return carryForwardHours;
	}

	public void setCarryForwardHours(RollOverHours carryForwardHours) {
		this.carryForwardHours = carryForwardHours;
	}

	public String getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(String noOfHours) {
		this.noOfHours = noOfHours;
	}

	public Boolean getIsHoursAccrueOnLeave() {
		return isHoursAccrueOnLeave;
	}

	public void setIsHoursAccrueOnLeave(Boolean isHoursAccrueOnLeave) {
		this.isHoursAccrueOnLeave = isHoursAccrueOnLeave;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyPaidBenefitsForm [id=").append(id).append(", companyEarningId=")
				.append(companyEarningId).append(", accrualMode=").append(accrualMode)
				.append(", rate=").append(rate).append(", carryForwardHours=")
				.append(carryForwardHours).append(", noOfHours=").append(noOfHours)
				.append(", isHoursAccrueOnLeave=").append(isHoursAccrueOnLeave).append(", status=")
				.append(status).append("]");
		return builder.toString();
	}

}
