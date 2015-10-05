/**
 * 
 */
package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Surendra Jha
 */
public class ChangeUserIDForm {

	private Long id;
	private Long userId;
	@NotBlank
	private String currentUserName;
	@NotBlank
	private String currentPassword;
	@NotBlank
	private String newUserName;
	@NotBlank
	private String confirmNewUserName;

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

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getConfirmNewUserName() {
		return confirmNewUserName;
	}

	public void setConfirmNewUserName(String confirmNewUserName) {
		this.confirmNewUserName = confirmNewUserName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeUserIDForm [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", currentUserName=");
		builder.append(currentUserName);
		builder.append(", currentPassword=");
		builder.append(currentPassword);
		builder.append(", newUserName=");
		builder.append(newUserName);
		builder.append(", confirmNewUserName=");
		builder.append(confirmNewUserName);
		builder.append("]");
		return builder.toString();
	}

}
