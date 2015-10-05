package com.epayroll.form.adminConsole;

/**
 * @author Uma
 */
public class PaymentModeForm {

	private long id;
	private String mode;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentModeForm [id=");
		builder.append(id);
		builder.append(", mode=");
		builder.append(mode);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}