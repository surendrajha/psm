package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AllowanceType generated by hbm2java
 */
@Entity
@Table(name = "allowance_type")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class AllowanceType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4760520767322429147L;
	private Long id;
	private String type;
	private String description;
	private Set<EmployeeAllowanceRecord> employeeAllowanceRecords = new HashSet<EmployeeAllowanceRecord>(
			0);
	private Set<AllowanceRate> allowanceRates = new HashSet<AllowanceRate>(0);

	public AllowanceType() {
	}

	public AllowanceType(String type, String description,
			Set<EmployeeAllowanceRecord> employeeAllowanceRecords, Set<AllowanceRate> allowanceRates) {
		this.type = type;
		this.description = description;
		this.employeeAllowanceRecords = employeeAllowanceRecords;
		this.allowanceRates = allowanceRates;
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

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "allowanceType")
	public Set<EmployeeAllowanceRecord> getEmployeeAllowanceRecords() {
		return this.employeeAllowanceRecords;
	}

	public void setEmployeeAllowanceRecords(Set<EmployeeAllowanceRecord> employeeAllowanceRecords) {
		this.employeeAllowanceRecords = employeeAllowanceRecords;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "allowanceType")
	public Set<AllowanceRate> getAllowanceRates() {
		return this.allowanceRates;
	}

	public void setAllowanceRates(Set<AllowanceRate> allowanceRates) {
		this.allowanceRates = allowanceRates;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AllowanceType [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
