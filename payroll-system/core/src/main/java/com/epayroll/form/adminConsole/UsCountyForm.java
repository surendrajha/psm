package com.epayroll.form.adminConsole;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class UsCountyForm {

	private Long id;
	@NotNull
	private Long usStateId;
	@NotBlank
	private String countyName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsStateId() {
		return usStateId;
	}

	public void setUsStateId(Long usStateId) {
		this.usStateId = usStateId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsCountyForm [id=");
		builder.append(id);
		builder.append(", usStateId=");
		builder.append(usStateId);
		builder.append(", countyName=");
		builder.append(countyName);
		builder.append("]");
		return builder.toString();
	}

}