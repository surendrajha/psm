package com.epayroll.form.company;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class BillingContactForm {

	private Long id;
	private Long companyId;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Pattern(regexp = "\\(?[2-9]\\d\\d\\)?[-?]?[2-9]\\d\\d-?\\d{4}")
	private String phone;
	@Pattern(regexp = "\\d+")
	private String ext;
	@Pattern(regexp = "\\(?[2-9]\\d\\d\\)?[-?]?[2-9]\\d\\d-?\\d{4}")
	private String fax;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return "BillingContactForm [id=" + id + ", companyId=" + companyId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", ext=" + ext + ", fax=" + fax + "]";
	}

}
