package com.epayroll.form.adminConsole;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class UsCityForm {

	private Long id;
	@NotNull
	private Long usStateId;

	@NotBlank
	private String cityName;

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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsCityForm [id=");
		builder.append(id);
		builder.append(", usStateId=");
		builder.append(usStateId);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append("]");
		return builder.toString();
	}

}