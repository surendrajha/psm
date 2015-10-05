package com.epayroll.form.adminConsole;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class EarningCategoryForm {

	private Long id;
	@NotBlank
	private String category;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		builder.append("EarningCategoryForm [id=");
		builder.append(id);
		builder.append(", category=");
		builder.append(category);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}