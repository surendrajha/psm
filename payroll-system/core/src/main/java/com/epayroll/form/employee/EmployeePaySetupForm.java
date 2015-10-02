package com.epayroll.form.employee;

import java.math.BigDecimal;

import com.epayroll.entity.EmployeePaySetup.PayType;

public class EmployeePaySetupForm {

	private Long id;
	private PayType payType;
	private Long payCycle;
	private BigDecimal salary;
	private BigDecimal standardHours;
	private BigDecimal otherRate;
	private BigDecimal hourlyRate;

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

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getStandardHours() {
		return standardHours;
	}

	public void setStandardHours(BigDecimal standardHours) {
		this.standardHours = standardHours;
	}

	public BigDecimal getOtherRate() {
		return otherRate;
	}

	public void setOtherRate(BigDecimal otherRate) {
		this.otherRate = otherRate;
	}

	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeePaySetupForm [id=");
		builder.append(id);
		builder.append(", payType=");
		builder.append(payType);
		builder.append(", payCycle=");
		builder.append(payCycle);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", standardHours=");
		builder.append(standardHours);
		builder.append(", otherRate=");
		builder.append(otherRate);
		builder.append(", hourlyRate=");
		builder.append(hourlyRate);
		builder.append("]");
		return builder.toString();
	}

}
