package com.epayroll.service.employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.constant.company.AddressType;
import com.epayroll.constant.employee.ExemptedValue;
import com.epayroll.dao.AddressDao;
import com.epayroll.dao.AddressTypeDao;
import com.epayroll.dao.ContactTypeDao;
import com.epayroll.dao.PersonDao;
import com.epayroll.dao.SystemTaskDao;
import com.epayroll.dao.SystemTaskUserMapDao;
import com.epayroll.dao.UsCityDao;
import com.epayroll.dao.UsCountyDao;
import com.epayroll.dao.UsStateDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.CompanyDeductionDao;
import com.epayroll.dao.company.CompanyEarningDao;
import com.epayroll.dao.company.CompanyWorkersCompensationDao;
import com.epayroll.dao.employee.AllowanceTypeDao;
import com.epayroll.dao.employee.EmployeeDao;
import com.epayroll.dao.employee.EmployeeDeductionDao;
import com.epayroll.dao.employee.EmployeeEarningDao;
import com.epayroll.dao.employee.EmployeePayrollDao;
import com.epayroll.dao.employee.EmployeeSectionDao;
import com.epayroll.dao.employee.EmployeeTaxDao;
import com.epayroll.dao.employee.EmployeeW4DetailsDao;
import com.epayroll.dao.employee.EmploymentHistoryDao;
import com.epayroll.dao.employee.FilingStatusDao;
import com.epayroll.dao.employee.TaxTypeDao;
import com.epayroll.entity.Address;
import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.ContactType;
import com.epayroll.entity.Deduction;
import com.epayroll.entity.DeductionCategory;
import com.epayroll.entity.Earning;
import com.epayroll.entity.EarningCategory;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Access;
import com.epayroll.entity.Employee.Status;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.EmployeeDeduction;
import com.epayroll.entity.EmployeeDeduction.DeductionType;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;
import com.epayroll.entity.EmployeeEarning;
import com.epayroll.entity.EmployeeEarning.EarningValueType;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;
import com.epayroll.entity.EmployeeSection;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.entity.EmployeeW4Detail;
import com.epayroll.entity.EmploymentHistory;
import com.epayroll.entity.FilingStatus;
import com.epayroll.entity.Person;
import com.epayroll.entity.SystemTask;
import com.epayroll.entity.SystemTaskUserMap;
import com.epayroll.entity.TaxType;
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
import com.epayroll.model.EmployeeW4DetailModel;
import com.epayroll.model.employee.EmpSection;
import com.epayroll.model.employee.EmployeeAllowanceModel;
import com.epayroll.model.employee.TaxTypeList;
import com.epayroll.utils.RandomNumberUtils;

