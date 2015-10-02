package com.epayroll.model;

import java.util.List;

public class ServerResponse {
	private String status;
	private String message;
	private List<ErrorMessage> errorMessages;
	private Object data;

	public ServerResponse() {
	}

	public ServerResponse(String status, String message, List<ErrorMessage> errorMessages,
			Object data) {
		this.status = status;
		this.message = message;
		this.errorMessages = errorMessages;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<ErrorMessage> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerResponse [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", errorMessages=");
		builder.append(errorMessages);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
