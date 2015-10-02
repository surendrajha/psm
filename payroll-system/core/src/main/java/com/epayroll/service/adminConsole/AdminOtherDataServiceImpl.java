package com.epayroll.service.adminConsole;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.AddressDao;
import com.epayroll.dao.CountyTaxRateDao;
import com.epayroll.dao.DeductionCapDao;
import com.epayroll.dao.FederalStateHolidayDao;
import com.epayroll.dao.TransactionBodyDao;
import com.epayroll.dao.UsCityDao;
import com.epayroll.dao.UsCountyDao;
import com.epayroll.dao.UsStateDao;
import com.epayroll.dao.company.CompanyFeatureDao;
import com.epayroll.dao.company.CompanyTransactionDao;
import com.epayroll.dao.company.CompanyWorkersCompensationDao;
import com.epayroll.dao.company.FundCategoryDao;
import com.epayroll.dao.company.PayrollFrequencyDao;
import com.epayroll.dao.company.StandardDeductionRateDao;
import com.epayroll.dao.company.TaxAuthorityDao;
import com.epayroll.dao.employee.AllowanceRateDao;
import com.epayroll.dao.employee.AllowanceTypeDao;
import com.epayroll.dao.employee.EmployeeAllowanceRecordDao;
import com.epayroll.dao.employee.PaymentModeDao;
import com.epayroll.dao.employee.PayrollPlanDao;
import com.epayroll.dao.employee.PlanFeatureDao;
import com.epayroll.entity.Address;
import com.epayroll.entity.AllowanceRate;
import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.CompanyFeature;
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.CountyTaxRate;
import com.epayroll.entity.DeductionCap;
import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.FederalStateHoliday;
import com.epayroll.entity.FundCategory;
import com.epayroll.entity.FundTransaction;
import com.epayroll.entity.PaymentMode;
import com.epayroll.entity.PayrollPlan;
import com.epayroll.entity.PlanFeature;
import com.epayroll.entity.StandardDeductionRate;
import com.epayroll.entity.TaxAuthority;
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
import com.epayroll.service.employeeAccess.EmployeeAccessLoginServiceImpl;

/**
 * @author Uma
 */
@Service
public class AdminOtherDataServiceImpl implements AdminOtherDataService {

	@Autowired
	private UsStateDao usStateDao;

	@Autowired
	private DeductionCapDao deductionCapDao;

	@Autowired
	private CompanyWorkersCompensationDao companyWorkersCompensationDao;

	@Autowired
	private TaxAuthorityDao taxAuthorityDao;

	@Autowired
	private UsCountyDao usCountyDao;

	@Autowired
	private AllowanceRateDao allowanceRateDao;

	@Autowired
	private UsCityDao usCityDao;

	@Autowired
	private StandardDeductionRateDao standardDeductionRateDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private CountyTaxRateDao countyTaxRateDao;

	@Autowired
	private FederalStateHolidayDao federalStateHolidayDao;

	@Autowired
	private PayrollFrequencyDao payrollFrequencyDao;

	@Autowired
	private AllowanceTypeDao allowanceTypeDao;

	@Autowired
	private EmployeeAllowanceRecordDao employeeAllowanceRecordDao;

	@Autowired
	private PayrollPlanDao payrollPlanDao;

	@Autowired
	private PlanFeatureDao planFeatureDao;

	@Autowired
	private CompanyFeatureDao companyFeatureDao;

	@Autowired
	private FundCategoryDao fundCategoryDao;

	@Autowired
	private CompanyTransactionDao companyTransactionDao;

	@Autowired
	private PaymentModeDao paymentModeDao;

	@Autowired
	private TransactionBodyDao transactionBodyDao;

	private Logger logger = LoggerFactory.getLogger(EmployeeAccessLoginServiceImpl.class);

