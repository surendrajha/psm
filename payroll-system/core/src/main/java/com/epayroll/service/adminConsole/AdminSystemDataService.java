package com.epayroll.service.adminConsole;

import java.text.ParseException;
import java.util.List;

import com.epayroll.entity.AllowanceRate;
import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.CountyTaxRate;
import com.epayroll.entity.DeductionCap;
import com.epayroll.entity.FederalStateTaxRate;
import com.epayroll.entity.FilingStatus;
import com.epayroll.entity.FutaSutaTaxRate;
import com.epayroll.entity.StandardDeductionRate;
import com.epayroll.entity.TaxAuthority;
import com.epayroll.entity.TaxAuthorityDepositCycle;
import com.epayroll.entity.TaxDepositCycle;
import com.epayroll.entity.TaxType;
import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.entity.WorkersCompensationTaxRate;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.AllowanceRateForm;
import com.epayroll.form.adminConsole.CountyTaxForm;
import com.epayroll.form.adminConsole.DeductionCapForm;
import com.epayroll.form.adminConsole.FilingStatusForm;
import com.epayroll.form.adminConsole.FutaForm;
import com.epayroll.form.adminConsole.FutaSutaTaxForm;
import com.epayroll.form.adminConsole.IncomeTaxSlabForm;
import com.epayroll.form.adminConsole.MedicareSocialSecurityForm;
import com.epayroll.form.adminConsole.StandardDeductionRateForm;
import com.epayroll.form.adminConsole.SutaForm;
import com.epayroll.form.adminConsole.TaxAuthorityDepositCycleForm;
import com.epayroll.form.adminConsole.TaxAuthorityForm;
import com.epayroll.form.adminConsole.TaxDepositCycleMasterForm;
import com.epayroll.form.adminConsole.TaxTypeForm;
import com.epayroll.form.adminConsole.WorkersCompensationTaxRateForm;

/**
 * @author Rajul Tiwari
 */
public interface AdminSystemDataService {

	// for TaxDepositCycle

	List<TaxDepositCycle> getTaxDepositCycles();

	Long addTaxDepositCycle(TaxDepositCycleMasterForm taxDepositCycleMasterForm);

	TaxDepositCycleMasterForm getTaxDepositCycleMasterForm(Long taxDepositCycleId)
			throws InstanceNotFoundException;

	void updateTaxDepositCycle(TaxDepositCycleMasterForm taxDepositCycleMasterForm)
			throws InstanceNotFoundException;

	boolean verifyAndDeleteTaxDepositCycle(Long taxDepositCycleId) throws InstanceNotFoundException;

	// for FilingStatus

	List<FilingStatus> getFilingStatusList();

	Long addFilingStatus(FilingStatusForm filingStatusForm);

	FilingStatusForm getFilingStatusForm(Long filingStatusId) throws InstanceNotFoundException;

	void updateFilingStatus(FilingStatusForm filingStatusForm) throws InstanceNotFoundException;

	boolean verifyAndDeleteFilingStatus(Long filingStatusId) throws InstanceNotFoundException;

	// for WorkersCompensationTaxRate

	List<Integer> getYearListForWorkersCompensationTaxRate();

	List<WorkersCompensationTaxRate> getWorkersCompensationTaxRateList(Integer year)
			throws ParseException;

	List<Company> getCompanies();

	List<CompanyWorkerCompensation> getcompanyWorkerCompensations(Long companyId);

	Long addWorkersCompensationTaxRate(WorkersCompensationTaxRateForm workersCompensationTaxRateForm)
			throws InstanceNotFoundException;

	WorkersCompensationTaxRateForm getWorkersCompensationTaxRateForm(Long workComTaxRateId)
			throws InstanceNotFoundException;

	void updateWorkersCompensationTaxRate(
			WorkersCompensationTaxRateForm workersCompensationTaxRateForm)
			throws InstanceNotFoundException;

