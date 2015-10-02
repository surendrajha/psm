package com.epayroll.form.company;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class BusinessInfoForm {

	private Long id;
	private Long companyId;
	@Length(min = 3)
	private String businessName;
	@Pattern(regexp = "\\(?[2-9]\\d\\d\\)?[-?]?[2-9]\\d\\d-?\\d{4}")
	private String businessPhone;
	@Pattern(regexp = "\\(?[2-9]\\d\\d\\)?[-?]?[2-9]\\d\\d-?\\d{4}")
	private String businessFax;
	@Pattern(regexp = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:]))")
	private String webAddress;
	private String businessStreetAddress;
	private Long businessCityId;
	private Long businessStateId;
	private Long businessCountyId;
	@Pattern(regexp = "^\\d{5}(-\\d{4})?$")
	private String businessPinZip;

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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBusinessFax() {
		return businessFax;
	}

	public void setBusinessFax(String businessFax) {
		this.businessFax = businessFax;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getBusinessStreetAddress() {
		return businessStreetAddress;
	}

	public void setBusinessStreetAddress(String businessStreetAddress) {
		this.businessStreetAddress = businessStreetAddress;
	}

	public Long getBusinessCityId() {
		return businessCityId;
	}

	public void setBusinessCityId(Long businessCityId) {
		this.businessCityId = businessCityId;
	}

	public Long getBusinessStateId() {
		return businessStateId;
	}

	public void setBusinessStateId(Long businessStateId) {
		this.businessStateId = businessStateId;
	}

	public Long getBusinessCountyId() {
		return businessCountyId;
	}

	public void setBusinessCountyId(Long businessCountyId) {
		this.businessCountyId = businessCountyId;
	}

	public String getBusinessPinZip() {
		return businessPinZip;
	}

	public void setBusinessPinZip(String businessPinZip) {
		this.businessPinZip = businessPinZip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessInfoForm [id=");
		builder.append(id);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", businessName=");
		builder.append(businessName);
		builder.append(", businessPhone=");
		builder.append(businessPhone);
		builder.append(", businessFax=");
		builder.append(businessFax);
		builder.append(", webAddress=");
		builder.append(webAddress);
		builder.append(", businessStreetAddress=");
		builder.append(businessStreetAddress);
		builder.append(", businessCityId=");
		builder.append(businessCityId);
		builder.append(", businessStateId=");
		builder.append(businessStateId);
		builder.append(", businessCountyId=");
		builder.append(businessCountyId);
		builder.append(", businessPinZip=");
		builder.append(businessPinZip);
		builder.append("]");
		return builder.toString();
	}

}
