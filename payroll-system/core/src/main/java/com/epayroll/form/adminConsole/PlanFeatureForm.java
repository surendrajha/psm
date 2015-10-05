package com.epayroll.form.adminConsole;

/**
 * @author Uma
 */
public class PlanFeatureForm {

	private Long id;
	private Long payrollPlanId;
	private String service;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPayrollPlanId() {
		return payrollPlanId;
	}

	public void setPayrollPlanId(Long payrollPlanId) {
		this.payrollPlanId = payrollPlanId;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlanFeatureForm [id=");
		builder.append(id);
		builder.append(", payrollPlanId=");
		builder.append(payrollPlanId);
		builder.append(", service=");
		builder.append(service);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}