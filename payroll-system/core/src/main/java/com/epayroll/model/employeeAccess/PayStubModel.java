package com.epayroll.model.employeeAccess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayrollDeduction;
import com.epayroll.entity.EmployeePayrollEarning;
import com.epayroll.entity.EmployeePayrollTax;

public class PayStubModel {
	private String employeeId;
	private String employeeName;
	private String employeeAddress;

	private String companyName;
	private String companyStreetAddress;
	private String companyCityName;
	private String companyCountyName;
	private String companyStateName;
	private String companyPinZip;

	private BigDecimal employeePayrollGrossPay;
	private BigDecimal netPay;
	private String payPeriod;
	private Date payDate;
	private BigDecimal totalDeduction;
	private BigDecimal totalTax;

	private String fitFilingStatus;
	private Long fitNoOfAllowance;
	private BigDecimal fitAdditionalWithholding;
	private BigDecimal fitTaxOverrideValue;

	private String sitFilingStatus;
	private Long sitNoOfAllowance;
	private BigDecimal sitAdditionalWithholding;
	private BigDecimal sitTaxOverrideValue;

	private List<EmployeePayroll> employeePayrolls;
	private List<EmployeePayrollEarning> employeePayrollEarnings;
	private List<EmployeePayrollDeduction> employeePayrollDeductions;
	private List<EmployeePayrollTax> employeePayrollTaxs;

	public PayStubModel() {
	}

	public PayStubModel(String employeeId, String employeeName, String employeeAddress,
			String employeeCityName, String employeeCountyName, String employeeStateName,
			String employeePinZip, String companyName, String companyStreetAddress,
			String companyCityName, String companyCountryName, String companyStateName,
			String companyPinZip, BigDecimal emloyeePayrollGrossPay, BigDecimal netPay,
			String payPeriod, Date payDate, BigDecimal totalDeduction, BigDecimal totalTax,
			String fitFilingStatus, Long fitNoOfAllowance, BigDecimal fitAdditionalWithholding,
			BigDecimal fitTaxOverrideValue, String sitFilingStatus, Long sitNoOfAllowance,
			BigDecimal sitAdditionalWithholding, BigDecimal sitTaxOverrideValue,
			List<EmployeePayroll> employeePayrolls,
			List<EmployeePayrollEarning> employeePayrollEarnings,
			List<EmployeePayrollDeduction> employeePayrollDeductions,
			List<EmployeePayrollTax> employeePayrollTaxs) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;

		this.companyStreetAddress = companyStreetAddress;
		this.companyCityName = companyCityName;
		this.companyCountyName = companyCountryName;
		this.companyStateName = companyStateName;
		this.companyPinZip = companyPinZip;

		this.employeePayrollGrossPay = emloyeePayrollGrossPay;
		this.netPay = netPay;
		this.payPeriod = payPeriod;
		this.payDate = payDate;
		this.totalDeduction = totalDeduction;
		this.totalTax = totalTax;

		this.fitFilingStatus = fitFilingStatus;
		this.fitAdditionalWithholding = fitAdditionalWithholding;
		this.fitNoOfAllowance = fitNoOfAllowance;
		this.fitTaxOverrideValue = fitTaxOverrideValue;

		this.sitFilingStatus = sitFilingStatus;
		this.sitAdditionalWithholding = sitAdditionalWithholding;
		this.sitNoOfAllowance = sitNoOfAllowance;
		this.sitTaxOverrideValue = sitTaxOverrideValue;

		this.employeePayrolls = employeePayrolls;
		this.employeePayrollEarnings = employeePayrollEarnings;
		this.employeePayrollDeductions = employeePayrollDeductions;
		this.employeePayrollTaxs = employeePayrollTaxs;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyStreetAddress() {
		return companyStreetAddress;
	}

	public void setCompanyStreetAddress(String companyStreetAddress) {
		this.companyStreetAddress = companyStreetAddress;
	}

	public String getCompanyCityName() {
		return companyCityName;
	}

	public void setCompanyCityName(String companyCityName) {
		this.companyCityName = companyCityName;
	}

	public String getCompanyCountyName() {
		return companyCountyName;
	}

	public void setCompanyCountyName(String companyCountyName) {
		this.companyCountyName = companyCountyName;
	}

	public String getCompanyStateName() {
		return companyStateName;
	}

	public void setCompanyStateName(String companyStateName) {
		this.companyStateName = companyStateName;
	}

	public String getCompanyPinZip() {
		return companyPinZip;
	}

	public void setCompanyPinZip(String companyPinZip) {
		this.companyPinZip = companyPinZip;
	}

	public BigDecimal getEmployeePayrollGrossPay() {
		return employeePayrollGrossPay;
	}

	public void setEmployeePayrollGrossPay(BigDecimal employeePayrollGrossPay) {
		this.employeePayrollGrossPay = employeePayrollGrossPay;
	}

	public BigDecimal getNetPay() {
		return netPay;
	}

