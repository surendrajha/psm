/**
 * 
 */
package com.epayroll.form.employee;

import java.util.List;

/**
 * @author Surendra Jha
 */
public class MessageCenterForm {

	private List<Long> toPersionId;
	private String subject;
	private String messageText;
	// for reply
	private Long toPerson;

	public List<Long> getToPersionId() {
		return toPersionId;
	}

	public void setToPersionId(List<Long> toPersionId) {
		this.toPersionId = toPersionId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Long getToPerson() {
		return toPerson;
	}

	public void setToPerson(Long toPerson) {
		this.toPerson = toPerson;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageCenterForm [toPersionId=");
		builder.append(toPersionId);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", messageText=");
		builder.append(messageText);
		builder.append("]");
		return builder.toString();
	}

}
