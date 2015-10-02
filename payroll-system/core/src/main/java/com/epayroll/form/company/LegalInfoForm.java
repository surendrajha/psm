package com.epayroll.form.company;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class LegalInfoForm {

	private Long id;
	private Long companyId;
	@Length(min = 3)
	private String legalName;
	private String legalStreetAddress;
	@NotNull
	private Long legalCityId;
	@NotNull
	private Long legalStateId;
	@NotNull
	private Long legalCountyId;
	@Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Enter Valid ZipCode (e.g. 85705-7598 or 85705)")
	private String legalPinZip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalStreetAddress() {
		return legalStreetAddress;
	}

	public void setLegalStreetAddress(String legalStreetAddress) {
		this.legalStreetAddress = legalStreetAddress;
	}

	public Long getLegalCityId() {
		return legalCityId;
	}

	public void setLegalCityId(Long legalCityId) {
		this.legalCityId = legalCityId;
	}

	public Long getLegalStateId() {
		return legalStateId;
	}

	public void setLegalStateId(Long legalStateId) {
		this.legalStateId = legalStateId;
	}

	public Long getLegalCountyId() {
		return legalCountyId;
	}

	public void setLegalCountyId(Long legalCountyId) {
		this.legalCountyId = legalCountyId;
	}

	public String getLegalPinZip() {
		return legalPinZip;
	}

	public void setLegalPinZip(String legalPinZip) {
		this.legalPinZip = legalPinZip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LegalInfoForm [id=");
		builder.append(id);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", legalName=");
		builder.append(legalName);
		builder.append(", legalStreetAddress=");
		builder.append(legalStreetAddress);
		builder.append(", legalCityId=");
		builder.append(legalCityId);
		builder.append(", legalStateId=");
		builder.append(legalStateId);
		builder.append(", legalCountyId=");
		builder.append(legalCountyId);
		builder.append(", legalPinZip=");
		builder.append(legalPinZip);
		builder.append("]");
		return builder.toString();
	}

}
