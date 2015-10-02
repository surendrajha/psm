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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * AddressType generated by hbm2java
 */
@Entity
@Table(name = "employee_section")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmployeeSection implements java.io.Serializable {

	private static final long serialVersionUID = 183511894327391632L;
	private Long id;
	private String sectionName;
	private String description;
	private Set<Company> companies = new HashSet<Company>(0);

	public EmployeeSection() {
	}

	public EmployeeSection(String sectionName) {
		this.sectionName = sectionName;
	}

	public EmployeeSection(Long id, String sectionName, String description, Set<Company> companies) {
		super();
		this.id = id;
		this.sectionName = sectionName;
		this.description = description;
		this.companies = companies;
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

	@Column(name = "section_name", length = 50)
	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "company_employee_section_map", joinColumns = { @JoinColumn(name = "employee_section_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "company_id", updatable = false) })
	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeSection [id=");
		builder.append(id);
		builder.append(", sectionName=");
		builder.append(sectionName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
