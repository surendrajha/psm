package com.epayroll.form.adminConsole;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.epayroll.entity.CountyTaxRate.ResidentStatus;

/**
 * @author Rajul Tiwari
 */
public class CountyTaxForm {

	private Long id;
	private Long countyId;
	private ResidentStatus residentStatus;
	@NotNull
	@Digits(fraction = 2, integer = 4)
	private BigDecimal taxRate;
	@NotNull
	@Digits(fraction = 2, integer = 4)
	private BigDecimal ceiling;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public ResidentStatus getResidentStatus() {
		return residentStatus;
	}

	public void setResidentStatus(ResidentStatus residentStatus) {
		this.residentStatus = residentStatus;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getCeiling() {
		return ceiling;
	}

	public void setCeiling(BigDecimal ceiling) {
		this.ceiling = ceiling;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountyTaxForm [id=");
		builder.append(id);
		builder.append(", countyId=");
		builder.append(countyId);
		builder.append(", residentStatus=");
		builder.append(residentStatus);
		builder.append(", taxRate=");
		builder.append(taxRate);
		builder.append(", ceiling=");
		builder.append(ceiling);
		builder.append("]");
		return builder.toString();
	}

}