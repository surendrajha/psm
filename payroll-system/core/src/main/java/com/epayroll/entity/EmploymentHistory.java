package com.epayroll.entity;

// Generated Dec 22, 2012 3:36:44 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EmploymentHistory generated by hbm2java
 */
@Entity
@Table(name = "employment_history")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EmploymentHistory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8947197331742535636L;
	private Long id;
	private Employee employee;
	private Date hireDate;
	private Date terminationDate;

	public EmploymentHistory() {
	}

	public EmploymentHistory(Employee employee) {
		this.employee = employee;
	}

	public EmploymentHistory(Employee employee, Date hireDate, Date terminationDate) {
		this.employee = employee;
		this.hireDate = hireDate;
		this.terminationDate = terminationDate;
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
	@JoinColumn(name = "employee_id")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hire_date", length = 10)
	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "termination_date", length = 10)
	public Date getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmploymentHistory [id=");
		builder.append(id);
		builder.append(", employee=");
		builder.append(employee.getId());
		builder.append(", hireDate=");
		builder.append(hireDate);
		builder.append(", terminationDate=");
		builder.append(terminationDate);
		builder.append("]");
		return builder.toString();
	}

}
