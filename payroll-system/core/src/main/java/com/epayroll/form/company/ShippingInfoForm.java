package com.epayroll.form.company;

import javax.validation.constraints.Pattern;

public class ShippingInfoForm {

	private Long id;
	private Long companyId;
	private String shippingStreetAddress;
	private Long shippingCityId;
	private Long shippingStateId;
	private Long shippingCountyId;
	@Pattern(regexp = "^\\d{5}(-\\d{4})?$")
	private String shippingPinZip;

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

	public String getShippingStreetAddress() {
		return shippingStreetAddress;
	}

	public void setShippingStreetAddress(String shippingStreetAddress) {
		this.shippingStreetAddress = shippingStreetAddress;
	}

	public Long getShippingCityId() {
		return shippingCityId;
	}

	public void setShippingCityId(Long shippingCityId) {
		this.shippingCityId = shippingCityId;
	}

	public Long getShippingStateId() {
		return shippingStateId;
	}

	public void setShippingStateId(Long shippingStateId) {
		this.shippingStateId = shippingStateId;
	}

	public Long getShippingCountyId() {
		return shippingCountyId;
	}

	public void setShippingCountyId(Long shippingCountyId) {
		this.shippingCountyId = shippingCountyId;
	}

	public String getShippingPinZip() {
		return shippingPinZip;
	}

	public void setShippingPinZip(String shippingPinZip) {
		this.shippingPinZip = shippingPinZip;
	}

}
