/**
 * 
 */
package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Surendra Jha
 */
public class ChangeUserPinForm {

	private Long id;
	private Long userId;
	@NotBlank
	private String currentPassword;
	@NotBlank
	private String currentPin;
	@NotBlank
	private String newPin;
	@NotBlank
	private String confirmNewPin;

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

	public String getCurrentPin() {
		return currentPin;
	}

	public void setCurrentPin(String currentPin) {
		this.currentPin = currentPin;
	}

	public String getNewPin() {
		return newPin;
	}

	public void setNewPin(String newPin) {
		this.newPin = newPin;
	}

	public String getConfirmNewPin() {
		return confirmNewPin;
	}

	public void setConfirmNewPin(String confirmNewPin) {
		this.confirmNewPin = confirmNewPin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeUserPinForm [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", currentPassword=");
		builder.append(currentPassword);
		builder.append(", currentPin=");
		builder.append(currentPin);
		builder.append(", newPin=");
		builder.append(newPin);
		builder.append(", confirmNewPin=");
		builder.append(confirmNewPin);
		builder.append("]");
		return builder.toString();
	}

}
