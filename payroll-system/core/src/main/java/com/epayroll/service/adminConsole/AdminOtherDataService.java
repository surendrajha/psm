package com.epayroll.service.adminConsole;

import java.util.List;

import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.FederalStateHoliday;
import com.epayroll.entity.FundCategory;
import com.epayroll.entity.PaymentMode;
import com.epayroll.entity.PayrollPlan;
import com.epayroll.entity.PlanFeature;
import com.epayroll.entity.TransactionBody;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.AllowanceTypeForm;
import com.epayroll.form.adminConsole.FederalAndStateHolidayForm;
import com.epayroll.form.adminConsole.FundCategoryForm;
import com.epayroll.form.adminConsole.PaymentModeForm;
import com.epayroll.form.adminConsole.PayrollPlanForm;
import com.epayroll.form.adminConsole.PlanFeatureForm;
import com.epayroll.form.adminConsole.TransactionBodyForm;
import com.epayroll.form.adminConsole.UsCityForm;
import com.epayroll.form.adminConsole.UsCountyForm;
import com.epayroll.form.adminConsole.UsStateForm;

/**
 * 
 * @author Uma
 * 
 */
public interface AdminOtherDataService {

	void addUsState(UsStateForm usStateForm);

	UsStateForm getUpdateUsStateForm(Long usStateId) throws InstanceNotFoundException;

	void updateUsState(UsStateForm usStateForm) throws InstanceNotFoundException;

	void verifyAnddeleteUsState(Long usStateId) throws InstanceNotFoundException;

	List<UsState> getUsStates();

	void addUsCounty(UsCountyForm usCountyForm) throws InstanceNotFoundException;

	UsCountyForm getUpdateUsCountyForm(Long usCountyId) throws InstanceNotFoundException;

	void updateUsCounty(UsCountyForm usCountyForm) throws InstanceNotFoundException;

	void verifyAnddeleteUsCounty(Long usCountyId) throws InstanceNotFoundException;

	List<UsCounty> getUsCounty();

	void addUsCity(UsCityForm usCityForm) throws InstanceNotFoundException;

	UsCityForm getUpdateUsCityForm(Long usCityId) throws InstanceNotFoundException;

	void updateUsCity(UsCityForm usCityForm) throws InstanceNotFoundException;

	void verifyAnddeleteUsCity(Long usCityId) throws InstanceNotFoundException;

	void addFederalAndStateHoliday(FederalAndStateHolidayForm federalAndStateHolidayForm)
			throws InstanceNotFoundException;

	FederalAndStateHolidayForm getUpdateFederalAndStateHolidayForm(Long federalAndStateHolidayId)
			throws InstanceNotFoundException;

	void updateFederalAndStateHoliday(FederalAndStateHolidayForm federalAndStateHolidayForm)
			throws InstanceNotFoundException;

	void deleteFederalAndStateHoliday(Long federalAndStateHolidayId)
			throws InstanceNotFoundException;

	void addAllowanceType(AllowanceTypeForm allowanceTypeForm);

	AllowanceTypeForm getUpdateAllowanceTypeForm(Long allowanceTypeId)
			throws InstanceNotFoundException;

	void updateAllowanceType(AllowanceTypeForm allowanceTypeForm) throws InstanceNotFoundException;

	void verifyAnddeleteAllowanceType(Long allowanceTypeId) throws InstanceNotFoundException;

	void addPayrollPlan(PayrollPlanForm payrollPlanForm);

	PayrollPlanForm getPayrollPlanForm(Long payrollPlanId) throws InstanceNotFoundException;

	void updatePayrollPlan(PayrollPlanForm payrollPlanForm) throws InstanceNotFoundException;

	void verifyAnddeletePayrollPlan(Long payrollPlanId) throws InstanceNotFoundException;

	void addPlanFeature(PlanFeatureForm planFeatureForm) throws InstanceNotFoundException;

	PlanFeatureForm getPlanFeatureForm(Long planFeatureId) throws InstanceNotFoundException;

	void updatePlanFeature(PlanFeatureForm planFeatureForm) throws InstanceNotFoundException;

	void verifyAnddeletePlanFeature(Long planFeatureId) throws InstanceNotFoundException;

	void addFundCategory(FundCategoryForm fundCategoryForm) throws InstanceNotFoundException;

	FundCategoryForm getFundCategoryForm(Long fundCategoryId) throws InstanceNotFoundException;

	void updateFundCategory(FundCategoryForm fundCategoryForm) throws InstanceNotFoundException;

	void verifyAnddeleteFundCategory(Long fundCategoryId) throws InstanceNotFoundException;

	List<FundCategory> getFundCategories();

	void addPaymentMode(PaymentModeForm paymentModeForm) throws InstanceNotFoundException;

	PaymentModeForm getPaymentModeForm(Long paymentModeId) throws InstanceNotFoundException;

	void updatePaymentMode(PaymentModeForm paymentModeForm) throws InstanceNotFoundException;

	void verifyAnddeletePaymentMode(Long paymentModeId) throws InstanceNotFoundException;

	List<PaymentMode> getPaymentModes();

	void addTransactionBody(TransactionBodyForm transactionBodyForm)
			throws InstanceNotFoundException;

	TransactionBodyForm getTransactionBodyForm(Long transactionBodyFormId)
			throws InstanceNotFoundException;

	void updateTransactionBody(TransactionBodyForm transactionBodyForm)
			throws InstanceNotFoundException;

	void deleteTransactionBody(Long transactionBodyId) throws InstanceNotFoundException;

	List<TransactionBody> getTransactionBodyList();

	List<UsCity> getUsCities();

	List<FederalStateHoliday> getFederalAndStateHolidays();

	List<AllowanceType> getAllowancetypes();

	List<PayrollPlan> getPayrollPlans();

	List<PlanFeature> getPlanFeatures();

}
