package com.epayroll.form.employee;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Rajul Tiwari
 */
public class EmployeePersonalInfoForm {
	private Long id;
	@NotBlank(message = "First Name can not be Empty")
	private String firstName;
	@NotBlank(message = "Last Name can not be Empty")
	private String lastName;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Past
	private Date dob;
	@NotBlank(message = "SSN can not be Empty")
	private String ssn;
	@NotBlank(message = "Phone Number can not be Empty")
	// @Pattern(regexp = "\\(?[2-9]\\d\\d\\)?[-?]?[2-9]\\d\\d-?\\d{4}")
	private String phone;
	private String gender;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String streetAddress;
	private Long cityId;
	private Long stateId;
	@NotNull(message = "Zip can not be Empty")
	// @Pattern(regexp = "^\\d{5}(-\\d{4})?$")
	private String zip;
	private Long countyId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeePersonalInfoForm [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", ssn=");
		builder.append(ssn);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", email=");
		builder.append(email);
		builder.append(", streetAddress=");
		builder.append(streetAddress);
		builder.append(", cityId=");
		builder.append(cityId);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", zip=");
		builder.append(zip);
		builder.append(", countyId=");
		builder.append(countyId);
		builder.append("]");
		return builder.toString();
	}
}
