package com.epayroll.form.adminConsole;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class UsStateForm {

	private Long id;
	@NotBlank
	private String stateName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsStateForm [id=");
		builder.append(id);
		builder.append(", stateName=");
		builder.append(stateName);
		builder.append("]");
		return builder.toString();
	}

}