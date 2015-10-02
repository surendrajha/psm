package com.epayroll.form.adminConsole;

import java.math.BigDecimal;

/**
 * @author Rajul Tiwari
 */
public class TaxAuthorityDepositCycleForm {

	private Long taxAuthorityId;
	private Long taxAuthorityDepositCycleId;
	private Long taxDepositCycleId;
	private Long usStateId;
	private BigDecimal maxTaxAmount;
	private BigDecimal minTaxAmount;

	public TaxAuthorityDepositCycleForm() {
	}

	public TaxAuthorityDepositCycleForm(Long taxAuthorityId, Long taxAuthorityDepositCycleId,
			Long usStateId, Long taxDepositCycleId, BigDecimal maxTaxAmount, BigDecimal minTaxAmount) {
		super();
		this.taxAuthorityId = taxAuthorityId;
		this.taxAuthorityDepositCycleId = taxAuthorityDepositCycleId;
		this.taxDepositCycleId = taxDepositCycleId;
		this.setUsStateId(usStateId);
		this.maxTaxAmount = maxTaxAmount;
		this.minTaxAmount = minTaxAmount;
	}

	public Long getTaxAuthorityId() {
		return taxAuthorityId;
	}

	public void setTaxAuthorityId(Long taxAuthorityId) {
		this.taxAuthorityId = taxAuthorityId;
	}

	public Long getTaxAuthorityDepositCycleId() {
		return taxAuthorityDepositCycleId;
	}

	public void setTaxAuthorityDepositCycleId(Long taxAuthorityDepositCycleId) {
		this.taxAuthorityDepositCycleId = taxAuthorityDepositCycleId;
	}

	public Long getTaxDepositCycleId() {
		return taxDepositCycleId;
	}

	public void setTaxDepositCycleId(Long taxDepositCycleId) {
		this.taxDepositCycleId = taxDepositCycleId;
	}

	public Long getUsStateId() {
		return usStateId;
	}

	public void setUsStateId(Long usStateId) {
		this.usStateId = usStateId;
	}

	public BigDecimal getMaxTaxAmount() {
		return maxTaxAmount;
	}

	public void setMaxTaxAmount(BigDecimal maxTaxAmount) {
		this.maxTaxAmount = maxTaxAmount;
	}

	public BigDecimal getMinTaxAmount() {
		return minTaxAmount;
	}

	public void setMinTaxAmount(BigDecimal minTaxAmount) {
		this.minTaxAmount = minTaxAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaxAuthorityDepositCycleForm [taxAuthorityId=");
		builder.append(taxAuthorityId);
		builder.append(", taxAuthorityDepositCycleId=");
		builder.append(taxAuthorityDepositCycleId);
		builder.append(", taxDepositCycleId=");
		builder.append(taxDepositCycleId);
		builder.append(", usStateId=");
		builder.append(usStateId);
		builder.append(", maxTaxAmount=");
		builder.append(maxTaxAmount);
		builder.append(", minTaxAmount=");
		builder.append(minTaxAmount);
		builder.append("]");
		return builder.toString();
	}

	public void clear() {
		this.maxTaxAmount = null;
		this.minTaxAmount = null;
		this.taxAuthorityDepositCycleId = null;
		this.taxDepositCycleId = null;
	}

}