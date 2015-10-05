package com.epayroll.form.employee;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import com.epayroll.entity.EmployeeBankAccount.AccountType;
import com.epayroll.entity.EmployeeBankAccount.DepositValueType;

public class EmployeeBankInfo {

	private Long id;
	private DepositValueType depositValueType;
	private AccountType accountType;
	@NotBlank
	private String routingNumber;
	@NotBlank
	private String accountNumber;
	private String bankName;
	private BigDecimal depositValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DepositValueType getDepositValueType() {
		return depositValueType;
	}

	public void setDepositValueType(DepositValueType depositValueType) {
		this.depositValueType = depositValueType;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getDepositValue() {
		return depositValue;
	}

	public void setDepositValue(BigDecimal depositValue) {
		this.depositValue = depositValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeBankInfo [id=");
		builder.append(id);
		builder.append(", depositValueType=");
		builder.append(depositValueType);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", routingNumber=");
		builder.append(routingNumber);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", depositValue=");
		builder.append(depositValue);
		builder.append("]");
		return builder.toString();
	}

}
