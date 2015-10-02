package com.epayroll.form.employee;

/**
 * @author Uma
 */
public class TemporaryPasswordForm {

	private Long id;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TeporaryPasswordForm [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

}