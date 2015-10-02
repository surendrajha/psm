package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

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
 * ProductPlan generated by hbm2java
 */
@Entity
@Table(name = "payroll_plan")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PayrollPlan implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8215914971439014L;
	private Long id;
	private String planName;
	private String description;
	private Set<PlanFeature> planFeatures = new HashSet<PlanFeature>(0);

	public PayrollPlan() {
	}

	public PayrollPlan(String planName) {
		this.planName = planName;
	}

	public PayrollPlan(String planName, String description, Set<PlanFeature> planFeatures) {
		this.planName = planName;
		this.description = description;
		this.planFeatures = planFeatures;
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

	@Column(name = "plan_name", length = 45, unique = true)
	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payrollPlan")
	public Set<PlanFeature> getPlanFeatures() {
		return planFeatures;
	}

	public void setPlanFeatures(Set<PlanFeature> planFeatures) {
		this.planFeatures = planFeatures;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductPlan [id=");
		builder.append(id);
		builder.append(", planName=");
		builder.append(planName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}