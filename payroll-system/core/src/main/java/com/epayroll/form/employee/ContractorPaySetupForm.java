package com.epayroll.form.employee;

import java.math.BigDecimal;

import com.epayroll.entity.EmployeePaySetup.PayType;

public class ContractorPaySetupForm {

	private Long id;
	private PayType payType;
	private Long payCycle;
	private BigDecimal payRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public Long getPayCycle() {
		return payCycle;
	}

	public void setPayCycle(Long payCycle) {
		this.payCycle = payCycle;
	}

	public BigDecimal getPayRate() {
		return payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
	}

}
