package com.epayroll.form.adminConsole;

/**
 * @author Uma
 */
public class FundCategoryForm {

	private Long id;
	private String categoryName;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
		builder.append("FundCategoryForm [id=");
		builder.append(id);
		builder.append(", categoryName=");
		builder.append(categoryName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}