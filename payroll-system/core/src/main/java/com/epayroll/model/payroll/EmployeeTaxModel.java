package com.epayroll.model.payroll;

import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;

public class EmployeeTaxModel {

	private Long employeePayrollTaxId;
	private String taxName;
	private TaxOverrideType taxOverrideType;
	private Double amount;

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public TaxOverrideType getTaxOverrideType() {
		return taxOverrideType;
	}

	public void setTaxOverrideType(TaxOverrideType taxOverrideType) {
		this.taxOverrideType = taxOverrideType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getEmployeePayrollTaxId() {
		return employeePayrollTaxId;
	}

	public void setEmployeePayrollTaxId(Long employeePayrollTaxId) {
		this.employeePayrollTaxId = employeePayrollTaxId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeTaxModel [employeePayrollTaxId=");
		builder.append(employeePayrollTaxId);
		builder.append(", taxName=");
		builder.append(taxName);
		builder.append(", taxOverrideType=");
		builder.append(taxOverrideType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

}
