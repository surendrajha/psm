/**
 * 
 */
package com.epayroll.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Surendra Jha
 */
public class GenerateOTPForm {

	private Long userId;
	@NotEmpty
	private String otp;
	private String changeSecurityInfoType;
	private String email;

	public String getChangeSecurityInfoType() {
		return changeSecurityInfoType;
	}

	public void setChangeSecurityInfoType(String changeSecurityInfoType) {
		this.changeSecurityInfoType = changeSecurityInfoType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long companyUserId) {
		this.userId = companyUserId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenerateOTPForm [userId=");
		builder.append(userId);
		builder.append(", otp=");
		builder.append(otp);
		builder.append(", changeSecurityInfoType=");
		builder.append(changeSecurityInfoType);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
