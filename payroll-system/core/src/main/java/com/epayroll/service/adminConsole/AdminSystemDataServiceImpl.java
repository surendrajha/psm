package com.epayroll.service.adminConsole;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.constant.company.AddressType;
import com.epayroll.dao.AddressTypeDao;
import com.epayroll.dao.CountyTaxRateDao;
import com.epayroll.dao.DeductionCapDao;
import com.epayroll.dao.FederalStateTaxRateDao;
import com.epayroll.dao.FutaSutaTaxRateDao;
import com.epayroll.dao.TaxAuthorityDepositCycleDao;
import com.epayroll.dao.UsCityDao;
import com.epayroll.dao.UsCountyDao;
import com.epayroll.dao.UsStateDao;
import com.epayroll.dao.WorkersCompensationTaxRateDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.CompanyDeductionDao;
import com.epayroll.dao.company.CompanyWorkersCompensationDao;
import com.epayroll.dao.company.StandardDeductionRateDao;
import com.epayroll.dao.company.TaxAuthorityDao;
import com.epayroll.dao.company.TaxDepositCycleDao;
import com.epayroll.dao.employee.AllowanceRateDao;
import com.epayroll.dao.employee.AllowanceTypeDao;
import com.epayroll.dao.employee.FilingStatusDao;
import com.epayroll.dao.employee.TaxTypeDao;
import com.epayroll.entity.Address;
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
import com.epayroll.model.adminConsole.MedicareSocialSecurityModel;

/**
 * @author Rajul Tiwari
 */
@Service
public class AdminSystemDataServiceImpl implements AdminSystemDataService {
	private Logger logger = LoggerFactory.getLogger(AdminSystemDataServiceImpl.class);

	@Autowired
	private TaxDepositCycleDao taxDepositCycleDao;

	@Autowired
	private FilingStatusDao filingStatusDao;

	@Autowired
	private WorkersCompensationTaxRateDao workersCompensationTaxRateDao;

	@Autowired
	private CompanyWorkersCompensationDao companyWorkersCompensationDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private TaxTypeDao taxTypeDao;

	@Autowired
	private TaxAuthorityDao taxAuthorityDao;

	@Autowired
	private UsCountyDao usCountyDao;

	@Autowired
	private UsStateDao usStateDao;

	@Autowired
	private UsCityDao usCityDao;

	@Autowired
	private AddressTypeDao addressTypeDao;

	@Autowired
	private AllowanceRateDao allowanceRateDao;

	@Autowired
	private AllowanceTypeDao allowanceTypeDao;

	@Autowired
	private StandardDeductionRateDao standardDeductionRateDao;

	@Autowired
	private DeductionCapDao deductionCapDao;

	@Autowired
	private CompanyDeductionDao companyDeductionDao;

	@Autowired
	private FederalStateTaxRateDao federalStateTaxRateDao;

	@Autowired
	private TaxAuthorityDepositCycleDao taxAuthorityDepositCycleDao;

	@Autowired
	private FutaSutaTaxRateDao futaSutaTaxRateDao;

	@Autowired
	private CountyTaxRateDao countyTaxRateDao;

	@Override
	public List<TaxDepositCycle> getTaxDepositCycles() {
		logger.debug(">> getTaxDepositCycles");
		List<TaxDepositCycle> taxDepositCycleList = taxDepositCycleDao.getEntities();
		logger.debug("taxDepositCycleList :: " + taxDepositCycleList);
		logger.debug("getTaxDepositCycles >>");
		return taxDepositCycleList;
	}

	@Override
	public Long addTaxDepositCycle(TaxDepositCycleMasterForm taxDepositCycleMasterForm) {
		logger.debug(">> addTaxDepositCycle");
		TaxDepositCycle taxDepositCycle = new TaxDepositCycle();
		taxDepositCycle.setCycle(taxDepositCycleMasterForm.getDepositCycle());
		taxDepositCycle.setNoOfDays(taxDepositCycleMasterForm.getNoOfDays());
		taxDepositCycle.setDescription(taxDepositCycleMasterForm.getDescription());
		Long id = taxDepositCycleDao.save(taxDepositCycle);
		logger.debug("addTaxDepositCycle >>");
		return id;
	}

	@Override
	public TaxDepositCycleMasterForm getTaxDepositCycleMasterForm(Long taxDepositCycleId)
			throws InstanceNotFoundException {
		logger.debug(">> getTaxDepositCycleMasterForm");
		TaxDepositCycle taxDepositCycle = taxDepositCycleDao.find(taxDepositCycleId);
		TaxDepositCycleMasterForm taxDepositCycleMasterForm = new TaxDepositCycleMasterForm();
		taxDepositCycleMasterForm.setId(taxDepositCycle.getId());
		taxDepositCycleMasterForm.setDepositCycle(taxDepositCycle.getCycle());
		taxDepositCycleMasterForm.setNoOfDays(taxDepositCycle.getNoOfDays());
		taxDepositCycleMasterForm.setDescription(taxDepositCycle.getDescription());
		logger.debug("getTaxDepositCycleMasterForm >>");
		return taxDepositCycleMasterForm;
	}

	@Override
	public void updateTaxDepositCycle(TaxDepositCycleMasterForm taxDepositCycleMasterForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateTaxDepositCycle");
		TaxDepositCycle taxDepositCycle = taxDepositCycleDao
				.find(taxDepositCycleMasterForm.getId());
		taxDepositCycle.setCycle(taxDepositCycleMasterForm.getDepositCycle());
		taxDepositCycle.setNoOfDays(taxDepositCycleMasterForm.getNoOfDays());
		taxDepositCycle.setDescription(taxDepositCycleMasterForm.getDescription());
		taxDepositCycleDao.update(taxDepositCycle);
		logger.debug("updateTaxDepositCycle >>");

	}

	private void deleteTaxDepositCycle(Long taxDepositCycleId) throws InstanceNotFoundException {
		logger.debug(">> deleteTaxDepositCycle");
		taxDepositCycleDao.remove(taxDepositCycleId);
		logger.debug("deleteTaxDepositCycle >>");
	}

	@Override
	public boolean verifyAndDeleteTaxDepositCycle(Long taxDepositCycleId)
			throws InstanceNotFoundException {

		TaxDepositCycle taxDepositCycle = taxDepositCycleDao.find(taxDepositCycleId);
		logger.debug(">> verifyAndDeleteTaxDepositCycle");
		if (taxDepositCycle.getCompanyTaxes().size() <= 0
				&& taxDepositCycle.getTaxAuthorityDepositCycles().size() <= 0) {

			deleteTaxDepositCycle(taxDepositCycleId);
			logger.debug("verifyAndDeleteTaxDepositCycle >>");
			return true;
		} else {
			logger.debug("verifyAndDeleteTaxDepositCycle >>");
			return false;
		}

	}

	@Override
	public List<FilingStatus> getFilingStatusList() {
		logger.debug(">> getFilingStatusList");
		List<FilingStatus> filingStatusList = filingStatusDao.getEntities();
		logger.debug("filingStatusList :: " + filingStatusList);
		logger.debug("getFilingStatusList >>");
		return filingStatusList;
	}

	@Override
	public Long addFilingStatus(FilingStatusForm filingStatusForm) {
		logger.debug(">> addFilingStatus");
		FilingStatus filingStatus = new FilingStatus();
		filingStatus.setStatus(filingStatusForm.getFilingStatus());
		filingStatus.setDescription(filingStatusForm.getDescription());
		Long id = filingStatusDao.save(filingStatus);
		logger.debug("addFilingStatus >>");
		return id;
	}

	@Override
	public FilingStatusForm getFilingStatusForm(Long filingStatusId)
			throws InstanceNotFoundException {
		logger.debug(">> getFilingStatusForm");
		FilingStatus filingStatus = filingStatusDao.find(filingStatusId);
		FilingStatusForm filingStatusForm = new FilingStatusForm();
		filingStatusForm.setId(filingStatus.getId());
		filingStatusForm.setFilingStatus(filingStatus.getStatus());
		filingStatusForm.setDescription(filingStatus.getDescription());
		logger.debug("getFilingStatusForm >>");
		return filingStatusForm;
	}

	@Override
	public void updateFilingStatus(FilingStatusForm filingStatusForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateFilingStatus");
		FilingStatus filingStatus = filingStatusDao.find(filingStatusForm.getId());
		filingStatus.setStatus(filingStatusForm.getFilingStatus());
		filingStatus.setDescription(filingStatusForm.getDescription());
		filingStatusDao.update(filingStatus);
		logger.debug("updateFilingStatus >>");

	}

	private void deleteFilingStatus(Long filingStatusId) throws InstanceNotFoundException {
		logger.debug(">> deleteFilingStatus");
		filingStatusDao.remove(filingStatusId);
		logger.debug("deleteFilingStatus >>");
	}

