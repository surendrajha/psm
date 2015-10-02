package com.epayroll.form.employee;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Rajul Tiwari
 */
public class RehireForm {

	private Long id;

	@AssertTrue(message = "Rehire Employee can not be Empty ")
	private Boolean isEmployeeRehire;

	@NotNull(message = "Date Of Rehire can not be Empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	// @Past
	private Date dateOfRehire;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsEmployeeRehire() {
		return isEmployeeRehire;
	}

	public void setIsEmployeeRehire(Boolean isEmployeeRehire) {
		this.isEmployeeRehire = isEmployeeRehire;
	}

	public Date getDateOfRehire() {
		return dateOfRehire;
	}

	public void setDateOfRehire(Date dateOfRehire) {
		this.dateOfRehire = dateOfRehire;
	}
}