	@Override
	@Transactional
	public void addUsState(UsStateForm usStateForm) {
		logger.debug(">>addUsState");
		UsState usState = new UsState();
		usState.setStateName(usStateForm.getStateName());
		usStateDao.save(usState);
		logger.debug("addUsState>>");
	}

	@Override
	public UsStateForm getUpdateUsStateForm(Long usStateId) throws InstanceNotFoundException {
		logger.debug(">>getUpdateUpdateUsStateForm");
		UsState usState = usStateDao.find(usStateId);
		UsStateForm usStateForm = new UsStateForm();
		usStateForm.setStateName(usState.getStateName());
		usStateForm.setId(usStateId);
		logger.debug(">>getUpdateUpdateUsStateForm");
		return usStateForm;
	}

	@Override
	@Transactional
	public void updateUsState(UsStateForm usStateForm) throws InstanceNotFoundException {
		logger.debug(">>updateUsState");
		UsState usState = usStateDao.find(usStateForm.getId());
		usState.setStateName(usStateForm.getStateName());
		usStateDao.update(usState);
		logger.debug("updateUsState>>");
	}

	@Override
	@Transactional
	public void verifyAnddeleteUsState(Long usStateId) throws InstanceNotFoundException {
		logger.debug(">>deleteUsState");
		List<DeductionCap> deductionCaps = deductionCapDao.getDeductionCaps(usStateId);
		List<CompanyWorkerCompensation> companyWorkerCompensations = companyWorkersCompensationDao
				.getCompanyWorkersCompensation(usStateId);
		List<TaxAuthority> taxAuthorities = taxAuthorityDao.getStateTaxAuthorities(usStateId);
		List<UsCounty> usCounties = usCountyDao.getCounties(usStateId);
		List<AllowanceRate> allowanceRates = allowanceRateDao.getAllowancesRates(usStateId);

		List<StandardDeductionRate> standardDeductionRates = standardDeductionRateDao
				.getStandardDeductionRates(usStateId);
		List<Address> addresses = addressDao.getAddressList(usStateId);
		List<FederalStateHoliday> federalStateHolidays = federalStateHolidayDao
				.getFederalStateHolidays(usStateId);
		if (deductionCaps.isEmpty() && companyWorkerCompensations.isEmpty()
				&& taxAuthorities.isEmpty() && usCounties.isEmpty() && allowanceRates.isEmpty()
				&& standardDeductionRates.isEmpty() && addresses.isEmpty()
				&& federalStateHolidays.isEmpty()) {
			usStateDao.remove(usStateId);
		}
		logger.debug("deleteUsState>>");
	}

	@Override
	public List<UsState> getUsStates() {
		return usStateDao.getEntities();
	}

	@Override
	@Transactional
	public void addUsCounty(UsCountyForm usCountyForm) throws InstanceNotFoundException {
		logger.debug(">>addUsCounty");
		UsCounty usCounty = new UsCounty();
		usCounty.setCountyName(usCountyForm.getCountyName());
		usCounty.setUsState(usStateDao.find(usCountyForm.getUsStateId()));
		usCountyDao.save(usCounty);
		logger.debug(">>addUsCounty");
	}

	@Override
	public UsCountyForm getUpdateUsCountyForm(Long usCountyId) throws InstanceNotFoundException {
		logger.debug(">>getUpdateUsCountyForm");
		UsCounty usCounty = usCountyDao.find(usCountyId);
		UsCountyForm usCountyForm = new UsCountyForm();
		usCountyForm.setCountyName(usCounty.getCountyName());
		usCountyForm.setUsStateId(usCounty.getUsState().getId());
		usCountyForm.setId(usCountyId);
		logger.debug(">>getUpdateUsCountyForm");
		return usCountyForm;
	}

