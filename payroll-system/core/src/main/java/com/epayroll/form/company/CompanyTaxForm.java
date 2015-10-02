package com.epayroll.form.company;

public class CompanyTaxForm {

	private Long id;
	private Long companyId;
	private Long depositCycleId;
	private Boolean exempted;
	private String ein;
	private Long taxTypeId;
	private String companyType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getDepositCycleId() {
		return depositCycleId;
	}

	public void setDepositCycleId(Long depositCycleId) {
		this.depositCycleId = depositCycleId;
	}

	public Boolean getExempted() {
		return exempted;
	}

	public void setExempted(Boolean exempted) {
		this.exempted = exempted;
	}

	public String getEin() {
		return ein;
	}

	public void setEin(String ein) {
		this.ein = ein;
	}

	public Long getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(Long taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyTaxForm [id=");
		builder.append(id);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", depositCycleId=");
		builder.append(depositCycleId);
		builder.append(", exempted=");
		builder.append(exempted);
		builder.append(", ein=");
		builder.append(ein);
		builder.append(", taxTypeId=");
		builder.append(taxTypeId);
		builder.append(", companyType=");
		builder.append(companyType);
		builder.append("]");
		return builder.toString();
	}

}
