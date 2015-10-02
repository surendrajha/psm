package com.epayroll.form.employeeAccess;

import org.hibernate.validator.constraints.ScriptAssert;

/**
 * @author Uma
 */
@ScriptAssert(lang = "javascript", script = "_this.password.equals(_this.reEnteredPassword)")
public class EmployeeAccessRegistrationForm {

	private Long id;
	private Long employeeId;
	private String userId;
	private String password;
	private String reEnteredPassword;
	private Long securityQuestionId;
	private String answer;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnteredPassword() {
		return reEnteredPassword;
	}

	public void setReEnteredPassword(String reEnteredPassword) {
		this.reEnteredPassword = reEnteredPassword;
	}

	public Long getSecurityQuestionId() {
		return securityQuestionId;
	}

	public void setSecurityQuestionId(Long securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeAccessRegistrationForm [id=");
		builder.append(id);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", password=");
		builder.append(password);
		builder.append(", reEnteredPassword=");
		builder.append(reEnteredPassword);
		builder.append(", securityQuestionId=");
		builder.append(securityQuestionId);
		builder.append(", answer=");
		builder.append(answer);
		builder.append("]");
		return builder.toString();
	}

}