package com.epayroll.service.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.constant.company.AddressType;
import com.epayroll.constant.company.CompanyDeductionName;
import com.epayroll.constant.company.ContactType;
import com.epayroll.constant.company.HolidayCheckDateOption;
import com.epayroll.dao.AddressDao;
import com.epayroll.dao.AddressTypeDao;
import com.epayroll.dao.ContactTypeDao;
import com.epayroll.dao.FederalStateHolidayDao;
import com.epayroll.dao.PersonDao;
import com.epayroll.dao.UsCityDao;
import com.epayroll.dao.UsCountyDao;
import com.epayroll.dao.UsStateDao;
import com.epayroll.dao.WorkersCompensationTaxRateDao;
import com.epayroll.dao.company.CompanyBankInfoDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.CompanyDeductionDao;
import com.epayroll.dao.company.CompanyDepartmentDao;
import com.epayroll.dao.company.CompanyEarningDao;
import com.epayroll.dao.company.CompanyTaxDao;
import com.epayroll.dao.company.CompanyUserDao;
import com.epayroll.dao.company.CompanyWorkersCompensationDao;
import com.epayroll.dao.company.DeductionCategoryDao;
import com.epayroll.dao.company.DeductionDao;
import com.epayroll.dao.company.EarningCategoryDao;
import com.epayroll.dao.company.EarningDao;
import com.epayroll.dao.company.PayrollFrequencyDao;
import com.epayroll.dao.company.PayrollScheduleDao;
import com.epayroll.dao.company.TaxDepositCycleDao;
import com.epayroll.dao.company.UserDao;
import com.epayroll.dao.company.WorkersCompensationTaxRateViewDao;
import com.epayroll.dao.employee.TaxTypeDao;
import com.epayroll.dao.payroll.PayrollDao;
import com.epayroll.entity.Address;
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
import com.epayroll.entity.FederalStateHoliday;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollFrequency.PayFrequencyType;
import com.epayroll.entity.PayrollSchedule;
import com.epayroll.entity.Person;
import com.epayroll.entity.SystemMessage;
import com.epayroll.entity.TaxDepositCycle;
import com.epayroll.entity.TaxType;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsCounty;
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
import com.epayroll.model.company.CompanyStateLocalTaxModel;

@Service
public class CompanyServiceImpl implements CompanyService {
	private Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private CompanyDepartmentDao companyDepartmentDao;

	@Autowired
	private CompanyBankInfoDao companyBankInfoDao;

	@Autowired
	private CompanyTaxDao companyTaxDao;

	@Autowired
	private CompanyEarningDao companyEarningDao;

	@Autowired
	private EarningDao earningDao;

	@Autowired
	private DeductionDao deductionDao;

	@Autowired
	private CompanyDeductionDao companyDeductionDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CompanyUserDao companyUserDao;

	@Autowired
	private EarningCategoryDao earningCategoryDao;

	@Autowired
	private TaxDepositCycleDao taxDepositCycleDao;

	@Autowired
	private DeductionCategoryDao deductionCategoryDao;

	@Autowired
	private PayrollFrequencyDao payrollFrequencyDao;

	@Autowired
	private PayrollScheduleDao payrollScheduleDao;

	@Autowired
	private FederalStateHolidayDao federalStateHolidayDao;

	@Autowired
	private CompanyWorkersCompensationDao companyWorkersCompensationDao;

	@Autowired
	private UsStateDao usStateDao;

	@Autowired
	private UsCityDao usCityDao;

	@Autowired
	private UsCountyDao usCountyDao;

	@Autowired
	private PayrollDao payrollDao;

	@Autowired
	private WorkersCompensationTaxRateViewDao workersCompensationTaxRateViewDao;

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private AddressTypeDao addressTypeDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private TaxTypeDao taxTypeDao;

	@Autowired
	private ContactTypeDao contactTypeDao;

	@Autowired
	private WorkersCompensationTaxRateDao workersCompensationTaxRateDao;

	@Override
	@Transactional
	public BusinessInfoForm getBusinessInfoForm(Company company) {
		logger.debug(">>Get Business Info Form");
		BusinessInfoForm businessInfoForm = new BusinessInfoForm();
		businessInfoForm.setCompanyId(company.getId());
		businessInfoForm.setBusinessName(company.getBusinessName());
		businessInfoForm.setBusinessPhone(company.getBusinessPhone());
		businessInfoForm.setBusinessFax(company.getBusinessFax());
		businessInfoForm.setWebAddress(company.getWebAddress());
		Address address = addressDao.getCompanyAddress(company.getId(),
				AddressType.BUSINESS_ADDRESS);
		// company.getAddresses().add(address);
		if (address != null) {
			businessInfoForm.setBusinessCityId(address.getUsCity().getId());
			businessInfoForm.setBusinessCountyId(address.getUsCounty().getId());
			businessInfoForm.setBusinessPinZip(address.getPinZip());
			businessInfoForm.setBusinessStateId(address.getUsState().getId());
			businessInfoForm.setBusinessStreetAddress(address.getStreetAddress());
		}
		logger.debug("Get Business Info Form>>");
		return businessInfoForm;
	}

	@Override
	@Transactional
	public Company addOrUpdateBusinessInfo(Company company, BusinessInfoForm businessInfoForm)
			throws InstanceNotFoundException {
		logger.debug(">> addOrUpdateBusinessInfo");
		company.setBusinessName(businessInfoForm.getBusinessName());
		company.setBusinessPhone(businessInfoForm.getBusinessPhone());
		company.setWebAddress(businessInfoForm.getWebAddress());
		company.setBusinessFax(businessInfoForm.getBusinessFax());
		UsCity usCity = usCityDao.find(businessInfoForm.getBusinessCityId());
		UsCounty usCounty = usCountyDao.find(businessInfoForm.getBusinessCountyId());
		UsState usState = usStateDao.find(businessInfoForm.getBusinessStateId());
		Address address = addressDao.getCompanyAddress(company.getId(),
				AddressType.BUSINESS_ADDRESS);
		if (address == null) {
			logger.debug(">> addBusinessInfoAddress");
			address = new Address();
			address.setAddressType(addressTypeDao.getAddressType(AddressType.BUSINESS_ADDRESS));
			address.setUsCity(usCity);
			address.setCityName(usCity.getCityName());
			address.setUsCounty(usCounty);
			address.setCountyName(usCounty.getCountyName());
			address.setPinZip(businessInfoForm.getBusinessPinZip());
			address.setStreetAddress(businessInfoForm.getBusinessStreetAddress());
			address.setUsState(usState);
			logger.debug("addBusinessInfoAddress >>");

		} else {
			logger.debug(">> updateBusinessInfoAddress");
			address.setUsCity(usCity);
			address.setCityName(usCity.getCityName());
			address.setUsCounty(usCounty);
			address.setCountyName(usCounty.getCountyName());
			address.setPinZip(businessInfoForm.getBusinessPinZip());
			address.setStreetAddress(businessInfoForm.getBusinessStreetAddress());
			address.setUsState(usState);

			logger.debug("updateBusinessInfoAddress >>");

		}

		company.getAddresses().add(address);
		company = companyDao.update(company);
		logger.debug("addOrUpdateBusinessInfo >>");
		return company;
	}

	@Override
	@Transactional
	public LegalInfoForm getLegalInfoForm(Company company) {
		logger.debug(">>Get Legal Info Form");
		LegalInfoForm legalInfoForm = new LegalInfoForm();
		legalInfoForm.setCompanyId(company.getId());
		legalInfoForm.setLegalName(company.getLegalName());
		logger.debug("company:" + company);
		Address address = addressDao.getCompanyAddress(company.getId(), AddressType.LEGAL_ADDRESS);
		logger.debug("address:" + address);
		// company.getAddresses().add(address);
		if (address != null) {

			legalInfoForm.setLegalCityId(address.getUsCity().getId());
			legalInfoForm.setLegalCountyId(address.getUsCounty().getId());
			legalInfoForm.setLegalPinZip(address.getPinZip());
			logger.debug("statename" + address.getUsState().getStateName());
			legalInfoForm.setLegalStateId(address.getUsState().getId());
			legalInfoForm.setLegalStreetAddress(address.getStreetAddress());
		}
		logger.debug("Get Legal Info Form>>");
		return legalInfoForm;
	}

	@Override
	@Transactional
	public void updateLegalInfo(Company company, LegalInfoForm legalInfoForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateLegalInfo");
		company.setLegalName(legalInfoForm.getLegalName());
		Address address = addressDao.getCompanyAddress(company.getId(), AddressType.LEGAL_ADDRESS);
		logger.debug("Address :: " + address);
		company.getAddresses().add(address);
		if (address != null) {
			UsCity usCity = usCityDao.find(legalInfoForm.getLegalCityId());
			address.setUsCity(usCity);
			address.setCityName(usCity.getCityName());
			UsCounty usCounty = usCountyDao.find(legalInfoForm.getLegalCountyId());
			address.setUsCounty(usCounty);
			address.setCountyName(usCounty.getCountyName());
			address.setPinZip(legalInfoForm.getLegalPinZip());
			address.setStreetAddress(legalInfoForm.getLegalStreetAddress());
			UsState usState = usStateDao.find(legalInfoForm.getLegalStateId());
			address.setUsState(usState);
		}
		companyDao.update(company);
		logger.debug("updateLegalInfo >>");
	}

	@Override
	@Transactional
	public Company addOrUpdateLegalInfo(LegalInfoForm legalInfoForm)
			throws InstanceNotFoundException {
		logger.debug(">> addOrUpdateLegalInfo");
		Company company;
		if (legalInfoForm.getCompanyId().equals(null)) {
			company = new Company();
		} else {
			company = companyDao.find(legalInfoForm.getCompanyId());
		}
		company.setLegalName(legalInfoForm.getLegalName());
		company.setStatus(com.epayroll.entity.Company.Status.NEW);
		UsCity usCity = usCityDao.find(legalInfoForm.getLegalCityId());
		UsCounty usCounty = usCountyDao.find(legalInfoForm.getLegalCountyId());
		UsState usState = usStateDao.find(legalInfoForm.getLegalStateId());
		Address address = addressDao.getCompanyAddress(company.getId(), AddressType.LEGAL_ADDRESS);
		if (address == null) {
			logger.debug(">> addLegalInfoAddress");
			address = new Address();
			address.setAddressType(addressTypeDao.getAddressType(AddressType.LEGAL_ADDRESS));
			logger.debug("addLegalInfoAddress >>");
		}
		address.setUsCity(usCity);
		address.setCityName(usCity.getCityName());
		address.setUsCounty(usCounty);
		address.setCountyName(usCounty.getCountyName());
		address.setPinZip(legalInfoForm.getLegalPinZip());
		address.setStreetAddress(legalInfoForm.getLegalStreetAddress());
		address.setUsState(usState);
		company.getAddresses().add(address);

		companyDao.saveOrUpdate(company);
		legalInfoForm.setCompanyId(company.getId());

		logger.debug("addOrUpdateLegalInfo >>");
		return company;
	}

