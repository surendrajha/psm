package com.epayroll.exception;

public class GenericException extends Exception {

	private String errorCode;
	private String errorMessage;

	public GenericException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
