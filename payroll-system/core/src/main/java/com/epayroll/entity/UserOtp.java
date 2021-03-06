package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserOtp generated by hbm2java
 */
@Entity
@Table(name = "user_otp")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class UserOtp implements java.io.Serializable {

	private static final long serialVersionUID = 2247295982165355343L;
	private Long id;
	private User user;
	private String otpCode;
	private Calendar generatedOn;

	public UserOtp() {
	}

	public UserOtp(User user, String otpCode, Calendar generatedOn) {
		this.user = user;
		this.otpCode = otpCode;
		this.generatedOn = generatedOn;
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "otp_code", length = 10)
	public String getOtpCode() {
		return this.otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}

	@Column(name = "generated_on", length = 19, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Calendar getGeneratedOn() {
		return this.generatedOn;
	}

	public void setGeneratedOn(Calendar generatedOn) {
		this.generatedOn = generatedOn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOtp [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user.getId());
		builder.append(", otpCode=");
		builder.append(otpCode);
		builder.append(", generatedOn=");
		builder.append(generatedOn);
		builder.append("]");
		return builder.toString();
	}

}
