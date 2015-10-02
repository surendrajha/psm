package com.epayroll.form.employee;

import java.util.Date;

/**
 * @author Rajul Tiwari
 */
public class PaymentCheckInfoForm {

	private Long id;
	private Date fromPayDate;
	private Date toPayDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFromPayDate() {
		return fromPayDate;
	}

	public void setFromPayDate(Date fromPayDate) {
		this.fromPayDate = fromPayDate;
	}

	public Date getToPayDate() {
		return toPayDate;
	}

	public void setToPayDate(Date toPayDate) {
		this.toPayDate = toPayDate;
	}

}