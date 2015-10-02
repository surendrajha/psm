/**
 * 
 */
package com.epayroll.form.employee;

import java.util.List;

import com.epayroll.model.employee.EmployeeBankInfo;

/**
 * @author Surendra Jha
 */
public class EmployeeBankInfoForm {

	private Long id; // for delete record
	private Long employeeId;
	// @Valid
	private List<EmployeeBankInfo> employeeBankInfos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public List<EmployeeBankInfo> getEmployeeBankInfos() {
		return employeeBankInfos;
	}

	public void setEmployeeBankInfos(List<EmployeeBankInfo> employeeBankInfos) {
		this.employeeBankInfos = employeeBankInfos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeBankInfoForm [employeeId=");
		builder.append(employeeId);
		builder.append(", employeeBankInfos=");
		builder.append(employeeBankInfos);
		builder.append("]");
		return builder.toString();
	}

}
