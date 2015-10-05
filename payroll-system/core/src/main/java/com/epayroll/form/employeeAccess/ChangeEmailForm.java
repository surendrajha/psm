package com.epayroll.form.employeeAccess;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * @author Rajul Tiwari
 */
@ScriptAssert(lang = "javascript", script = "_this.newEmail.equals(_this.confirmNewEmail)", message = "{New Email and Confirm Email not matched}")
public class ChangeEmailForm {

	private Long id;
	@NotBlank
	private String password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		builder.append("ChangePasswordForm [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
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