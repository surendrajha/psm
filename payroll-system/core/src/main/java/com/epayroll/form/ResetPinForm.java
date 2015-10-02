package com.epayroll.form;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

@ScriptAssert(lang = "javascript", script = "_this.newPin.equals(_this.confirmPin)", message = "{New Pin and Confirm Pin not matched}")
public class ResetPinForm {
	@NotBlank(message = "New Pin  can not be Empty")
	@Digits(fraction = 0, integer = 3)
	private String newPin;
	@NotBlank(message = "Confirm Pin can not be Empty")
	@Digits(fraction = 0, integer = 3)
	private String confirmPin;

	public String getNewPin() {
		return newPin;
	}

	public void setNewPin(String newPin) {
		this.newPin = newPin;
	}

	public String getConfirmPin() {
		return confirmPin;
	}

	public void setConfirmPin(String confirmPin) {
		this.confirmPin = confirmPin;
	}

}
