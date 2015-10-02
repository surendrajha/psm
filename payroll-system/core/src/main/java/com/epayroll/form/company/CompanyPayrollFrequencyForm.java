package com.epayroll.form.company;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.epayroll.entity.PayrollFrequency.PayFrequencyType;

public class CompanyPayrollFrequencyForm {

	private Long id;
	private PayFrequencyType payFrequencyType;
	@NotNull
	// @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date editPeriodStartDate;
	@NotNull
	// @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date editPeriodEndDate;
	@NotNull
	// @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date editCheckDate;
	private String holidayCheckDateOption;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PayFrequencyType getPayFrequencyType() {
		return payFrequencyType;
	}

	public void setPayFrequencyType(PayFrequencyType payFrequencyType) {
		this.payFrequencyType = payFrequencyType;
	}

	public Date getEditPeriodStartDate() {
		return editPeriodStartDate;
	}

	public void setEditPeriodStartDate(Date editPeriodStartDate) {
		this.editPeriodStartDate = editPeriodStartDate;
	}

	public Date getEditPeriodEndDate() {
		return editPeriodEndDate;
	}

	public void setEditPeriodEndDate(Date editPeriodEndDate) {
		this.editPeriodEndDate = editPeriodEndDate;
	}

	public Date getEditCheckDate() {
		return editCheckDate;
	}

	public void setEditCheckDate(Date editCheckDate) {
		this.editCheckDate = editCheckDate;
	}

	public String getHolidayCheckDateOption() {
		return holidayCheckDateOption;
	}

	public void setHolidayCheckDateOption(String holidayCheckDateOption) {
		this.holidayCheckDateOption = holidayCheckDateOption;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyPayrollFrequencyForm [id=");
		builder.append(id);
		builder.append(", payFrequencyType=");
		builder.append(payFrequencyType);
		builder.append(", editPeriodStartDate=");
		builder.append(editPeriodStartDate);
		builder.append(", editPeriodEndDate=");
		builder.append(editPeriodEndDate);
		builder.append(", editCheckDate=");
		builder.append(editCheckDate);
		builder.append(", holidayCheckDateOption=");
		builder.append(holidayCheckDateOption);
		builder.append("]");
		return builder.toString();
	}
}