	public void setNetPay(BigDecimal netPay) {
		this.netPay = netPay;
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public BigDecimal getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(BigDecimal totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public String getFitFilingStatus() {
		return fitFilingStatus;
	}

	public void setFitFilingStatus(String fitFilingStatus) {
		this.fitFilingStatus = fitFilingStatus;
	}

	public Long getFitNoOfAllowance() {
		return fitNoOfAllowance;
	}

	public void setFitNoOfAllowance(Long fitNoOfAllowance) {
		this.fitNoOfAllowance = fitNoOfAllowance;
	}

	public BigDecimal getFitAdditionalWithholding() {
		return fitAdditionalWithholding;
	}

	public void setFitAdditionalWithholding(BigDecimal fitAdditionalWithholding) {
		this.fitAdditionalWithholding = fitAdditionalWithholding;
	}

	public BigDecimal getFitTaxOverrideValue() {
		return fitTaxOverrideValue;
	}

	public void setFitTaxOverrideValue(BigDecimal fitTaxOverrideValue) {
		this.fitTaxOverrideValue = fitTaxOverrideValue;
	}

	public String getSitFilingStatus() {
		return sitFilingStatus;
	}

	public void setSitFilingStatus(String sitFilingStatus) {
		this.sitFilingStatus = sitFilingStatus;
	}

	public Long getSitNoOfAllowance() {
		return sitNoOfAllowance;
	}

	public void setSitNoOfAllowance(Long sitNoOfAllowance) {
		this.sitNoOfAllowance = sitNoOfAllowance;
	}

	public BigDecimal getSitAdditionalWithholding() {
		return sitAdditionalWithholding;
	}

	public void setSitAdditionalWithholding(BigDecimal sitAdditionalWithholding) {
		this.sitAdditionalWithholding = sitAdditionalWithholding;
	}

	public BigDecimal getSitTaxOverrideValue() {
		return sitTaxOverrideValue;
	}

	public void setSitTaxOverrideValue(BigDecimal sitTaxOverrideValue) {
		this.sitTaxOverrideValue = sitTaxOverrideValue;
	}

	public List<EmployeePayroll> getEmployeePayrolls() {
		return employeePayrolls;
	}

	public void setEmployeePayrolls(List<EmployeePayroll> employeePayrolls) {
		this.employeePayrolls = employeePayrolls;
	}

	public List<EmployeePayrollEarning> getEmployeePayrollEarnings() {
		return employeePayrollEarnings;
	}

	public void setEmployeePayrollEarnings(List<EmployeePayrollEarning> employeePayrollEarnings) {
		this.employeePayrollEarnings = employeePayrollEarnings;
	}

	public List<EmployeePayrollDeduction> getEmployeePayrollDeductions() {
		return employeePayrollDeductions;
	}

	public void setEmployeePayrollDeductions(
			List<EmployeePayrollDeduction> employeePayrollDeductions) {
		this.employeePayrollDeductions = employeePayrollDeductions;
	}

	public List<EmployeePayrollTax> getEmployeePayrollTaxs() {
		return employeePayrollTaxs;
	}

	public void setEmployeePayrollTaxs(List<EmployeePayrollTax> employeePayrollTaxs) {
		this.employeePayrollTaxs = employeePayrollTaxs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayStubModel [employeeId=");
		builder.append(employeeId);
		builder.append(", employeeName=");
		builder.append(employeeName);
		builder.append(", employeeAddress=");
		builder.append(employeeAddress);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", companyStreetAddress=");
		builder.append(companyStreetAddress);
		builder.append(", companyCityName=");
		builder.append(companyCityName);
		builder.append(", companyCountyName=");
		builder.append(companyCountyName);
		builder.append(", companyStateName=");
		builder.append(companyStateName);
		builder.append(", companyPinZip=");
		builder.append(companyPinZip);
		builder.append(", employeePayrollGrossPay=");
		builder.append(employeePayrollGrossPay);
		builder.append(", netPay=");
		builder.append(netPay);
		builder.append(", payPeriod=");
		builder.append(payPeriod);
		builder.append(", payDate=");
		builder.append(payDate);
		builder.append(", totalDeduction=");
		builder.append(totalDeduction);
		builder.append(", totalTax=");
		builder.append(totalTax);
		builder.append(", fitFilingStatus=");
		builder.append(fitFilingStatus);
		builder.append(", fitNoOfAllowance=");
		builder.append(fitNoOfAllowance);
		builder.append(", fitAdditionalWithholding=");
		builder.append(fitAdditionalWithholding);
		builder.append(", fitTaxOverrideValue=");
		builder.append(fitTaxOverrideValue);
		builder.append(", sitFilingStatus=");
		builder.append(sitFilingStatus);
		builder.append(", sitNoOfAllowance=");
		builder.append(sitNoOfAllowance);
		builder.append(", sitAdditionalWithholding=");
		builder.append(sitAdditionalWithholding);
		builder.append(", sitTaxOverrideValue=");
		builder.append(sitTaxOverrideValue);
		builder.append(", employeePayrolls=");
		builder.append(employeePayrolls);
		builder.append(", employeePayrollEarnings=");
		builder.append(employeePayrollEarnings);
		builder.append(", employeePayrollDeductions=");
		builder.append(employeePayrollDeductions);
		builder.append(", employeePayrollTaxs=");
		builder.append(employeePayrollTaxs);
		builder.append("]");
		return builder.toString();
	}

}
