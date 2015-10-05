package com.epayroll.form.employee;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class EmployeeDeptAllocation {

	private Long id;
	private Long deptId;
	@NotNull
	@NumberFormat(style = Style.PERCENT)
	private BigDecimal allocationPercentage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public BigDecimal getAllocationPercentage() {
		return allocationPercentage;
	}

	public void setAllocationPercentage(BigDecimal allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}

}