package com.epayroll.form;

import org.hibernate.validator.constraints.NotBlank;

public class ForgotPasswordAndPinStep1Form {
	private Long id;
	@NotBlank(message = "User Name can not be Empty")
	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
