package com.epayroll.form.employee;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.epayroll.entity.Employee.Status;

/**
 * @author Rajul Tiwari
 */
public class ContractorEmploymentInfoForm {
	@NotBlank
	private String employeeId;
	// @NotBlank
	// @Email
	private String workEmail;
	// @NotBlank
	// @Pattern(regexp = "\\(?[2-9]\\d\\d\\)?[-?]?[2-9]\\d\\d-?\\d{4}")
	private String workFax;
	// @NotBlank(message = "Phone Number can not be Empty")
	// @Pattern(regexp = "\\(?[2-9]\\d\\d\\)?[-?]?[2-9]\\d\\d-?\\d{4}")
	private String wrkPhone;
	// @Pattern(regexp = "\\d+")
	private String workExt;
	private String employeeAccess;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Past
	private Date dateOfHireing;
	// @NotNull
	// @DateTimeFormat(pattern = "MM/dd/yyyy")
	// @Past
	private Date dateOfTermination;
	private Status status;
	// @NotBlank
	private String streetAddress;
	private Long cityId;
	private Long stateId;
	private Long countyId;
	// @NotBlank
	// @Pattern(regexp = "^\\d{5}(-\\d{4})?$")
	private String zip;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public String getWorkFax() {
		return workFax;
	}

	public void setWorkFax(String workFax) {
		this.workFax = workFax;
	}

	public String getWrkPhone() {
		return wrkPhone;
	}

	public void setWrkPhone(String wrkPhone) {
		this.wrkPhone = wrkPhone;
	}

	public String getWorkExt() {
		return workExt;
	}

	public void setWorkExt(String workExt) {
		this.workExt = workExt;
	}

	public String getEmployeeAccess() {
		return employeeAccess;
	}

	public void setEmployeeAccess(String employeeAccess) {
		this.employeeAccess = employeeAccess;
	}

	public Date getDateOfHireing() {
		return dateOfHireing;
	}

	public void setDateOfHireing(Date dateOfHireing) {
		this.dateOfHireing = dateOfHireing;
	}

	public Date getDateOfTermination() {
		return dateOfTermination;
	}

	public void setDateOfTermination(Date dateOfTermination) {
		this.dateOfTermination = dateOfTermination;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeEmploymentInfoForm [employeeId=");
		builder.append(employeeId);
		builder.append(", workEmail=");
		builder.append(workEmail);
		builder.append(", workFax=");
		builder.append(workFax);
		builder.append(", wrkPhone=");
		builder.append(wrkPhone);
		builder.append(", workExt=");
		builder.append(workExt);
		builder.append(", employeeAccess=");
		builder.append(employeeAccess);
		builder.append(", dateOfHireing=");
		builder.append(dateOfHireing);
		builder.append(", dateOfTermination=");
		builder.append(dateOfTermination);
		builder.append(", status=");
		builder.append(status);
		builder.append(", streetAddress=");
		builder.append(streetAddress);
		builder.append(", cityId=");
		builder.append(cityId);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", countyId=");
		builder.append(countyId);
		builder.append(", zip=");
		builder.append(zip);
		builder.append("]");
		return builder.toString();
	}

}