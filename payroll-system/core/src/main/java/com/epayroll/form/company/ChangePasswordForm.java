package com.epayroll.form.company;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordForm {

	@NotEmpty
	private String currentPassword;
	@NotEmpty
	private String newPassword;
	@NotEmpty
	private String confirmNewPassword;
	private Long companyUserId;
	private String userName;
	private Boolean isFirstTime = false;

	public Long getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(Long companyUserId) {
		this.companyUserId = companyUserId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getIsFirstTime() {
		return isFirstTime;
	}

	public void setIsFirstTime(Boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}

}
