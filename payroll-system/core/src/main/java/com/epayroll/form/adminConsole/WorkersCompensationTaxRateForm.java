package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class WorkersCompensationTaxRateForm {

	private Long id;
	@NotNull
	private BigDecimal rate;
	@NotNull
	private Long companyId;
	@NotNull
	private Long WorkComCodeId;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date wef;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getWorkComCodeId() {
		return WorkComCodeId;
	}

	public void setWorkComCodeId(Long workComCodeId) {
		WorkComCodeId = workComCodeId;
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
		builder.append("WorkersCompensationTaxRateForm [id=");
		builder.append(id);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", WorkComCodeId=");
		builder.append(WorkComCodeId);
		builder.append(", wef=");
		builder.append(wef);
		builder.append("]");
		return builder.toString();
	}

}
