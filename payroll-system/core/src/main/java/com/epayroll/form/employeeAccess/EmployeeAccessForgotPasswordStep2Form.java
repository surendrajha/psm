package com.epayroll.form.employeeAccess;

import org.hibernate.validator.constraints.NotBlank;

public class EmployeeAccessForgotPasswordStep2Form {
	private Long id;
	private String securityQuestion1;
	@NotBlank(message = "Answer can not be Empty")
	private String securityAnswer1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}

	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeAccessForgotUserIdStep2Form [id=");
		builder.append(id);
		builder.append(", securityQuestion1=");
		builder.append(securityQuestion1);
		builder.append(", securityAnswer1=");
		builder.append(securityAnswer1);
		builder.append("]");
		return builder.toString();
	}
}
