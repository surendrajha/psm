package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

/**
 * CompanyDepartment generated by hbm2java
 */
@Entity
@Table(name = "company_department")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@JsonIgnoreProperties({ "company", "employeeDepartmentAllocations" })
public class CompanyDepartment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1525572724610712754L;

	public enum Status {
		ACTIVE("Active"), INACTIVE("InActive");

		private String name;

		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private Long id;
	private Company company;
	@NotBlank(message = "ID can not be Empty")
	private String deptId;
	@NotBlank(message = "Name can not be Empty")
	private String deptName;
	private String description;
	private Status status;
	private Set<EmployeeDepartmentAllocation> employeeDepartmentAllocations = new HashSet<EmployeeDepartmentAllocation>(
			0);

	public CompanyDepartment() {
	}

	public CompanyDepartment(Company company, String deptName) {
		this.company = company;
		this.deptName = deptName;
	}

	public CompanyDepartment(Company company, String deptId, String deptName, String description,
			Status status, Set<EmployeeDepartmentAllocation> employeeDepartmentAllocations) {
		this.company = company;
		this.deptId = deptId;
		this.deptName = deptName;
		this.description = description;
		this.status = status;
		this.employeeDepartmentAllocations = employeeDepartmentAllocations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "dept_id", length = 20)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dept_name", length = 45)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "status", length = 20)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyDepartment", cascade = CascadeType.ALL)
	public Set<EmployeeDepartmentAllocation> getEmployeeDepartmentAllocations() {
		return this.employeeDepartmentAllocations;
	}

	public void setEmployeeDepartmentAllocations(
			Set<EmployeeDepartmentAllocation> employeeDepartmentAllocations) {
		this.employeeDepartmentAllocations = employeeDepartmentAllocations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyDepartment [id=");
		builder.append(id);
		builder.append(", company=");
		builder.append(company.getId());
		builder.append(", deptId=");
		builder.append(deptId);
		builder.append(", deptName=");
		builder.append(deptName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
