package com.epayroll.form.employee;

import java.util.List;

import com.epayroll.model.employee.EmpSection;

/**
 * @author Uma
 */
public class EmployeeSectionForm {

	private List<EmpSection> empSections;

	public List<EmpSection> getEmpSections() {
		return empSections;
	}

	public void setEmpSections(List<EmpSection> empSections) {
		this.empSections = empSections;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeSectionForm [empSections=");
		builder.append(empSections);
		builder.append("]");
		return builder.toString();
	}

}