	@Override
	public boolean verifyAndDeleteFilingStatus(Long filingStatusId)
			throws InstanceNotFoundException {
		FilingStatus filingStatus = filingStatusDao.find(filingStatusId);
		logger.debug(">> verifyAndDeleteFilingStatus");
		if (filingStatus.getEmployeeW4Details().size() <= 0
				&& filingStatus.getFederalStateTaxRates().size() <= 0
				&& filingStatus.getStandardDeductionRates().size() <= 0) {
			deleteFilingStatus(filingStatusId);
			logger.debug("verifyAndDeleteFilingStatus >>");
			return true;
		} else {
			logger.debug("verifyAndDeleteFilingStatus >>");
			return false;
		}
	}

	@Override
	public List<Integer> getYearListForWorkersCompensationTaxRate() {
		logger.debug(">> getYearListForWorkersCompensationTaxRate");
		List<Integer> yearList = new ArrayList<Integer>();
		Date date = workersCompensationTaxRateDao.getDate();

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int defaultMinYear = currentYear - 5;
		int maxYear = currentYear + 1;
		int minYear = 0;

		if (date == null) {
			minYear = defaultMinYear;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			if (year >= defaultMinYear) {
				minYear = defaultMinYear;
			} else {
				minYear = year;
			}
		}
		for (int i = minYear; i <= maxYear; i++) {
			yearList.add(i);
		}
		logger.debug("yearlist ::" + yearList);
		logger.debug("getYearListForWorkersCompensationTaxRate >>");
		return yearList;
	}

	@Override
	public List<WorkersCompensationTaxRate> getWorkersCompensationTaxRateList(Integer year)
			throws ParseException {
		logger.debug(">> getWorkersCompensationTaxRateList");
		String fromDateString = "01/01/" + year;
		String toDateString = "12/31/" + year;
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		Date fromDate = sdf1.parse(fromDateString);
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		Date toDate = sdf2.parse(toDateString);
		List<WorkersCompensationTaxRate> workersCompensationTaxRateList = workersCompensationTaxRateDao
				.getworkersCompensationTaxRateList(fromDate, toDate);
		logger.debug(">> getWorkersCompensationTaxRateList");
		return workersCompensationTaxRateList;
	}

	@Override
	public List<Company> getCompanies() {
		logger.debug(">> getCompanies");
		List<Company> companies = companyDao.getEntities();
		logger.debug("companies :: " + companies);
		logger.debug("getCompanies >>");
		return companies;
	}

	@Override
	public List<CompanyWorkerCompensation> getcompanyWorkerCompensations(Long companyId) {
		logger.debug(">> getcompanyWorkerCompensations");
		List<CompanyWorkerCompensation> companyWorkerCompensations = companyWorkersCompensationDao
				.getWorkersCompensations(companyId);
		logger.debug("companyWorkerCompensations :: " + companyWorkerCompensations);
		logger.debug("getcompanyWorkerCompensations >>");
		return companyWorkerCompensations;
	}

	@Override
	public Long addWorkersCompensationTaxRate(
			WorkersCompensationTaxRateForm workersCompensationTaxRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> addWorkersCompensationTaxRate");
		WorkersCompensationTaxRate workersCompensationTaxRate = new WorkersCompensationTaxRate();
		workersCompensationTaxRate.setDateOfEntry(Calendar.getInstance().getTime());
		workersCompensationTaxRate.setRate(workersCompensationTaxRateForm.getRate());
		workersCompensationTaxRate.setWef(workersCompensationTaxRateForm.getWef());

		CompanyWorkerCompensation companyWorkerCompensation = companyWorkersCompensationDao
				.find(workersCompensationTaxRateForm.getWorkComCodeId());
		workersCompensationTaxRate.setCompanyWorkerCompensation(companyWorkerCompensation);

		Long id = workersCompensationTaxRateDao.save(workersCompensationTaxRate);
		logger.debug("addWorkersCompensationTaxRate >>");
		return id;
	}

	@Override
	public WorkersCompensationTaxRateForm getWorkersCompensationTaxRateForm(Long workComTaxRateId)
			throws InstanceNotFoundException {
		logger.debug(">> getWorkersCompensationTaxRateForm");
		WorkersCompensationTaxRate workersCompensationTaxRate = workersCompensationTaxRateDao
				.find(workComTaxRateId);
		WorkersCompensationTaxRateForm workersCompensationTaxRateForm = new WorkersCompensationTaxRateForm();
		workersCompensationTaxRateForm.setId(workersCompensationTaxRate.getId());
		workersCompensationTaxRateForm.setRate(workersCompensationTaxRate.getRate());
		workersCompensationTaxRateForm.setWef(workersCompensationTaxRate.getWef());
		workersCompensationTaxRateForm.setCompanyId(workersCompensationTaxRate
				.getCompanyWorkerCompensation().getCompany().getId());
		workersCompensationTaxRateForm.setWorkComCodeId(workersCompensationTaxRate
				.getCompanyWorkerCompensation().getId());
		logger.debug("getWorkersCompensationTaxRateForm >>");
		return workersCompensationTaxRateForm;
	}

	@Override
	public void updateWorkersCompensationTaxRate(
			WorkersCompensationTaxRateForm workersCompensationTaxRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateWorkersCompensationTaxRate");
		WorkersCompensationTaxRate workersCompensationTaxRate = workersCompensationTaxRateDao
				.find(workersCompensationTaxRateForm.getId());
		workersCompensationTaxRate.setRate(workersCompensationTaxRateForm.getRate());
		workersCompensationTaxRate.setWef(workersCompensationTaxRateForm.getWef());

		CompanyWorkerCompensation companyWorkerCompensation = companyWorkersCompensationDao
				.find(workersCompensationTaxRateForm.getWorkComCodeId());
		workersCompensationTaxRate.setCompanyWorkerCompensation(companyWorkerCompensation);
		workersCompensationTaxRateDao.update(workersCompensationTaxRate);
		logger.debug("updateWorkersCompensationTaxRate >>");

	}

	private void deleteWorkersCompensationTaxRate(Long workComTaxRateId)
			throws InstanceNotFoundException {
		logger.debug(">> deleteWorkersCompensationTaxRate");
		workersCompensationTaxRateDao.remove(workComTaxRateId);
		logger.debug("deleteWorkersCompensationTaxRate >>");
	}

	@Override
	public boolean verifyAndDeleteWorkersCompensationTaxRate(Long workComTaxRateId)
			throws InstanceNotFoundException {
		logger.debug(">> verifyAndDeleteWorkersCompensationTaxRate");
		// WorkersCompensationTaxRate workersCompensationTaxRate =
		// workersCompensationTaxRateDao
		// .find(workComTaxRateId);
		// TODO need to verify entity Later on

		deleteWorkersCompensationTaxRate(workComTaxRateId);
		logger.debug("verifyAndDeleteWorkersCompensationTaxRate >>");
		return true;

	}

	@Override
	public List<TaxAuthority> getTaxAuthorityList() {
		logger.debug(">> getTaxAuthorityList");
		List<TaxAuthority> taxAuthorityList = taxAuthorityDao.getEntities();
		logger.debug("taxAuthorityList :: " + taxAuthorityList);
		logger.debug("getTaxAuthorityList >>");
		return taxAuthorityList;
	}

	@Override
	public List<TaxType> getTaxTypeList() {
		logger.debug(">> getTaxTypeList");
		List<TaxType> taxTypeList = taxTypeDao.getEntities();
		logger.debug("taxTypeList :: " + taxTypeList);
		logger.debug("getTaxTypeList >>");
		return taxTypeList;
	}

	@Override
	public List<UsState> getUsStateList() {
		logger.debug(">> getUsStateList");
		List<UsState> usStateList = usStateDao.getEntities();
		logger.debug("usStateList :: " + usStateList);
		logger.debug("getUsStateList >>");
		return usStateList;
	}

	@Override
	public List<UsCounty> getUsCountyList() {
		logger.debug(">> getUsCountyList");
		List<UsCounty> usCountyList = usCountyDao.getEntities();
		logger.debug("usCountyList :: " + usCountyList);
		logger.debug("getUsCountyList >>");
		return usCountyList;
	}

