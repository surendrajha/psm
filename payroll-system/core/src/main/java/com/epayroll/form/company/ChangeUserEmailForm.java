/**
 * 
 */
package com.epayroll.form.company;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Surendra Jha
 */
public class ChangeUserEmailForm {

	private Long id;
	private Long userId;
	@NotBlank
	private String currentPassword;
	@NotBlank
	@Email
	private String currentEmail;
	@NotBlank
	@Email
	private String newEmail;
	@NotBlank
	@Email
	private String confirmNewEmail;

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

	public String getCurrentEmail() {
		return currentEmail;
	}

	public void setCurrentEmail(String currentEmail) {
		this.currentEmail = currentEmail;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getConfirmNewEmail() {
		return confirmNewEmail;
	}

	public void setConfirmNewEmail(String confirmNewEmail) {
		this.confirmNewEmail = confirmNewEmail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeUserEmailForm [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", currentPassword=");
		builder.append(currentPassword);
		builder.append(", currentEmail=");
		builder.append(currentEmail);
		builder.append(", newEmail=");
		builder.append(newEmail);
		builder.append(", confirmNewEmail=");
		builder.append(confirmNewEmail);
		builder.append("]");
		return builder.toString();
	}

}
