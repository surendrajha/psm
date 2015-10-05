package com.epayroll.form.adminConsole;

public class YearForm {
	private Long id;
	private Integer year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YearForm [id=");
		builder.append(id);
		builder.append(", year=");
		builder.append(year);
		builder.append("]");
		return builder.toString();
	}

}