	@Override
	@Transactional
	public void updateUsCounty(UsCountyForm usCountyForm) throws InstanceNotFoundException {
		logger.debug(">>updateUsCounty");
		UsCounty usCounty = usCountyDao.find(usCountyForm.getId());
		usCounty.setCountyName(usCountyForm.getCountyName());
		usCounty.setUsState(usStateDao.find(usCountyForm.getUsStateId()));
		usCountyDao.update(usCounty);
		logger.debug("updateUsCounty>>");

	}

	@Override
	@Transactional
	public void verifyAnddeleteUsCounty(Long usCountyId) throws InstanceNotFoundException {
		logger.debug(">>deleteUsState");
		List<CountyTaxRate> countyTaxRates = countyTaxRateDao.getCountyTaxRates(usCountyId);
		List<Address> addresses = addressDao.getAddresses(usCountyId);
		if (countyTaxRates.isEmpty() && addresses.isEmpty()) {
			usCountyDao.remove(usCountyId);
		}
		logger.debug("deleteUsState>>");

	}

	@Override
	public List<UsCounty> getUsCounty() {
		return usCountyDao.getEntities();
	}

	@Override
	@Transactional
	public void addUsCity(UsCityForm usCityForm) throws InstanceNotFoundException {
		logger.debug(">>addUsCity");
		UsCity usCity = new UsCity();
		usCity.setCityName(usCityForm.getCityName());
		usCity.setUsState(usStateDao.find(usCityForm.getUsStateId()));
		usCityDao.save(usCity);
		logger.debug(">>addUsCity");

	}

	@Override
	public UsCityForm getUpdateUsCityForm(Long usCityId) throws InstanceNotFoundException {
		logger.debug(">>getUpdateUsCountyForm");
		UsCity usCity = usCityDao.find(usCityId);
		UsCityForm usCityForm = new UsCityForm();
		usCityForm.setCityName(usCity.getCityName());
		usCityForm.setUsStateId(usCity.getUsState().getId());
		usCityForm.setId(usCityId);
		logger.debug(">>getUpdateUsCountyForm");
		return usCityForm;
	}

	@Override
	@Transactional
	public void updateUsCity(UsCityForm usCityForm) throws InstanceNotFoundException {
		logger.debug(">>updateUsCity");
		UsCity usCity = usCityDao.find(usCityForm.getId());
		usCity.setCityName(usCityForm.getCityName());
		usCity.setUsState(usStateDao.find(usCityForm.getUsStateId()));
		usCityDao.update(usCity);
		logger.debug("updateUsCity>>");

	}

	@Override
	@Transactional
	public void verifyAnddeleteUsCity(Long usCityId) throws InstanceNotFoundException {
		logger.debug(">>deleteUsCity");
		List<Address> addresses = addressDao.getAddressesList(usCityId);
		if (addresses.isEmpty()) {
			usCityDao.remove(usCityId);
		}
		logger.debug("deleteUsCity>>");

	}

	@Override
	@Transactional
	public void addFederalAndStateHoliday(FederalAndStateHolidayForm federalAndStateHolidayForm)
			throws InstanceNotFoundException {
		logger.debug(">>addFederalAndStateHoliday");
		FederalStateHoliday federalStateHoliday = new FederalStateHoliday();
		federalStateHoliday.setHolidayDate(federalAndStateHolidayForm.getHolidayDate());
		federalStateHoliday.setHolidayDescription(federalAndStateHolidayForm.getDescription());
		federalStateHoliday.setUsState(usStateDao.find(federalAndStateHolidayForm.getUsStateId()));
		federalStateHolidayDao.save(federalStateHoliday);
		logger.debug("addFederalAndStateHoliday>>");

	}

