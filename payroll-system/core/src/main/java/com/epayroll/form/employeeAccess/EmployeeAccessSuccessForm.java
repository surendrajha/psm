package com.epayroll.form.employeeAccess;

/**
 * @author Uma
 */
public class EmployeeAccessSuccessForm {

	private Long id;
	private String emailId;
	private String userName;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeAccessSuccessForm [id=");
		builder.append(id);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append("]");
		return builder.toString();
	}

}