package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Rajul Tiwari
 */
public class FutaForm {

	private Long taxTypeId;
	private Long federalStateTaxRateId;
	private Date wef;
	private BigDecimal ceiling;
	private BigDecimal maximumFutaCredit;
	private BigDecimal companyRate;

	public FutaForm() {
	}

	public FutaForm(Long taxTypeId, Long federalStateTaxRateId, Date wef, BigDecimal ceiling,
			BigDecimal maximumFutaCredit, BigDecimal companyRate) {
		this.taxTypeId = taxTypeId;
		this.federalStateTaxRateId = federalStateTaxRateId;
		this.wef = wef;
		this.ceiling = ceiling;
		this.maximumFutaCredit = maximumFutaCredit;
		this.setCompanyRate(companyRate);
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

	public Date getWef() {
		return wef;
	}

	public void setWef(Date wef) {
		this.wef = wef;
	}

	public BigDecimal getCeiling() {
		return ceiling;
	}

	public void setCeiling(BigDecimal ceiling) {
		this.ceiling = ceiling;
	}

	public BigDecimal getMaximumFutaCredit() {
		return maximumFutaCredit;
	}

	public void setMaximumFutaCredit(BigDecimal maximumFutaCredit) {
		this.maximumFutaCredit = maximumFutaCredit;
	}

	public BigDecimal getCompanyRate() {
		return companyRate;
	}

	public void setCompanyRate(BigDecimal companyRate) {
		this.companyRate = companyRate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FutaForm [taxTypeId=");
		builder.append(taxTypeId);
		builder.append(", federalStateTaxRateId=");
		builder.append(federalStateTaxRateId);
		builder.append(", wef=");
		builder.append(wef);
		builder.append(", ceiling=");
		builder.append(ceiling);
		builder.append(", maximumFutaCredit=");
		builder.append(maximumFutaCredit);
		builder.append(", companyRate=");
		builder.append(companyRate);
		builder.append("]");
		return builder.toString();
	}

}