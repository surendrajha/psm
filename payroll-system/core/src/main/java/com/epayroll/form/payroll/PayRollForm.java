package com.epayroll.form.payroll;

import java.util.Date;
import java.util.List;

import com.epayroll.model.payroll.PayrollEmployeeModel;

public class PayRollForm {

	private Long payrollId;
	private Long payrollFrequencyId;
	private Date startDate;
	private Date endDate;
	private Date checkDate;
	private Integer noOfEarning;
	private Integer noOfEmployee;
	private List<PayrollEmployeeModel> payrollEmployees;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getNoOfEarning() {
		return noOfEarning;
	}

	public void setNoOfEarning(Integer noOfEarning) {
		this.noOfEarning = noOfEarning;
	}

	public List<PayrollEmployeeModel> getPayrollEmployees() {
		return payrollEmployees;
	}

	public void setPayrollEmployees(List<PayrollEmployeeModel> payrollEmployees) {
		this.payrollEmployees = payrollEmployees;
	}

	public Integer getNoOfEmployee() {
		return noOfEmployee;
	}

	public void setNoOfEmployee(Integer noOfEmployee) {
		this.noOfEmployee = noOfEmployee;
	}

	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
	}

	public Long getPayrollFrequencyId() {
		return payrollFrequencyId;
	}

	public void setPayrollFrequencyId(Long payrollFrequencyId) {
		this.payrollFrequencyId = payrollFrequencyId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayRollForm [payrollId=");
		builder.append(payrollId);
		builder.append(", payrollFrequencyId=");
		builder.append(payrollFrequencyId);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", checkDate=");
		builder.append(checkDate);
		builder.append(", noOfEarning=");
		builder.append(noOfEarning);
		builder.append(", noOfEmployee=");
		builder.append(noOfEmployee);
		builder.append(", payrollEmployees=");
		builder.append(payrollEmployees);
		builder.append("]");
		return builder.toString();
	}

}
