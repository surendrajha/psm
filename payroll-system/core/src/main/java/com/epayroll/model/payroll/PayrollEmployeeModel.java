/**
 * 
 */
package com.epayroll.model.payroll;

import java.util.List;

import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeePaySetup.PayType;

/**
 * @author Surendra Jha
 */
public class PayrollEmployeeModel {

	private Long employeePayrollId;
	private Long employeeId;
	private Boolean directDepositStatus;
	private String employeeName;
	private Double salary;// fixedPay for contractors
	private Double hourlyRate; // hourly rate
	private Double regularHours;
	private Type employeeType;
	private PayType payType;

	// earnings
	List<PayrollEmployeeEarningModel> payrollEmployeeEarnings;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Boolean getDirectDepositStatus() {
		return directDepositStatus;
	}

	public void setDirectDepositStatus(Boolean directDepositStatus) {
		this.directDepositStatus = directDepositStatus;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public Double getRegularHours() {
		return regularHours;
	}

	public void setRegularHours(Double regularHours) {
		this.regularHours = regularHours;
	}

	public Type getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(Type employeeType) {
		this.employeeType = employeeType;
	}

	public Long getEmployeePayrollId() {
		return employeePayrollId;
	}

	public void setEmployeePayrollId(Long employeePayrollId) {
		this.employeePayrollId = employeePayrollId;
	}

	public List<PayrollEmployeeEarningModel> getPayrollEmployeeEarnings() {
		return payrollEmployeeEarnings;
	}

	public void setPayrollEmployeeEarnings(List<PayrollEmployeeEarningModel> payrollEmployeeEarnings) {
		this.payrollEmployeeEarnings = payrollEmployeeEarnings;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollEmployeeModel [employeePayrollId=");
		builder.append(employeePayrollId);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", directDepositStatus=");
		builder.append(directDepositStatus);
		builder.append(", employeeName=");
		builder.append(employeeName);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", hourlyRate=");
		builder.append(hourlyRate);
		builder.append(", regularHours=");
		builder.append(regularHours);
		builder.append(", employeeType=");
		builder.append(employeeType);
		builder.append(", payType=");
		builder.append(payType);
		builder.append(", payrollEmployeeEarnings=");
		builder.append(payrollEmployeeEarnings);
		builder.append("]");
		return builder.toString();
	}

}
