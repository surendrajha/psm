package com.epayroll.form.payroll;

import java.util.List;

import com.epayroll.model.payroll.EmployeePayrollModel;

public class PayrollPreviewForm {

	private Long payrollId;
	private Long companyId;
	private Long payrollFrequencyId;
	private Double directDepositTotal;
	private Double employeeTaxTotal;
	private Double employerTaxTotal;
	private Double vjsProcessingFees;
	private Double totalAmountTransferElectronically;
	private Double checkAmountTotal;
	private Double employeeContractorDeductionTotal;
	private Double employerDeductionTotal;
	private Double grandTotal;
	List<EmployeePayrollModel> employeePayrollModels;

	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getPayrollFrequencyId() {
		return payrollFrequencyId;
	}

	public void setPayrollFrequencyId(Long payrollFrequencyId) {
		this.payrollFrequencyId = payrollFrequencyId;
	}

	public Double getDirectDepositTotal() {
		return directDepositTotal;
	}

	public void setDirectDepositTotal(Double directDepositTotal) {
		this.directDepositTotal = directDepositTotal;
	}

	public Double getEmployeeTaxTotal() {
		return employeeTaxTotal;
	}

	public void setEmployeeTaxTotal(Double employeeTaxTotal) {
		this.employeeTaxTotal = employeeTaxTotal;
	}

	public Double getEmployerTaxTotal() {
		return employerTaxTotal;
	}

	public void setEmployerTaxTotal(Double employerTaxTotal) {
		this.employerTaxTotal = employerTaxTotal;
	}

	public Double getVjsProcessingFees() {
		return vjsProcessingFees;
	}

	public void setVjsProcessingFees(Double vjsProcessingFees) {
		this.vjsProcessingFees = vjsProcessingFees;
	}

	public Double getTotalAmountTransferElectronically() {
		return totalAmountTransferElectronically;
	}

	public void setTotalAmountTransferElectronically(Double totalAmountTransferElectronically) {
		this.totalAmountTransferElectronically = totalAmountTransferElectronically;
	}

	public Double getCheckAmountTotal() {
		return checkAmountTotal;
	}

	public void setCheckAmountTotal(Double checkAmountTotal) {
		this.checkAmountTotal = checkAmountTotal;
	}

	public Double getEmployeeContractorDeductionTotal() {
		return employeeContractorDeductionTotal;
	}

	public void setEmployeeContractorDeductionTotal(Double employeeContractorDeductionTotal) {
		this.employeeContractorDeductionTotal = employeeContractorDeductionTotal;
	}

	public Double getEmployerDeductionTotal() {
		return employerDeductionTotal;
	}

	public void setEmployerDeductionTotal(Double employerDeductionTotal) {
		this.employerDeductionTotal = employerDeductionTotal;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public List<EmployeePayrollModel> getEmployeePayrollModels() {
		return employeePayrollModels;
	}

	public void setEmployeePayrollModels(List<EmployeePayrollModel> employeePayrollModels) {
		this.employeePayrollModels = employeePayrollModels;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayrollPreviewForm [payrollId=");
		builder.append(payrollId);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", payrollFrequencyId=");
		builder.append(payrollFrequencyId);
		builder.append(", directDepositTotal=");
		builder.append(directDepositTotal);
		builder.append(", employeeTaxTotal=");
		builder.append(employeeTaxTotal);
		builder.append(", employerTaxTotal=");
		builder.append(employerTaxTotal);
		builder.append(", vjsProcessingFees=");
		builder.append(vjsProcessingFees);
		builder.append(", totalAmountTransferElectronically=");
		builder.append(totalAmountTransferElectronically);
		builder.append(", checkAmountTotal=");
		builder.append(checkAmountTotal);
		builder.append(", employeeContractorDeductionTotal=");
		builder.append(employeeContractorDeductionTotal);
		builder.append(", employerDeductionTotal=");
		builder.append(employerDeductionTotal);
		builder.append(", grandTotal=");
		builder.append(grandTotal);
		builder.append(", employeePayrollModels=");
		builder.append(employeePayrollModels);
		builder.append("]");
		return builder.toString();
	}

}
