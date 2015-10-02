package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;

/**
 * @author Rajul Tiwari
 */
public class IncomeTaxSlabForm {

	private Long id;
	private Long taxTypeId;
	private Long federalStateTaxRateId;
	private Long filingStatusId;
	private Date wef;
	@Digits(fraction = 2, integer = 4)
	private BigDecimal minIncome;
	@Digits(fraction = 2, integer = 4)
	private BigDecimal maxIncome;
	@Digits(fraction = 2, integer = 4)
	private BigDecimal taxRate;
	@Digits(fraction = 2, integer = 4)
	private BigDecimal taxAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(Long taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public Long getFederalStateTaxRateId() {
		return federalStateTaxRateId;
	}

	public void setFederalStateTaxRateId(Long federalStateTaxRateId) {
		this.federalStateTaxRateId = federalStateTaxRateId;
	}

	public Long getFilingStatusId() {
		return filingStatusId;
	}

	public void setFilingStatusId(Long filingStatusId) {
		this.filingStatusId = filingStatusId;
	}

	public Date getWef() {
		return wef;
	}

	public void setWef(Date wef) {
		this.wef = wef;
	}

	public BigDecimal getMinIncome() {
		return minIncome;
	}

	public void setMinIncome(BigDecimal minIncome) {
		this.minIncome = minIncome;
	}

	public BigDecimal getMaxIncome() {
		return maxIncome;
	}

	public void setMaxIncome(BigDecimal maxIncome) {
		this.maxIncome = maxIncome;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FederalIncomeTaxSlabForm [id=");
		builder.append(id);
		builder.append(", taxTypeId=");
		builder.append(taxTypeId);
		builder.append(", federalStateTaxRateId=");
		builder.append(federalStateTaxRateId);
		builder.append(", filingStatusId=");
		builder.append(filingStatusId);
		builder.append(", wef=");
		builder.append(wef);
		builder.append(", minIncome=");
		builder.append(minIncome);
		builder.append(", maxIncome=");
		builder.append(maxIncome);
		builder.append(", taxRate=");
		builder.append(taxRate);
		builder.append(", taxAmount=");
		builder.append(taxAmount);
		builder.append("]");
		return builder.toString();
	}

}