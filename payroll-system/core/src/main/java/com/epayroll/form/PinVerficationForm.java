package com.epayroll.form;

import org.hibernate.validator.constraints.NotBlank;

public class PinVerficationForm {
	private Long id;
	private String username;
	private String password;
	@NotBlank(message = "Pin can not be Empty")
	private String pin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}
