package com.epayroll.form.adminConsole;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class PayCycleForm {

	private Long id;
	@NotBlank
	private String type;
	@Digits(fraction = 0, integer = 0)
	private Integer noOfDays;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayCycleForm [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", noOfDays=");
		builder.append(noOfDays);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}