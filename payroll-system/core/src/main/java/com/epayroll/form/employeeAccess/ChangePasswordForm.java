package com.epayroll.form.employeeAccess;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * @author Rajul Tiwari
 */
@ScriptAssert(lang = "javascript", script = "_this.newPassword.equals(_this.confirmNewPassword)", message = "{New Password and Confirm Password not matched}")
public class ChangePasswordForm {

	private Long id;
	@NotBlank
	private String currentPassword;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String confirmNewPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePasswordForm [id=");
		builder.append(id);
		builder.append(", currentPassword=");
		builder.append(currentPassword);
		builder.append(", newPassword=");
		builder.append(newPassword);
		builder.append(", confirmNewPassword=");
		builder.append(confirmNewPassword);
		builder.append("]");
		return builder.toString();
	}

}