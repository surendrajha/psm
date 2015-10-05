package com.epayroll.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class ForgotUserIdStep1Form {
	private Long id;
	@NotBlank(message = "Email can not be Empty")
	@Email(message = "Enter Valid Email Id (e.g. abc@xyz.com) ")
	private String emailId;
	@NotNull(message = "Date of Birth can not be Empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Past
	private Date dob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
