package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Uma
 */
public class DeductionCapForm {

	private Long id;
	private Long stateId;
	private Long companyDeductionId;
	private BigDecimal capAmount;
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

	public Long getCompanyDeductionId() {
		return companyDeductionId;
	}

	public void setCompanyDeductionId(Long companyDeductionId) {
		this.companyDeductionId = companyDeductionId;
	}

	public BigDecimal getCapAmount() {
		return capAmount;
	}

	public void setCapAmount(BigDecimal capAmount) {
		this.capAmount = capAmount;
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
		builder.append("DeductionCapForm [id=");
		builder.append(id);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", companyDeductionId=");
		builder.append(companyDeductionId);
		builder.append(", capAmount=");
		builder.append(capAmount);
		builder.append(", wef=");
		builder.append(wef);
		builder.append("]");
		return builder.toString();
	}

}