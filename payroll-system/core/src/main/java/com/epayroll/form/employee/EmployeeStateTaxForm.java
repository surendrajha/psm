package com.epayroll.form.employee;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;
import com.epayroll.model.employee.EmployeeAllowanceModel;

/**
 * @author Uma
 */
public class EmployeeStateTaxForm {
	private Long id;
	private Long employeeId;
	private Long employeeSitId;
	private Long employeeSutaId;
	private Long taxFillingStatusId;
	private Long allowanceTypeId;
	private Long noOfAllowances;
	private BigDecimal additionalWithholding;
	private Boolean sitExempted;
	private Boolean sutaExempted;
	private List<EmployeeAllowanceModel> allowanceModels;
	private TaxOverrideType taxOverrideType;
	private BigDecimal taxOverrideValue;

	public EmployeeStateTaxForm() {
	}

	public EmployeeStateTaxForm(Long id, Long employeeId, Long employeeSitId, Long employeeSutaId,
			Long taxFillingStatusId, Long allowanceTypeId, Long noOfAllowances,
			BigDecimal additionalWithholding, Boolean sitExempted, Boolean sutaExempted,
			List<EmployeeAllowanceModel> allowanceModels, TaxOverrideType taxOverrideType,
			BigDecimal taxOverrideValue) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeSitId = employeeSitId;
		this.employeeSutaId = employeeSutaId;
		this.taxFillingStatusId = taxFillingStatusId;
		this.allowanceTypeId = allowanceTypeId;
		this.noOfAllowances = noOfAllowances;
		this.additionalWithholding = additionalWithholding;
		this.sitExempted = sitExempted;
		this.sutaExempted = sutaExempted;
		this.allowanceModels = allowanceModels;
		this.taxOverrideType = taxOverrideType;
		this.taxOverrideValue = taxOverrideValue;
	}

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

	public Long getEmployeeSitId() {
		return employeeSitId;
	}

	public void setEmployeeSitId(Long employeeSitId) {
		this.employeeSitId = employeeSitId;
	}

	public Long getEmployeeSutaId() {
		return employeeSutaId;
	}

	public void setEmployeeSutaId(Long employeeSutaId) {
		this.employeeSutaId = employeeSutaId;
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

	public Boolean getSitExempted() {
		return sitExempted;
	}

	public void setSitExempted(Boolean sitExempted) {
		this.sitExempted = sitExempted;
	}

	public Boolean getSutaExempted() {
		return sutaExempted;
	}

	public void setSutaExempted(Boolean sutaExempted) {
		this.sutaExempted = sutaExempted;
	}

	public List<EmployeeAllowanceModel> getAllowanceModels() {
		return allowanceModels;
	}

	public void setAllowanceModels(List<EmployeeAllowanceModel> allowanceModels) {
		this.allowanceModels = allowanceModels;
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
		builder.append("EmployeeStateTaxForm [id=");
		builder.append(id);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", employeeSitId=");
		builder.append(employeeSitId);
		builder.append(", employeeSutaId=");
		builder.append(employeeSutaId);
		builder.append(", taxFillingStatusId=");
		builder.append(taxFillingStatusId);
		builder.append(", allowanceTypeId=");
		builder.append(allowanceTypeId);
		builder.append(", noOfAllowances=");
		builder.append(noOfAllowances);
		builder.append(", additionalWithholding=");
		builder.append(additionalWithholding);
		builder.append(", sitExempted=");
		builder.append(sitExempted);
		builder.append(", sutaExempted=");
		builder.append(sutaExempted);
		builder.append(", allowanceModels=");
		builder.append(allowanceModels);
		builder.append(", taxOverrideType=");
		builder.append(taxOverrideType);
		builder.append(", taxOverrideValue=");
		builder.append(taxOverrideValue);
		builder.append("]");
		return builder.toString();
	}

}
