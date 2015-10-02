package com.epayroll.form.adminConsole;

public class TaxTypeForm {

	private Long id;
	private Long taxAuthorityId;
	private String taxName;
	private Boolean paidByEmployee;
	private Boolean paidByCompany;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaxAuthorityId() {
		return taxAuthorityId;
	}

	public void setTaxAuthorityId(Long taxAuthorityId) {
		this.taxAuthorityId = taxAuthorityId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public Boolean getPaidByEmployee() {
		return paidByEmployee;
	}

	public void setPaidByEmployee(Boolean paidByEmployee) {
		this.paidByEmployee = paidByEmployee;
	}

	public Boolean getPaidByCompany() {
		return paidByCompany;
	}

	public void setPaidByCompany(Boolean paidByCompany) {
		this.paidByCompany = paidByCompany;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
