package com.epayroll.model.employee;

/**
 * @author Uma
 */
public class TaxTypeList {
	private Long taxTypeId;
	private String taxName;
	private Boolean exemptedValue;
	private Boolean fit;

	public TaxTypeList() {
	}

	public TaxTypeList(Long taxTypeId, String taxName, Boolean exemptedValue, Boolean fit) {
		super();
		this.taxTypeId = taxTypeId;
		this.taxName = taxName;
		this.exemptedValue = exemptedValue;
		this.fit = fit;
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

	public Boolean getExemptedValue() {
		return exemptedValue;
	}

	public void setExemptedValue(Boolean exemptedValue) {
		this.exemptedValue = exemptedValue;
	}

	public Boolean isFit() {
		return fit;
	}

	public void setFit(Boolean fit) {
		this.fit = fit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaxTypeList [taxTypeId=");
		builder.append(taxTypeId);
		builder.append(", taxName=");
		builder.append(taxName);
		builder.append(", exemptedValue=");
		builder.append(exemptedValue);
		builder.append(", fit=");
		builder.append(fit);
		builder.append("]");
		return builder.toString();
	}

}