	@Override
	@Transactional
	public ShippingInfoForm getShippingInfoForm(Company company) {
		logger.debug(">>Get Shipping Info Form");
		ShippingInfoForm shippingInfoForm = new ShippingInfoForm();
		shippingInfoForm.setCompanyId(company.getId());
		Address address = addressDao.getCompanyAddress(company.getId(),
				AddressType.SHIPPING_ADDRESS);
		// company.getAddresses().add(address);
		if (address != null) {
			shippingInfoForm.setShippingCityId(address.getUsCity().getId());
			shippingInfoForm.setShippingCountyId(address.getUsCounty().getId());
			shippingInfoForm.setShippingPinZip(address.getPinZip());
			shippingInfoForm.setShippingStateId(address.getUsState().getId());
			shippingInfoForm.setShippingStreetAddress(address.getStreetAddress());
		}
		logger.debug("Get Shipping Info Form>>");
		return shippingInfoForm;
	}

	@Override
	@Transactional
	public Company addOrUpdateShippingInfo(Company company, ShippingInfoForm shippingInfoForm)
			throws InstanceNotFoundException {
		logger.debug(">> addOrUpdateShippingInfo");
		UsCity usCity = usCityDao.find(shippingInfoForm.getShippingCityId());
		UsCounty usCounty = usCountyDao.find(shippingInfoForm.getShippingCountyId());
		UsState usState = usStateDao.find(shippingInfoForm.getShippingStateId());
		Address address = addressDao.getCompanyAddress(company.getId(),
				AddressType.SHIPPING_ADDRESS);
		if (address == null) {
			logger.debug(">> addShippingInfoAddress");
			address = new Address();
			address.setAddressType(addressTypeDao.getAddressType(AddressType.SHIPPING_ADDRESS));
			address.setUsCity(usCity);
			address.setCityName(usCity.getCityName());
			address.setUsCounty(usCounty);
			address.setCountyName(usCounty.getCountyName());
			address.setPinZip(shippingInfoForm.getShippingPinZip());
			address.setStreetAddress(shippingInfoForm.getShippingStreetAddress());
			address.setUsState(usState);
			logger.debug("addShippingInfoAddress >>");

		} else {
			logger.debug(">> updateShippingInfoAddress");
			address.setUsCity(usCity);
			address.setCityName(usCity.getCityName());
			address.setUsCounty(usCounty);
			address.setCountyName(usCounty.getCountyName());
			address.setPinZip(shippingInfoForm.getShippingPinZip());
			address.setStreetAddress(shippingInfoForm.getShippingStreetAddress());
			address.setUsState(usState);
			logger.debug("updateShippingInfoAddress >>");
		}
		company.getAddresses().add(address);

		company = companyDao.update(company);
		logger.debug("addOrUpdateShippingInfo >>");
		return company;
	}

	@Override
	@Transactional
	public AuthorizedSignatoryContactForm getAuthorizedSignatoryContactForm(Company company) {
		AuthorizedSignatoryContactForm authorizedSignatoryContactForm = new AuthorizedSignatoryContactForm();
		logger.debug(">>Get AuthorizedSignatory ContactForm");
		authorizedSignatoryContactForm.setCompanyId(company.getId());
		Person contact = personDao.getCompanyContact(company.getId(),
				ContactType.AUTHORIZED_SIGNATORY_CONTACT);
		logger.debug("contact....." + contact);
		// company.getContacts().add(contact);
		if (contact != null) {
			authorizedSignatoryContactForm.setFirstName(contact.getFirstName());
			authorizedSignatoryContactForm.setLastName(contact.getLastName());
			authorizedSignatoryContactForm.setDesignation(contact.getDesignation());
			authorizedSignatoryContactForm.setEmail(contact.getEmail());
			authorizedSignatoryContactForm.setExt(contact.getExt());
			authorizedSignatoryContactForm.setFax(contact.getFax());
			authorizedSignatoryContactForm.setPhone(contact.getPhone());
		}
		logger.debug("Get AuthorizedSignatory ContactForm>>");
		return authorizedSignatoryContactForm;
	}

	@Override
	@Transactional
	public Company addOrUpdateAuthorizedSignatoryContact(Company company,
			AuthorizedSignatoryContactForm authorizedSignatoryContactForm)
			throws InstanceNotFoundException {
		logger.debug(">> addOrUpdateAuthorizedSignatoryContact");
		Person contact = personDao.getCompanyContact(company.getId(),
				ContactType.AUTHORIZED_SIGNATORY_CONTACT);
		if (contact != null) {
			logger.debug(">> update");
			contact.setFirstName(authorizedSignatoryContactForm.getFirstName());
			contact.setLastName(authorizedSignatoryContactForm.getLastName());
			contact.setDesignation(authorizedSignatoryContactForm.getDesignation());
			contact.setEmail(authorizedSignatoryContactForm.getEmail());
			contact.setExt(authorizedSignatoryContactForm.getExt());
			contact.setFax(authorizedSignatoryContactForm.getFax());
			contact.setPhone(authorizedSignatoryContactForm.getPhone());
			logger.debug("update >>");

		} else {
			logger.debug(">> add");
			contact = new Person();
			contact.setContactType(contactTypeDao
					.getContactType(ContactType.AUTHORIZED_SIGNATORY_CONTACT));
			contact.setFirstName(authorizedSignatoryContactForm.getFirstName());
			contact.setLastName(authorizedSignatoryContactForm.getLastName());
			contact.setDesignation(authorizedSignatoryContactForm.getDesignation());
			contact.setEmail(authorizedSignatoryContactForm.getEmail());
			contact.setExt(authorizedSignatoryContactForm.getExt());
			contact.setFax(authorizedSignatoryContactForm.getFax());
			contact.setPhone(authorizedSignatoryContactForm.getPhone());
			logger.debug("add >>");
		}
		company.getContacts().add(contact);
		company = companyDao.update(company);
		logger.debug("addOrUpdateAuthorizedSignatoryContact >>");
		return company;
	}

	@Override
	@Transactional
	public PrimaryContactForm getPrimaryContactForm(Company company) {
		PrimaryContactForm primaryContactForm = new PrimaryContactForm();
		logger.debug(">>Get PrimaryContact Form");
		primaryContactForm.setCompanyId(company.getId());
		Person contact = personDao.getCompanyContact(company.getId(), ContactType.PRIMARY_CONTACT);
		// company.getContacts().add(contact);
		if (contact != null) {
			primaryContactForm.setFirstName(contact.getFirstName());
			primaryContactForm.setLastName(contact.getLastName());
			primaryContactForm.setDesignation(contact.getDesignation());
			primaryContactForm.setEmail(contact.getEmail());
			primaryContactForm.setExt(contact.getExt());
			primaryContactForm.setFax(contact.getFax());
			primaryContactForm.setPhone(contact.getPhone());
		}
		logger.debug("Get PrimaryContact Form>>");
		return primaryContactForm;
	}

	@Override
	@Transactional
	public Company addOrUpdatePrimaryContact(Company company, PrimaryContactForm primaryContactForm)
			throws InstanceNotFoundException {
		logger.debug(">> addOrUpdatePrimaryContact");
		Person contact = personDao.getCompanyContact(company.getId(), ContactType.PRIMARY_CONTACT);
		if (contact != null) {
			logger.debug(">> update");
			contact.setFirstName(primaryContactForm.getFirstName());
			contact.setLastName(primaryContactForm.getLastName());
			contact.setDesignation(primaryContactForm.getDesignation());
			contact.setEmail(primaryContactForm.getEmail());
			contact.setExt(primaryContactForm.getExt());
			contact.setFax(primaryContactForm.getFax());
			contact.setPhone(primaryContactForm.getPhone());
			logger.debug("update >>");

		} else {
			logger.debug(">> add");
			contact = new Person();
			contact.setContactType(contactTypeDao.getContactType(ContactType.PRIMARY_CONTACT));
			contact.setFirstName(primaryContactForm.getFirstName());
			contact.setLastName(primaryContactForm.getLastName());
			contact.setDesignation(primaryContactForm.getDesignation());
			contact.setEmail(primaryContactForm.getEmail());
			contact.setExt(primaryContactForm.getExt());
			contact.setFax(primaryContactForm.getFax());
			contact.setPhone(primaryContactForm.getPhone());
			logger.debug("add >>");
		}
		company.getContacts().add(contact);
		company = companyDao.update(company);
		logger.debug("addOrUpdatePrimaryContact >>");
		return company;
	}

	@Override
	@Transactional
	public BillingContactForm getBillingContactForm(Company company) {
		logger.debug(">>Get Billing Contact Form");
		BillingContactForm billingContactForm = new BillingContactForm();
		billingContactForm.setCompanyId(company.getId());
		Person contact = personDao.getCompanyContact(company.getId(), ContactType.BILLING_CONTACT);
		// company.getContacts().add(contact);
		if (contact != null) {
			billingContactForm.setFirstName(contact.getFirstName());
			billingContactForm.setLastName(contact.getLastName());
			billingContactForm.setEmail(contact.getEmail());
			billingContactForm.setExt(contact.getExt());
			billingContactForm.setFax(contact.getFax());
			billingContactForm.setPhone(contact.getPhone());
		}
		logger.debug("Get Billing Contact Form>>");
		return billingContactForm;
	}

	@Override
	@Transactional
	public Company addOrUpdateBillingContact(Company company, BillingContactForm billingContactForm)
			throws InstanceNotFoundException {
		logger.debug(">> addOrUpdateBillingContact");
		Person contact = personDao.getCompanyContact(company.getId(), ContactType.BILLING_CONTACT);
		if (contact != null) {
			logger.debug(">> update");
			contact.setFirstName(billingContactForm.getFirstName());
			contact.setLastName(billingContactForm.getLastName());
			contact.setEmail(billingContactForm.getEmail());
			contact.setExt(billingContactForm.getExt());
			contact.setFax(billingContactForm.getFax());
			contact.setPhone(billingContactForm.getPhone());
			logger.debug("update >>");

		} else {
			logger.debug(">> add");
			contact = new Person();
			contact.setContactType(contactTypeDao.getContactType(ContactType.BILLING_CONTACT));
			contact.setFirstName(billingContactForm.getFirstName());
			contact.setLastName(billingContactForm.getLastName());
			contact.setEmail(billingContactForm.getEmail());
			contact.setExt(billingContactForm.getExt());
			contact.setFax(billingContactForm.getFax());
			contact.setPhone(billingContactForm.getPhone());
			logger.debug("add >>");
		}
		company.getContacts().add(contact);
		company = companyDao.update(company);
		logger.debug("addOrUpdateBillingContact >>");
		return company;
	}

	@Override
	@Transactional
	public List<PayrollFrequency> getPayrollFrequencies(Long companyId) {
		return payrollFrequencyDao.getPayrollFrequencies(companyId);
	}

