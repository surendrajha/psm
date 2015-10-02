package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Uma
 */
public class AllowanceRateForm {

	private Long id;
	private Long stateId;
	private Long allowanceTypeId;
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

	public Long getAllowanceTypeId() {
		return allowanceTypeId;
	}

	public void setAllowanceTypeId(Long allowanceTypeId) {
		this.allowanceTypeId = allowanceTypeId;
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
		builder.append("AllowanceRateForm [id=");
		builder.append(id);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", allowanceTypeId=");
		builder.append(allowanceTypeId);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", wef=");
		builder.append(wef);
		builder.append("]");
		return builder.toString();
	}

}