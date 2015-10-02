package com.epayroll.model.adminConsole;

import java.math.BigDecimal;
import java.math.MathContext;

public class MedicareSocialSecurityModel {
	private Long federalStateTaxRateId;
	private Long taxTypeId;
	private String taxName;
	private BigDecimal employeeContribution;
	private BigDecimal companyContribution;

	public MedicareSocialSecurityModel() {
		employeeContribution = new BigDecimal("0.00");
		companyContribution = new BigDecimal("0.00");
	}

	public MedicareSocialSecurityModel(Long federalStateTaxRateId, Long taxTypeId, String taxName,
			BigDecimal employeeContribution, BigDecimal companyContribution) {
		super();
		this.federalStateTaxRateId = federalStateTaxRateId;
		this.taxTypeId = taxTypeId;
		this.taxName = taxName;
		this.employeeContribution = employeeContribution;
		this.companyContribution = companyContribution;
	}

	public Long getFederalStateTaxRateId() {
		return federalStateTaxRateId;
	}

	public void setFederalStateTaxRateId(Long federalStateTaxRateId) {
		this.federalStateTaxRateId = federalStateTaxRateId;
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

	public BigDecimal getEmployeeContribution() {
		return employeeContribution;
	}

	public void setEmployeeContribution(BigDecimal employeeContribution) {
		this.employeeContribution = new BigDecimal(employeeContribution.toString(),
				MathContext.DECIMAL64);
	}

	public BigDecimal getCompanyContribution() {
		return companyContribution;
	}

	public void setCompanyContribution(BigDecimal companyContribution) {
		this.companyContribution = new BigDecimal(companyContribution.toString(),
				MathContext.DECIMAL64);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MedicareSocialSecurityModel [federalStateTaxRateId=");
		builder.append(federalStateTaxRateId);
		builder.append(", taxTypeId=");
		builder.append(taxTypeId);
		builder.append(", taxName=");
		builder.append(taxName);
		builder.append(", employeeContribution=");
		builder.append(employeeContribution);
		builder.append(", companyContribution=");
		builder.append(companyContribution);
		builder.append("]");
		return builder.toString();
	}

}
