package com.epayroll.model;

import java.util.Arrays;

public class MailMessage {

	private String[] mailTo;
	private String subject;
	private String content;
	private String userName;
	private String userId;
	private String password;
	private String link;

	public String[] getMailTo() {
		return mailTo;
	}

	public void setMailTo(String[] mailTo) {
		this.mailTo = mailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MailMessage [mailTo=");
		builder.append(Arrays.toString(mailTo));
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", content=");
		builder.append(content);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", password=");
		builder.append(password);
		builder.append(", link=");
		builder.append(link);
		builder.append("]");
		return builder.toString();
	}

}
