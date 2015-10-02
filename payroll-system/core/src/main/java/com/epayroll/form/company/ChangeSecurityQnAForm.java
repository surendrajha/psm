package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotBlank;

public class ChangeSecurityQnAForm {

	private Long userId;
	private String currentPassword;

	private String securityQues1;
	private Long securityQues2;
	private Long securityQues3;
	@NotBlank
	private String answer1;
	@NotBlank
	private String answer2;
	@NotBlank
	private String answer3;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSecurityQues1() {
		return securityQues1;
	}

	public void setSecurityQues1(String securityQues1) {
		this.securityQues1 = securityQues1;
	}

	public Long getSecurityQues2() {
		return securityQues2;
	}

	public void setSecurityQues2(Long securityQues2) {
		this.securityQues2 = securityQues2;
	}

	public Long getSecurityQues3() {
		return securityQues3;
	}

	public void setSecurityQues3(Long securityQues3) {
		this.securityQues3 = securityQues3;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeSecurityQnAForm [userId=");
		builder.append(userId);
		builder.append(", currentPassword=");
		builder.append(currentPassword);
		builder.append(", securityQues1=");
		builder.append(securityQues1);
		builder.append(", securityQues2=");
		builder.append(securityQues2);
		builder.append(", securityQues3=");
		builder.append(securityQues3);
		builder.append(", answer1=");
		builder.append(answer1);
		builder.append(", answer2=");
		builder.append(answer2);
		builder.append(", answer3=");
		builder.append(answer3);
		builder.append("]");
		return builder.toString();
	}

}
