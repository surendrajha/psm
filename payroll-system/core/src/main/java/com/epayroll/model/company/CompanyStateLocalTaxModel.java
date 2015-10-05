package com.epayroll.model.company;

/**
 * @author Uma
 */
public class CompanyStateLocalTaxModel {
	private Long taxTypeId;
	private String taxName;
	private Boolean exempted;
	private String ein;
	private Long depositCycleId;

	public CompanyStateLocalTaxModel() {
	}

	public CompanyStateLocalTaxModel(Long taxTypeId, String taxName, Boolean exempted, String ein,
			Long depositCycleId) {
		super();
		this.taxTypeId = taxTypeId;
		this.taxName = taxName;
		this.exempted = exempted;
		this.ein = ein;
		this.depositCycleId = depositCycleId;
	}

	public Long getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(Long taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
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

	public Long getDepositCycleId() {
		return depositCycleId;
	}

	public void setDepositCycleId(Long depositCycleId) {
		this.depositCycleId = depositCycleId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyStateLocalTaxModel [taxTypeId=");
		builder.append(taxTypeId);
		builder.append(", taxName=");
		builder.append(taxName);
		builder.append(", exempted=");
		builder.append(exempted);
		builder.append(", ein=");
		builder.append(ein);
		builder.append(", depositCycleId=");
		builder.append(depositCycleId);
		builder.append("]");
		return builder.toString();
	}

}
