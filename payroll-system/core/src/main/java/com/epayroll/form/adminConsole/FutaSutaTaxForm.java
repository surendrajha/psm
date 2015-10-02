package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Rajul Tiwari
 */
public class FutaSutaTaxForm {

	private Long taxTypeId;
	private Long futaSutaTaxRateId;
	private Long companyId;
	private Date wef;
	private BigDecimal rate;

	public FutaSutaTaxForm() {
	}

	public FutaSutaTaxForm(Long taxTypeId, Long futaSutaTaxRateId, Long companyId, Date wef,
			BigDecimal rate) {
		super();
		this.taxTypeId = taxTypeId;
		this.futaSutaTaxRateId = futaSutaTaxRateId;
		this.companyId = companyId;
		this.wef = wef;
		this.rate = rate;
	}

	public Long getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(Long taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public Long getFutaSutaTaxRateId() {
		return futaSutaTaxRateId;
	}

	public void setFutaSutaTaxRateId(Long futaSutaTaxRateId) {
		this.futaSutaTaxRateId = futaSutaTaxRateId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Date getWef() {
		return wef;
	}

	public void setWef(Date wef) {
		this.wef = wef;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FutaSutaTaxForm [taxTypeId=");
		builder.append(taxTypeId);
		builder.append(", futaSutaTaxRateId=");
		builder.append(futaSutaTaxRateId);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", wef=");
		builder.append(wef);
		builder.append(", rate=");
		builder.append(rate);
		builder.append("]");
		return builder.toString();
	}

	public void clear() {
		this.companyId = null;
		this.rate = null;
		this.futaSutaTaxRateId = null;
	}

}