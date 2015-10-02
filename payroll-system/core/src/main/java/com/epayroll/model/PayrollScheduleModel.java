package com.epayroll.model;

import java.util.List;

import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollSchedule;

public class PayrollScheduleModel {

	private PayrollFrequency payrollFrequency;
	private List<PayrollSchedule> payrollSchedules;
	private List<String> holidayOptions;

	public PayrollFrequency getPayrollFrequency() {
		return payrollFrequency;
	}

	public void setPayrollFrequency(PayrollFrequency payrollFrequency) {
		this.payrollFrequency = payrollFrequency;
	}

	public List<PayrollSchedule> getPayrollSchedules() {
		return payrollSchedules;
	}

	public void setPayrollSchedules(List<PayrollSchedule> payrollSchedules) {
		this.payrollSchedules = payrollSchedules;
	}

	public List<String> getHolidayOptions() {
		return holidayOptions;
	}

	public void setHolidayOptions(List<String> holidayOptions) {
		this.holidayOptions = holidayOptions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollScheduleModel [payrollFrequency=");
		builder.append(payrollFrequency);
		builder.append(", payrollSchedules=");
		builder.append(payrollSchedules);
		builder.append(", holidayOptions=");
		builder.append(holidayOptions);
		builder.append("]");
		return builder.toString();
	}
}
