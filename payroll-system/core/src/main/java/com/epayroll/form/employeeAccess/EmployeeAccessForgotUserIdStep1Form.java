package com.epayroll.form.employeeAccess;

/**
 * @author Uma
 */
public class EmployeeAccessForgotUserIdStep1Form {

	private Long id;
	private String emailId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ForgotUserIdStep1Form [id=");
		builder.append(id);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append("]");
		return builder.toString();
	}

}