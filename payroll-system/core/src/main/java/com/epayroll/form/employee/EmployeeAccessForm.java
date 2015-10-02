package com.epayroll.form.employee;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Uma
 */
public class EmployeeAccessForm {
	private Long id;
	@NotNull
	private Long companyId;
	@NotNull
	private Long employeeId;
	private boolean allowAccessCheckBox;
	private boolean emailCheckBox;
	@NotBlank
	@Email
	private String emailAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isAllowAccessCheckBox() {
		return allowAccessCheckBox;
	}

	public void setAllowAccessCheckBox(boolean allowAccessCheckBox) {
		this.allowAccessCheckBox = allowAccessCheckBox;
	}

	public boolean isEmailCheckBox() {
		return emailCheckBox;
	}

	public void setEmailCheckBox(boolean emailCheckBox) {
		this.emailCheckBox = emailCheckBox;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "EmployeeAccessForm [id=" + id + ", companyId=" + companyId + ", employeeId="
				+ employeeId + ", allowAccessCheckBox=" + allowAccessCheckBox + ", emailCheckBox="
				+ emailCheckBox + ", emailAddress=" + emailAddress + "]";
	}
}
