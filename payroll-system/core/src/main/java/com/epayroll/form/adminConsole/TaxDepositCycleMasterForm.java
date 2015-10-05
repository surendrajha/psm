package com.epayroll.form.adminConsole;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TaxDepositCycleMasterForm {

	private Long id;
	@NotBlank
	private String depositCycle;
	private String description;
	@NotNull
	@Digits(fraction = 0, integer = 3)
	private Integer noOfDays;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepositCycle() {
		return depositCycle;
	}

	public void setDepositCycle(String depositCycle) {
		this.depositCycle = depositCycle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaxDepositCycleMasterForm [depositCycle=");
		builder.append(depositCycle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", noOfDays=");
		builder.append(noOfDays);
		builder.append("]");
		return builder.toString();
	}

}
