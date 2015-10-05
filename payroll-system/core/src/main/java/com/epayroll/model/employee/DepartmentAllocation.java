package com.epayroll.model.employee;

import java.math.BigDecimal;

public class DepartmentAllocation {

	private Long id;
	private Long deptId;
	private BigDecimal allocationPercentage;
	private String departmentCode;
	private String departmentDesciption;

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

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentDesciption() {
		return departmentDesciption;
	}

	public void setDepartmentDesciption(String departmentDesciption) {
		this.departmentDesciption = departmentDesciption;
	}

}
