/**
 * 
 */
package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Surendra Jha
 */
public class ChangeUserPasswordForm {

	private Long id;
	private Long userId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		builder.append("ChangeUserPasswordForm [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
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