	@Override
	public FederalAndStateHolidayForm getUpdateFederalAndStateHolidayForm(
			Long federalAndStateHolidayId) throws InstanceNotFoundException {
		logger.debug(">>getUpdateUpdateUsStateForm");
		FederalStateHoliday federalStateHoliday = federalStateHolidayDao
				.find(federalAndStateHolidayId);
		FederalAndStateHolidayForm federalAndStateHolidayForm = new FederalAndStateHolidayForm();
		federalAndStateHolidayForm.setDescription(federalStateHoliday.getHolidayDescription());
		federalAndStateHolidayForm.setId(federalAndStateHolidayId);
		federalAndStateHolidayForm.setHolidayDate(federalStateHoliday.getHolidayDate());
		federalAndStateHolidayForm.setUsStateId(federalStateHoliday.getUsState().getId());
		logger.debug(">>getUpdateUpdateUsStateForm");
		return federalAndStateHolidayForm;
	}

	@Override
	@Transactional
	public void updateFederalAndStateHoliday(FederalAndStateHolidayForm federalAndStateHolidayForm)
			throws InstanceNotFoundException {
		logger.debug(">>updateFederalAndStateHoliday");
		UsState usState = usStateDao.find(federalAndStateHolidayForm.getUsStateId());
		FederalStateHoliday federalStateHoliday = federalStateHolidayDao
				.find(federalAndStateHolidayForm.getId());
		federalStateHoliday.setHolidayDate(federalAndStateHolidayForm.getHolidayDate());
		federalStateHoliday.setHolidayDescription(federalAndStateHolidayForm.getDescription());
		federalStateHoliday.setUsState(usState);
		federalStateHolidayDao.update(federalStateHoliday);
		logger.debug("updateFederalAndStateHoliday>>");

	}

	@Override
	@Transactional
	public void deleteFederalAndStateHoliday(Long federalAndStateHolidayId)
			throws InstanceNotFoundException {
		logger.debug(">>deleteFederalAndStateHoliday");
		federalStateHolidayDao.remove(federalAndStateHolidayId);
		logger.debug("deleteFederalAndStateHoliday>>");

	}

	@Override
	@Transactional
	public void addAllowanceType(AllowanceTypeForm allowanceTypeForm) {
		logger.debug(">>addAllowanceType");
		AllowanceType allowanceType = new AllowanceType();
		allowanceType.setDescription(allowanceTypeForm.getDescription());
		allowanceType.setType(allowanceTypeForm.getType());
		allowanceTypeDao.save(allowanceType);
		logger.debug("addAllowanceType>>");

	}

	@Override
	public AllowanceTypeForm getUpdateAllowanceTypeForm(Long allowanceTypeId)
			throws InstanceNotFoundException {
		logger.debug(">>getUpdateAllowanceTypeForm");
		AllowanceType allowanceType = allowanceTypeDao.find(allowanceTypeId);
		AllowanceTypeForm allowanceTypeForm = new AllowanceTypeForm();
		allowanceTypeForm.setType(allowanceType.getType());
		allowanceTypeForm.setDescription(allowanceType.getDescription());
		allowanceTypeForm.setId(allowanceTypeId);
		logger.debug(">>getUpdateAllowanceTypeForm");
		return allowanceTypeForm;
	}

	@Override
	@Transactional
	public void updateAllowanceType(AllowanceTypeForm allowanceTypeForm)
			throws InstanceNotFoundException {
		logger.debug(">>updateAllowanceType");
		AllowanceType allowanceType = allowanceTypeDao.find(allowanceTypeForm.getId());
		allowanceType.setDescription(allowanceTypeForm.getDescription());
		allowanceType.setType(allowanceTypeForm.getType());
		allowanceTypeDao.update(allowanceType);
		logger.debug("updateAllowanceType>>");

	}

	@Override
	@Transactional
	public void verifyAnddeleteAllowanceType(Long allowanceTypeId) throws InstanceNotFoundException {
		logger.debug(">>deleteAllowanceType");
		List<EmployeeAllowanceRecord> employeeAllowanceRecords = employeeAllowanceRecordDao
				.getEmployeeAllowanceList(allowanceTypeId);
		List<AllowanceRate> allowanceRates = allowanceRateDao
				.getAllowanceRecordList(allowanceTypeId);
		if (employeeAllowanceRecords.isEmpty() && allowanceRates.isEmpty()) {
			allowanceTypeDao.remove(allowanceTypeId);
		}
		logger.debug("deleteAllowanceType>>");

	}

