package com.epayroll.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Surendra Jha
 */
@Entity
@Table(name = "to_message_map")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ToMessageMap implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6718856335568122811L;
	private Long id;
	private Message message;
	private Person toPerson;
	private Status status;

	public enum Status {
		READ("Read"), UNREAD("Unread");
		private String name;

		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_message_id")
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_person_id")
	public Person getToPerson() {
		return toPerson;
	}

	public void setToPerson(Person toPerson) {
		this.toPerson = toPerson;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ToMessageMap [id=");
		builder.append(id);
		builder.append(", personMessage=");
		builder.append(message.getId());
		builder.append(", toPerson=");
		builder.append(toPerson.getId());
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
