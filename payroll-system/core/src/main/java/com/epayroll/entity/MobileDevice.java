package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MobileDevice generated by hbm2java
 */
@Entity
@Table(name = "mobile_device")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class MobileDevice implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 128298823642136471L;

	public enum Status {
		ACTIVE("Active"), INACTIVE("InActive");
		private String name;

		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private Long id;
	private CompanyUser companyUser;
	private String deviceId;
	private String type;
	private Date registeredOn;
	private Status status;
	private Date lastAccessed;

	public MobileDevice() {
	}

	public MobileDevice(CompanyUser companyUser, String deviceId) {
		this.companyUser = companyUser;
		this.deviceId = deviceId;
	}

	public MobileDevice(CompanyUser companyUser, String deviceId, String type, Date registeredOn,
			Status status, Date lastAccessed) {
		this.companyUser = companyUser;
		this.deviceId = deviceId;
		this.type = type;
		this.registeredOn = registeredOn;
		this.status = status;
		this.lastAccessed = lastAccessed;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_user_id")
	public CompanyUser getCompanyUser() {
		return this.companyUser;
	}

	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}

	@Column(name = "device_id", length = 45)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registered_on", length = 19)
	public Date getRegisteredOn() {
		return this.registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	@Column(name = "status", length = 20)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_accessed", length = 19)
	public Date getLastAccessed() {
		return this.lastAccessed;
	}

	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MobileDevice [id=");
		builder.append(id);
		builder.append(", companyUser=");
		builder.append(companyUser.getId());
		builder.append(", deviceId=");
		builder.append(deviceId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", registeredOn=");
		builder.append(registeredOn);
		builder.append(", status=");
		builder.append(status);
		builder.append(", lastAccessed=");
		builder.append(lastAccessed);
		builder.append("]");
		return builder.toString();
	}

}