	@Override
	@Transactional
	public void addPayrollPlan(PayrollPlanForm payrollPlanForm) {
		logger.debug(">>addPayrollPlan");
		PayrollPlan payrollPlan = new PayrollPlan();
		payrollPlan.setPlanName(payrollPlanForm.getPlanName());
		payrollPlan.setDescription(payrollPlanForm.getDescription());
		payrollPlanDao.save(payrollPlan);
		logger.debug("addPayrollPlan>>");

	}

	@Override
	public PayrollPlanForm getPayrollPlanForm(Long payrollPlanId) throws InstanceNotFoundException {
		logger.debug(">>getPayrollPlanForm");
		PayrollPlan payrollPlan = payrollPlanDao.find(payrollPlanId);
		PayrollPlanForm payrollPlanForm = new PayrollPlanForm();
		payrollPlanForm.setDescription(payrollPlan.getDescription());
		payrollPlanForm.setPlanName(payrollPlan.getPlanName());
		payrollPlanForm.setId(payrollPlanId);
		logger.debug(">>getPayrollPlanForm");
		return payrollPlanForm;
	}

	@Override
	@Transactional
	public void updatePayrollPlan(PayrollPlanForm payrollPlanForm) throws InstanceNotFoundException {
		logger.debug(">>updatePayrollPlan");
		PayrollPlan payrollPlan = payrollPlanDao.find(payrollPlanForm.getId());
		payrollPlan.setDescription(payrollPlanForm.getDescription());
		payrollPlan.setPlanName(payrollPlanForm.getPlanName());
		payrollPlanDao.update(payrollPlan);
		logger.debug("updatePayrollPlan>>");

	}

	@Override
	@Transactional
	public void verifyAnddeletePayrollPlan(Long payrollPlanId) throws InstanceNotFoundException {
		logger.debug(">>deletePayrollPlan");
		List<PlanFeature> planFeatures = planFeatureDao.getPlanFeatures(payrollPlanId);
		if (planFeatures.isEmpty()) {
			payrollPlanDao.remove(payrollPlanId);
		}
		logger.debug("deletePayrollPlan>>");

	}

	@Override
	@Transactional
	public void addPlanFeature(PlanFeatureForm planFeatureForm) throws InstanceNotFoundException {
		logger.debug(">>addPlanFeature");
		PlanFeature planFeature = new PlanFeature();
		planFeature.setDescription(planFeatureForm.getDescription());
		planFeature.setService(planFeatureForm.getService());
		planFeature.setPayrollPlan(payrollPlanDao.find(planFeatureForm.getPayrollPlanId()));
		planFeatureDao.save(planFeature);
		logger.debug("addPlanFeature>>");

	}

	@Override
	public PlanFeatureForm getPlanFeatureForm(Long planFeatureId) throws InstanceNotFoundException {
		logger.debug(">>getPlanFeatureForm");
		PlanFeature planFeature = planFeatureDao.find(planFeatureId);
		PlanFeatureForm planFeatureForm = new PlanFeatureForm();
		planFeatureForm.setDescription(planFeature.getDescription());
		planFeatureForm.setPayrollPlanId(planFeature.getPayrollPlan().getId());
		planFeatureForm.setId(planFeatureId);
		planFeatureForm.setService(planFeature.getService());
		logger.debug(">>getPlanFeatureForm");
		return planFeatureForm;
	}

