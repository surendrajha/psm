package com.epayroll.exception;

public class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6766666022933418563L;
	private String message;

	public CommonException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
