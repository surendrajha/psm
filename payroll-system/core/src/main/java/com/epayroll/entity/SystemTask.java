package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SystemTask generated by hbm2java
 */
@Entity
@Table(name = "system_task")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SystemTask implements java.io.Serializable, Comparable<SystemTask> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6227453972478335679L;

	private Long id;
	private Date dateReceived;
	private Date dueDate;
	private String taskText;
	private String keyValue;
	private String redirectingUrl;
	private Set<SystemTaskUserMap> systemTaskUserMaps = new HashSet<SystemTaskUserMap>(0);

	public SystemTask() {
	}

	public SystemTask(Date dateReceived, String taskText) {
		this.dateReceived = dateReceived;
		this.taskText = taskText;
	}

	public SystemTask(Date dateReceived, Date dueDate, String taskText, String keyValue,
			String redirectingUrl, Set<SystemTaskUserMap> systemTaskUserMaps) {
		this.dateReceived = dateReceived;
		this.dueDate = dueDate;
		this.taskText = taskText;
		this.keyValue = keyValue;
		this.redirectingUrl = redirectingUrl;
		this.setSystemTaskUserMaps(systemTaskUserMaps);
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_received", length = 19)
	public Date getDateReceived() {
		return this.dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "due_date", length = 19)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "task_text", length = 250)
	public String getTaskText() {
		return this.taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	@Column(name = "key_value", length = 250)
	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	@Column(name = "redirecting_url", length = 200)
	public String getRedirectingUrl() {
		return this.redirectingUrl;
	}

	public void setRedirectingUrl(String redirectingUrl) {
		this.redirectingUrl = redirectingUrl;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "systemTask")
	public Set<SystemTaskUserMap> getSystemTaskUserMaps() {
		return systemTaskUserMaps;
	}

	public void setSystemTaskUserMaps(Set<SystemTaskUserMap> systemTaskUserMaps) {
		this.systemTaskUserMaps = systemTaskUserMaps;
	}

	@Override
	public int compareTo(SystemTask systemTask) {
		if (this.getDateReceived().compareTo(systemTask.getDateReceived()) == 0) {
			return 1;
		}
		return this.getDateReceived().compareTo(systemTask.getDateReceived());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemTask [id=");
		builder.append(id);
		builder.append(", dateReceived=");
		builder.append(dateReceived);
		builder.append(", dueDate=");
		builder.append(dueDate);
		builder.append(", taskText=");
		builder.append(taskText);
		builder.append(", keyValue=");
		builder.append(keyValue);
		builder.append(", redirectingUrl=");
		builder.append(redirectingUrl);
		builder.append("]");
		return builder.toString();
	}

}