	@Override
	@Transactional
	public void updatePlanFeature(PlanFeatureForm planFeatureForm) throws InstanceNotFoundException {
		logger.debug(">>updatePlanFeature");
		PlanFeature planFeature = planFeatureDao.find(planFeatureForm.getId());
		planFeature.setDescription(planFeatureForm.getDescription());
		planFeature.setPayrollPlan(payrollPlanDao.find(planFeatureForm.getPayrollPlanId()));
		planFeature.setService(planFeatureForm.getService());
		planFeatureDao.update(planFeature);
		logger.debug("updatePlanFeature>>");

	}

	@Override
	@Transactional
	public void verifyAnddeletePlanFeature(Long planFeatureId) throws InstanceNotFoundException {
		logger.debug(">>deletePlanFeature");
		List<CompanyFeature> companyFeatures = companyFeatureDao.getCompanyFeatures(planFeatureId);
		if (companyFeatures.isEmpty()) {
			planFeatureDao.remove(planFeatureId);
		}
		logger.debug("deletePlanFeature>>");

	}

	@Override
	@Transactional
	public void addFundCategory(FundCategoryForm fundCategoryForm) throws InstanceNotFoundException {
		logger.debug(">>addFundCategory");
		FundCategory fundCategory = new FundCategory();
		fundCategory.setCategoryName(fundCategoryForm.getCategoryName());
		fundCategory.setDescription(fundCategoryForm.getDescription());
		fundCategoryDao.save(fundCategory);
		logger.debug("addFundCategory>>");

	}

	@Override
	public FundCategoryForm getFundCategoryForm(Long fundCategoryId)
			throws InstanceNotFoundException {
		logger.debug(">>getFundCategoryForm");
		FundCategory fundCategory = fundCategoryDao.find(fundCategoryId);
		FundCategoryForm fundCategoryForm = new FundCategoryForm();
		fundCategoryForm.setCategoryName(fundCategory.getCategoryName());
		fundCategoryForm.setDescription(fundCategory.getDescription());
		fundCategoryForm.setId(fundCategoryId);
		logger.debug(">>getFundCategoryForm");
		return fundCategoryForm;
	}

	@Override
	@Transactional
	public void updateFundCategory(FundCategoryForm fundCategoryForm)
			throws InstanceNotFoundException {
		logger.debug(">>updateFundCategory");
		FundCategory fundCategory = fundCategoryDao.find(fundCategoryForm.getId());
		fundCategory.setCategoryName(fundCategoryForm.getCategoryName());
		fundCategory.setDescription(fundCategoryForm.getDescription());
		fundCategoryDao.update(fundCategory);
		logger.debug("updateFundCategory>>");

	}

	@Override
	@Transactional
	public void verifyAnddeleteFundCategory(Long fundCategoryId) throws InstanceNotFoundException {
		logger.debug(">>deleteFundCategory");

		List<FundTransaction> fundTransactions = companyTransactionDao
				.getCompanyTransactions(fundCategoryId);

		if (fundTransactions.isEmpty()) {
			fundCategoryDao.remove(fundCategoryId);
		}
		logger.debug("deleteFundCategory>>");

	}

	@Override
	public List<FundCategory> getFundCategories() {
		return fundCategoryDao.getEntities();
	}

	@Override
	@Transactional
	public void addPaymentMode(PaymentModeForm paymentModeForm) throws InstanceNotFoundException {
		logger.debug(">>addPaymentMode");
		PaymentMode paymentMode = new PaymentMode();
		paymentMode.setDescription(paymentModeForm.getDescription());
		paymentMode.setMode(paymentModeForm.getMode());
		paymentModeDao.save(paymentMode);
		logger.debug("addPaymentMode>>");

	}

	@Override
	public PaymentModeForm getPaymentModeForm(Long paymentModeId) throws InstanceNotFoundException {
		logger.debug(">>getPaymentModeForm");
		PaymentMode paymentMode = paymentModeDao.find(paymentModeId);
		PaymentModeForm paymentModeForm = new PaymentModeForm();
		paymentModeForm.setDescription(paymentMode.getDescription());
		paymentModeForm.setMode(paymentMode.getMode());
		paymentModeForm.setId(paymentModeId);
		logger.debug(">>getPaymentModeForm");
		return paymentModeForm;
	}

