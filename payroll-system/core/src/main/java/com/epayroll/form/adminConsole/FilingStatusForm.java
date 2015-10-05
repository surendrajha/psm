package com.epayroll.form.adminConsole;

import org.hibernate.validator.constraints.NotBlank;

public class FilingStatusForm {

	private Long id;
	@NotBlank
	private String filingStatus;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
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
		builder.append("FilingStatusForm [id=");
		builder.append(id);
		builder.append(", filingStatus=");
		builder.append(filingStatus);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
