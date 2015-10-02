package com.epayroll.model.employee;

/**
 * @author Uma
 */
public class EmployeeAllowanceModel {
	private Long allowanceTypeId;
	private String allowanceName;
	private Long noOfAllowances;

	public EmployeeAllowanceModel() {
	}

	public EmployeeAllowanceModel(Long allowanceTypeId, String allowanceName, Long noOfAllowances) {
		super();
		this.allowanceTypeId = allowanceTypeId;
		this.allowanceName = allowanceName;
		this.noOfAllowances = noOfAllowances;
	}

	public Long getAllowanceTypeId() {
		return allowanceTypeId;
	}

	public void setAllowanceTypeId(Long allowanceTypeId) {
		this.allowanceTypeId = allowanceTypeId;
	}

	public String getAllowanceName() {
		return allowanceName;
	}

	public void setAllowanceName(String allowanceName) {
		this.allowanceName = allowanceName;
	}

	public Long getNoOfAllowances() {
		return noOfAllowances;
	}

	public void setNoOfAllowances(Long noOfAllowances) {
		this.noOfAllowances = noOfAllowances;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeAllowanceModel [allowanceTypeId=");
		builder.append(allowanceTypeId);
		builder.append(", allowanceName=");
		builder.append(allowanceName);
		builder.append(", noOfAllowances=");
		builder.append(noOfAllowances);
		builder.append("]");
		return builder.toString();
	}

}
