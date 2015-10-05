/**
 * 
 */
package com.epayroll.model.employee;

/**
 * @author Surendra Jha
 */
public class MessageCenter {

	private Long persionId;
	private String name; // include "firstName lastName[email]" eg. surendra jha
							// [surendra@itechsoftware.com]

	public Long getPersionId() {
		return persionId;
	}

	public void setPersionId(Long persionId) {
		this.persionId = persionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageCenter [persionId=");
		builder.append(persionId);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
