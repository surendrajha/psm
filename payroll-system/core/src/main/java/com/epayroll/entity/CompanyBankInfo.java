package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CompanyBankInfo generated by hbm2java
 */
@Entity
@Table(name = "company_bank_info")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class CompanyBankInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2889050953989891297L;

	public enum AccountType {
		SAVING("Saving"), CHECKING("Checking");
		private String name;

		private AccountType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum Status {
		ACTIVE("Active"), INACTIVE("InActive");
		private String name;

		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private Long id;
	private Company company;
	private String bankName;
	private String routingNumber;
	private String accountNumber;
	private AccountType accountType;
	private Status status;

	public CompanyBankInfo() {
	}

	public CompanyBankInfo(Company company, String accountNumber, AccountType accountType,
			String bankName, String routingNumber) {
		this.company = company;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.bankName = bankName;
		this.routingNumber = routingNumber;
	}

	public CompanyBankInfo(Company company, String accountNumber, AccountType accountType,
			String bankName, String routingNumber, Status status) {
		this.company = company;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.bankName = bankName;
		this.routingNumber = routingNumber;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "bank_name", length = 45)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "routing_number", unique = true, length = 15)
	public String getRoutingNumber() {
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	@Column(name = "account_number", unique = true, length = 15)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "account_type", length = 25)
	public AccountType getAccountType() {
		return this.accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Column(name = "status", length = 20)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyBankInfo [id=");
		builder.append(id);
		builder.append(", company=");
		builder.append(company);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", routingNumber=");
		builder.append(routingNumber);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
