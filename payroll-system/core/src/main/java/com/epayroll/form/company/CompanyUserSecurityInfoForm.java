/**
 * 
 */
package com.epayroll.form.company;

/**
 * @author Surendra Jha
 */
public class CompanyUserSecurityInfoForm {

	private Long id;
	private Long companyUserId;
	private String currentPassword;
	// for change user Name
	private String currentUserName;
	private String newUserName;
	// for change password
	private String newPassword;
	// for change pin
	private String currentPin;
	private String newPin;
	// for change email id
	private String currentEmail;
	private String newEmail;
	private String changeSecurityInfoType;

	public String getChangeSecurityInfoType() {
		return changeSecurityInfoType;
	}

	public void setChangeSecurityInfoType(String changeSecurityInfoType) {
		this.changeSecurityInfoType = changeSecurityInfoType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyUserSecurityInfoForm [id=").append(id).append(", companyUserId=")
				.append(companyUserId).append(", currentPassword=").append(currentPassword)
				.append(", currentUserName=").append(currentUserName).append(", newUserName=")
				.append(newUserName).append(", newPassword=").append(newPassword)
				.append(", currentPin=").append(currentPin).append(", newPin=").append(newPin)
				.append(", currentEmail=").append(currentEmail).append(", newEmail=")
				.append(newEmail).append(", changeSecurityInfoType=")
				.append(changeSecurityInfoType).append("]");
		return builder.toString();
	}

}
