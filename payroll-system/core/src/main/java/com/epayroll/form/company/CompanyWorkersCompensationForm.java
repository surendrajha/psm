package com.epayroll.form.company;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CompanyWorkersCompensationForm {

	private Long id;
	private Long companyId;
	@NotNull
	private Long stateId;
	private Long companyDeductionId;
	@NotBlank
	private String code;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCompanyDeductionId() {
		return companyDeductionId;
	}

	public void setCompanyDeductionId(Long companyDeductionId) {
		this.companyDeductionId = companyDeductionId;
	}

	@Override
	public String toString() {
		return "CompanyWorkersCompensationForm [id=" + id + ", companyId=" + companyId
				+ ", stateId=" + stateId + ", companyDeductionId=" + companyDeductionId + ", code="
				+ code + ", description=" + description + "]";
	}
}
