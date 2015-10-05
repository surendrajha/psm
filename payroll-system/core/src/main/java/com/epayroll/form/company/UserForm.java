package com.epayroll.form.company;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.epayroll.entity.User.Status;

public class UserForm {

	private Long id;
	private Long companyId;
	@NotEmpty
	private String firstName;
	private String middleName;
	@NotEmpty
	private String lastName;
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private Date dob;
	@NotEmpty
	private String ssn;
	@NotEmpty
	private String phone;
	private String mobile;
	private String ext;
	// @Email
	private String email;
	private String fax;
	private String designation;
	// @NotEmpty
	private String country;
	@NotNull
	private Long state;
	@NotEmpty
	private String city;
	@NotEmpty
	private String streetAddress;
	private String landmark;
	@NotEmpty
	private String pinZip;
	@NotEmpty
	private String county;
	private String userName;
	private String password;
	private String securityPin;
	private Boolean mobileAccess;
	private String firmName;
	private Long role;
	private Status status;
	private String securityQues1;
	private Long securityQues2;
	private Long securityQues3;
	// @NotEmpty
	private String answer1;
	// @NotEmpty
	private String answer2;
	// @NotEmpty
	private String answer3;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPinZip() {
		return pinZip;
	}

	public void setPinZip(String pinZip) {
		this.pinZip = pinZip;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityPin() {
		return securityPin;
	}

	public void setSecurityPin(String securityPin) {
		this.securityPin = securityPin;
	}

	public Boolean getMobileAccess() {
		return mobileAccess;
	}

	public void setMobileAccess(Boolean mobileAccess) {
		this.mobileAccess = mobileAccess;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getSecurityQues1() {
		return securityQues1;
	}

	public void setSecurityQues1(String securityQues1) {
		this.securityQues1 = securityQues1;
	}

	public Long getSecurityQues2() {
		return securityQues2;
	}

	public void setSecurityQues2(Long securityQues2) {
		this.securityQues2 = securityQues2;
	}

	public Long getSecurityQues3() {
		return securityQues3;
	}

	public void setSecurityQues3(Long securityQues3) {
		this.securityQues3 = securityQues3;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyUserForm [id=").append(id).append(", companyId=").append(companyId)
				.append(", firstName=").append(firstName).append(", middleName=")
				.append(middleName).append(", lastName=").append(lastName).append(", dob=")
				.append(dob).append(", ssn=").append(ssn).append(", phone=").append(phone)
				.append(", mobile=").append(mobile).append(", ext=").append(ext).append(", email=")
				.append(email).append(", fax=").append(fax).append(", designation=")
				.append(designation).append(", country=").append(country).append(", state=")
				.append(state).append(", city=").append(city).append(", streetAddress=")
				.append(streetAddress).append(", landmark=").append(landmark).append(", pinZip=")
				.append(pinZip).append(", county=").append(county).append(", userName=")
				.append(userName).append(", password=").append(password).append(", securityPin=")
				.append(securityPin).append(", mobileAccess=").append(mobileAccess)
				.append(", firmName=").append(firmName).append(", role=").append(role)
				.append(", status=").append(status).append(", securityQues1=")
				.append(securityQues1).append(", securityQues2=").append(securityQues2)
				.append(", securityQues3=").append(securityQues3).append(", answer1=")
				.append(answer1).append(", answer2=").append(answer2).append(", answer3=")
				.append(answer3).append("]");
		return builder.toString();
	}

}