	@Override
	public Long addTaxAuthority(TaxAuthorityForm taxAuthorityForm) throws InstanceNotFoundException {
		logger.debug(">> addTaxAuthority");
		TaxAuthority taxAuthority = new TaxAuthority();

		Address address = new Address();
		address.setAddressType(addressTypeDao.getAddressType(AddressType.NORMAL.toString()));
		address.setPinZip(taxAuthorityForm.getPinZip());
		address.setUsCounty(usCountyDao.find(taxAuthorityForm.getUsCountyId()));
		address.setUsCity(usCityDao.find(taxAuthorityForm.getUsCityId()));
		address.setUsState(usStateDao.find(taxAuthorityForm.getUsStateId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		address.setCountry(taxAuthorityForm.getCountry());
		address.setStreetAddress(taxAuthorityForm.getStreetAddress());

		taxAuthority.setAddress(address);
		taxAuthority.setAuthorityName(taxAuthorityForm.getAuthorityName());
		taxAuthority.setDescription(taxAuthorityForm.getDescription());
		taxAuthority.setEmailId(taxAuthorityForm.getEmailId());
		taxAuthority.setFax(taxAuthorityForm.getFax());
		taxAuthority.setPhone(taxAuthorityForm.getPhone());
		taxAuthority.setWebAddress(taxAuthorityForm.getWebAddress());

		Long id = taxAuthorityDao.save(taxAuthority);

		logger.debug("addTaxAuthority >>");
		return id;
	}

	@Override
	public Long addTaxType(TaxTypeForm taxTypeForm) throws InstanceNotFoundException {
		logger.debug(">> addTaxType");
		TaxType taxType = new TaxType();

		taxType.setDescription(taxTypeForm.getDescription());
		taxType.setPaidByCompany(taxTypeForm.getPaidByCompany());
		taxType.setPaidByEmployee(taxTypeForm.getPaidByEmployee());
		taxType.setTaxAuthority(taxAuthorityDao.find(taxTypeForm.getTaxAuthorityId()));
		taxType.setTaxName(taxTypeForm.getTaxName());

		Long id = taxTypeDao.save(taxType);

		logger.debug("addTaxType >>");
		return id;

	}

	@Override
	public TaxAuthorityForm getTaxAuthorityForm(Long taxAuthorityId)
			throws InstanceNotFoundException {
		logger.debug(">> getTaxAuthorityForm");
		TaxAuthority taxAuthority = taxAuthorityDao.find(taxAuthorityId);

		TaxAuthorityForm taxAuthorityForm = new TaxAuthorityForm();
		taxAuthorityForm.setId(taxAuthority.getId());
		taxAuthorityForm.setAuthorityName(taxAuthority.getAuthorityName());
		taxAuthorityForm.setDescription(taxAuthority.getDescription());
		taxAuthorityForm.setEmailId(taxAuthority.getEmailId());
		taxAuthorityForm.setFax(taxAuthority.getFax());
		taxAuthorityForm.setPhone(taxAuthority.getPhone());
		taxAuthorityForm.setWebAddress(taxAuthority.getWebAddress());

		Address address = taxAuthority.getAddress();
		taxAuthorityForm.setPinZip(address.getPinZip());
		taxAuthorityForm.setStreetAddress(address.getStreetAddress());
		taxAuthorityForm.setUsCityId(address.getUsCity().getId());
		taxAuthorityForm.setUsCountyId(address.getUsCounty().getId());
		taxAuthorityForm.setUsStateId(address.getUsState().getId());
		taxAuthorityForm.setCountry(address.getCountry());

		logger.debug("getTaxAuthorityForm >>");
		return taxAuthorityForm;
	}

	@Override
	public TaxTypeForm getTaxTypeForm(Long taxTypeId) throws InstanceNotFoundException {
		logger.debug(">> getTaxTypeForm");
		TaxType taxType = taxTypeDao.find(taxTypeId);

		TaxTypeForm taxTypeForm = new TaxTypeForm();
		taxTypeForm.setId(taxType.getId());
		taxTypeForm.setDescription(taxType.getDescription());
		taxTypeForm.setPaidByCompany(taxType.getPaidByCompany());
		taxTypeForm.setPaidByEmployee(taxType.getPaidByEmployee());
		taxTypeForm.setTaxName(taxType.getTaxName());
		taxTypeForm.setTaxAuthorityId(taxType.getTaxAuthority().getId());

		logger.debug("getTaxTypeForm >>");
		return taxTypeForm;
	}

	@Override
	public void updateTaxAuthority(TaxAuthorityForm taxAuthorityForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateTaxAuthority");
		TaxAuthority taxAuthority = taxAuthorityDao.find(taxAuthorityForm.getId());

		Address address = taxAuthority.getAddress();
		address.setPinZip(taxAuthorityForm.getPinZip());
		address.setUsCounty(usCountyDao.find(taxAuthorityForm.getUsCountyId()));
		address.setUsCity(usCityDao.find(taxAuthorityForm.getUsCityId()));
		address.setUsState(usStateDao.find(taxAuthorityForm.getUsStateId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		address.setCountry(taxAuthorityForm.getCountry());
		address.setStreetAddress(taxAuthorityForm.getStreetAddress());

		taxAuthority.setAddress(address);
		taxAuthority.setAuthorityName(taxAuthorityForm.getAuthorityName());
		taxAuthority.setDescription(taxAuthorityForm.getDescription());
		taxAuthority.setEmailId(taxAuthorityForm.getEmailId());
		taxAuthority.setFax(taxAuthorityForm.getFax());
		taxAuthority.setPhone(taxAuthorityForm.getPhone());
		taxAuthority.setWebAddress(taxAuthorityForm.getWebAddress());

		taxAuthorityDao.update(taxAuthority);
		logger.debug("updateTaxAuthority >>");
	}

	@Override
	public void updateTaxType(TaxTypeForm taxTypeForm) throws InstanceNotFoundException {
		logger.debug(">> updateTaxType");
		TaxType taxType = taxTypeDao.find(taxTypeForm.getId());

		taxType.setDescription(taxTypeForm.getDescription());
		taxType.setPaidByCompany(taxTypeForm.getPaidByCompany());
		taxType.setPaidByEmployee(taxTypeForm.getPaidByEmployee());
		taxType.setTaxAuthority(taxAuthorityDao.find(taxTypeForm.getTaxAuthorityId()));
		taxType.setTaxName(taxTypeForm.getTaxName());

		taxTypeDao.update(taxType);
		logger.debug("updateTaxType >>");
	}

	private void deleteTaxAuthority(Long taxAuthorityId) throws InstanceNotFoundException {
		logger.debug(">> deleteTaxAuthority");
		taxAuthorityDao.remove(taxAuthorityId);
		logger.debug("deleteTaxAuthority >>");
	}

	@Override
	public boolean verifyAndDeleteTaxAuthority(Long taxAuthorityId)
			throws InstanceNotFoundException {
		TaxAuthority taxAuthority = taxAuthorityDao.find(taxAuthorityId);
		logger.debug(">> verifyAndDeleteTaxAuthority");
		if (taxAuthority.getTaxAuthorityDepositCycles().size() <= 0
				&& taxAuthority.getTaxTypes().size() <= 0) {

			deleteTaxAuthority(taxAuthorityId);
			logger.debug("verifyAndDeleteTaxAuthority >>");
			return true;
		} else {
			logger.debug("verifyAndDeleteTaxAuthority >>");
			return false;
		}

	}

	private void deleteTaxType(Long taxTypeId) throws InstanceNotFoundException {
		logger.debug(">> deleteTaxType");
		taxTypeDao.remove(taxTypeId);
		logger.debug("deleteTaxType >>");
	}

	@Override
	public boolean verifyAndDeleteTaxType(Long taxTypeId) throws InstanceNotFoundException {
		TaxType taxType = taxTypeDao.find(taxTypeId);
		logger.debug(">> verifyAndDeleteTaxType");
		if (taxType.getCompanyTaxes().size() <= 0 && taxType.getCountyTaxRates().size() <= 0
				&& taxType.getEmployeeTaxes().size() <= 0
				&& taxType.getFederalStateTaxRates().size() <= 0
				&& taxType.getFutaSutaTaxRates().size() <= 0) {

			deleteTaxType(taxTypeId);
			logger.debug("verifyAndDeleteTaxType >>");
			return true;
		} else {
			logger.debug("verifyAndDeleteTaxType >>");
			return false;
		}
	}

	@Override
	public List<AllowanceRate> getFederalAllowanceRates() {
		return allowanceRateDao.getFederalAllowanceRates();
	}

	@Override
	public List<AllowanceType> getFederalAllowanceTypes() {
		return allowanceTypeDao.getFederalAllowanceTypes();
	}

	@Override
	@Transactional
	public Long addFederalAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> addFederalAllowanceRate");
		AllowanceRate allowanceRate = new AllowanceRate();
		allowanceRate
				.setAllowanceType(allowanceTypeDao.find(allowanceRateForm.getAllowanceTypeId()));
		allowanceRate.setAmount(allowanceRateForm.getRate());
		allowanceRate.setWef(allowanceRateForm.getWef());
		Long allowanceRateId = allowanceRateDao.save(allowanceRate);
		logger.debug("addFederalAllowanceRat>>");
		return allowanceRateId;

	}

	@Override
	@Transactional
	public AllowanceRateForm getFederalAllowanceRateForm(Long allowanceRateId)
			throws InstanceNotFoundException {
		logger.debug(">> getFederalAllowanceRateForm");
		AllowanceRateForm allowanceRateForm = new AllowanceRateForm();
		allowanceRateForm.setId(allowanceRateId);
		AllowanceRate allowanceRate = allowanceRateDao.find(allowanceRateId);
		allowanceRateForm.setAllowanceTypeId(allowanceRate.getAllowanceType().getId());
		allowanceRateForm.setRate(allowanceRate.getAmount());
		allowanceRateForm.setWef(allowanceRate.getWef());
		logger.debug("getFederalAllowanceRateForm>>");
		logger.debug("federalAllowanceRateForm::" + allowanceRateForm);
		return allowanceRateForm;

	}

	@Override
	@Transactional
	public void updateFederalAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateFederalAllowanceRate");
		AllowanceRate allowanceRate = allowanceRateDao.find(allowanceRateForm.getId());
		allowanceRate.setAmount(allowanceRateForm.getRate());
		allowanceRate
				.setAllowanceType(allowanceTypeDao.find(allowanceRateForm.getAllowanceTypeId()));
		allowanceRate.setWef(allowanceRateForm.getWef());
		logger.debug("updateFederalAllowanceRate>>");
		allowanceRateDao.update(allowanceRate);
	}

	@Override
	@Transactional
	public void deleteFederalAllowanceRate(Long allowanceRateId) throws InstanceNotFoundException {
		logger.debug(">> deleteFederalAllowanceRate");
		allowanceRateDao.remove(allowanceRateId);
		logger.debug("deleteFederalAllowanceRate>>");
	}

	@Override
	public List<StandardDeductionRate> getFederalStandardDeductions() {
		return standardDeductionRateDao.getFederalStandardDeductions();
	}

	@Override
	@Transactional
	public Long addFederalStandardDeductionRate(StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> addFederalStandardDeductionRate");
		StandardDeductionRate standardDeductionRate = new StandardDeductionRate();
		standardDeductionRate.setFilingStatus(filingStatusDao.find(standardDeductionRateForm
				.getFilingStatusId()));
		standardDeductionRate.setMaxValue(standardDeductionRateForm.getMaxValue());
		standardDeductionRate.setMinValue(standardDeductionRateForm.getMinValue());
		standardDeductionRate.setRate(standardDeductionRateForm.getRate());
		standardDeductionRate.setWef(standardDeductionRateForm.getWef());
		Long standardDeductionRateId = standardDeductionRateDao.save(standardDeductionRate);
		logger.debug("addFederalStandardDeductionRate>>");
		return standardDeductionRateId;

	}

	@Override
	public StandardDeductionRateForm getFederalStandardDeductionRateForm(
			Long federalStandardDeductionId) throws InstanceNotFoundException {
		logger.debug(">>getFederalStandardDeductionRateForm");
		StandardDeductionRate standardDeductionRate = standardDeductionRateDao
				.find(federalStandardDeductionId);
		StandardDeductionRateForm standardDeductionRateForm = new StandardDeductionRateForm();
		standardDeductionRateForm
				.setFilingStatusId(standardDeductionRate.getFilingStatus().getId());
		standardDeductionRateForm.setId(federalStandardDeductionId);
		standardDeductionRateForm.setMaxValue(standardDeductionRate.getMaxValue());
		standardDeductionRateForm.setMinValue(standardDeductionRate.getMinValue());
		standardDeductionRateForm.setRate(standardDeductionRate.getRate());
		standardDeductionRateForm.setWef(standardDeductionRate.getWef());
		logger.debug("getFederalStandardDeductionRateForm>>");
		return standardDeductionRateForm;

	}

	@Override
	@Transactional
	public void updateFederalStandardDeductionRate(
			StandardDeductionRateForm standardDeductionRateForm) throws InstanceNotFoundException {
		logger.debug(">>updateFederalStandardDeductionRate");
		StandardDeductionRate standardDeductionRate = standardDeductionRateDao
				.find(standardDeductionRateForm.getId());
		standardDeductionRate.setFilingStatus(filingStatusDao.find(standardDeductionRateForm
				.getFilingStatusId()));
		standardDeductionRate.setMaxValue(standardDeductionRateForm.getMaxValue());
		standardDeductionRate.setMinValue(standardDeductionRateForm.getMinValue());
		standardDeductionRate.setRate(standardDeductionRateForm.getRate());
		standardDeductionRate.setWef(standardDeductionRateForm.getWef());
		standardDeductionRateDao.update(standardDeductionRate);
		logger.debug("updateFederalStandardDeductionRate>>");
	}

	@Override
	@Transactional
	public void deleteFederalStandardDeductionRate(Long federalStandardDeductionId)
			throws InstanceNotFoundException {
		logger.debug(">> deleteFederalStandardDeductionRate");
		standardDeductionRateDao.remove(federalStandardDeductionId);
		logger.debug("deleteFederalStandardDeductionRate>>");
	}

	// Deduction Cap

	@Override
	public List<DeductionCap> getFederalDeductionCap() {
		return deductionCapDao.getFederalDeductionCap();
	}

	@Override
	public List<Company> getCompanyList() {
		return companyDeductionDao.getCompanyList();
	}

	@Override
	public List<CompanyDeduction> getCompanyDeductionList(Long companyId) {
		return companyDeductionDao.getCompanyDeductionList(companyId);
	}

	@Override
	@Transactional
	public Long addFederalDeductionCap(DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException {
		logger.debug(">> addFederalDeductionCap");
		DeductionCap deductionCap = new DeductionCap();
		deductionCap.setCapAmount(deductionCapForm.getCapAmount());
		deductionCap.setCompanyDeduction(companyDeductionDao.find(deductionCapForm
				.getCompanyDeductionId()));
		deductionCap.setWef(deductionCapForm.getWef());
		logger.debug("addFederalDeductionCap>>");
		return deductionCapDao.save(deductionCap);
	}

	@Override
	public DeductionCapForm getFederalDeductionCapForm(Long deductionCapId)
			throws InstanceNotFoundException {
		logger.debug(">> getFederalDeductionCapForm");
		DeductionCapForm deductionCapForm = new DeductionCapForm();
		DeductionCap deductionCap = deductionCapDao.find(deductionCapId);
		deductionCapForm.setCapAmount(deductionCap.getCapAmount());
		deductionCapForm.setCompanyDeductionId(deductionCap.getCompanyDeduction().getId());
		deductionCapForm.setId(deductionCapId);
		deductionCapForm.setWef(deductionCap.getWef());
		logger.debug(" getFederalDeductionCapForm>>");
		return deductionCapForm;
	}

	@Override
	@Transactional
	public void updateFederalDeductionCap(DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateFederalDeductionCap");
		DeductionCap deductionCap = deductionCapDao.find(deductionCapForm.getId());
		deductionCap.setCapAmount(deductionCapForm.getCapAmount());
		deductionCap.setCompanyDeduction(companyDeductionDao.find(deductionCapForm
				.getCompanyDeductionId()));
		deductionCap.setWef(deductionCapForm.getWef());
		deductionCapDao.update(deductionCap);
		logger.debug("updateFederalDeductionCap>>");
	}

	@Override
	@Transactional
	public void deleteFederalDeductionCap(Long deductionCapId) throws InstanceNotFoundException {
		logger.debug(">> deleteFederalDeductionCap");
		deductionCapDao.remove(deductionCapId);
		logger.debug("deleteFederalDeductionCap>>");
	}

	@Override
	public List<FederalStateTaxRate> getFederalStateTaxRates(Long filingStatusId, Long taxTypeId) {
		logger.debug(">> getfederalStateTaxRates");
		List<FederalStateTaxRate> federalStateTaxRates = federalStateTaxRateDao
				.getFederalStateTaxRates(filingStatusId, taxTypeId);
		logger.debug("getfederalStateTaxRates >>");
		return federalStateTaxRates;
	}

	@Override
	public Long getFitTaxTypeId() {
		logger.debug(">> getFitTaxTypeId");
		Long taxTypeId = taxTypeDao.getFitTaxTypeId();
		logger.debug("getFitTaxTypeId >>");
		return taxTypeId;
	}

	@Override
	@Transactional
	public Long addFederalStateTaxRate(IncomeTaxSlabForm incomeTaxSlabForm)
			throws InstanceNotFoundException {
		logger.debug(">> addFederalStateTaxRate");
		FederalStateTaxRate federalStateTaxRate = new FederalStateTaxRate();
		federalStateTaxRate.setCalculatedSlabAmount(incomeTaxSlabForm.getTaxAmount());
		federalStateTaxRate.setFilingStatus(filingStatusDao.find(incomeTaxSlabForm
				.getFilingStatusId()));
		federalStateTaxRate.setTaxType(taxTypeDao.find(incomeTaxSlabForm.getTaxTypeId()));
		federalStateTaxRate.setWef(new Date());
		federalStateTaxRate.setMaxValue(incomeTaxSlabForm.getMaxIncome());
		federalStateTaxRate.setMinValue(incomeTaxSlabForm.getMinIncome());
		federalStateTaxRate.setEmployeeRate(incomeTaxSlabForm.getTaxRate());
		Long id = federalStateTaxRateDao.save(federalStateTaxRate);
		logger.debug("addFederalStateTaxRate >>");
		return id;
	}

	@Override
	public IncomeTaxSlabForm getIncomeTaxSlabForm(Long federalStateTaxRateId)
			throws InstanceNotFoundException {
		logger.debug(">> getIncomeTaxSlabForm");
		IncomeTaxSlabForm incomeTaxSlabForm = new IncomeTaxSlabForm();
		FederalStateTaxRate federalStateTaxRate = federalStateTaxRateDao
				.find(federalStateTaxRateId);
		incomeTaxSlabForm.setFederalStateTaxRateId(federalStateTaxRate.getId());
		incomeTaxSlabForm.setFilingStatusId(federalStateTaxRate.getFilingStatus().getId());
		incomeTaxSlabForm.setMaxIncome(federalStateTaxRate.getMaxValue());
		incomeTaxSlabForm.setMinIncome(federalStateTaxRate.getMinValue());
		incomeTaxSlabForm.setTaxAmount(federalStateTaxRate.getCalculatedSlabAmount());
		incomeTaxSlabForm.setTaxRate(federalStateTaxRate.getEmployeeRate());
		incomeTaxSlabForm.setTaxTypeId(federalStateTaxRate.getTaxType().getId());
		incomeTaxSlabForm.setWef(federalStateTaxRate.getWef());
		logger.debug("incomeTaxSlabForm :: " + incomeTaxSlabForm);
		logger.debug("getIncomeTaxSlabForm >>");
		return incomeTaxSlabForm;
	}

	@Override
	@Transactional
	public void updateFederalStateTaxRate(IncomeTaxSlabForm incomeTaxSlabForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateFederalStateTaxRate");
		FederalStateTaxRate federalStateTaxRate = federalStateTaxRateDao.find(incomeTaxSlabForm
				.getFederalStateTaxRateId());
		federalStateTaxRate.setCalculatedSlabAmount(incomeTaxSlabForm.getTaxAmount());
		// federalStateTaxRate.setFilingStatus(filingStatusDao.find(incomeTaxSlabForm
		// .getFilingStatusId()));
		// federalStateTaxRate.setTaxType(taxTypeDao.find(incomeTaxSlabForm.getTaxTypeId()));
		// federalStateTaxRate.setWef(new Date());
		federalStateTaxRate.setMaxValue(incomeTaxSlabForm.getMaxIncome());
		federalStateTaxRate.setMinValue(incomeTaxSlabForm.getMinIncome());
		federalStateTaxRate.setEmployeeRate(incomeTaxSlabForm.getTaxRate());
		federalStateTaxRateDao.update(federalStateTaxRate);
		logger.debug("updateFederalStateTaxRate >>");

	}

	private void deleteFederalStateTaxRate(Long federalStateTaxRateId)
			throws InstanceNotFoundException {
		logger.debug(">> deleteFederalStateTaxRate");
		federalStateTaxRateDao.remove(federalStateTaxRateId);
		logger.debug("deleteFederalStateTaxRate >>");
	}

	@Override
	@Transactional
	public boolean verifyAndDeleteFederalStateTaxRate(Long federalStateTaxRateId)
			throws InstanceNotFoundException {
		// TODO needs to be verified with other calculation entities later on
		deleteFederalStateTaxRate(federalStateTaxRateId);
		return true;
	}

	@Override
	public Long getFutaTaxTypeId() {
		logger.debug(">> getFutaTaxTypeId");
		Long taxTypeId = taxTypeDao.getFutaTaxTypeId();
		logger.debug("getFutaTaxTypeId >>");
		return taxTypeId;
	}

	@Override
	@Transactional
	public FederalStateTaxRate addUpdateFuta(FutaForm futaForm) throws InstanceNotFoundException {
		logger.debug(">> addUpdateFuta");
		FederalStateTaxRate federalStateTaxRate = new FederalStateTaxRate();
		federalStateTaxRate.setCeiling(futaForm.getCeiling());
		federalStateTaxRate.setMaximumFutaCredit(futaForm.getMaximumFutaCredit());
		federalStateTaxRate.setTaxType(taxTypeDao.find(futaForm.getTaxTypeId()));
		federalStateTaxRate.setWef(futaForm.getWef());
		federalStateTaxRate.setCompanyRate(futaForm.getCompanyRate());
		if (futaForm.getFederalStateTaxRateId() != null) {
			federalStateTaxRate.setId(futaForm.getFederalStateTaxRateId());
		}
		federalStateTaxRateDao.saveOrUpdate(federalStateTaxRate);
		futaForm.setFederalStateTaxRateId(federalStateTaxRate.getId());
		logger.debug("addUpdateFuta >>");
		return federalStateTaxRate;
	}

	@Override
	public MedicareSocialSecurityForm getMedicareSocialSecurityForm() {
		logger.debug(">> getMedicareSocialSecurityForm");
		MedicareSocialSecurityForm medicareSocialSecurityForm = new MedicareSocialSecurityForm();
		List<FederalStateTaxRate> federalStateTaxRates = federalStateTaxRateDao
				.getMedicareSocialSecurityRates();
		List<MedicareSocialSecurityModel> medicareSocialSecurityModels = new ArrayList<>();
		if (federalStateTaxRates != null && federalStateTaxRates.size() > 0) {
			for (FederalStateTaxRate federalStateTaxRate : federalStateTaxRates) {
				MedicareSocialSecurityModel medicareSocialSecurityModel = new MedicareSocialSecurityModel();
				medicareSocialSecurityModel.setFederalStateTaxRateId(federalStateTaxRate.getId());
				medicareSocialSecurityModel.setTaxTypeId(federalStateTaxRate.getTaxType().getId());
				medicareSocialSecurityModel.setTaxName(federalStateTaxRate.getTaxType()
						.getTaxName());
				medicareSocialSecurityModel.setEmployeeContribution(federalStateTaxRate
						.getEmployeeRate());
				medicareSocialSecurityModel.setCompanyContribution(federalStateTaxRate
						.getCompanyRate());
				medicareSocialSecurityModels.add(medicareSocialSecurityModel);
				medicareSocialSecurityForm.setWef(federalStateTaxRate.getWef());
			}
		} else {
			List<TaxType> taxTypes = taxTypeDao.getMedicareSocialSecurityTaxes();
			for (TaxType taxType : taxTypes) {
				MedicareSocialSecurityModel medicareSocialSecurityModel = new MedicareSocialSecurityModel();
				medicareSocialSecurityModel.setTaxTypeId(taxType.getId());
				medicareSocialSecurityModel.setTaxName(taxType.getTaxName());
				medicareSocialSecurityModels.add(medicareSocialSecurityModel);
			}
		}
		medicareSocialSecurityForm.setMedicareSocialSecurityModels(medicareSocialSecurityModels);
		logger.debug("getMedicareSocialSecurityForm >>");
		return medicareSocialSecurityForm;
	}

	@Override
	@Transactional
	public void addUpdateMedicareSocialSecurity(
			MedicareSocialSecurityForm medicareSocialSecurityForm) throws InstanceNotFoundException {
		logger.debug(">> addUpdateMedicareSocialSecurity");
		List<MedicareSocialSecurityModel> medicareSocialSecurityModels = new ArrayList<>();
		for (MedicareSocialSecurityModel medicareSocialSecurityModel : medicareSocialSecurityForm
				.getMedicareSocialSecurityModels()) {
			FederalStateTaxRate federalStateTaxRate = new FederalStateTaxRate();
			if (medicareSocialSecurityModel.getFederalStateTaxRateId() != null) {
				federalStateTaxRate.setId(medicareSocialSecurityModel.getFederalStateTaxRateId());
			}
			federalStateTaxRate.setWef(medicareSocialSecurityForm.getWef());
			federalStateTaxRate.setEmployeeRate(medicareSocialSecurityModel
					.getEmployeeContribution());
			federalStateTaxRate
					.setCompanyRate(medicareSocialSecurityModel.getCompanyContribution());
			federalStateTaxRate.setTaxType(taxTypeDao.find(medicareSocialSecurityModel
					.getTaxTypeId()));
			federalStateTaxRateDao.saveOrUpdate(federalStateTaxRate);
			medicareSocialSecurityModel.setFederalStateTaxRateId(federalStateTaxRate.getId());
			medicareSocialSecurityModels.add(medicareSocialSecurityModel);
		}
		medicareSocialSecurityForm.setMedicareSocialSecurityModels(medicareSocialSecurityModels);
		logger.debug(">> addUpdateMedicareSocialSecurity");
	}

	@Override
	public FutaForm getFutaForm() {
		logger.debug(">> getFutaForm");
		FutaForm futaForm = new FutaForm();
		FederalStateTaxRate federalStateTaxRate = federalStateTaxRateDao.getFutaRate();
		if (federalStateTaxRate != null && federalStateTaxRate.getId() > 0) {
			futaForm.setFederalStateTaxRateId(federalStateTaxRate.getId());
			futaForm.setMaximumFutaCredit(federalStateTaxRate.getMaximumFutaCredit());
			futaForm.setCeiling(federalStateTaxRate.getCeiling());
			futaForm.setTaxTypeId(federalStateTaxRate.getTaxType().getId());
			futaForm.setCompanyRate(federalStateTaxRate.getCompanyRate());
		} else {
			futaForm.setTaxTypeId(getFutaTaxTypeId());
		}
		logger.debug("getFutaForm >>");
		return futaForm;
	}

	@Override
	public List<TaxDepositCycle> getDepositCycles() {
		logger.debug(">> getFutaForm");
		List<TaxDepositCycle> taxDepositCycles = taxDepositCycleDao.getEntities();
		logger.debug(">> getFutaForm");
		return taxDepositCycles;
	}

	@Override
	public TaxAuthorityDepositCycleForm getTaxAuthorityDepositCycleForm() {
		logger.debug(">> getTaxAuthorityDepositCycleForm");
		TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = new TaxAuthorityDepositCycleForm();
		Long taxAuthorityId = taxAuthorityDao.getFederalAuhtorityId();
		taxAuthorityDepositCycleForm.setTaxAuthorityId(taxAuthorityId);
		logger.debug(">> getTaxAuthorityDepositCycleForm");
		return taxAuthorityDepositCycleForm;
	}

	@Override
	public TaxAuthorityDepositCycleForm getTaxAuthorityDepositCycleForm(Long stateId) {
		logger.debug(">> getTaxAuthorityDepositCycleForm");
		TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = new TaxAuthorityDepositCycleForm();
		Long taxAuthorityId = taxAuthorityDao.getStateAuhtorityId(stateId);
		taxAuthorityDepositCycleForm.setTaxAuthorityId(taxAuthorityId);
		logger.debug(">> getTaxAuthorityDepositCycleForm");
		return taxAuthorityDepositCycleForm;
	}

	@Override
	public List<TaxAuthorityDepositCycle> getTaxAuthorityDepositCycleList(Long taxAuthorityId) {
		logger.debug(">> getTaxAuthorityDepositCycleList");
		List<TaxAuthorityDepositCycle> taxAuthorityDepositCycles = taxAuthorityDepositCycleDao
				.getTaxAuthorityDepositCycles(taxAuthorityId);
		logger.debug(">> getTaxAuthorityDepositCycleList");
		return taxAuthorityDepositCycles;
	}

	@Override
	@Transactional
	public TaxAuthorityDepositCycle addTaxAuthorityDepositCycle(
			TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm)
			throws InstanceNotFoundException {
		logger.debug(">> addTaxAuthorityDepositCycle");
		TaxAuthorityDepositCycle taxAuthorityDepositCycle = new TaxAuthorityDepositCycle();
		taxAuthorityDepositCycle.setMaxTaxAmount(taxAuthorityDepositCycleForm.getMaxTaxAmount());
		taxAuthorityDepositCycle.setMinTaxAmount(taxAuthorityDepositCycleForm.getMinTaxAmount());
		taxAuthorityDepositCycle.setTaxDepositCycle(taxDepositCycleDao
				.find(taxAuthorityDepositCycleForm.getTaxDepositCycleId()));
		taxAuthorityDepositCycle.setTaxAuthority(taxAuthorityDao.find(taxAuthorityDepositCycleForm
				.getTaxAuthorityId()));
		Long id = taxAuthorityDepositCycleDao.save(taxAuthorityDepositCycle);
		taxAuthorityDepositCycle.setId(id);
		logger.debug("addTaxAuthorityDepositCycle >>");
		return taxAuthorityDepositCycle;
	}

	@Override
	public TaxAuthorityDepositCycleForm getTaxAuthorityDepositCycleUpdateForm(
			Long taxAuthorityDepositCycleId) throws InstanceNotFoundException {
		logger.debug(">> getTaxAuthorityDepositCycleForm");
		TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = new TaxAuthorityDepositCycleForm();
		TaxAuthorityDepositCycle taxAuthorityDepositCycle = taxAuthorityDepositCycleDao
				.find(taxAuthorityDepositCycleId);
		taxAuthorityDepositCycleForm.setMaxTaxAmount(taxAuthorityDepositCycle.getMaxTaxAmount());
		taxAuthorityDepositCycleForm.setMinTaxAmount(taxAuthorityDepositCycle.getMinTaxAmount());
		taxAuthorityDepositCycleForm.setTaxAuthorityDepositCycleId(taxAuthorityDepositCycleId);
		taxAuthorityDepositCycleForm.setTaxDepositCycleId(taxAuthorityDepositCycle
				.getTaxDepositCycle().getId());
		taxAuthorityDepositCycleForm.setTaxAuthorityId(taxAuthorityDepositCycle.getTaxAuthority()
				.getId());
		logger.debug("getTaxAuthorityDepositCycleForm >>");
		return taxAuthorityDepositCycleForm;
	}

	@Override
	public TaxAuthorityDepositCycle updateTaxAuthorityDepositCycle(
			TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateTaxAuthorityDepositCycle");
		TaxAuthorityDepositCycle taxAuthorityDepositCycle = new TaxAuthorityDepositCycle();
		taxAuthorityDepositCycle.setMaxTaxAmount(taxAuthorityDepositCycleForm.getMaxTaxAmount());
		taxAuthorityDepositCycle.setMinTaxAmount(taxAuthorityDepositCycleForm.getMinTaxAmount());
		taxAuthorityDepositCycle.setTaxDepositCycle(taxDepositCycleDao
				.find(taxAuthorityDepositCycleForm.getTaxDepositCycleId()));
		taxAuthorityDepositCycle.setTaxAuthority(taxAuthorityDao.find(taxAuthorityDepositCycleForm
				.getTaxAuthorityId()));
		taxAuthorityDepositCycle
				.setId(taxAuthorityDepositCycleForm.getTaxAuthorityDepositCycleId());
		taxAuthorityDepositCycleDao.update(taxAuthorityDepositCycle);
		logger.debug("updateTaxAuthorityDepositCycle >>");
		return taxAuthorityDepositCycle;
	}

	@Override
	public void deleteTaxAuthorityDepositCycle(Long taxAuthorityDepositCycleId)
			throws InstanceNotFoundException {
		logger.debug(">> deleteTaxAuthorityDepositCycle");
		// TODO : Needs to be verified from calculation tables later on.
		taxAuthorityDepositCycleDao.remove(taxAuthorityDepositCycleId);
		logger.debug("deleteTaxAuthorityDepositCycle >>");

	}

	@Override
	public List<AllowanceRate> getStateAllowanceRates(Long stateId) {
		return allowanceRateDao.getStateAllowanceRates(stateId);

	}

	@Override
	public List<AllowanceType> getStateAllowanceTypes(Long stateId) {
		return allowanceTypeDao.getStateAllowanceTypes(stateId);

	}

	@Override
	public Long addStateAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> addStateAllowanceRate");
		AllowanceRate allowanceRate = new AllowanceRate();
		allowanceRate
				.setAllowanceType(allowanceTypeDao.find(allowanceRateForm.getAllowanceTypeId()));
		allowanceRate.setAmount(allowanceRateForm.getRate());
		allowanceRate.setWef(allowanceRateForm.getWef());
		allowanceRate.setUsState(usStateDao.find(allowanceRateForm.getStateId()));
		Long allowanceRateId = allowanceRateDao.save(allowanceRate);
		logger.debug("addStateAllowanceRate>>");
		return allowanceRateId;
	}

	@Override
	public AllowanceRateForm getStateAllowanceRateForm(Long allowanceRateId)
			throws InstanceNotFoundException {
		logger.debug(">> getFederalAllowanceRateForm");
		AllowanceRateForm allowanceRateForm = new AllowanceRateForm();
		allowanceRateForm.setId(allowanceRateId);
		AllowanceRate allowanceRate = allowanceRateDao.find(allowanceRateId);
		allowanceRateForm.setAllowanceTypeId(allowanceRate.getAllowanceType().getId());
		allowanceRateForm.setRate(allowanceRate.getAmount());
		allowanceRateForm.setWef(allowanceRate.getWef());
		allowanceRateForm.setStateId(allowanceRate.getUsState().getId());
		logger.debug("getFederalAllowanceRateForm>>");
		logger.debug("stateAllowanceRateForm::" + allowanceRateForm);
		return allowanceRateForm;
	}

	@Override
	public void updateStateAllowanceRate(AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateFederalAllowanceRate");
		AllowanceRate allowanceRate = allowanceRateDao.find(allowanceRateForm.getId());
		allowanceRate.setAmount(allowanceRateForm.getRate());
		allowanceRate
				.setAllowanceType(allowanceTypeDao.find(allowanceRateForm.getAllowanceTypeId()));
		allowanceRate.setWef(allowanceRateForm.getWef());
		allowanceRate.setUsState(usStateDao.find(allowanceRateForm.getStateId()));
		logger.debug("updateFederalAllowanceRate>>");
		allowanceRateDao.update(allowanceRate);

	}

	@Override
	public void deleteStateAllowanceRate(Long allowanceRateId) throws InstanceNotFoundException {
		logger.debug(">> deleteFederalAllowanceRate");
		allowanceRateDao.remove(allowanceRateId);
		logger.debug("deleteFederalAllowanceRate>>");

	}

	@Override
	public List<StandardDeductionRate> getStateStandardDeductions(Long stateId) {
		return standardDeductionRateDao.getStateStandardDeductions(stateId);
	}

	@Override
	public Long addStateStandardDeductionRate(StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException {
		logger.debug(">> addStateStandardDeductionRate");
		StandardDeductionRate standardDeductionRate = new StandardDeductionRate();
		standardDeductionRate.setFilingStatus(filingStatusDao.find(standardDeductionRateForm
				.getFilingStatusId()));
		standardDeductionRate.setMaxValue(standardDeductionRateForm.getMaxValue());
		standardDeductionRate.setMinValue(standardDeductionRateForm.getMinValue());
		standardDeductionRate.setRate(standardDeductionRateForm.getRate());
		standardDeductionRate.setWef(standardDeductionRateForm.getWef());
		standardDeductionRate.setUsState(usStateDao.find(standardDeductionRateForm.getStateId()));
		Long standardDeductionRateId = standardDeductionRateDao.save(standardDeductionRate);
		logger.debug("addStateStandardDeductionRate>>");
		return standardDeductionRateId;
	}

	@Override
	public StandardDeductionRateForm getStateStandardDeductionRateForm(Long stateStandardDeductionId)
			throws InstanceNotFoundException {
		logger.debug(">>getStateStandardDeductionRateForm");
		StandardDeductionRate standardDeductionRate = standardDeductionRateDao
				.find(stateStandardDeductionId);
		StandardDeductionRateForm standardDeductionRateForm = new StandardDeductionRateForm();
		standardDeductionRateForm
				.setFilingStatusId(standardDeductionRate.getFilingStatus().getId());
		standardDeductionRateForm.setId(stateStandardDeductionId);
		standardDeductionRateForm.setMaxValue(standardDeductionRate.getMaxValue());
		standardDeductionRateForm.setMinValue(standardDeductionRate.getMinValue());
		standardDeductionRateForm.setRate(standardDeductionRate.getRate());
		standardDeductionRateForm.setWef(standardDeductionRate.getWef());
		standardDeductionRateForm.setStateId(standardDeductionRate.getUsState().getId());
		logger.debug("getStateStandardDeductionRateForm>>");
		return standardDeductionRateForm;
	}

	@Override
	public void updateStateStandardDeductionRate(StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException {
		logger.debug(">>updateStateStandardDeductionRate");
		StandardDeductionRate standardDeductionRate = standardDeductionRateDao
				.find(standardDeductionRateForm.getId());
		standardDeductionRate.setFilingStatus(filingStatusDao.find(standardDeductionRateForm
				.getFilingStatusId()));
		standardDeductionRate.setMaxValue(standardDeductionRateForm.getMaxValue());
		standardDeductionRate.setMinValue(standardDeductionRateForm.getMinValue());
		standardDeductionRate.setRate(standardDeductionRateForm.getRate());
		standardDeductionRate.setWef(standardDeductionRateForm.getWef());
		standardDeductionRate.setUsState(usStateDao.find(standardDeductionRateForm.getStateId()));
		standardDeductionRateDao.update(standardDeductionRate);
		logger.debug("updateStateStandardDeductionRate>>");

	}

	@Override
	public void deleteStateStandardDeductionRate(Long stateStandardDeductionId)
			throws InstanceNotFoundException {
		logger.debug(">> deleteStateStandardDeductionRate");
		standardDeductionRateDao.remove(stateStandardDeductionId);
		logger.debug("deleteStateStandardDeductionRate>>");

	}

	@Override
	public List<DeductionCap> getStateDeductionCap(Long stateId) {
		return deductionCapDao.getStateDeductionCap(stateId);
	}

	@Override
	public Long addStateDeductionCap(DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException {
		logger.debug(">> addStateDeductionCap");
		DeductionCap deductionCap = new DeductionCap();
		deductionCap.setCapAmount(deductionCapForm.getCapAmount());
		deductionCap.setCompanyDeduction(companyDeductionDao.find(deductionCapForm
				.getCompanyDeductionId()));
		deductionCap.setWef(deductionCapForm.getWef());
		deductionCap.setUsState(usStateDao.find(deductionCapForm.getStateId()));
		logger.debug("addStateDeductionCap>>");
		return deductionCapDao.save(deductionCap);

	}

	@Override
	public DeductionCapForm getStateDeductionCapForm(Long deductionCapId)
			throws InstanceNotFoundException {
		logger.debug(">> getStateDeductionCapForm");
		DeductionCapForm deductionCapForm = new DeductionCapForm();
		DeductionCap deductionCap = deductionCapDao.find(deductionCapId);
		deductionCapForm.setCapAmount(deductionCap.getCapAmount());
		deductionCapForm.setCompanyDeductionId(deductionCap.getCompanyDeduction().getId());
		deductionCapForm.setId(deductionCapId);
		deductionCapForm.setWef(deductionCap.getWef());
		deductionCapForm.setStateId(deductionCap.getUsState().getId());
		logger.debug(" getStateDeductionCapForm>>");
		return deductionCapForm;
	}

	@Override
	public void updateStateDeductionCap(DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateStateDeductionCap");
		DeductionCap deductionCap = deductionCapDao.find(deductionCapForm.getId());
		deductionCap.setCapAmount(deductionCapForm.getCapAmount());
		deductionCap.setCompanyDeduction(companyDeductionDao.find(deductionCapForm
				.getCompanyDeductionId()));
		deductionCap.setWef(deductionCapForm.getWef());
		deductionCap.setUsState(usStateDao.find(deductionCapForm.getStateId()));
		deductionCapDao.update(deductionCap);
		logger.debug("updateStateDeductionCap>>");
	}

	@Override
	public void deleteStateDeductionCap(Long deductionCapId) throws InstanceNotFoundException {
		logger.debug(">> deleteStateDeductionCap");
		deductionCapDao.remove(deductionCapId);
		logger.debug("deleteStateDeductionCap>>");
	}

	@Override
	public Long getSitTaxTypeId(Long usStateId) {
		logger.debug(">> getSitTaxTypeId");
		Long taxTypeId = taxTypeDao.getSitTaxTypeId(usStateId);
		logger.debug("getSitTaxTypeId >>");
		return taxTypeId;
	}

	// For Testing
	@Override
	public UsState getUsState(long usStateId) throws InstanceNotFoundException {
		logger.debug(">> getUsState");
		UsState usState = usStateDao.find(usStateId);
		logger.debug("usState :: " + usState);
		logger.debug("getUsState >>");
		return usState;
	}

	@Override
	public List<Company> getAllCompanies() {
		logger.debug(">> getAllCompanies");
		List<Company> companies = companyDao.getEntities();
		logger.debug("companies :: " + companies);
		logger.debug("getAllCompanies >>");
		return companies;
	}

	@Override
	public FutaSutaTaxForm getFutaSutaTaxForm() {
		logger.debug(">> getFutaSutaTaxForm");
		FutaSutaTaxForm futaSutaTaxForm = new FutaSutaTaxForm();
		futaSutaTaxForm.setTaxTypeId(getFutaTaxTypeId());
		logger.debug("getFutaSutaTaxForm >>");
		return futaSutaTaxForm;
	}

	@Override
	public FutaSutaTaxForm getFutaSutaTaxForm(Long StateId) {
		logger.debug(">> getFutaSutaTaxForm");
		FutaSutaTaxForm futaSutaTaxForm = new FutaSutaTaxForm();
		futaSutaTaxForm.setTaxTypeId(getSutaTaxTypeId(StateId));
		logger.debug("getFutaSutaTaxForm >>");
		return futaSutaTaxForm;
	}

	private Long getSutaTaxTypeId(Long stateId) {
		logger.debug(">> getSutaTaxTypeId");
		Long taxTypeId = taxTypeDao.getSutaTaxTypeId(stateId);
		logger.debug("getSutaTaxTypeId >>");
		return taxTypeId;
	}

	@Override
	public List<FutaSutaTaxRate> getFutaSutaTaxRates(Long taxTypeId) {
		logger.debug(">> getFutaTaxRates");
		List<FutaSutaTaxRate> futaTaxRates = futaSutaTaxRateDao.getFutaSutaTaxRates(taxTypeId);
		logger.debug("getFutaTaxRates >>");
		return futaTaxRates;
	}

	@Override
	public FutaSutaTaxRate addFutaSutaCompanyRate(FutaSutaTaxForm futaSutaTaxForm)
			throws InstanceNotFoundException {
		logger.debug(">> addFutaSutaCompanyRate");
		FutaSutaTaxRate futaSutaTaxRate = new FutaSutaTaxRate();
		futaSutaTaxRate.setTaxType(taxTypeDao.find(futaSutaTaxForm.getTaxTypeId()));
		futaSutaTaxRate.setCompany(companyDao.find(futaSutaTaxForm.getCompanyId()));
		futaSutaTaxRate.setRate(futaSutaTaxForm.getRate());
		futaSutaTaxRate.setWef(futaSutaTaxForm.getWef());
		Long id = futaSutaTaxRateDao.save(futaSutaTaxRate);
		futaSutaTaxRate.setId(id);
		logger.debug("addFutaSutaCompanyRate >>");
		return futaSutaTaxRate;
	}

	@Override
	public FutaSutaTaxForm getFutaSutaTaxUpdateForm(Long futaSutaTaxRateId)
			throws InstanceNotFoundException {
		logger.debug(">> getFutaSutaTaxForm");
		FutaSutaTaxRate futaSutaTaxRate = futaSutaTaxRateDao.find(futaSutaTaxRateId);
		FutaSutaTaxForm futaSutaTaxForm = new FutaSutaTaxForm();
		futaSutaTaxForm.setFutaSutaTaxRateId(futaSutaTaxRateId);
		futaSutaTaxForm.setCompanyId(futaSutaTaxRate.getCompany().getId());
		futaSutaTaxForm.setTaxTypeId(futaSutaTaxRate.getTaxType().getId());
		futaSutaTaxForm.setRate(futaSutaTaxRate.getRate());
		logger.debug("getFutaSutaTaxForm >>");
		return futaSutaTaxForm;
	}

	@Override
	public void udateFutaSutaCompanyRate(FutaSutaTaxForm futaSutaTaxForm)
			throws InstanceNotFoundException {
		logger.debug(">> udateFutaSutaCompanyRate");
		FutaSutaTaxRate futaSutaTaxRate = futaSutaTaxRateDao.find(futaSutaTaxForm
				.getFutaSutaTaxRateId());
		futaSutaTaxRate.setCompany(companyDao.find(futaSutaTaxForm.getCompanyId()));
		futaSutaTaxRate.setRate(futaSutaTaxForm.getRate());
		futaSutaTaxRate.setWef(futaSutaTaxForm.getWef());
		futaSutaTaxRateDao.update(futaSutaTaxRate);
		logger.debug("udateFutaSutaCompanyRate >>");
	}

	@Override
	public void deleteFutaSutaCompanyRate(Long futaSutaTaxRateId) throws InstanceNotFoundException {
		logger.debug(">> deleteFutaSutaCompanyRate");
		// TODO : Needs to be verified from calculation tables later on.
		futaSutaTaxRateDao.remove(futaSutaTaxRateId);
		logger.debug("deleteFutaSutaCompanyRate >>");
	}

	@Override
	public List<CountyTaxRate> getCountyTaxRates(Long usStateId) {
		logger.debug(">> getCountyTaxRates");
		List<CountyTaxRate> countyTaxRates = countyTaxRateDao.getCountyTaxRateList(usStateId);
		logger.debug("getCountyTaxRates >>");
		return countyTaxRates;
	}

	@Override
	public Long addCountyTaxRate(CountyTaxForm countyTaxForm, Long usStateId)
			throws InstanceNotFoundException {
		logger.debug(">> addCountyTaxRate");
		CountyTaxRate countyTaxRate = new CountyTaxRate();
		countyTaxRate.setCeiling(countyTaxForm.getCeiling());
		countyTaxRate.setDateOfEntry(new Date());
		countyTaxRate.setRate(countyTaxForm.getTaxRate());
		countyTaxRate.setResidentStatus(countyTaxForm.getResidentStatus());
		countyTaxRate.setUsCounty(usCountyDao.find(countyTaxForm.getCountyId()));
		countyTaxRate
				.setTaxType(taxTypeDao.getCountyTaxType(countyTaxForm.getCountyId(), usStateId));
		Long id = countyTaxRateDao.save(countyTaxRate);
		logger.debug("addCountyTaxRate >>");
		return id;
	}

	@Override
	public CountyTaxForm getCountyTaxForm(Long countyTaxRateId) throws InstanceNotFoundException {
		logger.debug(">> getCountyTaxForm");
		CountyTaxRate countyTaxRate = countyTaxRateDao.find(countyTaxRateId);
		CountyTaxForm countyTaxForm = new CountyTaxForm();
		countyTaxForm.setId(countyTaxRate.getId());
		countyTaxForm.setCeiling(countyTaxRate.getCeiling());
		countyTaxForm.setTaxRate(countyTaxRate.getRate());
		countyTaxForm.setResidentStatus(countyTaxRate.getResidentStatus());
		countyTaxForm.setCountyId(countyTaxRate.getUsCounty().getId());
		logger.debug("countyTaxForm :: " + countyTaxForm);
		logger.debug("getCountyTaxForm >>");
		return countyTaxForm;
	}

	@Override
	public void updateCountyTaxRate(CountyTaxForm countyTaxForm) throws InstanceNotFoundException {
		logger.debug(">> updateCountyTaxRate");
		CountyTaxRate countyTaxRate = countyTaxRateDao.find(countyTaxForm.getId());
		countyTaxRate.setCeiling(countyTaxForm.getCeiling());
		countyTaxRate.setRate(countyTaxForm.getTaxRate());
		countyTaxRate.setResidentStatus(countyTaxForm.getResidentStatus());
		countyTaxRate.setUsCounty(usCountyDao.find(countyTaxForm.getCountyId()));
		countyTaxRateDao.update(countyTaxRate);
		logger.debug("updateCountyTaxRate >>");
	}

	private void deleteCountyTaxRate(Long countyTaxRateId) throws InstanceNotFoundException {
		logger.debug(">> deleteCountyTaxRate");
		countyTaxRateDao.remove(countyTaxRateId);
		logger.debug("deleteCountyTaxRate >>");
	}

	@Override
	public boolean verifyAndDeleteCountyTaxRate(Long countyTaxRateId)
			throws InstanceNotFoundException {
		// TODO needs to be verified with other calculation entities later on
		deleteCountyTaxRate(countyTaxRateId);
		return true;
	}

	@Override
	public List<UsCounty> getUsCountyList(Long usStateId) {
		logger.debug(">> getUsCountyList");
		List<UsCounty> usCounties = usCountyDao.getTaxTypeUsCounties(usStateId);
		logger.debug("getUsCountyList >>");
		return usCounties;
	}

	@Override
	public SutaForm getSutaForm(Long stateId) {
		logger.debug(">> getSutaForm");
		SutaForm sutaForm = new SutaForm();
		FederalStateTaxRate federalStateTaxRate = federalStateTaxRateDao.getSutaRate(stateId);
		if (federalStateTaxRate != null && federalStateTaxRate.getId() > 0) {
			sutaForm.setFederalStateTaxRateId(federalStateTaxRate.getId());
			sutaForm.setCeiling(federalStateTaxRate.getCeiling());
			sutaForm.setTaxTypeId(federalStateTaxRate.getTaxType().getId());
			sutaForm.setCompanyRate(federalStateTaxRate.getCompanyRate());
		} else {
			sutaForm.setTaxTypeId(getFutaTaxTypeId());
		}
		logger.debug("sutaForm :: " + sutaForm);
		logger.debug("getSutaForm >>");
		return sutaForm;
	}

	@Override
	public FederalStateTaxRate addUpdateSuta(SutaForm sutaForm) throws InstanceNotFoundException {
		logger.debug(">> addUpdateSuta");
		FederalStateTaxRate federalStateTaxRate = new FederalStateTaxRate();
		federalStateTaxRate.setCeiling(sutaForm.getCeiling());
		federalStateTaxRate.setTaxType(taxTypeDao.find(sutaForm.getTaxTypeId()));
		federalStateTaxRate.setWef(sutaForm.getWef());
		federalStateTaxRate.setCompanyRate(sutaForm.getCompanyRate());
		if (sutaForm.getFederalStateTaxRateId() != null) {
			federalStateTaxRate.setId(sutaForm.getFederalStateTaxRateId());
		}
		federalStateTaxRateDao.saveOrUpdate(federalStateTaxRate);
		sutaForm.setFederalStateTaxRateId(federalStateTaxRate.getId());
		logger.debug("addUpdateSuta >>");
		return federalStateTaxRate;
	}
}
