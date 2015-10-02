package com.epayroll.form.employee;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.entity.Earning;
import com.epayroll.entity.EarningCategory;
import com.epayroll.entity.EmployeeEarning.EarningValueType;

/**
 * @author Uma
 */
public class EmployeeEarningForm {
	private Long id;
	private Long employeeId;
	private Long employeeEarningId;
	private List<EarningCategory> earningCategory;
	private List<Earning> earnings;
	private EarningValueType earningValueType;
	private BigDecimal value;

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

	public Long getEmployeeEarningId() {
		return employeeEarningId;
	}

	public void setEmployeeEarningId(Long employeeEarningId) {
		this.employeeEarningId = employeeEarningId;
	}

	public List<EarningCategory> getEarningCategory() {
		return earningCategory;
	}

	public void setEarningCategory(List<EarningCategory> earningCategory) {
		this.earningCategory = earningCategory;
	}

	public List<Earning> getEarnings() {
		return earnings;
	}

	public void setEarnings(List<Earning> earnings) {
		this.earnings = earnings;
	}

	public EarningValueType getEarningValueType() {
		return earningValueType;
	}

	public void setEarningValueType(EarningValueType earningValueType) {
		this.earningValueType = earningValueType;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeEarningForm [id=");
		builder.append(id);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", employeeEarningId=");
		builder.append(employeeEarningId);
		builder.append(", earningCategory=");
		builder.append(earningCategory);
		builder.append(", earnings=");
		builder.append(earnings);
		builder.append(", earningValueType=");
		builder.append(earningValueType);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
