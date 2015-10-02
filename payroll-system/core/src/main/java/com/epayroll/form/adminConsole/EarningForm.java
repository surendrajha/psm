package com.epayroll.form.adminConsole;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class EarningForm {

	private Long id;
	@NotNull
	private Long earningCategoryId;
	@NotBlank
	private String earning;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEarningCategoryId() {
		return earningCategoryId;
	}

	public void setEarningCategoryId(Long earningCategoryId) {
		this.earningCategoryId = earningCategoryId;
	}

	public String getEarning() {
		return earning;
	}

	public void setEarning(String earning) {
		this.earning = earning;
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
		builder.append("EarningForm [id=");
		builder.append(id);
		builder.append(", earningCategoryId=");
		builder.append(earningCategoryId);
		builder.append(", earning=");
		builder.append(earning);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}