	boolean verifyAndDeleteWorkersCompensationTaxRate(Long workComTaxRateId)
			throws InstanceNotFoundException;

	List<TaxAuthority> getTaxAuthorityList();

	List<TaxType> getTaxTypeList();

	List<UsState> getUsStateList();

	List<UsCounty> getUsCountyList();

	Long addTaxAuthority(TaxAuthorityForm taxAuthorityForm) throws InstanceNotFoundException;

	Long addTaxType(TaxTypeForm taxTypeForm) throws InstanceNotFoundException;

	TaxAuthorityForm getTaxAuthorityForm(Long id) throws InstanceNotFoundException;

	TaxTypeForm getTaxTypeForm(Long id) throws InstanceNotFoundException;

	void updateTaxAuthority(TaxAuthorityForm taxAuthorityForm) throws InstanceNotFoundException;

	void updateTaxType(TaxTypeForm taxTypeForm) throws InstanceNotFoundException;

	boolean verifyAndDeleteTaxAuthority(Long id) throws InstanceNotFoundException;

	boolean verifyAndDeleteTaxType(Long id) throws InstanceNotFoundException;

	// Federal Allowance Rate

	List<AllowanceRate> getFederalAllowanceRates();

	List<AllowanceType> getFederalAllowanceTypes();

	Long addFederalAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException;

	AllowanceRateForm getFederalAllowanceRateForm(Long allowanceRateId)
			throws InstanceNotFoundException;

	void updateFederalAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException;

	void deleteFederalAllowanceRate(Long allowanceRateId) throws InstanceNotFoundException;

	// Federal Standard Deduction

	List<StandardDeductionRate> getFederalStandardDeductions();