	@Override
	@Transactional
	public Long addPayrollFrequency(Company company, PayrollFrequency payrollFrequency)
			throws InstanceNotFoundException {
		logger.debug(">>Add Payroll Frequency");
		Long id = 0L;
		payrollFrequency.setCompany(company);
		PayFrequencyType payFrequencyType = payrollFrequency.getPayFrequencyType();

		Date startDate = payrollFrequency.getPeriodStartDate();
		Date endDate = payrollFrequency.getPeriodEndDate();
		// Date endDate = getEndDate(noOfDays, startDate);
		payrollFrequency.setPeriodStartDate(startDate);
		payrollFrequency.setPeriodEndDate(endDate);
		payrollFrequency.setPayFrequencyType(payFrequencyType);
		int diffEndDateAndCheckDate = diff(endDate, payrollFrequency.getCheckDate());
		PayrollSchedule payrollSchedule = new PayrollSchedule();
		Date checkDate = getcheckDate(payrollFrequency.getCheckDate(),
				payrollFrequency.getHolidayCheckDateOption());
		payrollFrequency.setCheckDate(checkDate);
		id = payrollFrequencyDao.save(payrollFrequency);
		logger.debug("id:::" + id);
		logger.debug("payrollFrequency:::" + payrollFrequency);
		payrollSchedule.setPayrollFrequency(payrollFrequency);
		payrollSchedule.setPeriodStartDate(startDate);
		payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.CURRENT);
		payrollSchedule.setPeriodEndDate(endDate);
		payrollSchedule.setCheckDate(checkDate);
		payrollScheduleDao.save(payrollSchedule);
		switch (payFrequencyType) {
		case MONTHLY:
			logger.debug("payrollFrequency:::" + payrollFrequency);
			addMonthlyPayrollSchedule(endDate, startDate, checkDate, payrollFrequency,
					diffEndDateAndCheckDate);
			break;
		case SEMI_MONTHLY:
			logger.debug("payrollFrequency:::" + payrollFrequency);
			addSemiMonthlyPayrollSchedule(endDate, startDate, checkDate, payrollFrequency,
					diffEndDateAndCheckDate);
			break;
		case WEEKLY:
			addWeeklyMonthlyPayrollSchedule(endDate, startDate, checkDate, payrollFrequency,
					diffEndDateAndCheckDate);
			break;
		case BI_WEEKLY:
			addBiweeklyMonthlyPayrollSchedule(endDate, startDate, checkDate, payrollFrequency,
					diffEndDateAndCheckDate);
			break;

		default:
			break;
		}
		logger.debug("Add Payroll Frequency>>");
		return id;
	}

	private void addBiweeklyMonthlyPayrollSchedule(Date endDate, Date startDate, Date checkDate,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate) {
		int cuurentYear = startDate.getYear();
		for (int i = 1; i < 26; i++) {
			PayrollSchedule payrollSchedule1 = new PayrollSchedule();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(endDate);
			cal1.add(Calendar.DATE, 1);
			startDate = cal1.getTime();

			payrollSchedule1.setPayrollFrequency(payrollFrequency);
			payrollSchedule1.setPeriodStartDate(startDate);
			payrollSchedule1.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startDate);
			cal2.add(Calendar.DATE, 13);
			endDate = cal2.getTime();
			payrollSchedule1.setPeriodEndDate(endDate);
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(endDate);
			cal3.add(Calendar.DATE, diffEndDateAndCheckDate);
			checkDate = cal3.getTime();
			checkDate = getcheckDate(checkDate, payrollFrequency.getHolidayCheckDateOption());
			payrollSchedule1.setCheckDate(checkDate);
			payrollScheduleDao.save(payrollSchedule1);
			Calendar cal4 = Calendar.getInstance();
			cal4.setTime(endDate);
			cal4.add(Calendar.DATE, 1);
			startDate = cal4.getTime();
			logger.debug("startDate: " + startDate);
			logger.debug("startDate.getYear(): " + startDate.getYear() + ", cuurentYear:"
					+ cuurentYear);
			if (startDate.getYear() != cuurentYear) {
				break;
			}
			logger.debug("startDate.getYear(): " + startDate.getYear() + ", cuurentYear:"
					+ cuurentYear);

		}

	}

	private void addWeeklyMonthlyPayrollSchedule(Date endDate, Date startDate, Date checkDate,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate) {
		int cuurentYear = startDate.getYear();
		for (int i = 1; i < 50; i++) {
			PayrollSchedule payrollSchedule1 = new PayrollSchedule();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(endDate);
			cal1.add(Calendar.DATE, 1);
			startDate = cal1.getTime();

			payrollSchedule1.setPayrollFrequency(payrollFrequency);
			payrollSchedule1.setPeriodStartDate(startDate);
			payrollSchedule1.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startDate);
			cal2.add(Calendar.DATE, 6);
			endDate = cal2.getTime();
			payrollSchedule1.setPeriodEndDate(endDate);
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(endDate);
			cal3.add(Calendar.DATE, diffEndDateAndCheckDate);
			checkDate = cal3.getTime();
			checkDate = getcheckDate(checkDate, payrollFrequency.getHolidayCheckDateOption());
			payrollSchedule1.setCheckDate(checkDate);
			payrollScheduleDao.save(payrollSchedule1);
			Calendar cal4 = Calendar.getInstance();
			cal4.setTime(endDate);
			cal4.add(Calendar.DATE, 1);
			startDate = cal4.getTime();
			logger.debug("startDate: " + startDate);
			logger.debug("startDate.getYear(): " + startDate.getYear() + ", cuurentYear:"
					+ cuurentYear);
			if (startDate.getYear() != cuurentYear) {
				break;
			}
			logger.debug("startDate.getYear(): " + startDate.getYear() + ", cuurentYear:"
					+ cuurentYear);

		}

	}

	private void addSemiMonthlyPayrollSchedule(Date endDate, Date startDate, Date checkDate,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate) {

		int loop = 2;
		int cuurentYear = startDate.getYear();
		for (int i = 1; i <= 26; i++) {
			PayrollSchedule payrollSchedule = new PayrollSchedule();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(endDate);
			cal1.add(Calendar.DATE, 1);
			startDate = cal1.getTime();

			payrollSchedule.setPayrollFrequency(payrollFrequency);
			payrollSchedule.setPeriodStartDate(startDate);
			payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(startDate);
			Integer m = null;
			Integer a = null;
			if (loop % 2 == 0) {
				m = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				a = (int) Math.floor((double) m / 2);
				loop = 1;
			} else {
				m = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				a = (int) Math.ceil((double) m / 2);
				loop = 2;
			}

			cal.add(Calendar.DAY_OF_MONTH, a);
			cal.add(Calendar.DATE, -1);
			endDate = cal.getTime();
			logger.debug("endDate:" + endDate);
			payrollSchedule.setPeriodEndDate(endDate);
			logger.debug("setPeriodEndDate(endDate):" + payrollSchedule.getPeriodEndDate());
			logger.debug("M: " + m + ", A:" + a + ", ST:" + startDate + " , ED:" + endDate);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(endDate);
			cal2.add(Calendar.DATE, diffEndDateAndCheckDate);
			checkDate = cal2.getTime();
			checkDate = getcheckDate(checkDate, payrollFrequency.getHolidayCheckDateOption());
			payrollSchedule.setCheckDate(checkDate);
			payrollScheduleDao.save(payrollSchedule);
			if (startDate.getMonth() < endDate.getMonth()) {
				logger.debug("startDate.getMonth() < endDate.getMonth()");
				loop = 2;
			}
			startDate = get1DayAfter(endDate);
			logger.debug("startDate.getYear(): " + startDate.getYear() + ", cuurentYear:"
					+ cuurentYear);
			if (startDate.getYear() != cuurentYear) {
				logger.debug("startDate.getYear(): " + startDate.getYear() + ", cuurentYear:"
						+ cuurentYear);
				logger.debug("break");
				break;
			}
		}

	}

	private static Date get1DayAfter(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date date2 = cal.getTime();
		return date2;
	}

	private void addMonthlyPayrollSchedule(Date endDate, Date startDate, Date checkDate,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate) {
		int cuurentYear = startDate.getYear();
		for (int i = 1; i < 13; i++) {
			PayrollSchedule payrollSchedule1 = new PayrollSchedule();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(endDate);
			cal1.add(Calendar.DATE, 1);
			startDate = cal1.getTime();

			payrollSchedule1.setPayrollFrequency(payrollFrequency);
			payrollSchedule1.setPeriodStartDate(startDate);
			payrollSchedule1.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startDate);
			cal2.add(Calendar.MONTH, 1);
			cal2.add(Calendar.DATE, -1);
			endDate = cal2.getTime();
			payrollSchedule1.setPeriodEndDate(endDate);
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(endDate);
			cal3.add(Calendar.DATE, diffEndDateAndCheckDate);
			checkDate = cal3.getTime();
			checkDate = getcheckDate(checkDate, payrollFrequency.getHolidayCheckDateOption());
			payrollSchedule1.setCheckDate(checkDate);
			payrollScheduleDao.save(payrollSchedule1);
			Calendar cal4 = Calendar.getInstance();
			cal4.setTime(endDate);
			cal4.add(Calendar.DATE, 1);
			startDate = cal4.getTime();
			if (startDate.getYear() != cuurentYear) {
				break;
			}

		}

	}

	@Override
	@Transactional
	public List<PayrollSchedule> getPayrollSchedules(Long payrollFrequencyId) {
		return payrollScheduleDao.getPayrollScheduleList(payrollFrequencyId);
	}

	private Date getcheckDate(Date checkDate, String holidayCheckDateOption) {
		if (!isDateSaturdayOrSunday(checkDate)) {
			while (getHolidayDate(checkDate)) {
				checkDate = setCheckDate(holidayCheckDateOption, checkDate);
				continue;
			}
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(checkDate);
			if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
				cal.add(Calendar.DAY_OF_YEAR, -1);
				checkDate = cal.getTime();
			} else {
				cal.add(Calendar.DAY_OF_YEAR, 1);
				checkDate = cal.getTime();
			}
		}
		return checkDate;
	}

	private Date setCheckDate(String holidayCheckDateOption, Date checkDate) {
		Calendar calendar = Calendar.getInstance();
		if (holidayCheckDateOption.equalsIgnoreCase(HolidayCheckDateOption.BEFORE)) {
			calendar.setTime(checkDate);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			checkDate = calendar.getTime();
		}
		if (holidayCheckDateOption.equalsIgnoreCase(HolidayCheckDateOption.AFTER)) {
			calendar.setTime(checkDate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			checkDate = calendar.getTime();
		}
		return checkDate;
	}

	private Boolean isDateSaturdayOrSunday(Date checkDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkDate);
		if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY | cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			logger.debug("yes saturday and sunday");
			return true;
		} else {
			logger.debug("No");
			return false;
		}
	}

	private Boolean getHolidayDate(Date checkDate) {
		List<FederalStateHoliday> holidays;
		Date holidayDate = null;
		Boolean result = false;

		holidays = federalStateHolidayDao.getEntities();
		for (FederalStateHoliday federalStateHoliday : holidays) {
			holidayDate = federalStateHoliday.getHolidayDate();
			logger.debug("holidayDate:::" + holidayDate);
			if (checkDate.equals(holidayDate)) {
				logger.debug("yes holiday");
				result = true;
				break;
			}
		}

		return result;
	}

	private static int diff(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		c2.setTime(date2);
		int diffDay = 0;

		if (c1.before(c2)) {
			diffDay = countDiffDay(c1, c2);
		}
		return diffDay;
	}

	private static int countDiffDay(Calendar c1, Calendar c2) {
		int returnInt = 0;
		while (!c1.after(c2)) {
			c1.add(Calendar.DATE, 1);
			returnInt++;
		}
		if (returnInt > 0) {
			returnInt = returnInt - 1;
		}
		return (returnInt);
	}

	@Override
	@Transactional
	public PayrollFrequency getPayrollFrequency(Long payrollFrequencyId)
			throws InstanceNotFoundException {
		logger.debug(">>Get Payroll Frequency Form");
		PayrollFrequency payrollFrequency = payrollFrequencyDao.find(payrollFrequencyId);
		logger.debug("Get Payroll Frequency Form >>");
		return payrollFrequency;
	}

	@Override
	@Transactional
	public PayrollFrequency updatePayrollFrequency(
			CompanyPayrollFrequencyForm companyPayrollFrequencyForm)
			throws InstanceNotFoundException {
		logger.debug(">> Update Payroll Frequency");
		PayrollFrequency frequency = payrollFrequencyDao.find(companyPayrollFrequencyForm.getId());
		Date startDate1 = companyPayrollFrequencyForm.getEditPeriodStartDate();
		Date endDate = companyPayrollFrequencyForm.getEditPeriodEndDate();
		// Date endDate = getEndDate(noOfDays, startDate1);
		frequency.setPeriodStartDate(startDate1);
		frequency.setPeriodEndDate(endDate);
		Date checkDate = getcheckDate(companyPayrollFrequencyForm.getEditCheckDate(),
				companyPayrollFrequencyForm.getHolidayCheckDateOption());
		frequency.setCheckDate(checkDate);
		frequency
				.setHolidayCheckDateOption(companyPayrollFrequencyForm.getHolidayCheckDateOption());
		int diffEndDateAndCheckDate = diff(endDate, companyPayrollFrequencyForm.getEditCheckDate());
		switch (companyPayrollFrequencyForm.getPayFrequencyType()) {
		case MONTHLY:
			logger.debug("MONTHLY::");
			updateMonthlyPayrollSchedule(endDate, startDate1, checkDate,
					companyPayrollFrequencyForm, frequency, diffEndDateAndCheckDate);
			break;
		case SEMI_MONTHLY:
			logger.debug("SEMI_MONTHLY::");
			updateSemiMonthlyPayrollSchedule(endDate, startDate1, checkDate,
					companyPayrollFrequencyForm, frequency, diffEndDateAndCheckDate);
			break;
		case WEEKLY:
			logger.debug("WEEKLY::");
			updateWeeklyPayrollSchedule(endDate, startDate1, checkDate,
					companyPayrollFrequencyForm, frequency, diffEndDateAndCheckDate);
			break;
		case BI_WEEKLY:
			logger.debug("BI_WEEKLY::");
			updateBiweeklyMonthlyPayrollSchedule(endDate, startDate1, checkDate,
					companyPayrollFrequencyForm, frequency, diffEndDateAndCheckDate);
			break;

		default:
			break;
		}

		// Date scheduleEndDate = endDate;
		// iterator = sortedPayrollSchedules.iterator();
		// while (iterator.hasNext()) {
		// PayrollSchedule payrollSchedule = iterator.next();
		// switch (payrollSchedule.getStatus()) {
		// case FUTURE:
		// switch (key) {
		// case payrollSchedule.:
		//
		// break;
		//
		// default:
		// break;
		// }
		// Calendar cal1 = Calendar.getInstance();
		// cal1.setTime(scheduleEndDate);
		// cal1.add(Calendar.DATE, 1);
		// startDate1 = cal1.getTime();
		// payrollSchedule.setPeriodStartDate(startDate1);
		// payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
		// Calendar cal2 = Calendar.getInstance();
		// cal2.setTime(startDate1);
		// cal2.add(Calendar.DATE, noOfDays);
		// scheduleEndDate = cal2.getTime();
		// payrollSchedule.setPeriodEndDate(scheduleEndDate);
		// Calendar cal3 = Calendar.getInstance();
		// cal3.setTime(scheduleEndDate);
		// cal3.add(Calendar.DATE, diffEndDateAndCheckDate);
		// checkDate = cal3.getTime();
		// checkDate = getcheckDate(checkDate,
		// companyPayrollFrequencyForm.getHolidayCheckDateOption());
		// payrollSchedule.setCheckDate(checkDate);
		// break;
		// default:
		// break;
		// }

		logger.debug("Update Payroll Frequency >>");
		return payrollFrequencyDao.update(frequency);
	}

	private void updateBiweeklyMonthlyPayrollSchedule(Date endDate, Date startDate1,
			Date checkDate, CompanyPayrollFrequencyForm companyPayrollFrequencyForm,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate)
			throws InstanceNotFoundException {
		// Fetching PayrollSchedules for this payrollFrequency
		Set<PayrollSchedule> payrollSchedules = payrollFrequency.getPayrollSchedules();
		Set<PayrollSchedule> sortedPayrollSchedules = new TreeSet<PayrollSchedule>();
		Iterator<PayrollSchedule> iter = payrollSchedules.iterator();
		while (iter.hasNext()) {
			sortedPayrollSchedules.add(iter.next());
		}
		logger.debug("sortedPayrollSchedules::" + sortedPayrollSchedules);
		// Updating current Payroll Schedules
		Iterator<PayrollSchedule> iterator = sortedPayrollSchedules.iterator();
		int cuurentYear = startDate1.getYear();
		List<Long> updatedPayrollScheduleId = new ArrayList<>();
		for (int i = 1; i <= 26; i++) {
			PayrollSchedule payrollSchedule = null;
			if (iterator.hasNext()) {
				payrollSchedule = iterator.next();
			} else {
				payrollSchedule = new PayrollSchedule();
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				payrollSchedule.setPayrollFrequency(payrollFrequency);

			}

			if (payrollSchedule.getStatus() == com.epayroll.entity.PayrollSchedule.Status.CURRENT) {
				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setPeriodEndDate(endDate);
				payrollSchedule.setCheckDate(checkDate);
				updatedPayrollScheduleId.add(payrollSchedule.getId());
			}

			// Updating future Payroll Schedules
			switch (payrollSchedule.getStatus()) {
			case FUTURE:
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(endDate);
				cal1.add(Calendar.DATE, 1);
				startDate1 = cal1.getTime();

				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(startDate1);
				cal2.add(Calendar.DATE, 13);
				endDate = cal2.getTime();
				payrollSchedule.setPeriodEndDate(endDate);
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(endDate);
				cal3.add(Calendar.DATE, diffEndDateAndCheckDate);
				checkDate = cal3.getTime();
				checkDate = getcheckDate(checkDate,
						companyPayrollFrequencyForm.getHolidayCheckDateOption());
				payrollSchedule.setCheckDate(checkDate);
				payrollScheduleDao.save(payrollSchedule);
				updatedPayrollScheduleId.add(payrollSchedule.getId());
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(endDate);
				cal4.add(Calendar.DATE, 1);
				startDate1 = cal4.getTime();
			}
			logger.debug("startDate: " + startDate1);
			logger.debug("startDate.getYear(): " + startDate1.getYear() + ", cuurentYear:"
					+ cuurentYear);
			if (startDate1.getYear() != cuurentYear) {
				payrollScheduleDao.deletePayrollSchedules(updatedPayrollScheduleId,
						PayFrequencyType.BI_WEEKLY, payrollFrequency.getId());
				break;
			}
			logger.debug("startDate.getYear(): " + startDate1.getYear() + ", cuurentYear:"
					+ cuurentYear);
			payrollFrequency.setPayrollSchedules(sortedPayrollSchedules);
		}
	}

	private void updateWeeklyPayrollSchedule(Date endDate, Date startDate1, Date checkDate,
			CompanyPayrollFrequencyForm companyPayrollFrequencyForm,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate)
			throws InstanceNotFoundException {
		// Fetching PayrollSchedules for this payrollFrequency
		Set<PayrollSchedule> payrollSchedules = payrollFrequency.getPayrollSchedules();
		Set<PayrollSchedule> sortedPayrollSchedules = new TreeSet<PayrollSchedule>();
		Iterator<PayrollSchedule> iter = payrollSchedules.iterator();
		List<Long> updatedPayrollScheduleId = new ArrayList<>();
		while (iter.hasNext()) {
			sortedPayrollSchedules.add(iter.next());
		}
		logger.debug("sortedPayrollSchedules::" + sortedPayrollSchedules);
		// Updating current Payroll Schedules
		Iterator<PayrollSchedule> iterator = sortedPayrollSchedules.iterator();
		int cuurentYear = startDate1.getYear();
		for (int i = 1; i < 50; i++) {
			PayrollSchedule payrollSchedule = null;
			if (iterator.hasNext()) {
				payrollSchedule = iterator.next();
			} else {
				payrollSchedule = new PayrollSchedule();
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				payrollSchedule.setPayrollFrequency(payrollFrequency);
			}
			if (payrollSchedule.getStatus() == com.epayroll.entity.PayrollSchedule.Status.CURRENT) {
				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setPeriodEndDate(endDate);
				payrollSchedule.setCheckDate(checkDate);
				updatedPayrollScheduleId.add(payrollSchedule.getId());
			}
			logger.debug("payrollSchedule.getStatus()" + payrollSchedule.getStatus());
			// Updating future Payroll Schedules
			switch (payrollSchedule.getStatus()) {
			case FUTURE:
				logger.debug("payrollSchedule.getStatus()" + payrollSchedule.getStatus());
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(endDate);
				cal1.add(Calendar.DATE, 1);
				startDate1 = cal1.getTime();

				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(startDate1);
				cal2.add(Calendar.DATE, 6);
				endDate = cal2.getTime();
				payrollSchedule.setPeriodEndDate(endDate);
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(endDate);
				cal3.add(Calendar.DATE, diffEndDateAndCheckDate);
				checkDate = cal3.getTime();
				checkDate = getcheckDate(checkDate,
						companyPayrollFrequencyForm.getHolidayCheckDateOption());
				payrollSchedule.setCheckDate(checkDate);
				payrollScheduleDao.save(payrollSchedule);
				updatedPayrollScheduleId.add(payrollSchedule.getId());
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(endDate);
				cal4.add(Calendar.DATE, 1);
				startDate1 = cal4.getTime();
			}
			logger.debug("startDate: " + startDate1);
			logger.debug("startDate.getYear(): " + startDate1.getYear() + ", cuurentYear:"
					+ cuurentYear);
			if (startDate1.getYear() != cuurentYear) {
				payrollScheduleDao.deletePayrollSchedules(updatedPayrollScheduleId,
						PayFrequencyType.WEEKLY, payrollFrequency.getId());
				break;
			}
			logger.debug("startDate.getYear(): " + startDate1.getYear() + ", cuurentYear:"
					+ cuurentYear);
		}
		payrollFrequency.setPayrollSchedules(sortedPayrollSchedules);
	}

	private void updateSemiMonthlyPayrollSchedule(Date endDate, Date startDate1, Date checkDate,
			CompanyPayrollFrequencyForm companyPayrollFrequencyForm,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate)
			throws InstanceNotFoundException {
		// Fetching PayrollSchedules for this payrollFrequency
		Set<PayrollSchedule> payrollSchedules = payrollFrequency.getPayrollSchedules();
		Set<PayrollSchedule> sortedPayrollSchedules = new TreeSet<PayrollSchedule>();
		Iterator<PayrollSchedule> iter = payrollSchedules.iterator();
		List<Long> updatedPayrollScheduleId = new ArrayList<>();
		while (iter.hasNext()) {
			sortedPayrollSchedules.add(iter.next());
		}
		logger.debug("sortedPayrollSchedules::" + sortedPayrollSchedules);
		// Updating current Payroll Schedules
		Iterator<PayrollSchedule> iterator = sortedPayrollSchedules.iterator();
		int cuurentYear = startDate1.getYear();
		for (int i = 1; i <= 26; i++) {
			PayrollSchedule payrollSchedule = null;
			if (iterator.hasNext()) {
				payrollSchedule = iterator.next();
			} else {
				payrollSchedule = new PayrollSchedule();
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				payrollSchedule.setPayrollFrequency(payrollFrequency);
			}
			if (payrollSchedule.getStatus() == com.epayroll.entity.PayrollSchedule.Status.CURRENT) {
				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setPeriodEndDate(endDate);
				payrollSchedule.setCheckDate(checkDate);
				updatedPayrollScheduleId.add(payrollSchedule.getId());
			}

			// Updating future Payroll Schedules
			switch (payrollSchedule.getStatus()) {
			case FUTURE:
				int loop = 2;
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(endDate);
				cal1.add(Calendar.DATE, 1);
				startDate1 = cal1.getTime();

				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(startDate1);
				Integer m = null;
				Integer a = null;
				if (loop % 2 == 0) {
					m = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					a = (int) Math.floor((double) m / 2);
					loop = 1;
				} else {
					m = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					a = (int) Math.ceil((double) m / 2);
					loop = 2;
				}

				cal.add(Calendar.DAY_OF_MONTH, a);
				cal.add(Calendar.DATE, -1);
				endDate = cal.getTime();
				payrollSchedule.setPeriodEndDate(endDate);
				logger.debug("M: " + m + ", A:" + a + ", ST:" + startDate1 + " , ED:" + endDate);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(endDate);
				cal2.add(Calendar.DATE, diffEndDateAndCheckDate);
				checkDate = cal2.getTime();
				checkDate = getcheckDate(checkDate,
						companyPayrollFrequencyForm.getHolidayCheckDateOption());
				payrollSchedule.setCheckDate(checkDate);
				if (startDate1.getMonth() < endDate.getMonth()) {
					loop = 2;
				}
				startDate1 = get1DayAfter(endDate);
				payrollScheduleDao.save(payrollSchedule);
				updatedPayrollScheduleId.add(payrollSchedule.getId());
			}
			logger.debug("startDate.getYear(): " + startDate1.getYear() + ", cuurentYear:"
					+ cuurentYear);
			if (startDate1.getYear() != cuurentYear) {
				logger.debug("startDate.getYear(): " + startDate1.getYear() + ", cuurentYear:"
						+ cuurentYear);
				payrollScheduleDao.deletePayrollSchedules(updatedPayrollScheduleId,
						PayFrequencyType.SEMI_MONTHLY, payrollFrequency.getId());
				break;
			}
		}
		payrollFrequency.setPayrollSchedules(sortedPayrollSchedules);
	}

	private void updateMonthlyPayrollSchedule(Date endDate, Date startDate1, Date checkDate,
			CompanyPayrollFrequencyForm companyPayrollFrequencyForm,
			PayrollFrequency payrollFrequency, int diffEndDateAndCheckDate)
			throws InstanceNotFoundException {
		// Fetching PayrollSchedules for this payrollFrequency
		Set<PayrollSchedule> payrollSchedules = payrollFrequency.getPayrollSchedules();
		Set<PayrollSchedule> sortedPayrollSchedules = new TreeSet<PayrollSchedule>();
		Iterator<PayrollSchedule> iter = payrollSchedules.iterator();
		while (iter.hasNext()) {
			sortedPayrollSchedules.add(iter.next());
		}
		logger.debug("sortedPayrollSchedules::" + sortedPayrollSchedules);
		// Updating current Payroll Schedules
		Iterator<PayrollSchedule> iterator = sortedPayrollSchedules.iterator();
		int cuurentYear = startDate1.getYear();

		List<Long> updatedPayrollScheduleId = new ArrayList<>();
		for (int i = 1; i <= 13; i++) {
			PayrollSchedule payrollSchedule = null;
			if (iterator.hasNext()) {
				payrollSchedule = iterator.next();
			} else {
				payrollSchedule = new PayrollSchedule();
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				payrollSchedule.setPayrollFrequency(payrollFrequency);
			}
			if (payrollSchedule.getStatus() == com.epayroll.entity.PayrollSchedule.Status.CURRENT) {
				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setPeriodEndDate(endDate);
				payrollSchedule.setCheckDate(checkDate);
				updatedPayrollScheduleId.add(payrollSchedule.getId());
			}

			// Updating future Payroll Schedules
			switch (payrollSchedule.getStatus()) {
			case FUTURE:

				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(endDate);
				cal1.add(Calendar.DATE, 1);
				startDate1 = cal1.getTime();
				payrollSchedule.setPeriodStartDate(startDate1);
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(startDate1);
				cal2.add(Calendar.MONTH, 1);
				cal2.add(Calendar.DATE, -1);
				endDate = cal2.getTime();
				payrollSchedule.setPeriodEndDate(endDate);
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(endDate);
				cal3.add(Calendar.DATE, diffEndDateAndCheckDate);
				checkDate = cal3.getTime();
				checkDate = getcheckDate(checkDate,
						companyPayrollFrequencyForm.getHolidayCheckDateOption());
				payrollSchedule.setCheckDate(checkDate);
				payrollScheduleDao.save(payrollSchedule);
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(endDate);
				cal4.add(Calendar.DATE, 1);
				startDate1 = cal4.getTime();
				updatedPayrollScheduleId.add(payrollSchedule.getId());

			}

			if (startDate1.getYear() != cuurentYear) {
				payrollScheduleDao.deletePayrollSchedules(updatedPayrollScheduleId,
						PayFrequencyType.MONTHLY, payrollFrequency.getId());
				break;
			}
		}
		payrollFrequency.setPayrollSchedules(sortedPayrollSchedules);
	}

	private Date getEndDate(PayFrequencyType frequencyType, Date periodStartDate) {
		logger.debug("getEndDate(PayFrequencyType frequencyType, Date periodStartDate) .."
				+ periodStartDate);
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(periodStartDate);

		switch (frequencyType) {
		case MONTHLY:
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			break;
		case SEMI_MONTHLY:
			int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int addNoOfDays = (int) Math.floor((double) maxDay / 2);
			cal.add(Calendar.DAY_OF_MONTH, addNoOfDays - 1);
			// cal.add(Calendar.DAY_OF_MONTH, -1);
			break;
		case WEEKLY:
			cal.add(Calendar.DATE, 6);
			// cal.add(Calendar.DATE, -1);
			break;
		case BI_WEEKLY:
			cal.add(Calendar.DATE, 13);
			// cal.add(Calendar.DATE, -1);
			break;

		default:
			break;
		}

		Date endDate = cal.getTime();
		return endDate;
	}

	@Override
	@Transactional
	public boolean deletePayrollFrequency(Long payrollFrequencyId) throws InstanceNotFoundException {
		logger.debug(">>Delete Payroll Frequency");
		Long count = payrollDao.getPayrollFrequencyCount(payrollFrequencyId);
		logger.debug("count..:" + count);
		if (count <= 0) {
			payrollFrequencyDao.remove(payrollFrequencyId);
			logger.debug("Delete Payroll Frequency>>");
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public PayrollSchedule deletePayrollSchedule(Long id) throws InstanceNotFoundException {
		logger.debug(">> deletePayrollSchedule");
		Long count = payrollDao.getPayrollScheduleCount(id);
		logger.debug("count..:" + count);
		if (count <= 0) {
			PayrollSchedule payrollSchedule = payrollScheduleDao.find(id);
			com.epayroll.entity.PayrollSchedule.Status payrollScheduleStatus = payrollSchedule
					.getStatus();
			payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.DELETED);
			payrollSchedule = payrollScheduleDao.update(payrollSchedule);
			if (payrollScheduleStatus.getName().equalsIgnoreCase(
					PayrollSchedule.Status.CURRENT.getName())) {
				PayrollSchedule nextFuturePayrollSchedule = payrollScheduleDao
						.getNextFuturePayrollSchedule(payrollSchedule.getPayrollFrequency().getId());
				nextFuturePayrollSchedule
						.setStatus(com.epayroll.entity.PayrollSchedule.Status.CURRENT);
				payrollScheduleDao.update(nextFuturePayrollSchedule);
			}
			logger.debug("deletePayrollSchedule >>");

			return payrollSchedule;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public List<CompanyWorkerCompensation> getWorkersCompansations(Long companyId) {
		List<CompanyWorkerCompensation> companyWorkerCompensations = companyWorkersCompensationDao
				.getWorkersCompensations(companyId);
		return companyWorkerCompensations;
	}

	@Override
	public List<WorkersCompensationTaxRateView> getWorkersCompensationRate(Long companyId) {
		List<WorkersCompensationTaxRateView> compensationTaxRateViewList = new ArrayList<>();
		compensationTaxRateViewList = workersCompensationTaxRateViewDao
				.getWorkersCompensationRates(companyId);
		logger.debug("compensationTaxRateView:::" + compensationTaxRateViewList);
		return compensationTaxRateViewList;
	}

	@Override
	@Transactional
	public List<UsState> getUsStates() {
		return usStateDao.getEntities();
	}

	@Override
	@Transactional
	public Long addWorkersCompensation(Company company,
			CompanyWorkersCompensationForm companyWorkersCompensationForm) {
		logger.debug(">>Add Workers Compensation");
		CompanyDeduction companyDeduction = companyDeductionDao.getworkersCompensationDeduction(
				company.getId(), CompanyDeductionName.WORKERS_COMPENSATION);
		UsState usState = usStateDao.getCriteriaForUsState(
				companyWorkersCompensationForm.getStateId()).get(0);
		CompanyWorkerCompensation companyWorkerCompensation = new CompanyWorkerCompensation();
		companyWorkerCompensation.setCode(companyWorkersCompensationForm.getCode());
		companyWorkerCompensation.setDescription(companyWorkersCompensationForm.getDescription());
		companyWorkerCompensation.setCompany(company);
		companyWorkerCompensation.setUsState(usState);
		Long id = companyWorkersCompensationDao.save(companyWorkerCompensation);
		logger.debug("Add Workers Compensation>>");
		return id;
	}

	@Override
	@Transactional
	public CompanyWorkersCompensationForm getWorkersCompensationForm(Company company,
			Long companyWorkersCompensationId) {
		logger.debug(">>Get Workers Compensation Form");
		CompanyWorkerCompensation companyWorkerCompensation = companyWorkersCompensationDao
				.load(companyWorkersCompensationId);
		CompanyWorkersCompensationForm companyWorkersCompensationForm = new CompanyWorkersCompensationForm();
		companyWorkersCompensationForm.setCode(companyWorkerCompensation.getCode());
		companyWorkersCompensationForm.setDescription(companyWorkerCompensation.getDescription());
		companyWorkersCompensationForm.setId(companyWorkerCompensation.getId());
		companyWorkersCompensationForm.setStateId(companyWorkerCompensation.getUsState().getId());
		logger.debug("companyWorkersCompensationForm...." + companyWorkersCompensationForm);
		logger.debug("Get Workers Compensation Form>>");
		return companyWorkersCompensationForm;
	}

	@Override
	@Transactional
	public CompanyWorkerCompensation updateWorkersCompensation(Company company,
			CompanyWorkersCompensationForm companyWorkersCompensationForm)
			throws InstanceNotFoundException {
		logger.debug(">>Update Workers Compensation");
		CompanyWorkerCompensation companyWorkerCompensation = companyWorkersCompensationDao
				.load(companyWorkersCompensationForm.getId());
		companyWorkerCompensation.setCode(companyWorkersCompensationForm.getCode());
		companyWorkerCompensation.setDescription(companyWorkersCompensationForm.getDescription());
		UsState usState = usStateDao.find(companyWorkersCompensationForm.getStateId());
		companyWorkerCompensation.setUsState(usState);
		companyWorkerCompensation = companyWorkersCompensationDao.update(companyWorkerCompensation);
		logger.debug("Update Workers Compensation>>");
		return companyWorkerCompensation;
	}

	@Override
	@Transactional
	public boolean deleteWorkersCompensation(Long id) throws InstanceNotFoundException {
		logger.debug(">>Delete Workers Compensation");
		Long count = companyWorkersCompensationDao.getWorkersCompensationCount(id);
		logger.debug(count + "::::count");
		if (count <= 0) {
			companyWorkersCompensationDao.remove(id);
			logger.debug("Delete Workers Compensation>>");
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public void removeCompany(Company company) throws InstanceNotFoundException {
		companyDao.remove(company.getId());
	}

	@Override
	@Transactional
	public Company findCompany(Long id) throws InstanceNotFoundException {
		return companyDao.find(id);
	}

	@Override
	@Transactional
	public void deleteDepartment(CompanyDepartment companyDepartment)
			throws InstanceNotFoundException {
		logger.debug(">> deleteDepartment");
		companyDepartmentDao.remove(companyDepartment);
		logger.debug(" deleteDepartment >>");
	}

	@Transactional
	private void deleteDepartment(Long id) throws InstanceNotFoundException {
		logger.debug(">> deleteDepartment");
		companyDepartmentDao.remove(id);
		logger.debug(" deleteDepartment >>");
	}

	@Transactional
	@Override
	public CompanyDepartment findDepartment(Long companyDepartmentId)
			throws InstanceNotFoundException {
		logger.debug(">> findDepartment");
		CompanyDepartment companyDepartment = companyDepartmentDao.find(companyDepartmentId);
		logger.debug("findDepartment >>");
		return companyDepartment;
	}

	@Override
	@Transactional
	public List<CompanyBankInfo> getCompanyBankInfo(Company company) {
		logger.debug(">> getCompanyBankInfoForm");
		List<CompanyBankInfo> companyBankInfos = new ArrayList<CompanyBankInfo>();
		Iterator<CompanyBankInfo> iterator = company.getCompanyBankInfos().iterator();
		while (iterator.hasNext()) {
			companyBankInfos.add(iterator.next());
		}
		logger.debug("companyBankInfos :: " + companyBankInfos);
		logger.debug(" getCompanyBankInfoForm >>");
		return companyBankInfos;
	}

	@Override
	@Transactional
	public void addBankInfo(CompanyBankInfo companyBankInfoForm, Company company) {
		logger.debug(">> addBankInfo");

		CompanyBankInfo companyBankInfo = new CompanyBankInfo();
		companyBankInfo.setCompany(company);
		companyBankInfo.setAccountNumber(companyBankInfoForm.getAccountNumber());
		companyBankInfo.setAccountType(companyBankInfoForm.getAccountType());
		companyBankInfo.setBankName(companyBankInfoForm.getBankName());
		companyBankInfo.setCompany(company);
		companyBankInfo.setRoutingNumber(companyBankInfoForm.getRoutingNumber());
		companyBankInfo.setStatus(com.epayroll.entity.CompanyBankInfo.Status.ACTIVE);
		companyBankInfo.setId(companyBankInfoForm.getId());
		companyBankInfoDao.update(companyBankInfo);

		logger.debug(" addBankInfo >>");
	}

	@Override
	@Transactional
	public CompanyDepartment addDepartment(CompanyDepartment companyDepartment) {
		logger.debug(">> addDepartment");
		companyDepartment.setStatus(Status.ACTIVE);
		long id = companyDepartmentDao.save(companyDepartment);
		companyDepartment.setId(id);
		logger.debug(" addDepartment >>");
		return companyDepartment;
	}

	@Override
	@Transactional
	public CompanyDepartment updateDepartment(CompanyDepartment companyDepartment) {
		logger.debug(">> updateDepartment");
		// companyDepartment.setStatus(Status.ACTIVE);
		companyDepartment = companyDepartmentDao.update(companyDepartment);
		logger.debug(" updateDepartment >>");
		return companyDepartment;

	}

	@Override
	@Transactional
	public CompanyTax findTax(Long taxId) throws InstanceNotFoundException {
		logger.debug(">> findTax");
		CompanyTax companyTax = companyTaxDao.find(taxId);
		logger.debug("findTax >>");
		return companyTax;
	}

	@Override
	@Transactional
	public void updateTaxDepositCycle(CompanyTaxForm companyTaxForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateTaxDepositCycle");
		CompanyTax companyTax = companyTaxDao.find(companyTaxForm.getId());
		companyTax.setTaxDepositCycle(taxDepositCycleDao.find(companyTaxForm.getDepositCycleId()));
		companyTaxDao.update(companyTax);
		logger.debug(" updateTaxDepositCycle >>");
	}

	@Override
	public void updateCompanyTax(CompanyTaxForm companyTaxForm, Company company)
			throws InstanceNotFoundException {
		logger.debug(">> updateCompanyTax");
		CompanyTax companyTax = companyTaxDao.find(companyTaxForm.getId());
		companyTax.setCompany(company);
		companyTax.setEin(companyTaxForm.getEin());
		companyTax.setExempted(companyTaxForm.getExempted());
		companyTax.setTaxType(taxTypeDao.find(companyTaxForm.getTaxTypeId()));
		companyTax.setTaxDepositCycle(taxDepositCycleDao.find(companyTaxForm.getDepositCycleId()));
		// companyTax.setCompanyPayrollTaxes()
		companyTaxDao.update(companyTax);
		logger.debug(" updateCompanyTax >>");

	}

	@Override
	@Transactional
	public CompanyEarning addCompanyEarning(Company company, CompanyEarningForm companyEarningForm)
			throws InstanceNotFoundException {
		logger.debug(">> addCompanyEarning");
		CompanyEarning companyEarning = new CompanyEarning();
		companyEarning.setCompany(company);
		companyEarning.setEarning(earningDao.find(companyEarningForm.getEarningId()));
		companyEarning.setDisplayName(companyEarningForm.getDisplayName());
		long id = companyEarningDao.save(companyEarning);
		companyEarning.setId(id);
		logger.debug(" addCompanyEarning >>");
		return companyEarning;
	}

	@Override
	@Transactional
	public CompanyDeduction addCompanyDeduction(Company company,
			CompanyDeductionForm companyDeductionForm) throws InstanceNotFoundException {
		logger.debug(">> addDeduction");
		CompanyDeduction companyDeduction = new CompanyDeduction();
		companyDeduction.setCompany(company);
		companyDeduction.setDeduction(deductionDao.find(companyDeductionForm.getDeductionId()));
		companyDeduction.setDisplayName(companyDeductionForm.getDisplayName());
		long id = companyDeductionDao.save(companyDeduction);
		companyDeduction.setId(id);
		logger.debug(" addDeduction >>");
		return companyDeduction;
	}

	@Override
	@Transactional
	public void updateCompanyEarningDisplayName(CompanyEarningForm companyEarningForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateCompanyEarningDisplayName");
		CompanyEarning companyEarning = companyEarningDao.find(companyEarningForm.getId());
		companyEarning.setDisplayName(companyEarningForm.getDisplayName());
		companyEarningDao.update(companyEarning);
		logger.debug(" updateCompanyEarningDisplayName >>");
	}

	@Override
	@Transactional
	public CompanyDeduction updateCompanyDeductionDisplayName(
			CompanyDeductionForm companyDeductionForm) throws InstanceNotFoundException {
		logger.debug(">> updateDeductionDisplayName");
		CompanyDeduction companyDeduction = companyDeductionDao.find(companyDeductionForm.getId());
		companyDeduction.setDisplayName(companyDeductionForm.getDisplayName());
		logger.debug(" updateDeductionDisplayName >>");
		return companyDeductionDao.update(companyDeduction);
	}

	@Override
	@Transactional
	public User getCompanyUser(Long companyUserId) throws InstanceNotFoundException {
		logger.debug(">> getCompanyUser");
		User user = userDao.find(companyUserId);
		logger.debug("getCompanyUser >>");
		return user;
	}

	@Override
	@Transactional
	public void updateDepartmentStatus(Long id, Status status) throws InstanceNotFoundException {
		logger.debug(">> updateDepartmentStatus");
		CompanyDepartment companyDepartment = companyDepartmentDao.find(id);
		companyDepartment.setStatus(status);
		companyDepartmentDao.update(companyDepartment);
		logger.debug(" updateDepartmentStatus >>");
	}

	@Override
	@Transactional
	public CompanyTaxForm getCompanyTaxForm(Long companyTaxId) throws InstanceNotFoundException {
		logger.debug(">> getCompanyTaxForm");
		CompanyTaxForm companyTaxForm = new CompanyTaxForm();
		CompanyTax companyTax = companyTaxDao.find(companyTaxId);
		companyTaxForm.setId(companyTaxId);
		companyTaxForm.setTaxTypeId(companyTax.getTaxType().getId());
		companyTaxForm.setExempted(companyTax.isExempted());
		companyTaxForm.setDepositCycleId(companyTax.getTaxDepositCycle().getId());
		companyTaxForm.setEin(companyTax.getEin());
		logger.debug(" getCompanyTaxForm >>");
		return companyTaxForm;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Earning> getEarnings(Long earningCategoryId) throws InstanceNotFoundException {
		logger.debug(">> getEarnings");
		List<Earning> earnings = earningDao.getCriteriaForEarnings(earningCategoryId).list();
		logger.debug("getEarnings >>");
		return earnings;
	}

	@Override
	@Transactional
	public List<EarningCategory> getEarningCategories() {
		logger.debug(">> getEarningCategories");
		List<EarningCategory> earningCategories = earningCategoryDao.getEntities();
		logger.debug("getEarningCategories >>");
		return earningCategories;
	}

	@Override
	@Transactional
	public CompanyEarningForm getCompanyEarningForm(Long id) throws InstanceNotFoundException {
		logger.debug(">> getCompanyEarningForm");
		CompanyEarningForm companyEarningForm = new CompanyEarningForm();
		CompanyEarning companyEarning = companyEarningDao.find(id);
		companyEarningForm.setDisplayName(companyEarning.getDisplayName());
		companyEarningForm.setEarningId(companyEarning.getEarning().getId());
		companyEarningForm.setDescription(companyEarning.getEarning().getDescription());
		companyEarningForm.setId(companyEarning.getId());
		companyEarningForm.setEarningCategoryId(companyEarning.getEarning().getEarningCategory()
				.getId());
		logger.debug("getCompanyEarningForm >>");
		return companyEarningForm;
	}

	@Transactional
	private void deleteCompanyEarning(Long id) throws InstanceNotFoundException {
		logger.debug(">> deleteCompanyEarning");
		companyEarningDao.remove(id);
		logger.debug("deleteCompanyEarning >>");

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Deduction> getDeductions(Long deductionCategoryId) throws InstanceNotFoundException {
		logger.debug(">> getDeductions");
		List<Deduction> deductions = deductionDao.getCriteriaForDeductions(deductionCategoryId)
				.list();
		logger.debug("getDeductions >>");
		return deductions;
	}

	@Override
	@Transactional
	public CompanyDeductionForm getCompanyDeductionForm(Long id) throws InstanceNotFoundException {
		logger.debug(">> getCompanyDeductionForm");
		CompanyDeductionForm companyDeductionForm = new CompanyDeductionForm();
		CompanyDeduction companyDeduction = companyDeductionDao.find(id);
		companyDeductionForm.setDisplayName(companyDeduction.getDisplayName());
		companyDeductionForm.setDeductionId(companyDeduction.getDeduction().getId());
		companyDeductionForm.setDescription(companyDeduction.getDeduction().getDescription());
		companyDeductionForm.setId(companyDeduction.getId());
		companyDeductionForm.setDeductionCategoryId(companyDeduction.getDeduction()
				.getDeductionCategory().getId());
		companyDeductionForm.setDeductionName(companyDeduction.getDeduction().getDeductionName());
		companyDeductionForm.setDeductionCategoryName(companyDeduction.getDeduction()
				.getDeductionCategory().getCategory());
		logger.debug(" getCompanyDeductionForm >>");
		return companyDeductionForm;
	}

	@Override
	@Transactional
	public List<DeductionCategory> getDeductionCategories() {
		logger.debug(">> getDeductionCategories");
		List<DeductionCategory> deductionCategories = deductionCategoryDao.getEntities();
		logger.debug("getDeductionCategories >>");
		return deductionCategories;
	}

	@Transactional
	private void deleteCompanyDeduction(Long id) throws InstanceNotFoundException {
		logger.debug(">> deleteDeduction");
		companyDeductionDao.remove(id);
		logger.debug("deleteDeduction >>");

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompanyDepartment> getCompanyDepartments(Company company) {
		logger.debug(">> getCompanyDepartments");
		List<CompanyDepartment> companyDepartments = companyDepartmentDao
				.getCriteriaForDepartmentList(company).list();
		logger.debug("companyDepartments  ::" + companyDepartments);
		logger.debug("getCompanyDepartments >>");
		return companyDepartments;
	}

	@Override
	@Transactional
	public List<CompanyEarning> getCompanyEarnings(Company company) {
		logger.debug(">> getCompanyEarnings");
		List<CompanyEarning> companyEarnings = companyEarningDao
				.getCompanyEarnings(company.getId());
		logger.debug("getCompanyEarnings >>");
		return companyEarnings;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompanyDeduction> getCompanyDeductions(Company company) {
		logger.debug(">> getCompanyDeductions");
		List<CompanyDeduction> companyDeductions = companyDeductionDao.getCriteriaForDeductionList(
				company).list();
		logger.debug("getCompanyDeductions >>");
		return companyDeductions;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompanyTax> getCompanyTaxes(Company company) {
		logger.debug(">> getCompanyTaxes");
		List<CompanyTax> companyTaxes = companyTaxDao.getCriteriaForTaxList(company).list();
		logger.debug(" getCompanyTaxes >>");
		return companyTaxes;
	}

	@Override
	@Transactional
	public Boolean verifyAndDeleteCompanyEarning(Long id) throws InstanceNotFoundException {
		logger.debug(">> verifyAndDeleteCompanyEarning");
		if (companyEarningDao.getPayrollCount(id) <= 0) {
			deleteCompanyEarning(id);
			logger.debug("verifyAndDeleteCompanyEarning >>");
			return true;
		} else {
			logger.debug("verifyAndDeleteCompanyEarning >>");
			return false;
		}

	}

	@Override
	@Transactional
	public Boolean verifyAndDeleteDepartment(Long id) throws InstanceNotFoundException {
		logger.debug(">> verifyAndDeleteDepartment");
		if (companyDepartmentDao.getPayrollCount(id) <= 0) {
			deleteDepartment(id);
			logger.debug("verifyAndDeleteDepartment >>");
			return true;
		} else {
			logger.debug("verifyAndDeleteDepartment >>");
			return false;
		}

	}

	@Override
	@Transactional
	public Boolean verifyAndDeleteCompanyDeduction(Long id) throws InstanceNotFoundException {
		logger.debug(">> verifyAndDeleteDeduction ");
		if (companyDeductionDao.getEmployeePayrollDeductionCount(id) <= 0
				&& companyDeductionDao.getCompanyPayrollDeductionCount(id) <= 0) {
			deleteCompanyDeduction(id);
			logger.debug("verifyAndDeleteDeduction >>");
			return true;
		} else {
			logger.debug("verifyAndDeleteDeduction >>");
			return false;
		}
	}

	@Override
	public Set<SystemMessage> getMessages() throws InstanceNotFoundException {
		// return userDao.find(1L).getSystemMessages();
		return null;
	}

	@Override
	public List<CompanyTax> getCompanyFederalTaxList(Long companyId) {
		logger.debug(">> getCompanyFederalTaxList");
		List<CompanyTax> federalTaxList = companyTaxDao.getFederalTaxList(companyId);
		logger.debug("getCompanyFederalTaxList >>");
		return federalTaxList;
	}

	@Override
	public List<CompanyTax> getCompanyStateTaxList(Long companyId) {
		logger.debug(">> getCompanyStateTaxList");
		List<CompanyTax> stateTaxList = companyTaxDao.getStateTaxList(companyId);
		logger.debug("getCompanyStateTaxList >>");
		return stateTaxList;
	}

	@Override
	public List<CompanyTax> getCompanyLocalTaxList(Long companyId) {
		logger.debug(">> getCompanyLocalTaxList");
		List<CompanyTax> localTaxList = companyTaxDao.getLocalTaxList(companyId);
		logger.debug("getCompanyLocalTaxList >>");
		return localTaxList;
	}

	@Override
	public List<Company> getCompanyList() {
		logger.debug("getCompanyList");
		List<Company> companyList = companyDao.getEntities();
		logger.debug("companyList :: " + companyList);
		logger.debug("getCompanyList");
		return companyList;
	}

	@Override
	public List<UsCity> getUsCities() {
		logger.debug("getUsCities");
		List<UsCity> usCityList = usCityDao.getEntities();
		logger.debug("usCityList :: " + usCityList);
		logger.debug("getUsCities");
		return usCityList;
	}

	@Override
	public List<Object[]> getSearchedCompanyList(CompanyForm companyForm) {
		logger.debug(">> getSearchedCompanyList");
		List<Object[]> searchedCompanyList = companyDao.getSearchedCompanyList(companyForm);
		for (Object[] o : searchedCompanyList) {
			for (Object o1 : o) {
				logger.debug("value : " + o1);
			}
		}
		logger.debug(">> getSearchedCompanyList");
		return searchedCompanyList;
	}

	@Override
	public boolean verificationForDeleteCompany(Long companyId) throws InstanceNotFoundException {
		Company company = companyDao.find(companyId);
		logger.debug(">> verificationForDeleteCompany");
		if (company.getPayrolls().size() <= 0) {
			logger.debug("verificationForDeleteCompany >>");
			return true;
		} else {
			logger.debug("verificationForDeleteCompany >>");
			return false;
		}

	}

	@Override
	public void deleteCompany(Long companyId) throws InstanceNotFoundException {
		logger.debug(">> deleteCompany");
		companyDao.remove(companyId);
		logger.debug("deleteCompany >>");
	}

	@Override
	public void deactivateCompany(Long companyId) {
		logger.debug(">> deactivateCompany");
		companyDao.deactivateCompany(companyId);
		companyUserDao.deactivateCompanyUsers(companyId);
		logger.debug("deactivateCompany >>");
	}

	@Override
	public void reactivateCompany(Long companyId) {
		logger.debug(">> reactivateCompany");
		companyDao.reactivateCompany(companyId);
		companyUserDao.reactivateCompanyUsers(companyId);
		logger.debug("reactivateCompany >>");

	}

	@Override
	public CompanyBankInfo getBankInfo(Long companyBankInfoId) throws InstanceNotFoundException {
		logger.debug(">> getBankInfo");
		CompanyBankInfo companyBankInfo = companyBankInfoDao.find(companyBankInfoId);
		logger.debug("companyBankInfo :: " + companyBankInfo);
		logger.debug("getBankInfo >>");
		return companyBankInfo;
	}

	@Override
	public void updateCompanyBankInfo(CompanyBankInfo companyBankInfo) {
		logger.debug(">> getBankInfo");
		companyBankInfoDao.update(companyBankInfo);
		logger.debug("getBankInfo >>");

	}

	@Override
	public TaxType getFutaTaxType(Long companyId) {
		TaxType taxType = taxTypeDao.getFutaTaxTypeList(companyId);
		return taxType;
	}

	@Override
	public CompanyTaxForm getFederalTaxForm(Long companyId) {
		CompanyTaxForm companyTaxForm = new CompanyTaxForm();
		companyTaxForm.setCompanyId(companyId);
		return companyTaxForm;

	}

	@Override
	public List<TaxDepositCycle> getDepositCycle() {
		return taxDepositCycleDao.getEntities();

	}

	@Override
	public CompanyStateLocalTaxFrom getStateTaxForm(Long companyId) {
		CompanyStateLocalTaxFrom companyStateLocalTaxFrom = new CompanyStateLocalTaxFrom();
		companyStateLocalTaxFrom.setCompanyId(companyId);
		List<TaxType> taxTypes = taxTypeDao.getStateTaxTypeList(companyId);
		List<CompanyStateLocalTaxModel> companyStateLocalTaxModels = new ArrayList<>();
		CompanyStateLocalTaxModel companyStateLocalTaxModel = null;
		for (TaxType taxType : taxTypes) {
			logger.debug("taxType:::" + taxType);
			companyStateLocalTaxModel = new CompanyStateLocalTaxModel();
			companyStateLocalTaxModel.setTaxTypeId(taxType.getId());
			companyStateLocalTaxModel.setTaxName(taxType.getTaxName());
			companyStateLocalTaxModels.add(companyStateLocalTaxModel);
			logger.debug("companyStateLocalTaxModels" + companyStateLocalTaxModels);
		}
		companyStateLocalTaxFrom.setCompanyStateLocalTaxModels(companyStateLocalTaxModels);
		logger.debug("companyStateLocalTaxFrom" + companyStateLocalTaxFrom);
		return companyStateLocalTaxFrom;
	}

	@Override
	public CompanyStateLocalTaxFrom getLocalTaxForm(Long companyId) {
		CompanyStateLocalTaxFrom companyStateLocalTaxFrom = new CompanyStateLocalTaxFrom();
		companyStateLocalTaxFrom.setCompanyId(companyId);
		List<TaxType> taxTypes = taxTypeDao.getLocalTaxTypeList(companyId);
		List<CompanyStateLocalTaxModel> companyStateLocalTaxModels = new ArrayList<>();
		CompanyStateLocalTaxModel companyStateLocalTaxModel = null;
		for (TaxType taxType : taxTypes) {
			companyStateLocalTaxModel = new CompanyStateLocalTaxModel();
			companyStateLocalTaxModel.setTaxTypeId(taxType.getId());
			companyStateLocalTaxModel.setTaxName(taxType.getTaxName());
			companyStateLocalTaxModels.add(companyStateLocalTaxModel);
			logger.debug("companyStateLocalTaxModels" + companyStateLocalTaxModels);
		}
		companyStateLocalTaxFrom.setCompanyStateLocalTaxModels(companyStateLocalTaxModels);
		logger.debug("companyStateLocalTaxFrom" + companyStateLocalTaxFrom);
		return companyStateLocalTaxFrom;
	}

	@Override
	@Transactional
	public void addFederalTax(CompanyTaxForm companyTaxForm) throws InstanceNotFoundException {
		CompanyTax companyTax = new CompanyTax();
		companyTax.setCompany(companyDao.find(companyTaxForm.getCompanyId()));
		companyTax.setEin(companyTaxForm.getEin());
		companyTax.setExempted(companyTaxForm.getExempted());
		companyTax.setTaxDepositCycle(taxDepositCycleDao.find(companyTaxForm.getDepositCycleId()));
		companyTax.setTaxType(taxTypeDao.find(companyTaxForm.getTaxTypeId()));
		companyTaxDao.save(companyTax);
	}

	@Override
	@Transactional
	public List<CompanyTax> addStateTax(CompanyStateLocalTaxFrom companyStateLocalTaxFrom)
			throws InstanceNotFoundException {
		CompanyTax companyTax = null;
		Company company = companyDao.find(companyStateLocalTaxFrom.getCompanyId());
		List<CompanyTax> companyTaxes = new ArrayList<>();
		for (CompanyStateLocalTaxModel companyStateLocalTaxModel : companyStateLocalTaxFrom
				.getCompanyStateLocalTaxModels()) {
			companyTax = new CompanyTax();
			companyTax.setCompany(company);
			companyTax.setEin(companyStateLocalTaxModel.getEin());
			companyTax.setExempted(companyStateLocalTaxModel.getExempted());
			companyTax.setTaxDepositCycle(taxDepositCycleDao.find(companyStateLocalTaxModel
					.getDepositCycleId()));
			companyTax.setTaxType(taxTypeDao.find(companyStateLocalTaxModel.getTaxTypeId()));
			companyTaxes.add(companyTax);
			companyTaxDao.save(companyTax);

		}
		return companyTaxes;

	}

	@Override
	@Transactional
	public List<CompanyTax> addLocalTax(CompanyStateLocalTaxFrom companyStateLocalTaxFrom)
			throws InstanceNotFoundException {
		CompanyTax companyTax = null;
		Company company = companyDao.find(companyStateLocalTaxFrom.getCompanyId());
		List<CompanyTax> companyTaxes = new ArrayList<>();
		for (CompanyStateLocalTaxModel companyStateLocalTaxModel : companyStateLocalTaxFrom
				.getCompanyStateLocalTaxModels()) {
			companyTax = new CompanyTax();
			companyTax.setCompany(company);
			companyTax.setEin(companyStateLocalTaxModel.getEin());
			companyTax.setExempted(companyStateLocalTaxModel.getExempted());
			companyTax.setTaxDepositCycle(taxDepositCycleDao.find(companyStateLocalTaxModel
					.getDepositCycleId()));
			companyTax.setTaxType(taxTypeDao.find(companyStateLocalTaxModel.getTaxTypeId()));
			companyTaxes.add(companyTax);
			companyTaxDao.save(companyTax);
		}
		return companyTaxes;

	}

	@Override
	public Deduction getDeduction(Long deductionId) throws InstanceNotFoundException {
		return deductionDao.find(deductionId);
	}

	@Override
	public List<String> getholidayCheckDateOption() {
		List<String> strings = new ArrayList<>();
		strings.add(HolidayCheckDateOption.BEFORE);
		strings.add(HolidayCheckDateOption.AFTER);
		return strings;
	}

	@Override
	public Date getEndDate(Date periodStartDate, PayFrequencyType frequencyType)
			throws InstanceNotFoundException {
		Date endDate = getEndDate(frequencyType, periodStartDate);
		return endDate;
	}

	@Override
	@Transactional
	public PayrollSchedule restorePayrollSchedule(Long payrollScheduleId)
			throws InstanceNotFoundException {
		logger.debug(">>Delete Payroll Schedule");
		Long count = payrollDao.getPayrollScheduleCount(payrollScheduleId);
		logger.debug("count..:" + count);
		if (count <= 0) {

			PayrollSchedule payrollSchedule = payrollScheduleDao.find(payrollScheduleId);

			PayrollSchedule currentPayrollSchedule = payrollScheduleDao
					.getCurrentPayrollSchedule(payrollSchedule.getPayrollFrequency().getId());
			if (currentPayrollSchedule != null) {
				if (currentPayrollSchedule.getCheckDate().after(payrollSchedule.getCheckDate())) {
					payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.CURRENT);
					payrollSchedule = payrollScheduleDao.update(payrollSchedule);
					currentPayrollSchedule
							.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
					payrollScheduleDao.update(currentPayrollSchedule);
				} else {
					payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
					payrollSchedule = payrollScheduleDao.update(payrollSchedule);
				}
			} else {
				payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.FUTURE);
				payrollSchedule = payrollScheduleDao.update(payrollSchedule);
				logger.debug("Delete Payroll Schedule >>");
			}
			return payrollSchedule;
		} else {
			return null;
		}
	}

	@Override
	public CompanyPayrollFrequencyForm getCompanyPayrollFrequencyForm(
			PayrollFrequency payrollFrequency) {
		CompanyPayrollFrequencyForm companyPayrollFrequencyForm = new CompanyPayrollFrequencyForm();
		companyPayrollFrequencyForm.setEditCheckDate(payrollFrequency.getCheckDate());
		companyPayrollFrequencyForm.setEditPeriodEndDate(payrollFrequency.getPeriodEndDate());
		companyPayrollFrequencyForm.setEditPeriodStartDate(payrollFrequency.getPeriodStartDate());
		companyPayrollFrequencyForm.setHolidayCheckDateOption(payrollFrequency
				.getHolidayCheckDateOption());
		companyPayrollFrequencyForm.setPayFrequencyType(payrollFrequency.getPayFrequencyType());
		companyPayrollFrequencyForm.setId(payrollFrequency.getId());
		return companyPayrollFrequencyForm;
	}

	// @Override
	// public List<PayrollSchedule> getPayrollScheduleList(Long companyId) {
	// return payrollScheduleDao.getPayrollSchedules(companyId);
	// }

	@Override
	public List<PayrollSchedule> getPreviousPayrollScheduleList(Date perioStartDate,
			Date periodEndDate, Long companyId) {
		return payrollScheduleDao.getPreviousPayrollScheduleList(perioStartDate, periodEndDate,
				companyId);
	}

	@Override
	public Long getPayrollScheduleRowCount(Long companyId) {
		return payrollScheduleDao.getPayrollScheduleRowCount(companyId);
	}

	@Override
	public List<PayrollSchedule> getPagedUserList(int startPage, int pageSize, Long companyId) {
		return payrollScheduleDao.getPagedListByCriteria(
				payrollScheduleDao.getCriteriaForPayrollSchedules(companyId), startPage * pageSize,
				pageSize);
	}

	@Override
	public List<CompanyWorkerCompensation> getWorkersCompensationList(Long companyId) {
		return companyWorkersCompensationDao.getWorkersCompensations(companyId);
	}

	@Override
	public WorkersCompensationTaxRate getWorkersCompensationRateList(
			Long companyWorkersCompensationId) {
		return workersCompensationTaxRateDao
				.getWorkerCompensationTaxRateList(companyWorkersCompensationId);

	}

	@Override
	public List<PayFrequencyType> getPayFrequencyType(Long companyId) {
		PayFrequencyType[] payFrequencyTypes = PayFrequencyType.values();
		List<PayFrequencyType> frequencyTypes = payrollFrequencyDao.getPayFrequencyType(companyId);

		List<PayFrequencyType> types = new ArrayList<>();

		for (PayFrequencyType payFrequencyType : payFrequencyTypes) {
			if (!frequencyTypes.contains(payFrequencyType)) {
				types.add(payFrequencyType);
			}
		}
		return types;
	}
}