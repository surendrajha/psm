package com.epayroll.service.company;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyBankInfo;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.CompanyDepartment;
import com.epayroll.entity.CompanyDepartment.Status;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.entity.CompanyTax;
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.Deduction;
import com.epayroll.entity.DeductionCategory;
import com.epayroll.entity.Earning;
import com.epayroll.entity.EarningCategory;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollFrequency.PayFrequencyType;
import com.epayroll.entity.PayrollSchedule;
import com.epayroll.entity.SystemMessage;
import com.epayroll.entity.TaxDepositCycle;
import com.epayroll.entity.TaxType;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsState;
import com.epayroll.entity.User;
import com.epayroll.entity.WorkersCompensationTaxRate;
import com.epayroll.entity.WorkersCompensationTaxRateView;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.AuthorizedSignatoryContactForm;
import com.epayroll.form.company.BillingContactForm;
import com.epayroll.form.company.BusinessInfoForm;
import com.epayroll.form.company.CompanyDeductionForm;
import com.epayroll.form.company.CompanyEarningForm;
import com.epayroll.form.company.CompanyPayrollFrequencyForm;
import com.epayroll.form.company.CompanyStateLocalTaxFrom;
import com.epayroll.form.company.CompanyTaxForm;
import com.epayroll.form.company.CompanyWorkersCompensationForm;
import com.epayroll.form.company.LegalInfoForm;
import com.epayroll.form.company.PrimaryContactForm;
import com.epayroll.form.company.ShippingInfoForm;
import com.epayroll.form.employeeAccess.CompanyForm;

public interface CompanyService {

	/*
	 * get the Legal information of the company
	 */
	public LegalInfoForm getLegalInfoForm(Company company);

	void updateLegalInfo(Company company, LegalInfoForm legalInfoForm)
			throws InstanceNotFoundException;

	/*
	 * update and get the business information of the company
	 */

	public BusinessInfoForm getBusinessInfoForm(Company company);

	public Company addOrUpdateBusinessInfo(Company companyFromSession,
			BusinessInfoForm businessInfoForm) throws InstanceNotFoundException;

	/*
	 * update and get the shipping information of the company
	 */

	public Company addOrUpdateShippingInfo(Company company, ShippingInfoForm shippingInfoForm)
			throws InstanceNotFoundException;

	public ShippingInfoForm getShippingInfoForm(Company company);

	/*
	 * update and get the Authorized Signatory Contact information of the
	 * company
	 */

	public Company addOrUpdateAuthorizedSignatoryContact(Company company,
			AuthorizedSignatoryContactForm authorizedSignatoryContactForm)
			throws InstanceNotFoundException;

	public AuthorizedSignatoryContactForm getAuthorizedSignatoryContactForm(Company company);

	/*
	 * update and get the Primary Contact information of the company
	 */

	public Company addOrUpdatePrimaryContact(Company company, PrimaryContactForm primaryContactForm)
			throws InstanceNotFoundException;

	public PrimaryContactForm getPrimaryContactForm(Company company);

	/*
	 * update and get the Billing Contact information of the company
	 */

	public Company addOrUpdateBillingContact(Company company, BillingContactForm billingContactForm)
			throws InstanceNotFoundException;

	public BillingContactForm getBillingContactForm(Company company);

	/*
	 * Get payFrequency List
	 */
	List<PayrollFrequency> getPayrollFrequencies(Long companyId);

	public List<String> getholidayCheckDateOption();

	public Date getEndDate(Date periodStartDate, PayFrequencyType payFrequencyType)
			throws InstanceNotFoundException;

	public PayrollSchedule restorePayrollSchedule(Long payrollScheduleId)
			throws InstanceNotFoundException;

	public CompanyPayrollFrequencyForm getCompanyPayrollFrequencyForm(
			PayrollFrequency payrollFrequency);

	// public List<PayrollSchedule> getPayrollScheduleList(Long companyId);

	public List<PayrollSchedule> getPreviousPayrollScheduleList(Date perioStartDate,
			Date periodEndDate, Long companyId);

	/*
	 * Add New Payroll Frequency
	 */
	Long addPayrollFrequency(Company company, PayrollFrequency payrollFrequency)
			throws InstanceNotFoundException;

	/*
	 * Get Payroll Schedule List
	 */
	List<PayrollSchedule> getPayrollSchedules(Long payrollFrequencyId);

	/*
	 * getpayrollFrequencies
	 */
	public PayrollFrequency getPayrollFrequency(Long payrollFrequencyId)
			throws InstanceNotFoundException;

	/*
	 * updatepayrollFrequencies
	 */
	public PayrollFrequency updatePayrollFrequency(
			CompanyPayrollFrequencyForm companyPayrollFrequencyForm)
			throws InstanceNotFoundException;

	/*
	 * deletepayrollFrequencies&schedule
	 */
	public boolean deletePayrollFrequency(Long payrollFrequencyId) throws InstanceNotFoundException;

	public PayrollSchedule deletePayrollSchedule(Long id) throws InstanceNotFoundException;

	/*
	 * show workers compensation List
	 */
	List<CompanyWorkerCompensation> getWorkersCompansations(Long companyId);

	/*
	 * Get Us state List
	 */
	public List<UsState> getUsStates();

	/*
	 * Add WorkersCompensation
	 */
	public Long addWorkersCompensation(Company company,
			CompanyWorkersCompensationForm companyWorkersCompensationForm);

	/*
	 * Get CompanyWorkersCompensationForm
	 */
	public CompanyWorkersCompensationForm getWorkersCompensationForm(Company company,
			Long companyWorkersCompensationId);

	/*
	 * update company workers compensation
	 */
	public CompanyWorkerCompensation updateWorkersCompensation(Company company,
			CompanyWorkersCompensationForm companyWorkersCompensationForm)
			throws InstanceNotFoundException;

