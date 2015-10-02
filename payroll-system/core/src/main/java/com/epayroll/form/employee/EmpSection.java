package com.epayroll.form.employee;

public class EmpSection {

	private Long sectionId;

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeSection [sectionId=");
		builder.append(sectionId);
		builder.append("]");
		return builder.toString();
	}

}
