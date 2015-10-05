package com.epayroll.form.employeeAccess;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Rajul Tiwari
 */
public class ChangeSecurityQnAForm {

	private Long id;
	@NotBlank
	private String password;
	@NotBlank
	private String securityQuestion;
	@NotBlank
	private String securityAnswer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeSecurityQnAForm [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append(", securityQuestion=");
		builder.append(securityQuestion);
		builder.append(", securityAnswer=");
		builder.append(securityAnswer);
		builder.append("]");
		return builder.toString();
	}

}