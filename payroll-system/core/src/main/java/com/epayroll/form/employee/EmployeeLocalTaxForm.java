package com.epayroll.form.employee;

/**
 * @author Uma
 */
public class EmployeeLocalTaxForm {
	private Long id;
	private Long employeeId;
	private Long employeeTaxId;
	private Boolean exempted;

	public EmployeeLocalTaxForm() {
	}

	public EmployeeLocalTaxForm(Long id, Long employeeId, Long employeeTaxId, Boolean exempted) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeTaxId = employeeTaxId;
		this.exempted = exempted;
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

	public Long getEmployeeTaxId() {
		return employeeTaxId;
	}

	public void setEmployeeTaxId(Long employeeTaxId) {
		this.employeeTaxId = employeeTaxId;
	}

	public Boolean getExempted() {
		return exempted;
	}

	public void setExempted(Boolean exempted) {
		this.exempted = exempted;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeLocalTaxForm [id=");
		builder.append(id);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", employeeTaxId=");
		builder.append(employeeTaxId);
		builder.append(", exempted=");
		builder.append(exempted);
		builder.append("]");
		return builder.toString();
	}

}
