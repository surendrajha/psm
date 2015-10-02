package com.epayroll.form.employeeAccess;

/**
 * @author Uma
 */
public class EmployeeAccessForgotPasswordStep1Form {

	private Long id;
	private String userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeAccessForgotPasswordStep1Form [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}