package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Uma
 */
public class StandardDeductionRateForm {

	private Long id;
	private Long stateId;
	private Long filingStatusId;
	private BigDecimal minValue;
	private BigDecimal maxValue;
	private BigDecimal rate;
	private Date wef;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getFilingStatusId() {
		return filingStatusId;
	}

	public void setFilingStatusId(Long filingStatusId) {
		this.filingStatusId = filingStatusId;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getWef() {
		return wef;
	}

	public void setWef(Date wef) {
		this.wef = wef;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StandardDeductionRateForm [id=");
		builder.append(id);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", filingStatusId=");
		builder.append(filingStatusId);
		builder.append(", minValue=");
		builder.append(minValue);
		builder.append(", maxValue=");
		builder.append(maxValue);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", wef=");
		builder.append(wef);
		builder.append("]");
		return builder.toString();
	}

}