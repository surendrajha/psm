package com.epayroll.form.adminConsole;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class DeductionForm {

	private Long id;
	@NotNull
	private Long deductionCategoryId;
	@NotBlank
	private String deduction;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeductionCategoryId() {
		return deductionCategoryId;
	}

	public void setDeductionCategoryId(Long deductionCategoryId) {
		this.deductionCategoryId = deductionCategoryId;
	}

	public String getDeduction() {
		return deduction;
	}

	public void setDeduction(String deduction) {
		this.deduction = deduction;
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
		builder.append("DeductionForm [id=");
		builder.append(id);
		builder.append(", deductionCategoryId=");
		builder.append(deductionCategoryId);
		builder.append(", deduction=");
		builder.append(deduction);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}