	Long addFederalStandardDeductionRate(StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException;

	StandardDeductionRateForm getFederalStandardDeductionRateForm(Long federalStandardDeductionId)
			throws InstanceNotFoundException;

	void updateFederalStandardDeductionRate(StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException;

	void deleteFederalStandardDeductionRate(Long federalStandardDeductionId)
			throws InstanceNotFoundException;

	// Federal Deduction Cap

	List<DeductionCap> getFederalDeductionCap();

	List<Company> getCompanyList();

	List<CompanyDeduction> getCompanyDeductionList(Long companyId);

	Long addFederalDeductionCap(DeductionCapForm deductionCapForm) throws InstanceNotFoundException;

	DeductionCapForm getFederalDeductionCapForm(Long deductionCapId)
			throws InstanceNotFoundException;

	void updateFederalDeductionCap(DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException;

	void deleteFederalDeductionCap(Long deductionCapId) throws InstanceNotFoundException;

	// Federal State Tax Rate

	List<FederalStateTaxRate> getFederalStateTaxRates(Long filingStatusId, Long taxTypeId);

	Long addFederalStateTaxRate(IncomeTaxSlabForm incomeTaxSlabForm)
			throws InstanceNotFoundException;

	Long getFitTaxTypeId();

	Long getFutaTaxTypeId();

	void updateFederalStateTaxRate(IncomeTaxSlabForm incomeTaxSlabForm)
			throws InstanceNotFoundException;

	IncomeTaxSlabForm getIncomeTaxSlabForm(Long federalStateTaxRateId)
			throws InstanceNotFoundException;

	boolean verifyAndDeleteFederalStateTaxRate(Long federalStateTaxRateId)
			throws InstanceNotFoundException;

	FederalStateTaxRate addUpdateFuta(FutaForm futaForm) throws InstanceNotFoundException;

	MedicareSocialSecurityForm getMedicareSocialSecurityForm();

	void addUpdateMedicareSocialSecurity(MedicareSocialSecurityForm medicareSocialSecurityForm)
			throws InstanceNotFoundException;

	FutaForm getFutaForm();

	List<TaxDepositCycle> getDepositCycles();

	TaxAuthorityDepositCycleForm getTaxAuthorityDepositCycleForm();

	TaxAuthorityDepositCycleForm getTaxAuthorityDepositCycleForm(Long stateId);

	List<TaxAuthorityDepositCycle> getTaxAuthorityDepositCycleList(Long taxAuthorityId);

	TaxAuthorityDepositCycle addTaxAuthorityDepositCycle(
			TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm)
			throws InstanceNotFoundException;

	TaxAuthorityDepositCycleForm getTaxAuthorityDepositCycleUpdateForm(
			Long taxAuthorityDepositCycleId) throws InstanceNotFoundException;

	TaxAuthorityDepositCycle updateTaxAuthorityDepositCycle(
			TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm)
			throws InstanceNotFoundException;

	void deleteTaxAuthorityDepositCycle(Long taxAuthorityDepositCycleId)
			throws InstanceNotFoundException;

	// State Allowance Rate

	List<AllowanceRate> getStateAllowanceRates(Long stateId);

	List<AllowanceType> getStateAllowanceTypes(Long stateId);

	Long addStateAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException;

	AllowanceRateForm getStateAllowanceRateForm(Long allowanceRateId)
			throws InstanceNotFoundException;

	void updateStateAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException;

	void deleteStateAllowanceRate(Long allowanceRateId) throws InstanceNotFoundException;

	// State Standard Deduction

	List<StandardDeductionRate> getStateStandardDeductions(Long stateId);

	Long addStateStandardDeductionRate(StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException;

	StandardDeductionRateForm getStateStandardDeductionRateForm(Long stateStandardDeductionId)
			throws InstanceNotFoundException;

	void updateStateStandardDeductionRate(StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException;

	void deleteStateStandardDeductionRate(Long stateStandardDeductionId)
			throws InstanceNotFoundException;

	// State Deduction Cap
	List<DeductionCap> getStateDeductionCap(Long stateId);

	Long addStateDeductionCap(DeductionCapForm deductionCapForm) throws InstanceNotFoundException;

	DeductionCapForm getStateDeductionCapForm(Long deductionCapId) throws InstanceNotFoundException;

	void updateStateDeductionCap(DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException;

	void deleteStateDeductionCap(Long deductionCapId) throws InstanceNotFoundException;

	Long getSitTaxTypeId(Long usStateId);

	List<Company> getAllCompanies();

	FutaSutaTaxForm getFutaSutaTaxForm();

	FutaSutaTaxForm getFutaSutaTaxForm(Long StateId);

	List<FutaSutaTaxRate> getFutaSutaTaxRates(Long taxTypeId);

	FutaSutaTaxRate addFutaSutaCompanyRate(FutaSutaTaxForm futaSutaTaxForm)
			throws InstanceNotFoundException;

	FutaSutaTaxForm getFutaSutaTaxUpdateForm(Long futaSutaTaxRateId)
			throws InstanceNotFoundException;

	void udateFutaSutaCompanyRate(FutaSutaTaxForm futaSutaTaxForm) throws InstanceNotFoundException;

	void deleteFutaSutaCompanyRate(Long futaSutaTaxRateId) throws InstanceNotFoundException;

	// For testing
	UsState getUsState(long usStateId) throws InstanceNotFoundException;

	//

	Long addCountyTaxRate(CountyTaxForm countyTaxForm, Long usStateId)
			throws InstanceNotFoundException;

	CountyTaxForm getCountyTaxForm(Long countyTaxRateId) throws InstanceNotFoundException;

	void updateCountyTaxRate(CountyTaxForm countyTaxForm) throws InstanceNotFoundException;

	boolean verifyAndDeleteCountyTaxRate(Long countyTaxRateId) throws InstanceNotFoundException;

	List<UsCounty> getUsCountyList(Long usStateIdFromSession);

	List<CountyTaxRate> getCountyTaxRates(Long usStateId);

	SutaForm getSutaForm(Long stateId);

	FederalStateTaxRate addUpdateSuta(SutaForm sutaForm) throws InstanceNotFoundException;
}
