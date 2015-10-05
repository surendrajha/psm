package com.epayroll.exception;

public class MailNotSentException extends Exception {

	private static final long serialVersionUID = -7946649283987505639L;
	private String message;

	public MailNotSentException() {
		message = null;
	}

	public MailNotSentException(String message) {
		this.message = message;
	}

	@Override
	public String getLocalizedMessage() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
