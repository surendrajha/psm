package com.epayroll.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

@ScriptAssert(lang = "javascript", script = "_this.newPassword.equals(_this.confirmPassword)", message = "{New Password and Confirm Password not matched}")
public class ResetPasswordForm {
	@NotBlank(message = "New Password  can not be Empty")
	private String newPassword;
	@NotBlank(message = "Confirm Password can not be Empty")
	private String confirmPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
