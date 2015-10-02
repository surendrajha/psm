package com.epayroll.form.adminConsole;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class PayrollPlanForm {

	private Long id;
	@NotBlank
	private String planName;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
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
		builder.append("PayrollPlanForm [id=");
		builder.append(id);
		builder.append(", planName=");
		builder.append(planName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}