package com.epayroll.form.employee;

import java.util.Date;

/**
 * @author Rajul Tiwari
 */
public class TerminateForm {

	private Long id;

	// @AssertTrue(message = "Terminate Employee can not be Empty  ")
	private Boolean isEmployeeTerminate;

	// @NotNull(message = "Date Of Termination can not be Empty")
	// @DateTimeFormat(pattern = "MM/dd/yyyy")
	// @Past
	private Date dateOfTermination;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsEmployeeTerminate() {
		return isEmployeeTerminate;
	}

	public void setIsEmployeeTerminate(Boolean isEmployeeTerminate) {
		this.isEmployeeTerminate = isEmployeeTerminate;
	}

	public Date getDateOfTermination() {
		return dateOfTermination;
	}

	public void setDateOfTermination(Date date) {
		this.dateOfTermination = date;
	}

}
