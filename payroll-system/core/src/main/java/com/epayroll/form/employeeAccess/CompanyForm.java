package com.epayroll.form.employeeAccess;

import com.epayroll.entity.Company.Status;

/**
 * @author Rajul Tiwari
 */
public class CompanyForm {
	private Long id;
	private String legalName;
	private String businessPhone;
	private String businessFax;
	private String fein;
	private Status status;
	private String webAddress;
	private Long legalAddressCityId;
	private Long legalAddressStateId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
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

	public String getFein() {
		return fein;
	}

	public void setFein(String fein) {
		this.fein = fein;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public Long getLegalAddressCityId() {
		return legalAddressCityId;
	}

	public void setLegalAddressCityId(Long legalAddressCityId) {
		this.legalAddressCityId = legalAddressCityId;
	}

	public Long getLegalAddressStateId() {
		return legalAddressStateId;
	}

	public void setLegalAddressStateId(Long legalAddressStateId) {
		this.legalAddressStateId = legalAddressStateId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyForm [id=");
		builder.append(id);
		builder.append(", legalName=");
		builder.append(legalName);
		builder.append(", businessPhone=");
		builder.append(businessPhone);
		builder.append(", businessFax=");
		builder.append(businessFax);
		builder.append(", fein=");
		builder.append(fein);
		builder.append(", status=");
		builder.append(status);
		builder.append(", webAddress=");
		builder.append(webAddress);
		builder.append(", legalAddressCityId=");
		builder.append(legalAddressCityId);
		builder.append(", legalAddressStateId=");
		builder.append(legalAddressStateId);
		builder.append("]");
		return builder.toString();
	}

}