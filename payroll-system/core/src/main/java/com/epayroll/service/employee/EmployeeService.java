package com.epayroll.service.employee;

import java.util.List;

import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Access;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeeDeduction;
import com.epayroll.entity.EmployeeDeduction.DeductionType;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;
import com.epayroll.entity.EmployeeEarning;
import com.epayroll.entity.EmployeeEarning.EarningValueType;
import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;
import com.epayroll.entity.EmployeeSection;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.entity.EmploymentHistory;
import com.epayroll.entity.FilingStatus;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.ContractorEmploymentInfoForm;
import com.epayroll.form.employee.ContractorPersonalInfoForm;
import com.epayroll.form.employee.ContractorSearchForm;
import com.epayroll.form.employee.EmployeeAccessForm;
import com.epayroll.form.employee.EmployeeDeductionForm;
import com.epayroll.form.employee.EmployeeEarningForm;
import com.epayroll.form.employee.EmployeeEmploymentInfoForm;
import com.epayroll.form.employee.EmployeeFederalTaxForm;
import com.epayroll.form.employee.EmployeeLocalTaxForm;
import com.epayroll.form.employee.EmployeePersonalInfoForm;
import com.epayroll.form.employee.EmployeeSearchForm;
import com.epayroll.form.employee.EmployeeSectionForm;
import com.epayroll.form.employee.EmployeeStateTaxForm;
import com.epayroll.form.employee.RehireForm;
import com.epayroll.form.employee.TerminateForm;

/**
 * @author Rajul Tiwari
 */
public interface EmployeeService {
	List<Object[]> getEmployeeList(Long companyId, Type employee);

	List<UsState> getUsStates();

	List<String> getUsCities();

	List<UsCity> getUsCityList();

	List<UsState> getUsStateList();

	List<UsCounty> getUsCountyList();

	List<CompanyWorkerCompensation> getcompanyWorkerCompensationList();

	List<Object[]> getSearchedEmployee(EmployeeSearchForm employeeSearchForm, Long companyId);

	List<Object[]> getSearchedContractor(ContractorSearchForm constractorSearchForm, Long companyId);

	void updateStatusTerminate(TerminateForm terminateForm, Employee employee);

	Employee getEmployee(Long employeeId) throws InstanceNotFoundException;

	void updateStatusRehire(RehireForm rehireForm, Employee employee);

	// For Employee

	EmployeePersonalInfoForm getEmployeePersonalInfoForm(Employee employee);

	EmployeeEmploymentInfoForm getEmployeeEmploymentInfoForm(Employee employee);

	void updateEmployeePersonalInfo(EmployeePersonalInfoForm employeePersonalInfoForm,
			Employee employee) throws InstanceNotFoundException;

	void updateEmployeeEmploymentInfo(EmployeeEmploymentInfoForm employeeEmploymentInfoForm,
			Employee employee) throws InstanceNotFoundException;

	// For Contractor

	ContractorPersonalInfoForm getContractorPersonalInfoForm(Employee employee);

	ContractorEmploymentInfoForm getContractorEmploymentInfoForm(Employee employee);

	void updateContractorPersonalInfo(ContractorPersonalInfoForm contractorPersonalInfoForm,
			Employee employee) throws InstanceNotFoundException;

	void updateContractorEmploymentInfo(ContractorEmploymentInfoForm contractorEmploymentInfoForm,
			Employee employee) throws InstanceNotFoundException;

	//

	List<EmploymentHistory> getEmploymentHistory(Long id);

	String getEmployeePassword();

	List<EmployeeSection> getEmployeeSectionList(Long companyId);

	List<EmployeeSection> getEmployeeSections();

	EmployeeAccessForm getEmployeeAccessForm(Access employeeAccess, Company company,
			Employee employee);

	EmployeeSectionForm getEmployeeSectionForm(Company company);

	void addEmployeeSections(EmployeeSectionForm employeeSectionForm, Long companyIn);

	EmployeeEarningForm getEmployeeEarningForm(Employee employee);

	EmployeeDeductionForm getEmployeeDeductionForm(Employee employee);

	EmployeeEarning getEmployeeEearnings(Long employeeEarningId) throws InstanceNotFoundException;

	EmployeeEarningForm getEmployeeEarningUpdateForm(EmployeeEarning employeeEarning);

	EmployeeDeduction getEmployeeDeductions(Long id) throws InstanceNotFoundException;

	EmployeeDeductionForm getEmployeeDeductionUpdateForm(EmployeeDeduction employeeDeduction);

	void updateEmployeeEarnings(EmployeeEarningForm employeeEarningForm);

	void updateEmployeeDeductions(EmployeeDeductionForm employeeDeductionForm);

	EarningValueType[] getEarningValueType();

	DeductionValueType[] getDeductionValueType();

	void updateEmployeeFederalTax(EmployeeFederalTaxForm employeeFederalTaxForm)
			throws InstanceNotFoundException;

	void updateEmployeeStateTax(EmployeeStateTaxForm employeeStateTaxForm)
			throws InstanceNotFoundException;

	void updateEmployeeLocalTax(EmployeeLocalTaxForm employeeLocalTaxForm)
			throws InstanceNotFoundException;

	List<FilingStatus> getFilingStatusList();

	TaxOverrideType[] getTaxOverrideType();

	List<String> getExemptedList();

	List<EmployeeTax> addEmployeeFederalTax(EmployeeFederalTaxForm employeeFederalTaxForm,
			Long employeeId) throws InstanceNotFoundException;

	List<EmployeeTax> addEmployeeStateTax(EmployeeStateTaxForm employeeStateTaxForm)
			throws InstanceNotFoundException;

	List<EmployeeTax> addEmployeeLocalTax(EmployeeLocalTaxForm employeeLocalTaxForm, Long employeeId)
			throws InstanceNotFoundException;

	List<AllowanceType> getallowanceTypes();

	EmployeeFederalTaxForm getFitForm(Long employeeId);

	EmployeeLocalTaxForm getLocalForm(Long employeeTaxId) throws InstanceNotFoundException;

	EmployeeStateTaxForm getStateTaxForm(Long employeeId);

	// add employee
	Long addEmployeePersonalDetail(EmployeePersonalInfoForm employeePersonalInfoForm,
			Company company) throws InstanceNotFoundException;

	void updateEmployeeEmploymentInfo(Long employeeId,
			EmployeeEmploymentInfoForm employeeEmploymentInfoForm) throws InstanceNotFoundException;

	// add contractor
	Long addContractorPersonalDetail(ContractorPersonalInfoForm contractorPersonalInfoForm,
			Company company) throws InstanceNotFoundException;

	void updateContractorEmploymentInfo(Long employeeId,
			ContractorEmploymentInfoForm contractorEmploymentInfoForm)
			throws InstanceNotFoundException;

	void createTask(Long employeeId, String url, User user) throws InstanceNotFoundException;

	Long getEmployeeIdFromSystemTask(Long taskId) throws InstanceNotFoundException;

	void updateTask(Long taskId, String url) throws InstanceNotFoundException;

	DeductionType[] getDeductionType();

	void addEmployeeEarnings(EmployeeEarningForm employeeEarningForm, Company company,
			Long employeeId) throws InstanceNotFoundException;

	void addEmployeeDeductions(EmployeeDeductionForm employeeDeductionForm, Company company,
			Long employeeId) throws InstanceNotFoundException;

	// Verify and Delete Employee
	Boolean verifyAndDeleteEmployee(Long employeeId) throws InstanceNotFoundException;

	void setEmployeeInactivate(Long employeeId) throws InstanceNotFoundException;
}