/**
 * @author Rajul Tiwari
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private UsStateDao usStateDao;

	@Autowired
	private UsCityDao usCityDao;

	@Autowired
	private UsCountyDao usCountyDao;

	@Autowired
	private EmploymentHistoryDao employmentHistoryDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private ContactTypeDao contactTypeDao;

	@Autowired
	private CompanyWorkersCompensationDao companyWorkersCompensationDao;

	@Autowired
	private EmployeeSectionDao employeeSectionDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private EmployeeEarningDao employeeEarningDao;

	@Autowired
	private EmployeeDeductionDao employeeDeductionDao;

	@Autowired
	private EmployeeTaxDao employeeTaxDao;

	@Autowired
	private AddressTypeDao addressTypeDao;

	@Autowired
	private SystemTaskDao systemTaskDao;

	@Autowired
	private SystemTaskUserMapDao systemTaskUserMapDao;
	@Autowired
	private CompanyEarningDao companyEarningDao;

	@Autowired
	private CompanyDeductionDao companyDeductionDao;

	@Autowired
	private FilingStatusDao filingStatusDao;

	@Autowired
	private AllowanceTypeDao allowanceTypeDao;

	@Autowired
	private TaxTypeDao taxTypeDao;

	@Autowired
	private EmployeeW4DetailsDao employeeW4DetailsDao;

	@Autowired
	private EmployeePayrollDao employeePayrollDao;

	@Override
	@Transactional
	public List<Object[]> getEmployeeList(Long companyId, Type employeeType) {
		logger.debug(">> getEmployeeList");
		List<Object[]> employeeList = employeeDao.getEmployeeList(companyId, employeeType);
		for (Object[] o : employeeList) {
			for (Object o1 : o) {
				logger.debug("value : " + o1);
			}
		}
		logger.debug("getEmployeeList >>");
		return employeeList;
	}

	@Override
	@Transactional
	public List<UsState> getUsStates() {
		logger.debug(">> getUsStates");
		List<UsState> usStateList = usStateDao.getStates();
		logger.debug("getUsStates >>");
		return usStateList;
	}

	@Override
	@Transactional
	public List<String> getUsCities() {
		logger.debug(">> getUsCities");
		List<String> usCityList = employeeDao.getCities();
		logger.debug(">> getUsCities");
		return usCityList;
	}

	@Override
	@Transactional
	public List<UsState> getUsStateList() {
		logger.debug(">> getUsStateList");
		List<UsState> usStateList = usStateDao.getEntities();
		logger.debug("usStateList::" + usStateList);
		logger.debug("getUsStateList >>");
		return usStateList;
	}

	@Override
	@Transactional
	public List<UsCity> getUsCityList() {
		logger.debug(">> getUsCityList");
		List<UsCity> usCityList = usCityDao.getEntities();
		logger.debug("usCityList::" + usCityList);
		logger.debug("getUsCityList >>");
		return usCityList;
	}

	@Override
	public List<UsCounty> getUsCountyList() {
		logger.debug(">> getUsCountyList");
		List<UsCounty> usCountyList = usCountyDao.getEntities();
		logger.debug("usCountyList::" + usCountyList);
		logger.debug("getUsCountyList >>");
		return usCountyList;
	}

	@Override
	public List<CompanyWorkerCompensation> getcompanyWorkerCompensationList() {
		logger.debug(">> getcompanyWorkerCompensationList");
		List<CompanyWorkerCompensation> companyWorkerCompensationList = companyWorkersCompensationDao
				.getEntities();
		logger.debug("companyWorkerCompensationList::" + companyWorkerCompensationList);
		logger.debug("getcompanyWorkerCompensationList >>");
		return companyWorkerCompensationList;
	}

	@Override
	@Transactional
	public List<Object[]> getSearchedEmployee(EmployeeSearchForm employeeSearchForm, Long companyId) {
		logger.debug(">> getSearchedEmployee");
		List<Object[]> searchedEmployee = employeeDao.getSearchedEmployees(employeeSearchForm,
				companyId);
		for (Object[] o : searchedEmployee) {
			for (Object o1 : o) {
				logger.debug("value : " + o1);
			}
		}
		logger.debug(">> getSearchedEmployee");
		return searchedEmployee;
	}

	@Override
	@Transactional
	public List<Object[]> getSearchedContractor(ContractorSearchForm constractorSearchForm,
			Long companyId) {
		logger.debug(">> getSearchedContractor");
		List<Object[]> searchedContractor = employeeDao.getSearchedContractor(
				constractorSearchForm, companyId);
		for (Object[] o : searchedContractor) {
			for (Object o1 : o) {
				logger.debug("value : " + o1);
			}
		}
		logger.debug("contractorList::" + searchedContractor);
		logger.debug(">> getSearchedContractor");
		return searchedContractor;
	}

	@Override
	@Transactional
	public void updateStatusTerminate(TerminateForm terminateForm, Employee employee) {
		logger.debug(">> updateEmployeeStatusTerminate");
		EmploymentHistory employmentHistory = employmentHistoryDao.getEmploymentHistory(employee
				.getId());
		employee.setStatus(Status.TERMINATED);
		employmentHistory.setTerminationDate(terminateForm.getDateOfTermination());
		employmentHistory.setEmployee(employee);
		employee.getEmploymentHistories().add(employmentHistory);
		employeeDao.update(employee);
		logger.debug("updateEmployeeStatusTerminate >>");
	}

	@Override
	@Transactional
	public Employee getEmployee(Long employeeId) throws InstanceNotFoundException {
		logger.debug(">> getEmployee");
		Employee employee = employeeDao.find(employeeId);
		logger.debug("employee::" + employee);
		logger.debug("getEmployee >> ");
		return employee;
	}

	@Override
	@Transactional
	public void updateStatusRehire(RehireForm rehireForm, Employee employee) {
		logger.debug(">> updateEmployeeStatusTerminate");
		EmploymentHistory employmentHistory = new EmploymentHistory();
		employee.setStatus(Status.ACTIVE);
		employmentHistory.setHireDate(rehireForm.getDateOfRehire());
		employmentHistory.setEmployee(employee);
		employee.getEmploymentHistories().add(employmentHistory);
		employeeDao.update(employee);
		logger.debug("updateEmployeeStatusTerminate >>");

	}

	@Override
	@Transactional
	public EmployeePersonalInfoForm getEmployeePersonalInfoForm(Employee employee) {
		logger.debug(">> getEmployeePersonalInfoForm");
		EmployeePersonalInfoForm employeePersonalInfoForm = new EmployeePersonalInfoForm();
		employeePersonalInfoForm.setFirstName(employee.getPerson().getFirstName());
		employeePersonalInfoForm.setLastName(employee.getPerson().getLastName());
		employeePersonalInfoForm.setDob(employee.getPerson().getDob());
		employeePersonalInfoForm.setSsn(employee.getPerson().getSsn());
		employeePersonalInfoForm.setGender(employee.getPerson().getGender());
		employeePersonalInfoForm.setEmail(employee.getPerson().getEmail());
		employeePersonalInfoForm.setPhone(employee.getPerson().getPhone());

		Iterator<Address> iterator = employee.getAddresses().iterator();
		if (iterator.hasNext()) {
			Address address = iterator.next();
			if (address.getAddressType().equals(AddressType.RESIDENCE)) {
				employeePersonalInfoForm.setStreetAddress(address.getStreetAddress());
				employeePersonalInfoForm.setCityId(address.getUsCity().getId());
				employeePersonalInfoForm.setStateId(address.getUsState().getId());
				employeePersonalInfoForm.setZip(address.getPinZip());
				employeePersonalInfoForm.setCountyId(address.getUsCounty().getId());
			}
		}
		logger.debug("EmployeePersonalInfoForm::" + employeePersonalInfoForm);
		logger.debug(">> getEmployeePersonalInfoForm");
		return employeePersonalInfoForm;
	}

	@Override
	@Transactional
	public EmployeeEmploymentInfoForm getEmployeeEmploymentInfoForm(Employee employee) {
		logger.debug(">> getEmployeeEmploymentInfoForm");
		EmployeeEmploymentInfoForm employeeEmploymentInfoForm = new EmployeeEmploymentInfoForm();
		employeeEmploymentInfoForm.setEmployeeId(employee.getEmployeeId());
		employeeEmploymentInfoForm.setWorkersCompCodeId(employee.getCompanyWorkerCompensation()
				.getId());
		employeeEmploymentInfoForm.setWorkEmail(employee.getWorkEmail());
		employeeEmploymentInfoForm.setWorkFax(employee.getWorkFax());
		employeeEmploymentInfoForm.setWrkPhone(employee.getWrkPhone());
		employeeEmploymentInfoForm.setWorkExt(employee.getWorkExt());
		employeeEmploymentInfoForm.setStatus(employee.getStatus());

		EmploymentHistory employmentHistory = employmentHistoryDao.getEmploymentHistory(employee
				.getId());
		employeeEmploymentInfoForm.setDateOfHireing(employmentHistory.getHireDate());
		employeeEmploymentInfoForm.setDateOfTermination(employmentHistory.getTerminationDate());

		Iterator<Address> iteratorAddress = employee.getAddresses().iterator();
		if (iteratorAddress.hasNext()) {
			Address address = iteratorAddress.next();
			if (address.getAddressType().equals(AddressType.WORK)) {

				employeeEmploymentInfoForm.setStreetAddress(address.getStreetAddress());
				employeeEmploymentInfoForm.setCityId(address.getUsCity().getId());
				employeeEmploymentInfoForm.setStateId(address.getUsState().getId());
				employeeEmploymentInfoForm.setZip(address.getPinZip());
				employeeEmploymentInfoForm.setCountyId(address.getUsCounty().getId());
			}
		}
		logger.debug("employeeEmploymentInfoForm::" + employeeEmploymentInfoForm);
		logger.debug(">> getEmployeeEmploymentInfoForm");
		return employeeEmploymentInfoForm;
	}

	@Override
	@Transactional
	public void updateEmployeePersonalInfo(EmployeePersonalInfoForm employeePersonalInfoForm,
			Employee employee) throws InstanceNotFoundException {
		logger.debug(">> updateEmployeePersonalInfo");
		Person person = personDao.find(employee.getPerson().getId());
		person.setFirstName(employeePersonalInfoForm.getFirstName());
		person.setLastName(employeePersonalInfoForm.getLastName());
		person.setDob(employeePersonalInfoForm.getDob());
		person.setSsn(employeePersonalInfoForm.getSsn());
		person.setGender(employeePersonalInfoForm.getGender());
		person.setEmail(employeePersonalInfoForm.getEmail());
		person.setPhone(employeePersonalInfoForm.getPhone());
		employee.setPerson(person);

		Address address = addressDao.getAddress(employee.getId(), AddressType.RESIDENCE);
		address.setStreetAddress(employeePersonalInfoForm.getStreetAddress());
		address.setPinZip(employeePersonalInfoForm.getZip());
		address.setUsState(usStateDao.find(employeePersonalInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(employeePersonalInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(employeePersonalInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);

		employeeDao.update(employee);
		logger.debug("updateEmployeePersonalInfo >>");
	}

	@Override
	@Transactional
	public void updateEmployeeEmploymentInfo(EmployeeEmploymentInfoForm employeeEmploymentInfoForm,
			Employee employee) throws InstanceNotFoundException {
		logger.debug(">> updateEmployeeEmploymentInfo");
		employee.setEmployeeId(employeeEmploymentInfoForm.getEmployeeId());
		employee.setWorkEmail(employeeEmploymentInfoForm.getWorkEmail());
		employee.setWorkExt(employeeEmploymentInfoForm.getWorkExt());
		employee.setWorkFax(employeeEmploymentInfoForm.getWorkFax());
		employee.setWrkPhone(employeeEmploymentInfoForm.getWrkPhone());
		employee.setStatus(employeeEmploymentInfoForm.getStatus());

		EmploymentHistory employmentHistory = employmentHistoryDao.getEmploymentHistory(employee
				.getId());
		employmentHistory.setHireDate(employeeEmploymentInfoForm.getDateOfHireing());
		employmentHistory.setTerminationDate(employeeEmploymentInfoForm.getDateOfTermination());
		employee.getEmploymentHistories().add(employmentHistory);

		employee.setCompanyWorkerCompensation(companyWorkersCompensationDao
				.find(employeeEmploymentInfoForm.getWorkersCompCodeId()));

		Address address = addressDao.getAddress(employee.getId(), AddressType.WORK);
		address.setStreetAddress(employeeEmploymentInfoForm.getStreetAddress());
		address.setPinZip(employeeEmploymentInfoForm.getZip());
		address.setUsState(usStateDao.find(employeeEmploymentInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(employeeEmploymentInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(employeeEmploymentInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);
		employeeDao.update(employee);
		logger.debug("updateEmployeeEmploymentInfo >>");
	}

	@Override
	@Transactional
	public ContractorPersonalInfoForm getContractorPersonalInfoForm(Employee employee) {
		logger.debug(">> getContractorPersonalInfoForm");
		ContractorPersonalInfoForm contractorPersonalInfoForm = new ContractorPersonalInfoForm();
		contractorPersonalInfoForm.setFirstName(employee.getPerson().getFirstName());
		contractorPersonalInfoForm.setLastName(employee.getPerson().getLastName());
		contractorPersonalInfoForm.setDob(employee.getPerson().getDob());
		contractorPersonalInfoForm.setSsn(employee.getPerson().getSsn());
		contractorPersonalInfoForm.setGender(employee.getPerson().getGender());
		contractorPersonalInfoForm.setEmail(employee.getPerson().getEmail());
		contractorPersonalInfoForm.setPhone(employee.getPerson().getPhone());
		contractorPersonalInfoForm.setTin(employee.getContractorCompanyTin());
		contractorPersonalInfoForm.setCompanyName(employee.getContractorCompanyName());
		Iterator<Address> iterator = employee.getAddresses().iterator();
		if (iterator.hasNext()) {
			Address address = iterator.next();
			if (address.getAddressType().equals(AddressType.RESIDENCE)) {
				contractorPersonalInfoForm.setStreetAddress(address.getStreetAddress());
				contractorPersonalInfoForm.setCityId(address.getUsCity().getId());
				contractorPersonalInfoForm.setStateId(address.getUsState().getId());
				contractorPersonalInfoForm.setZip(address.getPinZip());
				contractorPersonalInfoForm.setCountyId(address.getUsCounty().getId());
			}
		}
		logger.debug("ContractorPersonalInfoForm::" + contractorPersonalInfoForm);
		logger.debug(">> getContractorPersonalInfoForm");
		return contractorPersonalInfoForm;
	}

	@Override
	@Transactional
	public ContractorEmploymentInfoForm getContractorEmploymentInfoForm(Employee employee) {
		logger.debug(">> getContractorEmploymentInfoForm");
		ContractorEmploymentInfoForm contractorEmploymentInfoForm = new ContractorEmploymentInfoForm();
		contractorEmploymentInfoForm.setEmployeeId(employee.getEmployeeId());
		contractorEmploymentInfoForm.setWorkEmail(employee.getWorkEmail());
		contractorEmploymentInfoForm.setWorkFax(employee.getWorkFax());
		contractorEmploymentInfoForm.setWrkPhone(employee.getWrkPhone());
		contractorEmploymentInfoForm.setWorkExt(employee.getWorkExt());
		contractorEmploymentInfoForm.setStatus(employee.getStatus());

		EmploymentHistory employmentHistory = employmentHistoryDao.getEmploymentHistory(employee
				.getId());
		contractorEmploymentInfoForm.setDateOfHireing(employmentHistory.getHireDate());
		contractorEmploymentInfoForm.setDateOfTermination(employmentHistory.getTerminationDate());

		Iterator<Address> iteratorAddress = employee.getAddresses().iterator();
		if (iteratorAddress.hasNext()) {
			Address address = iteratorAddress.next();
			if (address.getAddressType().equals(AddressType.WORK)) {

				contractorEmploymentInfoForm.setStreetAddress(address.getStreetAddress());
				contractorEmploymentInfoForm.setCityId(address.getUsCity().getId());
				contractorEmploymentInfoForm.setStateId(address.getUsState().getId());
				contractorEmploymentInfoForm.setZip(address.getPinZip());
				contractorEmploymentInfoForm.setCountyId(address.getUsCounty().getId());
			}
		}
		logger.debug("ContractorEmploymentInfoForm::" + contractorEmploymentInfoForm);
		logger.debug(">> getContractorEmploymentInfoForm");
		return contractorEmploymentInfoForm;
	}

	@Override
	@Transactional
	public void updateContractorPersonalInfo(ContractorPersonalInfoForm contractorPersonalInfoForm,
			Employee employee) throws InstanceNotFoundException {
		logger.debug(">> updateContractorPersonalInfo");
		Person person = personDao.find(employee.getPerson().getId());
		person.setFirstName(contractorPersonalInfoForm.getFirstName());
		person.setLastName(contractorPersonalInfoForm.getLastName());
		person.setDob(contractorPersonalInfoForm.getDob());

		if (!contractorPersonalInfoForm.getSsn().equals(null)) {
			person.setSsn(contractorPersonalInfoForm.getSsn());
		} else if (!contractorPersonalInfoForm.getTin().equals(null)
				&& !contractorPersonalInfoForm.getCompanyName().equals(null)) {
			employee.setContractorCompanyTin(contractorPersonalInfoForm.getTin());
			employee.setContractorCompanyName(contractorPersonalInfoForm.getCompanyName());
		}

		person.setGender(contractorPersonalInfoForm.getGender());
		person.setEmail(contractorPersonalInfoForm.getEmail());
		person.setPhone(contractorPersonalInfoForm.getPhone());
		employee.setPerson(person);

		Address address = addressDao.getAddress(employee.getId(), AddressType.RESIDENCE);
		address.setStreetAddress(contractorPersonalInfoForm.getStreetAddress());
		address.setPinZip(contractorPersonalInfoForm.getZip());
		address.setUsState(usStateDao.find(contractorPersonalInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(contractorPersonalInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(contractorPersonalInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);

		employeeDao.update(employee);
		logger.debug(">> updateContractorPersonalInfo");
	}

	@Override
	@Transactional
	public void updateContractorEmploymentInfo(
			ContractorEmploymentInfoForm contractorEmploymentInfoForm, Employee employee)
			throws InstanceNotFoundException {
		logger.debug(">> updateContractorEmploymentInfo");
		employee.setEmployeeId(contractorEmploymentInfoForm.getEmployeeId());
		employee.setWorkEmail(contractorEmploymentInfoForm.getWorkEmail());
		employee.setWorkExt(contractorEmploymentInfoForm.getWorkExt());
		employee.setWorkFax(contractorEmploymentInfoForm.getWorkFax());
		employee.setWrkPhone(contractorEmploymentInfoForm.getWrkPhone());
		employee.setStatus(contractorEmploymentInfoForm.getStatus());

		EmploymentHistory employmentHistory = employmentHistoryDao.getEmploymentHistory(employee
				.getId());
		employmentHistory.setHireDate(contractorEmploymentInfoForm.getDateOfHireing());
		employmentHistory.setTerminationDate(contractorEmploymentInfoForm.getDateOfTermination());
		employee.getEmploymentHistories().add(employmentHistory);

		CompanyWorkerCompensation companyWorkerCompensation = companyWorkersCompensationDao
				.find(employee.getCompanyWorkerCompensation().getId());
		employee.setCompanyWorkerCompensation(companyWorkerCompensation);

		Address address = addressDao.getAddress(employee.getId(), AddressType.WORK);
		address.setStreetAddress(contractorEmploymentInfoForm.getStreetAddress());
		address.setPinZip(contractorEmploymentInfoForm.getZip());
		address.setUsState(usStateDao.find(contractorEmploymentInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(contractorEmploymentInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(contractorEmploymentInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);
		employeeDao.update(employee);
		logger.debug(">> updateContractorEmploymentInfo");
	}

	@Override
	@Transactional
	public List<EmploymentHistory> getEmploymentHistory(Long employeeId) {
		logger.debug(">> getEmploymentHistory");
		List<EmploymentHistory> employmentHistoryList = employmentHistoryDao
				.getEmploymentHistoryForViewHistory(employeeId);
		logger.debug("getEmploymentHistory >>");
		return employmentHistoryList;
	}

	@Override
	@Transactional
	public String getEmployeePassword() {
		logger.debug(">> Get Employee Password");
		return RandomNumberUtils.get6digitUniqueNumber().toString();

	}

	@Override
	@Transactional
	public List<EmployeeSection> getEmployeeSectionList(Long companyId) {
		logger.debug(">>Get Employee Section List");
		return employeeDao.getEmployeeSectionList(companyId);
	}

	@Override
	@Transactional
	public List<EmployeeSection> getEmployeeSections() {
		logger.debug(">>Get Employee Sections");
		return employeeSectionDao.getEntities();
	}

	@Override
	@Transactional
	public EmployeeAccessForm getEmployeeAccessForm(Access employeeAccess, Company company,
			Employee employee) {
		logger.debug(">> Get Employee Access Form");
		EmployeeAccessForm employeeAccessForm = new EmployeeAccessForm();
		if (employeeAccess != null) {
			if (employeeAccess == Access.DISABLED) {
				employeeAccessForm.setAllowAccessCheckBox(false);
				employeeAccessForm.setEmailCheckBox(false);
			} else {
				employeeAccessForm.setAllowAccessCheckBox(true);
				employeeAccessForm.setEmailCheckBox(true);
			}
		} else {
			employeeAccessForm.setAllowAccessCheckBox(false);
			employeeAccessForm.setEmailCheckBox(false);
		}
		employeeAccessForm.setEmployeeId(employee.getId());
		employeeAccessForm.setCompanyId(company.getId());
		employeeAccessForm.setEmailAddress(employee.getWorkEmail());
		logger.debug("Get Employee Access Form>>");
		return employeeAccessForm;
	}

	@Override
	public EmployeeSectionForm getEmployeeSectionForm(Company company) {
		logger.debug(">>Get Employee Section Form");
		EmployeeSectionForm employeeSectionForm = new EmployeeSectionForm();
		List<EmpSection> empSections = new ArrayList<>();
		EmpSection empSection = null;

		List<Long> sectionIds = new ArrayList<>();
		for (EmployeeSection employeeSection : employeeSectionDao.getEmployeeSections(company
				.getId())) {
			sectionIds.add(employeeSection.getId());
		}
		logger.debug("sectionIds:" + sectionIds);
		for (EmployeeSection section : getEmployeeSections()) {
			logger.debug("employeeSection.getId()::::" + section.getId());
			empSection = new EmpSection();
			if (sectionIds.contains(section.getId())) {
				logger.debug("in contains..");
				empSection.setSectionId(section.getId());
			}
			empSections.add(empSection);
		}
		employeeSectionForm.setEmpSections(empSections);
		logger.debug("Get Employee Section Form>>");
		return employeeSectionForm;
	}

	@Override
	@Transactional
	public void addEmployeeSections(EmployeeSectionForm employeeSectionForm, Long companyId) {
		logger.debug(">>Add Sections");

		try {
			Company company = companyDao.find(companyId);
			company.getEmployeeSections().clear();
			logger.debug("company::" + company);
			for (EmpSection section : employeeSectionForm.getEmpSections()) {
				if (section.getSectionId() != null) {
					company.getEmployeeSections().add(
							employeeSectionDao.find(section.getSectionId()));
				}
			}
		} catch (InstanceNotFoundException e) {
			logger.debug("error in addEmployeeSections:", e.getLocalizedMessage());
		}

		logger.debug(">>Add Sections");
	}

	@Override
	@Transactional
	public EmployeeEarningForm getEmployeeEarningForm(Employee employee) {

		List<EmployeeEarning> employeeEarnings = employeeEarningDao.getEmployeeEarnings(employee
				.getId());
		EmployeeEarningForm employeeEarningForm = getEmployeeEarningForm(employeeEarnings);
		return employeeEarningForm;
	}

	private EmployeeEarningForm getEmployeeEarningForm(List<EmployeeEarning> employeeEarnings) {
		List<EarningCategory> earningCategories = new ArrayList<>();
		List<Earning> earnings = new ArrayList<>();
		EmployeeEarningForm employeeEarningForm = new EmployeeEarningForm();
		EmployeeEarning employeeEarning = new EmployeeEarning();
		for (EmployeeEarning employeeEar : employeeEarnings) {
			earningCategories
					.add(employeeEar.getCompanyEarning().getEarning().getEarningCategory());
			earnings.add(employeeEar.getCompanyEarning().getEarning());
			employeeEarning = employeeEar;
		}
		employeeEarningForm.setEarningCategory(earningCategories);
		employeeEarningForm.setEarnings(earnings);
		employeeEarningForm.setValue(employeeEarning.getValue());
		employeeEarningForm.setEmployeeEarningId(employeeEarning.getId());
		employeeEarningForm.setEmployeeId(employeeEarning.getEmployee().getId());
		return employeeEarningForm;
	}

	@Override
	public EmployeeDeductionForm getEmployeeDeductionForm(Employee employee) {

		List<EmployeeDeduction> employeeDeductions = employeeDeductionDao
				.getEmployeeDeductions(employee.getId());
		EmployeeDeductionForm employeeDeductionForm = getEmployeeDeductionForm(employeeDeductions);
		logger.debug("employeeDeductions:::" + employeeDeductions);
		return employeeDeductionForm;
	}

	private EmployeeDeductionForm getEmployeeDeductionForm(
			List<EmployeeDeduction> employeeDeductions) {
		List<DeductionCategory> deductionCategories = new ArrayList<>();
		List<Deduction> deductions = new ArrayList<>();
		EmployeeDeduction employeeDeduction = new EmployeeDeduction();
		for (EmployeeDeduction employeededuc : employeeDeductions) {
			deductionCategories.add(employeededuc.getCompanyDeduction().getDeduction()
					.getDeductionCategory());
			deductions.add(employeededuc.getCompanyDeduction().getDeduction());
			employeeDeduction = employeededuc;
		}
		EmployeeDeductionForm employeeDeductionForm = new EmployeeDeductionForm();
		employeeDeductionForm.setDeductionCategory(deductionCategories);
		employeeDeductionForm.setDeductions(deductions);
		employeeDeductionForm.setValue(employeeDeduction.getValue());
		employeeDeductionForm.setTargetValue(employeeDeduction.getTargetValue());
		employeeDeductionForm.setEmployeeId(employeeDeduction.getEmployee().getId());
		employeeDeductionForm.setEmployeeDeductionId(employeeDeduction.getId());
		return employeeDeductionForm;
	}

	@Override
	public EmployeeEarning getEmployeeEearnings(Long employeeEarningId)
			throws InstanceNotFoundException {

		return employeeEarningDao.find(employeeEarningId);

	}

	@Override
	public EmployeeEarningForm getEmployeeEarningUpdateForm(EmployeeEarning employeeEarning) {
		List<EmployeeEarning> employeeEarningList = employeeEarningDao
				.getEmployeeEarnings(employeeEarning);
		EmployeeEarningForm employeeEarningForm = getEmployeeEarningForm(employeeEarningList);
		return employeeEarningForm;
	}

	@Override
	public EmployeeDeduction getEmployeeDeductions(Long employeeDeductionId)
			throws InstanceNotFoundException {

		return employeeDeductionDao.find(employeeDeductionId);
	}

	@Override
	public EmployeeDeductionForm getEmployeeDeductionUpdateForm(EmployeeDeduction employeeDeduction) {
		List<EmployeeDeduction> employeeDeductionList = employeeDeductionDao
				.getEmployeeDeductions(employeeDeduction);
		EmployeeDeductionForm employeeDeductionForm = getEmployeeDeductionForm(employeeDeductionList);
		return employeeDeductionForm;
	}

	@Override
	@Transactional
	public void updateEmployeeEarnings(EmployeeEarningForm employeeEarningForm) {
		EmployeeEarning employeeEarning = employeeEarningDao.getEmployeeEarnings(
				employeeEarningForm.getEmployeeEarningId(),
				employeeEarningForm.getEarningValueType());
		logger.debug("employeeEarning query:::::" + employeeEarning);
		employeeEarning.setValue(employeeEarningForm.getValue());
		employeeEarningDao.update(employeeEarning);
	}

	@Override
	@Transactional
	public void updateEmployeeDeductions(EmployeeDeductionForm employeeDeductionForm) {
		EmployeeDeduction employeeDeduction = employeeDeductionDao.getEmployeeDeductions(
				employeeDeductionForm.getEmployeeDeductionId(),
				employeeDeductionForm.getDeductionValueType());
		employeeDeduction.setValue(employeeDeductionForm.getValue());
		employeeDeduction.setTargetValue(employeeDeductionForm.getTargetValue());
		employeeDeductionDao.update(employeeDeduction);

	}

	@Override
	public EarningValueType[] getEarningValueType() {
		return EarningValueType.values();
	}

	@Override
	public DeductionValueType[] getDeductionValueType() {
		return DeductionValueType.values();
	}

	@Override
	public DeductionType[] getDeductionType() {
		return DeductionType.values();
	}

	@Override
	@Transactional
	public void updateEmployeeFederalTax(EmployeeFederalTaxForm employeeFederalTaxForm)
			throws InstanceNotFoundException {
		// Fetching Other Federal Taxes
		List<EmployeeTax> employeeTaxList = employeeTaxDao.getFederalTaxList(employeeFederalTaxForm
				.getEmployeeId());

		// Fetching FIT
		EmployeeW4DetailModel employeeW4DetailModel = employeeTaxDao
				.getFITRecord(employeeFederalTaxForm.getEmployeeId());

		EmployeeTax employeeTax = employeeW4DetailModel.getEmployeeTax();
		logger.debug("employeeTax:::::" + employeeTax);
		EmployeeAllowanceRecord employeeAllowanceRecord = employeeW4DetailModel
				.getEmployeeAllowanceRecords().get(0);
		logger.debug("employeeAllowanceRecord:::::" + employeeAllowanceRecord);
		EmployeeW4Detail employeeW4Detail = employeeAllowanceRecord.getEmployeeW4Detail();
		logger.debug("employeeW4Detail:::::" + employeeW4Detail);

		List<TaxTypeList> taxTypeList = employeeFederalTaxForm.getExemptedList();
		logger.debug("taxTypeList:::::" + taxTypeList);

		for (TaxTypeList taxTypes : taxTypeList) {
			logger.debug("taxTypes" + taxTypes);
			logger.debug("taxTypes.isFit():::::" + taxTypes.isFit());
			if (taxTypes.isFit()) {
				employeeTax.setExempted(taxTypes.getExemptedValue());
			}
		}

		// Setting W4 details
		employeeW4Detail
				.setAdditionalWithholding(employeeFederalTaxForm.getAdditionalWithholding());

		employeeW4Detail.setAsOnDate(new Date());
		FilingStatus filingStatus = filingStatusDao.find(employeeFederalTaxForm
				.getTaxFillingStatusId());
		logger.debug("filingStatus:::::" + filingStatus);
		employeeW4Detail.setFilingStatus(filingStatus);
		employeeW4Detail.setTaxOverrideType(employeeFederalTaxForm.getTaxOverrideType());
		if (employeeFederalTaxForm.getTaxOverrideType() != null) {
			employeeW4Detail.setOverrideFromCompany(true);
		} else {
			employeeW4Detail.setOverrideFromCompany(false);
		}
		employeeW4Detail.setTaxOverrideValue(employeeFederalTaxForm.getTaxOverrideValue());
		// employeeW4DetailsDao.update(employeeW4Detail);

		// Setting Allowances Details
		employeeAllowanceRecord.setAllowanceType(allowanceTypeDao.find(employeeFederalTaxForm
				.getAllowanceTypeId()));
		employeeAllowanceRecord.setNoOfAllowance(employeeFederalTaxForm.getNoOfAllowances());
		// employeeAllowanceRecordDao.update(employeeAllowanceRecord);
		employeeTaxDao.update(employeeTax);

		// Setting Other Federal Tax Exempt status
		for (TaxTypeList taxTypes : taxTypeList) {
			logger.debug("taxTypeList:::::" + taxTypeList);
			for (EmployeeTax empTax : employeeTaxList) {
				logger.debug("empTax:::::" + empTax);
				logger.debug("taxTypes.getTaxTypeId() == empTax.getTaxType().getId():::::"
						+ taxTypes.getTaxTypeId() + empTax.getTaxType().getId());
				if (taxTypes.getTaxTypeId() == empTax.getTaxType().getId()) {
					empTax.setExempted(taxTypes.getExemptedValue());
					employeeTaxDao.update(empTax);
				}
			}
		}
	}

	@Override
	@Transactional
	public void updateEmployeeStateTax(EmployeeStateTaxForm employeeStateTaxForm)
			throws InstanceNotFoundException {

		// Updating SIT Details
		EmployeeW4Detail employeeW4Detail = employeeW4DetailsDao
				.getEmployeeW4Detail(employeeStateTaxForm.getEmployeeSitId());
		EmployeeTax employeeSitTax = employeeW4Detail.getEmployeeTax();
		employeeW4Detail.setAdditionalWithholding(employeeStateTaxForm.getAdditionalWithholding());
		employeeW4Detail.setTaxOverrideType(employeeStateTaxForm.getTaxOverrideType());
		if (employeeStateTaxForm.getTaxOverrideType() != null) {
			employeeW4Detail.setOverrideFromCompany(true);
		} else {
			employeeW4Detail.setOverrideFromCompany(false);
		}
		employeeW4Detail.setTaxOverrideValue(employeeStateTaxForm.getTaxOverrideValue());
		employeeW4Detail.setFilingStatus(filingStatusDao.find(employeeStateTaxForm
				.getTaxFillingStatusId()));
		for (EmployeeAllowanceRecord employeeAllowanceRecord : employeeW4Detail
				.getEmployeeAllowanceRecords()) {
			for (EmployeeAllowanceModel employeeAllowanceModel : employeeStateTaxForm
					.getAllowanceModels()) {
				if (employeeAllowanceModel.getAllowanceTypeId() == employeeAllowanceRecord
						.getAllowanceType().getId()) {
					employeeAllowanceRecord.setNoOfAllowance(employeeAllowanceModel
							.getNoOfAllowances());
				}
			}
		}
		employeeSitTax.setExempted(employeeStateTaxForm.getSitExempted());
		employeeTaxDao.update(employeeSitTax);

		// Updating SUTA Details
		EmployeeTax employeeSutaTax = employeeTaxDao.load(employeeStateTaxForm.getEmployeeSutaId());
		employeeSutaTax.setExempted(employeeStateTaxForm.getSutaExempted());
		employeeTaxDao.update(employeeSutaTax);

	}

	@Override
	@Transactional
	public void updateEmployeeLocalTax(EmployeeLocalTaxForm employeeLocalTaxForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateEmployeeLocalTax");
		logger.debug("employeeLocalTaxForm:::::::" + employeeLocalTaxForm);
		EmployeeTax employeeTax = employeeTaxDao.load(employeeLocalTaxForm.getEmployeeTaxId());
		employeeTax.setExempted(employeeLocalTaxForm.getExempted());
		employeeTaxDao.update(employeeTax);
		logger.debug("employeeTax:::::::" + employeeTax);
		employeeTaxDao.update(employeeTax);
	}

	@Override
	public Long addEmployeePersonalDetail(EmployeePersonalInfoForm employeePersonalInfoForm,
			Company company) throws InstanceNotFoundException {
		logger.debug(">> addEmployeePersonalDetail");
		Employee employee = new Employee();
		employee.setEmployeeType(Type.EMPLOYEE);
		employee.setCompany(company);
		// random
		employee.setEmployeeId(RandomNumberUtils.getUserName());

		Person person = new Person();
		person.setFirstName(employeePersonalInfoForm.getFirstName());
		person.setLastName(employeePersonalInfoForm.getLastName());
		person.setDob(employeePersonalInfoForm.getDob());
		person.setSsn(employeePersonalInfoForm.getSsn());
		person.setGender(employeePersonalInfoForm.getGender());
		person.setEmail(employeePersonalInfoForm.getEmail());
		person.setPhone(employeePersonalInfoForm.getPhone());

		ContactType contactType = contactTypeDao
				.getContactType(com.epayroll.constant.company.ContactType.NORMAL);
		person.setContactType(contactType);
		employee.setPerson(person);

		Address address = new Address();
		address.setAddressType(addressTypeDao.getAddressType(AddressType.RESIDENCE));
		address.setStreetAddress(employeePersonalInfoForm.getStreetAddress());
		address.setPinZip(employeePersonalInfoForm.getZip());
		address.setUsState(usStateDao.find(employeePersonalInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(employeePersonalInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(employeePersonalInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);
		Long employeeId = employeeDao.save(employee);
		logger.debug("addEmployeePersonalDetail >>");
		return employeeId;
	}

	@Override
	public void updateEmployeeEmploymentInfo(Long employeeId,
			EmployeeEmploymentInfoForm employeeEmploymentInfoForm) throws InstanceNotFoundException {
		logger.debug(">> updateEmployeeEmploymentInfo");
		Employee employee = employeeDao.find(employeeId);
		employee.setEmployeeId(employeeEmploymentInfoForm.getEmployeeId());
		employee.setWorkEmail(employeeEmploymentInfoForm.getWorkEmail());
		employee.setWorkExt(employeeEmploymentInfoForm.getWorkExt());
		employee.setWorkFax(employeeEmploymentInfoForm.getWorkFax());
		employee.setWrkPhone(employeeEmploymentInfoForm.getWrkPhone());
		employee.setStatus(employeeEmploymentInfoForm.getStatus());

		EmploymentHistory employmentHistory = new EmploymentHistory();
		employmentHistory.setHireDate(employeeEmploymentInfoForm.getDateOfHireing());
		employmentHistory.setTerminationDate(employeeEmploymentInfoForm.getDateOfTermination());
		employmentHistory.setEmployee(employee);
		employee.getEmploymentHistories().add(employmentHistory);

		employee.setCompanyWorkerCompensation(companyWorkersCompensationDao
				.find(employeeEmploymentInfoForm.getWorkersCompCodeId()));

		Address address = new Address();
		address.setAddressType(addressTypeDao.getAddressType(AddressType.WORK));
		address.setStreetAddress(employeeEmploymentInfoForm.getStreetAddress());
		address.setPinZip(employeeEmploymentInfoForm.getZip());
		address.setUsState(usStateDao.find(employeeEmploymentInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(employeeEmploymentInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(employeeEmploymentInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);

		employeeDao.update(employee);
		logger.debug("updateEmployeeEmploymentInfo >>");

	}

	@Override
	public Long addContractorPersonalDetail(ContractorPersonalInfoForm contractorPersonalInfoForm,
			Company company) throws InstanceNotFoundException {
		logger.debug(">> addContractorPersonalDetail");
		Employee employee = new Employee();
		employee.setEmployeeType(Type.CONTRACTOR);
		employee.setCompany(company);
		// random
		employee.setEmployeeId(RandomNumberUtils.getUserName());

		Person person = new Person();
		person.setFirstName(contractorPersonalInfoForm.getFirstName());
		person.setLastName(contractorPersonalInfoForm.getLastName());
		person.setDob(contractorPersonalInfoForm.getDob());

		if (!contractorPersonalInfoForm.getSsn().equals(null)) {
			person.setSsn(contractorPersonalInfoForm.getSsn());
		} else if (!contractorPersonalInfoForm.getTin().equals(null)
				&& !contractorPersonalInfoForm.getCompanyName().equals(null)) {
			employee.setContractorCompanyTin(contractorPersonalInfoForm.getTin());
			employee.setContractorCompanyName(contractorPersonalInfoForm.getCompanyName());
		}

		person.setGender(contractorPersonalInfoForm.getGender());
		person.setEmail(contractorPersonalInfoForm.getEmail());
		person.setPhone(contractorPersonalInfoForm.getPhone());
		ContactType contactType = contactTypeDao
				.getContactType(com.epayroll.constant.company.ContactType.NORMAL);
		person.setContactType(contactType);
		employee.setPerson(person);

		Address address = new Address();
		address.setAddressType(addressTypeDao.getAddressType(AddressType.RESIDENCE));
		address.setStreetAddress(contractorPersonalInfoForm.getStreetAddress());
		address.setPinZip(contractorPersonalInfoForm.getZip());
		address.setUsState(usStateDao.find(contractorPersonalInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(contractorPersonalInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(contractorPersonalInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);

		Long employeeId = employeeDao.save(employee);
		logger.debug("addContractorPersonalDetail >>");
		return employeeId;
	}

	@Override
	public void updateContractorEmploymentInfo(Long employeeId,
			ContractorEmploymentInfoForm contractorEmploymentInfoForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateContractorEmploymentInfo");
		Employee employee = employeeDao.find(employeeId);
		employee.setEmployeeId(contractorEmploymentInfoForm.getEmployeeId());
		employee.setWorkEmail(contractorEmploymentInfoForm.getWorkEmail());
		employee.setWorkExt(contractorEmploymentInfoForm.getWorkExt());
		employee.setWorkFax(contractorEmploymentInfoForm.getWorkFax());
		employee.setWrkPhone(contractorEmploymentInfoForm.getWrkPhone());
		employee.setStatus(contractorEmploymentInfoForm.getStatus());

		EmploymentHistory employmentHistory = new EmploymentHistory();
		employmentHistory.setHireDate(contractorEmploymentInfoForm.getDateOfHireing());
		employmentHistory.setTerminationDate(contractorEmploymentInfoForm.getDateOfTermination());
		employmentHistory.setEmployee(employee);
		employee.getEmploymentHistories().add(employmentHistory);

		Address address = new Address();
		address.setAddressType(addressTypeDao.getAddressType(AddressType.WORK));
		address.setStreetAddress(contractorEmploymentInfoForm.getStreetAddress());
		address.setPinZip(contractorEmploymentInfoForm.getZip());
		address.setUsState(usStateDao.find(contractorEmploymentInfoForm.getStateId()));
		address.setUsCity(usCityDao.find(contractorEmploymentInfoForm.getCityId()));
		address.setUsCounty(usCountyDao.find(contractorEmploymentInfoForm.getCountyId()));
		address.setCityName(address.getUsCity().getCityName());
		address.setCountyName(address.getUsCounty().getCountyName());
		employee.getAddresses().add(address);
		employeeDao.update(employee);
		logger.debug("updateContractorEmploymentInfo >>");

	}

	@Override
	public void createTask(Long employeeId, String url, User user) throws InstanceNotFoundException {
		logger.debug(">> createTask");

		SystemTask systemTask = new SystemTask();
		Calendar c = Calendar.getInstance();
		Date receiveDate = c.getTime();
		c.add(Calendar.DATE, 2);
		Date dueDate = c.getTime();
		systemTask.setKeyValue(employeeId + "");
		systemTask.setTaskText("Complete this task");
		systemTask.setDateReceived(receiveDate);
		systemTask.setDueDate(dueDate);
		systemTask.setRedirectingUrl(url);
		// Long taskId = systemTaskDao.save(systemTask);
		SystemTaskUserMap systemTaskUserMap = new SystemTaskUserMap();
		systemTaskUserMap.setUser(user);
		systemTaskUserMap.setStatus(com.epayroll.entity.SystemTaskUserMap.Status.INCOMPLETE);
		systemTaskUserMap.setSystemTask(systemTask);
		Long id = systemTaskUserMapDao.save(systemTaskUserMap);
		Long taskId = systemTaskUserMapDao.find(id).getSystemTask().getId();
		logger.debug("taskId::" + taskId);
		systemTask.setRedirectingUrl(url + "/{" + taskId + "}");
		systemTaskDao.update(systemTask);

		logger.debug("createTask >>");
	}

	@Override
	public Long getEmployeeIdFromSystemTask(Long taskId) throws InstanceNotFoundException {
		logger.debug(">> getEmployeeIdFromSystemTask");
		SystemTask systemTask = systemTaskDao.find(taskId);
		String s = systemTask.getKeyValue();
		Long employeeId = Long.parseLong(s.trim());
		// systemTaskDao.remove(taskId);
		logger.debug("getEmployeeIdFromSystemTask >>");
		return employeeId;
	}

	@Override
	public void updateTask(Long taskId, String url) throws InstanceNotFoundException {
		logger.debug(">> updateTask");
		SystemTask systemTask = systemTaskDao.find(taskId);
		systemTask.setRedirectingUrl(url + "/" + taskId + "/");
		systemTaskDao.update(systemTask);
		logger.debug("updateTask >>");
	}

	@Override
	@Transactional
	public void addEmployeeEarnings(EmployeeEarningForm employeeEarningForm, Company company,
			Long employeeId) throws InstanceNotFoundException {
		EmployeeEarning employeeEarning = new EmployeeEarning();
		Employee employee = employeeDao.find(employeeId);
		List<CompanyEarning> companyEarningList = companyEarningDao.getCompanyEarnings(company
				.getId());
		for (CompanyEarning companyEarning : companyEarningList) {
			employeeEarning.setCompanyEarning(companyEarning);
			employeeEarning.setEarningValueType(employeeEarningForm.getEarningValueType());
			employeeEarning.setEmployee(employee);
			employeeEarning.setValue(employeeEarningForm.getValue());
			employeeEarningDao.save(employeeEarning);
		}

	}

	@Override
	@Transactional
	public void addEmployeeDeductions(EmployeeDeductionForm employeeDeductionForm, Company company,
			Long employeeId) throws InstanceNotFoundException {
		EmployeeDeduction employeeDeduction = new EmployeeDeduction();
		@SuppressWarnings("unchecked")
		List<CompanyDeduction> companyDeductionList = companyDeductionDao
				.getCriteriaForDeductionList(company).list();
		Employee employee = employeeDao.find(employeeId);
		for (CompanyDeduction companyDeduction : companyDeductionList) {
			employeeDeduction.setCompanyDeduction(companyDeduction);
			employeeDeduction.setDeductionType(employeeDeductionForm.getDeductionType());
			employeeDeduction.setDeductionValueType(employeeDeductionForm.getDeductionValueType());
			employeeDeduction.setEmployee(employee);
			employeeDeduction.setTargetValue(employeeDeductionForm.getTargetValue());
			employeeDeduction.setValue(employeeDeductionForm.getValue());
			employeeDeductionDao.save(employeeDeduction);
		}
	}

	@Override
	public List<FilingStatus> getFilingStatusList() {
		return filingStatusDao.getEntities();
	}

	@Override
	public TaxOverrideType[] getTaxOverrideType() {
		return TaxOverrideType.values();
	}

	@Override
	public List<String> getExemptedList() {
		// List<TaxType> taxTypeList = taxTypeDao.getFederalTaxType();
		// List<TaxTypeList> list = new ArrayList<>();
		// TaxTypeList taxTypeList2 = null;
		// for (TaxType taxType : taxTypeList) {
		// taxTypeList2 = new TaxTypeList();
		// taxTypeList2.setTaxTypeId(taxType.getId());
		// list.add(taxTypeList2);
		// }
		List<String> list = new ArrayList<>();
		list.add(ExemptedValue.YES);
		list.add(ExemptedValue.NO);
		return list;
	}

	@Override
	@Transactional
	public List<EmployeeTax> addEmployeeFederalTax(EmployeeFederalTaxForm employeeFederalTaxForm,
			Long employeeId) throws InstanceNotFoundException {
		EmployeeTax employeeTax = null;
		Employee employee = employeeDao.find(employeeId);
		List<TaxType> taxTypeList = taxTypeDao.getFederalTaxType();
		List<EmployeeTax> employeeTaxes = new ArrayList<>();
		for (TaxType taxType : taxTypeList) {
			logger.debug("taxType::" + taxType);
			employeeTax = new EmployeeTax();
			employeeTax.setEmployee(employee);
			employeeTax.setAsOnDate(new Date());
			for (TaxTypeList typeList : employeeFederalTaxForm.getExemptedList()) {
				if (typeList.getTaxTypeId().equals(taxType.getId())) {
					employeeTax.setExempted(typeList.getExemptedValue());
				}
			}
			employeeTax.setTaxType(taxType);

			if (!taxType.getPaidByCompany() && taxType.getPaidByEmployee()) {

				EmployeeW4Detail employeeW4Detail = new EmployeeW4Detail();
				employeeW4Detail.setAdditionalWithholding(employeeFederalTaxForm
						.getAdditionalWithholding());

				employeeW4Detail.setAsOnDate(new Date());
				FilingStatus filingStatus = filingStatusDao.find(employeeFederalTaxForm
						.getTaxFillingStatusId());
				employeeW4Detail.setFilingStatus(filingStatus);
				employeeW4Detail.setTaxOverrideType(employeeFederalTaxForm.getTaxOverrideType());
				if (employeeW4Detail.getTaxOverrideType() != null) {
					employeeW4Detail.setOverrideFromCompany(true);
				} else {
					employeeW4Detail.setOverrideFromCompany(false);
				}
				employeeW4Detail.setTaxOverrideValue(employeeFederalTaxForm.getTaxOverrideValue());

				EmployeeAllowanceRecord employeeAllowanceRecord = new EmployeeAllowanceRecord();
				Set<EmployeeAllowanceRecord> employeeAllowanceRecords = new HashSet<EmployeeAllowanceRecord>(
						0);
				employeeAllowanceRecord
						.setNoOfAllowance(employeeFederalTaxForm.getNoOfAllowances());
				employeeAllowanceRecord.setAsOnDate(new Date());
				employeeAllowanceRecord.setAllowanceType(allowanceTypeDao
						.find(employeeFederalTaxForm.getAllowanceTypeId()));
				employeeAllowanceRecord.setEmployeeW4Detail(employeeW4Detail);
				employeeAllowanceRecords.add(employeeAllowanceRecord);
				employeeW4Detail.setEmployeeAllowanceRecords(employeeAllowanceRecords);
				employeeW4Detail.setEmployeeTax(employeeTax);
				employeeTax.getEmployeeW4Details().add(employeeW4Detail);
				employeeTaxes.add(employeeTax);
				employeeTaxDao.save(employeeTax);
			}
			employeeTaxes.add(employeeTax);
			employeeTaxDao.save(employeeTax);
		}
		return employeeTaxes;
	}

	@Override
	@Transactional
	public List<EmployeeTax> addEmployeeStateTax(EmployeeStateTaxForm employeeStateTaxForm)
			throws InstanceNotFoundException {

		List<TaxType> taxTypeList = taxTypeDao.getStateTaxTypes(employeeStateTaxForm
				.getEmployeeId());
		Employee employee = employeeDao.find(employeeStateTaxForm.getEmployeeId());
		List<EmployeeTax> employeeTaxes = new ArrayList<>();
		for (TaxType taxType : taxTypeList) {
			// Adding SUTA
			if (taxType.getPaidByCompany()) {
				EmployeeTax employeeTax = new EmployeeTax();
				employeeTax.setAsOnDate(new Date());
				employeeTax.setEmployee(employee);
				employeeTax.setExempted(employeeStateTaxForm.getSutaExempted());
				employeeTax.setTaxType(taxType);
				employeeTax.setStatus(com.epayroll.entity.EmployeeTax.Status.ACTIVE);
				employeeTaxes.add(employeeTax);
				employeeTaxDao.save(employeeTax);
			}
			// Adding SIT
			else {
				EmployeeTax employeeTax = new EmployeeTax();
				employeeTax.setExempted(employeeStateTaxForm.getSitExempted());
				employeeTax.setAsOnDate(new Date());
				employeeTax.setEmployee(employee);
				employeeTax.setStatus(com.epayroll.entity.EmployeeTax.Status.ACTIVE);
				employeeTax.setTaxType(taxType);
				// employeeTaxId = employeeTaxDao.save(employeeTax);

				EmployeeW4Detail employeeW4Detail = new EmployeeW4Detail();
				employeeW4Detail.setAdditionalWithholding(employeeStateTaxForm
						.getAdditionalWithholding());
				employeeW4Detail.setTaxOverrideType(employeeStateTaxForm.getTaxOverrideType());
				if (employeeW4Detail.getTaxOverrideType() != null) {
					employeeW4Detail.setOverrideFromCompany(true);
				} else {
					employeeW4Detail.setOverrideFromCompany(false);
				}
				employeeW4Detail.setTaxOverrideValue(employeeStateTaxForm.getTaxOverrideValue());
				employeeW4Detail.setStatus(com.epayroll.entity.EmployeeW4Detail.Status.ACTIVE);
				employeeW4Detail.setFilingStatus(filingStatusDao.find(employeeStateTaxForm
						.getTaxFillingStatusId()));
				employeeW4Detail.setAsOnDate(new Date());
				employeeW4Detail.setStatus(com.epayroll.entity.EmployeeW4Detail.Status.ACTIVE);
				employeeW4Detail.setEmployeeTax(employeeTax);
				employeeTax.getEmployeeW4Details().add(employeeW4Detail);

				Set<EmployeeAllowanceRecord> employeeAllowanceRecords = new HashSet<>();
				for (EmployeeAllowanceModel employeeAllowanceModel : employeeStateTaxForm
						.getAllowanceModels()) {
					EmployeeAllowanceRecord employeeAllowanceRecord = new EmployeeAllowanceRecord();
					employeeAllowanceRecord.setAllowanceType(allowanceTypeDao
							.find(employeeAllowanceModel.getAllowanceTypeId()));
					employeeAllowanceRecord.setAsOnDate(new Date());
					employeeAllowanceRecord.setNoOfAllowance(employeeAllowanceModel
							.getNoOfAllowances());
					employeeAllowanceRecord.setEmployeeW4Detail(employeeW4Detail);
					employeeAllowanceRecords.add(employeeAllowanceRecord);

				}
				employeeW4Detail.setEmployeeAllowanceRecords(employeeAllowanceRecords);
				employeeTaxes.add(employeeTax);
				employeeTaxDao.save(employeeTax);

			}
		}

		return employeeTaxes;
	}

	@Override
	@Transactional
	public List<EmployeeTax> addEmployeeLocalTax(EmployeeLocalTaxForm employeeLocalTaxForm,
			Long employeeId) throws InstanceNotFoundException {
		Employee employee = employeeDao.find(employeeId);
		List<TaxType> taxTypeList = taxTypeDao.getLocalTaxTypes(employeeLocalTaxForm
				.getEmployeeId());
		List<EmployeeTax> employeeTaxes = new ArrayList<>();
		for (TaxType taxType : taxTypeList) {
			logger.debug("taxType::" + taxType);
			EmployeeTax employeeTax = new EmployeeTax();
			employeeTax.setEmployee(employee);
			employeeTax.setAsOnDate(new Date());
			employeeTax.setExempted(employeeLocalTaxForm.getExempted());
			employeeTax.setTaxType(taxType);
			employeeTaxes.add(employeeTax);
			employeeTaxDao.save(employeeTax);
		}
		return employeeTaxes;
	}

	@Override
	public List<AllowanceType> getallowanceTypes() {

		return allowanceTypeDao.getEntities();
	}

	@Override
	public EmployeeFederalTaxForm getFitForm(Long employeeId) {
		EmployeeFederalTaxForm employeeFederalTaxForm = new EmployeeFederalTaxForm();
		EmployeeW4DetailModel employeeW4DetailModel = employeeTaxDao.getFITRecord(employeeId);
		// Setting EmployeeTax details
		EmployeeTax employeeTax = employeeW4DetailModel.getEmployeeTax();
		List<TaxTypeList> exemptedList = new ArrayList<>();
		exemptedList.add(new TaxTypeList(employeeTax.getTaxType().getId(), employeeTax.getTaxType()
				.getTaxName(), employeeTax.getExempted(), true));
		employeeFederalTaxForm.setExemptedList(exemptedList);

		// Setting Allowances
		EmployeeAllowanceRecord employeeAllowanceRecord = employeeW4DetailModel
				.getEmployeeAllowanceRecords().get(0);
		employeeFederalTaxForm.setAllowanceTypeId(employeeAllowanceRecord.getAllowanceType()
				.getId());
		employeeFederalTaxForm.setNoOfAllowances(employeeAllowanceRecord.getNoOfAllowance());

		// Setting W4 details
		EmployeeW4Detail employeeW4Detail = employeeAllowanceRecord.getEmployeeW4Detail();
		employeeFederalTaxForm
				.setAdditionalWithholding(employeeW4Detail.getAdditionalWithholding());
		employeeFederalTaxForm.setTaxOverrideType(employeeW4Detail.getTaxOverrideType());
		employeeFederalTaxForm.setTaxOverrideValue(employeeW4Detail.getTaxOverrideValue());
		employeeFederalTaxForm.setTaxFillingStatusId(employeeW4Detail.getFilingStatus().getId());

		return employeeFederalTaxForm;
	}

	@Override
	public EmployeeLocalTaxForm getLocalForm(Long employeeTaxId) throws InstanceNotFoundException {
		EmployeeLocalTaxForm employeeLocalTaxForm = new EmployeeLocalTaxForm();
		EmployeeTax employeeTax = employeeTaxDao.find(employeeTaxId);
		employeeLocalTaxForm.setExempted(employeeTax.getExempted());
		employeeLocalTaxForm.setEmployeeTaxId(employeeTaxId);
		return employeeLocalTaxForm;
	}

	@Override
	public EmployeeStateTaxForm getStateTaxForm(Long employeeId) {
		EmployeeW4DetailModel employeeSitDetailModel = employeeTaxDao.getSITRecord(employeeId);
		EmployeeStateTaxForm employeeStateTaxForm = new EmployeeStateTaxForm();

		List<EmployeeAllowanceModel> allowanceModels = new ArrayList<>();
		// Setting Allowances
		EmployeeW4Detail employeeW4Detail = null;
		for (EmployeeAllowanceRecord employeeAllowanceRecord : employeeSitDetailModel
				.getEmployeeAllowanceRecords()) {
			employeeW4Detail = employeeAllowanceRecord.getEmployeeW4Detail();
			allowanceModels.add(new EmployeeAllowanceModel(employeeAllowanceRecord
					.getAllowanceType().getId(), employeeAllowanceRecord.getAllowanceType()
					.getType(), employeeAllowanceRecord.getNoOfAllowance()));
		}
		employeeStateTaxForm.setAllowanceModels(allowanceModels);

		// Setting W4 details
		EmployeeTax employeeTax = employeeSitDetailModel.getEmployeeTax();
		employeeStateTaxForm.setEmployeeId(employeeId);
		employeeStateTaxForm.setEmployeeSitId(employeeTax.getId());
		employeeStateTaxForm.setAdditionalWithholding(employeeW4Detail.getAdditionalWithholding());
		employeeStateTaxForm.setSitExempted(employeeTax.getExempted());
		employeeStateTaxForm.setTaxOverrideType(employeeW4Detail.getTaxOverrideType());
		employeeStateTaxForm.setTaxOverrideValue(employeeW4Detail.getTaxOverrideValue());
		employeeStateTaxForm.setTaxFillingStatusId(employeeW4Detail.getFilingStatus().getId());

		// Setting SUTA Details
		List<EmployeeTax> employeeTaxes = employeeTaxDao.getStateTaxList(employeeId);
		EmployeeTax sutaTax = employeeTaxes.get(0);
		employeeStateTaxForm.setSutaExempted(sutaTax.getExempted());
		employeeStateTaxForm.setEmployeeSutaId(sutaTax.getId());

		return employeeStateTaxForm;
	}

	@Override
	public Boolean verifyAndDeleteEmployee(Long employeeId) throws InstanceNotFoundException {
		List<EmployeePayroll> employeePayrolls = employeePayrollDao.getPaymentes(employeeId);
		if (employeePayrolls.isEmpty()) {
			employeeDao.remove(employeeId);
			return true;
		} else {
			return null;
		}

	}

	@Override
	public void setEmployeeInactivate(Long employeeId) throws InstanceNotFoundException {
		Employee employee = employeeDao.find(employeeId);
		employee.setStatus(Status.INACTIVE);
		employeeDao.update(employee);
	}

}
