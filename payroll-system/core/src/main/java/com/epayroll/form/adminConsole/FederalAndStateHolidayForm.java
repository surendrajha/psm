package com.epayroll.form.adminConsole;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Uma
 */
public class FederalAndStateHolidayForm {

	private Long id;
	@NotNull
	private Long usStateId;
	@DateTimeFormat
	private Date holidayDate;
	private String description;

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

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
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
		builder.append("FederalAndStateHolidayForm [id=");
		builder.append(id);
		builder.append(", usStateId=");
		builder.append(usStateId);
		builder.append(", holidayDate=");
		builder.append(holidayDate);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}