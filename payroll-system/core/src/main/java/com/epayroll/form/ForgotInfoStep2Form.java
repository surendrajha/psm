package com.epayroll.form;

import org.hibernate.validator.constraints.NotBlank;

public class ForgotInfoStep2Form {
	private Long id;
	private String securityQuestion1;
	private String securityQuestion2;
	private Long securityQuestionId1;
	private Long securityQuestionId2;
	@NotBlank(message = "Answer 1 can not be Empty")
	private String securityAnswer1;
	@NotBlank(message = "Answer 2 can not be Empty")
	private String securityAnswer2;

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

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public Long getSecurityQuestionId1() {
		return securityQuestionId1;
	}

	public void setSecurityQuestionId1(Long securityQuestionId1) {
		this.securityQuestionId1 = securityQuestionId1;
	}

	public Long getSecurityQuestionId2() {
		return securityQuestionId2;
	}

	public void setSecurityQuestionId2(Long securityQuestionId2) {
		this.securityQuestionId2 = securityQuestionId2;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}

	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	public String getSecurityAnswer2() {
		return securityAnswer2;
	}

	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ForgotInfoStep2Form [id=");
		builder.append(id);
		builder.append(", securityQuestion1=");
		builder.append(securityQuestion1);
		builder.append(", securityQuestion2=");
		builder.append(securityQuestion2);
		builder.append(", securityQuestionId1=");
		builder.append(securityQuestionId1);
		builder.append(", securityQuestionId2=");
		builder.append(securityQuestionId2);
		builder.append(", securityAnswer1=");
		builder.append(securityAnswer1);
		builder.append(", securityAnswer2=");
		builder.append(securityAnswer2);
		builder.append("]");
		return builder.toString();
	}

}
