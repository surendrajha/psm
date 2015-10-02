package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotBlank;

public class CompanyEarningForm {

	private Long id;
	@NotBlank(message = "Display Name can not be Empty")
	private String displayName;
	private String description;
	private Long earningCategoryId;
	private Long earningId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEarningCategoryId() {
		return earningCategoryId;
	}

	public void setEarningCategoryId(Long earningCategoryId) {
		this.earningCategoryId = earningCategoryId;
	}

	public Long getEarningId() {
		return earningId;
	}

	public void setEarningId(Long earningId) {
		this.earningId = earningId;
	}

}