	@Override
	@Transactional
	public void updatePaymentMode(PaymentModeForm paymentModeForm) throws InstanceNotFoundException {
		logger.debug(">>updatePaymentMode");
		PaymentMode paymentMode = paymentModeDao.find(paymentModeForm.getId());
		paymentMode.setDescription(paymentModeForm.getDescription());
		paymentMode.setMode(paymentModeForm.getMode());
		paymentModeDao.update(paymentMode);
		logger.debug("updatePaymentMode>>");

	}

	@Override
	@Transactional
	public void verifyAnddeletePaymentMode(Long paymentModeId) throws InstanceNotFoundException {
		logger.debug(">>deletePaymentMode");

		List<FundTransaction> fundTransactions = companyTransactionDao
				.getCompanyTransaction(paymentModeId);
		if (fundTransactions.isEmpty()) {
			paymentModeDao.remove(paymentModeId);
		}
		logger.debug("deletePaymentMode>>");

	}

	@Override
	public List<PaymentMode> getPaymentModes() {
		return paymentModeDao.getEntities();
	}

	@Override
	@Transactional
	public void addTransactionBody(TransactionBodyForm transactionBodyForm)
			throws InstanceNotFoundException {
		logger.debug(">>addTransactionBody");
		TransactionBody transactionBody = new TransactionBody();
		transactionBody.setName(transactionBodyForm.getName());
		transactionBody.setDescription(transactionBodyForm.getDescription());
		transactionBodyDao.save(transactionBody);
		logger.debug("addTransactionBody>>");

	}

	@Override
	public TransactionBodyForm getTransactionBodyForm(Long transactionBodyId)
			throws InstanceNotFoundException {
		logger.debug(">>getTransactionBodyForm");
		TransactionBody transactionBody = transactionBodyDao.find(transactionBodyId);
		TransactionBodyForm transactionBodyForm = new TransactionBodyForm();
		transactionBodyForm.setDescription(transactionBody.getDescription());
		transactionBodyForm.setName(transactionBody.getName());
		transactionBodyForm.setId(transactionBodyId);
		logger.debug(">>getTransactionBodyForm");
		return transactionBodyForm;
	}

	@Override
	@Transactional
	public void updateTransactionBody(TransactionBodyForm transactionBodyForm)
			throws InstanceNotFoundException {
		logger.debug(">>updateTransactionBody");
		TransactionBody transactionBody = transactionBodyDao.find(transactionBodyForm.getId());
		transactionBody.setDescription(transactionBodyForm.getDescription());
		transactionBody.setName(transactionBodyForm.getName());
		transactionBodyDao.update(transactionBody);
		logger.debug("updateTransactionBody>>");

	}

	@Override
	@Transactional
	public void deleteTransactionBody(Long transactionBodyId) throws InstanceNotFoundException {
		logger.debug(">>deleteTransactionBody");
		transactionBodyDao.remove(transactionBodyId);
		logger.debug("deleteTransactionBody>>");

	}

	@Override
	public List<TransactionBody> getTransactionBodyList() {
		return transactionBodyDao.getEntities();
	}

	@Override
	public List<UsCity> getUsCities() {
		return usCityDao.getEntities();
	}

	@Override
	public List<FederalStateHoliday> getFederalAndStateHolidays() {
		return federalStateHolidayDao.getEntities();
	}

	@Override
	public List<AllowanceType> getAllowancetypes() {
		return allowanceTypeDao.getEntities();
	}

	@Override
	public List<PayrollPlan> getPayrollPlans() {
		return payrollPlanDao.getEntities();
	}

	@Override
	public List<PlanFeature> getPlanFeatures() {
		return planFeatureDao.getEntities();
	}

}