	/*
	 * delete workers compensation
	 */
	public boolean deleteWorkersCompensation(Long id) throws InstanceNotFoundException;

	public void removeCompany(Company company) throws InstanceNotFoundException;

	public Company findCompany(Long id) throws InstanceNotFoundException;

	// Company Bank Info
	List<CompanyBankInfo> getCompanyBankInfo(Company company);

	public void addBankInfo(CompanyBankInfo companyBankInfo, Company company);

	// Company Department

	public CompanyDepartment addDepartment(CompanyDepartment companyDepartment);

	public List<CompanyDepartment> getCompanyDepartments(Company company);

	public CompanyDepartment updateDepartment(CompanyDepartment companyDepartment);

	public void deleteDepartment(CompanyDepartment companyDepartment)
			throws InstanceNotFoundException;

	CompanyDepartment findDepartment(Long companyDepartmentId) throws InstanceNotFoundException;

	public void updateDepartmentStatus(Long id, Status status) throws InstanceNotFoundException;

	Boolean verifyAndDeleteDepartment(Long id) throws InstanceNotFoundException;

	// Company Tax Info

	public void updateTaxDepositCycle(CompanyTaxForm companyTaxForm)
			throws InstanceNotFoundException;

	public List<CompanyTax> getCompanyFederalTaxList(Long companyId);

	public List<CompanyTax> getCompanyStateTaxList(Long companyId);

	public List<CompanyTax> getCompanyLocalTaxList(Long companyId);

	public void updateCompanyTax(CompanyTaxForm companyTaxForm, Company company)
			throws InstanceNotFoundException;

	CompanyTax findTax(Long taxId) throws InstanceNotFoundException;

	public List<CompanyTax> getCompanyTaxes(Company company);

	CompanyTaxForm getCompanyTaxForm(Long companyTaxId) throws InstanceNotFoundException;

	// Company Earning
	List<Earning> getEarnings(Long earningCategoryId) throws InstanceNotFoundException;

	public List<CompanyEarning> getCompanyEarnings(Company company);

	CompanyEarningForm getCompanyEarningForm(Long id) throws InstanceNotFoundException;

	List<EarningCategory> getEarningCategories();

	public CompanyEarning addCompanyEarning(Company company, CompanyEarningForm companyEarningForm)
			throws InstanceNotFoundException;

	public void updateCompanyEarningDisplayName(CompanyEarningForm companyEarningForm)
			throws InstanceNotFoundException;

	Boolean verifyAndDeleteCompanyEarning(Long id) throws InstanceNotFoundException;

	// Company Deduction
	public List<CompanyDeduction> getCompanyDeductions(Company company);

	List<Deduction> getDeductions(Long deductionCategoryId) throws InstanceNotFoundException;

	CompanyDeductionForm getCompanyDeductionForm(Long id) throws InstanceNotFoundException;

	List<DeductionCategory> getDeductionCategories();

	public CompanyDeduction addCompanyDeduction(Company company,
			CompanyDeductionForm companyDeductionForm) throws InstanceNotFoundException;

	public CompanyDeduction updateCompanyDeductionDisplayName(
			CompanyDeductionForm companyDeductionForm) throws InstanceNotFoundException;

	Boolean verifyAndDeleteCompanyDeduction(Long id) throws InstanceNotFoundException;

	// Company User

	public User getCompanyUser(Long companyUserId) throws InstanceNotFoundException;

	Set<SystemMessage> getMessages() throws InstanceNotFoundException;

	//
	public List<CompanyWorkerCompensation> getWorkersCompensationList(Long companyId);

	public WorkersCompensationTaxRate getWorkersCompensationRateList(Long companyId);

	public List<WorkersCompensationTaxRateView> getWorkersCompensationRate(Long companyId);

	public Company addOrUpdateLegalInfo(LegalInfoForm legalInfoForm)
			throws InstanceNotFoundException;

	// for Admin Console

	public List<Company> getCompanyList();

	public List<UsCity> getUsCities();

	public List<Object[]> getSearchedCompanyList(CompanyForm companyForm);

	public boolean verificationForDeleteCompany(Long companyId) throws InstanceNotFoundException;

	public void deleteCompany(Long companyId) throws InstanceNotFoundException;

	public void deactivateCompany(Long companyId);

	public void reactivateCompany(Long companyId);

	public CompanyBankInfo getBankInfo(Long companyBankInfoId) throws InstanceNotFoundException;

	public void updateCompanyBankInfo(CompanyBankInfo companyBankInfo);

	// add Company Tax
	public TaxType getFutaTaxType(Long companyId);

	List<TaxDepositCycle> getDepositCycle();

	public CompanyStateLocalTaxFrom getStateTaxForm(Long companyId);

	public CompanyStateLocalTaxFrom getLocalTaxForm(Long companyId);

	void addFederalTax(CompanyTaxForm companyTaxForm) throws InstanceNotFoundException;

	CompanyTaxForm getFederalTaxForm(Long companyId);

	List<CompanyTax> addStateTax(CompanyStateLocalTaxFrom companyStateLocalTaxFrom)
			throws InstanceNotFoundException;

	List<CompanyTax> addLocalTax(CompanyStateLocalTaxFrom companyStateLocalTaxFrom)
			throws InstanceNotFoundException;

	Deduction getDeduction(Long deductionId) throws InstanceNotFoundException;

	public Long getPayrollScheduleRowCount(Long companyId);

	public List<PayrollSchedule> getPagedUserList(int startPage, int pageSize, Long companyId);

	// Pay frequency Type
	public List<PayFrequencyType> getPayFrequencyType(Long companyId);

}