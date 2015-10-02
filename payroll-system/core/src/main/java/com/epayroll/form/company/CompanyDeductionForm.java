package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotBlank;

public class CompanyDeductionForm {

	private Long id;
	@NotBlank(message = "Display Name can not be Empty")
	private String displayName;
	private String description;
	private Long deductionCategoryId;
	private Long deductionId;
	private String deductionName;
	private String deductionCategoryName;

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

	public Long getDeductionCategoryId() {
		return deductionCategoryId;
	}

	public void setDeductionCategoryId(Long deductionCategoryId) {
		this.deductionCategoryId = deductionCategoryId;
	}

	public Long getDeductionId() {
		return deductionId;
	}

	public void setDeductionId(Long deductionId) {
		this.deductionId = deductionId;
	}

	public String getDeductionName() {
		return deductionName;
	}

	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}

	public String getDeductionCategoryName() {
		return deductionCategoryName;
	}

	public void setDeductionCategoryName(String deductionCategoryName) {
		this.deductionCategoryName = deductionCategoryName;
	}

}
