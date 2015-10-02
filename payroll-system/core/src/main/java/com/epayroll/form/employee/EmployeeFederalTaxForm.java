package com.epayroll.form.employee;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;
import com.epayroll.model.employee.TaxTypeList;

/**
 * @author Uma
 */
public class EmployeeFederalTaxForm {
	private Long id;
	private Long employeeId;
	private Long employeeTaxId;
	private String taxClassification;
	private Long taxFillingStatusId;
	private Long allowanceTypeId;
	private Long noOfAllowances;
	private BigDecimal additionalWithholding;
	private List<TaxTypeList> exemptedList;
	// private String fitExempted;
	// private String futaExempted;
	// private String medicareExempted;
	// private String socialSecurityExempted;
	private TaxOverrideType taxOverrideType;
	private BigDecimal taxOverrideValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getEmployeeTaxId() {
		return employeeTaxId;
	}

	public void setEmployeeTaxId(Long employeeTaxId) {
		this.employeeTaxId = employeeTaxId;
	}

	public String getTaxClassification() {
		return taxClassification;
	}

	public void setTaxClassification(String taxClassification) {
		this.taxClassification = taxClassification;
	}

	public Long getTaxFillingStatusId() {
		return taxFillingStatusId;
	}

	public void setTaxFillingStatusId(Long taxFillingStatusId) {
		this.taxFillingStatusId = taxFillingStatusId;
	}

	public Long getAllowanceTypeId() {
		return allowanceTypeId;
	}

	public void setAllowanceTypeId(Long allowanceTypeId) {
		this.allowanceTypeId = allowanceTypeId;
	}

	public Long getNoOfAllowances() {
		return noOfAllowances;
	}

	public void setNoOfAllowances(Long noOfAllowances) {
		this.noOfAllowances = noOfAllowances;
	}

	public BigDecimal getAdditionalWithholding() {
		return additionalWithholding;
	}

	public void setAdditionalWithholding(BigDecimal additionalWithholding) {
		this.additionalWithholding = additionalWithholding;
	}

	public List<TaxTypeList> getExemptedList() {
		return exemptedList;
	}

	public void setExemptedList(List<TaxTypeList> exemptedList) {
		this.exemptedList = exemptedList;
	}

	public TaxOverrideType getTaxOverrideType() {
		return taxOverrideType;
	}

	public void setTaxOverrideType(TaxOverrideType taxOverrideType) {
		this.taxOverrideType = taxOverrideType;
	}

	public BigDecimal getTaxOverrideValue() {
		return taxOverrideValue;
	}

	public void setTaxOverrideValue(BigDecimal taxOverrideValue) {
		this.taxOverrideValue = taxOverrideValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeFedralTaxForm [id=");
		builder.append(id);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", employeeTaxId=");
		builder.append(employeeTaxId);
		builder.append(", taxClassification=");
		builder.append(taxClassification);
		builder.append(", taxFillingStatusId=");
		builder.append(taxFillingStatusId);
		builder.append(", allowanceTypeId=");
		builder.append(allowanceTypeId);
		builder.append(", noOfAllowances=");
		builder.append(noOfAllowances);
		builder.append(", additionalWithholding=");
		builder.append(additionalWithholding);
		builder.append(", exemptedList=");
		builder.append(exemptedList);
		builder.append(", taxOverrideType=");
		builder.append(taxOverrideType);
		builder.append(", taxOverrideValue=");
		builder.append(taxOverrideValue);
		builder.append("]");
		return builder.toString();
	}
}
