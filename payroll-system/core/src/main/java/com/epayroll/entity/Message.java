package com.epayroll.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Surendra Jha
 */
@Entity
@Table(name = "message")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Message implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4914443454476555514L;
	private Long id;
	private Person fromPerson;
	private String subject;
	private String message;
	private Date receivedAt;
	private Set<ToMessageMap> toMessageMaps = new HashSet<ToMessageMap>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_person_id")
	public Person getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(Person fromPerson) {
		this.fromPerson = fromPerson;
	}

	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "message", length = 65000)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "received_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "message", cascade = CascadeType.ALL)
	public Set<ToMessageMap> getToMessageMaps() {
		return toMessageMaps;
	}

	public void setToMessageMaps(Set<ToMessageMap> toMessageMaps) {
		this.toMessageMaps = toMessageMaps;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonMessage [id=");
		builder.append(id);
		builder.append(", fromPerson=");
		builder.append(fromPerson.getId());
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", message=");
		builder.append(message);
		builder.append(", receivedAt=");
		builder.append(receivedAt);
		builder.append("]");
		return builder.toString();
	}

}
