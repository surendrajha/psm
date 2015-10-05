package com.epayroll.form.adminConsole;

import java.math.BigDecimal;
import java.util.Date;

import com.epayroll.entity.FundTransaction.CheckStatus;
import com.epayroll.entity.FundTransaction.TransactionStatus;
import com.epayroll.entity.FundTransaction.TransactionType;

public class FundTransactionForm {

	private Long id;
	private Long employeePayrollId;
	private Long payrollId;
	private Long transactionBodyId;
	private Long companyId;
	private Long payrollCompanyId;
	private Long paymentModeId;
	private String referenceNumber;
	private TransactionStatus transactionStatus;
	private TransactionType transactionType;
	private BigDecimal amount;
	private Long fundCategoryId;
	private Date dateOfTransaction;
	private CheckStatus checkStatus;
	private Date checkVoidDate;
	private Date checkClearingDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeePayrollId() {
		return employeePayrollId;
	}

	public void setEmployeePayrollId(Long employeePayrollId) {
		this.employeePayrollId = employeePayrollId;
	}

	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
	}

	public Long getTransactionBodyId() {
		return transactionBodyId;
	}

	public void setTransactionBodyId(Long transactionBodyId) {
		this.transactionBodyId = transactionBodyId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getPayrollCompanyId() {
		return payrollCompanyId;
	}

	public void setPayrollCompanyId(Long payrollCompanyId) {
		this.payrollCompanyId = payrollCompanyId;
	}

	public Long getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(Long paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getFundCategoryId() {
		return fundCategoryId;
	}

	public void setFundCategoryId(Long fundCategoryId) {
		this.fundCategoryId = fundCategoryId;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public CheckStatus getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(CheckStatus checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getCheckVoidDate() {
		return checkVoidDate;
	}

	public void setCheckVoidDate(Date checkVoidDate) {
		this.checkVoidDate = checkVoidDate;
	}

	public Date getCheckClearingDate() {
		return checkClearingDate;
	}

	public void setCheckClearingDate(Date checkClearingDate) {
		this.checkClearingDate = checkClearingDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FundTransactionForm [id=");
		builder.append(id);
		builder.append(", employeePayrollId=");
		builder.append(employeePayrollId);
		builder.append(", payrollId=");
		builder.append(payrollId);
		builder.append(", transactionBodyId=");
		builder.append(transactionBodyId);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", payrollCompanyId=");
		builder.append(payrollCompanyId);
		builder.append(", paymentModeId=");
		builder.append(paymentModeId);
		builder.append(", referenceNumber=");
		builder.append(referenceNumber);
		builder.append(", transactionStatus=");
		builder.append(transactionStatus);
		builder.append(", transactionType=");
		builder.append(transactionType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", fundCategoryId=");
		builder.append(fundCategoryId);
		builder.append(", dateOfTransaction=");
		builder.append(dateOfTransaction);
		builder.append(", checkStatus=");
		builder.append(checkStatus);
		builder.append(", checkVoidDate=");
		builder.append(checkVoidDate);
		builder.append(", checkClearingDate=");
		builder.append(checkClearingDate);
		builder.append("]");
		return builder.toString();
	}

}
