package com.epayroll.service.payroll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.constant.employee.TaxName;
import com.epayroll.dao.AddressDao;
import com.epayroll.dao.CountyTaxRateDao;
import com.epayroll.dao.DeductionCapDao;
import com.epayroll.dao.EmployeePayrollEarningDao;
import com.epayroll.dao.FederalStateTaxRateDao;
import com.epayroll.dao.FutaSutaTaxRateDao;
import com.epayroll.dao.WorkersCompensationTaxRateDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.CompanyEarningDao;
import com.epayroll.dao.company.CompanyPayrollDeductionDao;
import com.epayroll.dao.company.CompanyPayrollTaxDao;
import com.epayroll.dao.company.CompanyPayrollWorkerCompDao;
import com.epayroll.dao.company.CompanyTaxDao;
import com.epayroll.dao.company.PayrollCompanyDao;
import com.epayroll.dao.company.PayrollFrequencyDao;
import com.epayroll.dao.company.PayrollScheduleDao;
import com.epayroll.dao.company.StandardDeductionRateDao;
import com.epayroll.dao.company.UserDao;
import com.epayroll.dao.employee.AllowanceRateDao;
import com.epayroll.dao.employee.EmployeeAllowanceRecordDao;
import com.epayroll.dao.employee.EmployeeDao;
import com.epayroll.dao.employee.EmployeeDeductionDao;
import com.epayroll.dao.employee.EmployeeEarningDao;
import com.epayroll.dao.employee.EmployeePaySetupDao;
import com.epayroll.dao.employee.EmployeePayrollDao;
import com.epayroll.dao.employee.EmployeePayrollDeductionDao;
import com.epayroll.dao.employee.EmployeePayrollTaxDao;
import com.epayroll.dao.employee.EmployeeTaxDao;
import com.epayroll.dao.employee.EmployeeW4DetailsDao;
import com.epayroll.dao.payroll.PayrollDao;
import com.epayroll.entity.AllowanceRate;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.entity.CompanyPayrollDeduction;
import com.epayroll.entity.CompanyPayrollTax;
import com.epayroll.entity.CompanyPayrollWorkerComp;
import com.epayroll.entity.CompanyTax;
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.CountyTaxRate;
import com.epayroll.entity.DeductionCap;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.EmployeeDeduction;
import com.epayroll.entity.EmployeeDeduction.DeductionType;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;
import com.epayroll.entity.EmployeeEarning;
import com.epayroll.entity.EmployeeEarning.EarningValueType;
import com.epayroll.entity.EmployeePaySetup;
import com.epayroll.entity.EmployeePaySetup.PayType;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayroll.RowType;
import com.epayroll.entity.EmployeePayrollDeduction;
import com.epayroll.entity.EmployeePayrollEarning;
import com.epayroll.entity.EmployeePayrollTax;
import com.epayroll.entity.EmployeePayrollTax.TaxOverrideFrom;
import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.entity.EmployeeW4Detail;
import com.epayroll.entity.FederalStateTaxRate;
import com.epayroll.entity.FutaSutaTaxRate;
import com.epayroll.entity.Payroll;
import com.epayroll.entity.Payroll.PayrollType;
import com.epayroll.entity.Payroll.Status;
import com.epayroll.entity.PayrollCompany;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollSchedule;
import com.epayroll.entity.StandardDeductionRate;
import com.epayroll.entity.StandardDeductionRate.RateValueType;
import com.epayroll.entity.WorkersCompensationTaxRate;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.payroll.PayRollForm;
import com.epayroll.form.payroll.PayrollDeductionForm;
import com.epayroll.form.payroll.PayrollPreviewForm;
import com.epayroll.model.MailMessage;
import com.epayroll.model.payroll.EmployeeDeductionModel;
import com.epayroll.model.payroll.EmployeePayrollModel;
import com.epayroll.model.payroll.EmployeeTaxModel;
import com.epayroll.model.payroll.PayrollEmployeeEarningModel;
import com.epayroll.model.payroll.PayrollEmployeeModel;

@Service
public class PayrollServiceImpl implements PayrollService {

	private Logger logger = LoggerFactory.getLogger(PayrollServiceImpl.class);

	@Autowired
	private PayrollFrequencyDao payrollFrequencyDao;

	@Autowired
	private PayrollScheduleDao payrollScheduleDao;

	@Autowired
	private PayrollDao payrollDao;

	@Autowired
	private CompanyEarningDao companyEarningDao;

	@Autowired
	private EmployeeEarningDao employeeEarningDao;

	@Autowired
	private EmployeePaySetupDao employeePaySetupDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private EmployeePayrollDao employeePayrollDao;

	@Autowired
	private EmployeePayrollEarningDao employeePayrollEarningDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmployeeDeductionDao employeeDeductionDao;

	@Autowired
	private CompanyTaxDao companyTaxDao;

	@Autowired
	private EmployeeW4DetailsDao employeeW4DetailsDao;

	@Autowired
	private EmployeePayrollTaxDao payrollTaxDao;

	@Autowired
	private EmployeePayrollDeductionDao payrollDeductionDao;

	@Autowired
	private CompanyPayrollDeductionDao companyPayrollDeductionDao;

	@Autowired
	private EmployeeTaxDao employeeTaxDao;

	@Autowired
	private EmployeeAllowanceRecordDao employeeAllowanceRecordDao;

	@Autowired
	private AllowanceRateDao allowanceRateDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private FederalStateTaxRateDao federalStateTaxRateDao;

	@Autowired
	private CountyTaxRateDao countyTaxRateDao;

	@Autowired
	private FutaSutaTaxRateDao futaSutaTaxRateDao;

	@Autowired
	private WorkersCompensationTaxRateDao workersCompensationTaxRateDao;

	@Autowired
	private DeductionCapDao deductionCapDao;

	@Autowired
	private StandardDeductionRateDao standardDeductionRateDao;

	@Autowired
	private CompanyPayrollTaxDao companyPayrollTaxDao;

	@Autowired
	private CompanyPayrollWorkerCompDao companyPayrollWorkerCompDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PayrollCompanyDao payrollCompanyDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeTax> getEmployeePrimaryTaxList(Long employeeId) {

		return payrollDao.getemployeePrimaryTaxList(employeeId);
	}

	// /////////////////// EMPLOYEE PAYROLL ///////////////////////////
	@Override
	public List<PayrollFrequency> getPayrollFrequencies(Long companyId) {

		return payrollFrequencyDao.getPayrollFrequencies(companyId);
	}

	@Override
	public List<CompanyEarning> getCompanyEarnings(Long companyId) {
		logger.debug("in getCompanyEarnings ");
		return companyEarningDao.getCompanyEarnings(companyId);
	}

	@Override
	@Transactional
	public PayRollForm getPayRollForm(Long companyId, PayRollForm payRollForm) {
		// get current payroll schedule from PayrollSchedule
		PayrollSchedule payrollSchedule = payrollScheduleDao.getCurrentPayrollSchedule(payRollForm
				.getPayrollFrequencyId());
		// If the current payroll schedule is null then we need to notify the
		// user to make a new frequency.
		try {
			if (payrollSchedule != null) {
				logger.debug("in payrollSchedule != null");
				Long payrollId = null;
				Payroll payroll = payrollDao.getPayroll(companyId, payrollSchedule.getId());
				// check in payroll table for this schedule
				if (payroll == null) {
					logger.debug("payroll == null : Saving data into payroll tabes form  base tables...");

					payrollId = savePayrollData(companyDao.find(companyId), payrollSchedule,
							payRollForm);
				} else {
					logger.debug("payroll != null : ");
					payrollId = payroll.getId();
				}

				payRollForm = getEmployeesFromPayroll(payrollId);

				payRollForm.setStartDate(payrollSchedule.getPeriodStartDate());
				payRollForm.setEndDate(payrollSchedule.getPeriodEndDate());
				payRollForm.setCheckDate(payrollSchedule.getCheckDate());

			} else {
				logger.debug("Notify user to add new payroll frequency");
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in getPayRollForm :", e);
		}
		return payRollForm;
	}

	@Override
	@Transactional
	public void updatePayrollData(PayRollForm payRollForm) {
		logger.debug("in updatePayrollData..");
		try {
			Payroll payroll = payrollDao.find(payRollForm.getPayrollId());

			EmployeePayroll employeePayroll = null;
			Set<EmployeePayroll> employeePayrolls = new HashSet<>();
			for (PayrollEmployeeModel payrollEmployeeModel : payRollForm.getPayrollEmployees()) {
				logger.debug("in payrollEmployeeData..");
				employeePayroll = employeePayrollDao.find(payrollEmployeeModel
						.getEmployeePayrollId());
				employeePayroll.setDirectDepositStatus(payrollEmployeeModel
						.getDirectDepositStatus());
				if (payrollEmployeeModel.getSalary() != null) {
					employeePayroll.setSalary(new BigDecimal(payrollEmployeeModel.getSalary()));
				}
				if (payrollEmployeeModel.getHourlyRate() != null) {
					employeePayroll.setHourlyRate(new BigDecimal(payrollEmployeeModel
							.getHourlyRate()));
				}
				employeePayroll.setRegularHours(new BigDecimal(payrollEmployeeModel
						.getRegularHours()));

				EmployeePayrollEarning employeePayrollEarning = null;
				Set<EmployeePayrollEarning> employeePayrollEarnings = new HashSet<>();

				if (payrollEmployeeModel.getPayrollEmployeeEarnings() != null) {

					for (PayrollEmployeeEarningModel employeeEarningData : payrollEmployeeModel
							.getPayrollEmployeeEarnings()) {
						logger.debug("in employeeEarningData");
						employeePayrollEarning = employeePayrollEarningDao.find(employeeEarningData
								.getEmployeePayrollEarningId());
						employeePayrollEarning.setValue(new BigDecimal(employeeEarningData
								.getValue()));
						employeePayrollEarnings.add(employeePayrollEarning);
					}
				}
				employeePayroll.setEmployeePayrollEarnings(employeePayrollEarnings);
			}
			employeePayrolls.add(employeePayroll);
			payroll.setEmployeePayrolls(employeePayrolls);
			// payrollDao.update(payroll);

		} catch (InstanceNotFoundException e) {
			logger.error("Error in updatePayrollData :", e);
		}
	}

	@Override
	@Transactional
	public void deletePayroll(Long payrollId) {
		logger.debug("in deletePayroll..");
		try {
			Payroll payroll = payrollDao.find(payrollId);
			Long payrollFreqId = payroll.getPayrollSchedule().getPayrollFrequency().getId();

			payrollDao.remove(payroll);

			// update payroll-schedule status to "DELETED" and make next
			// schedule
			// to "CURRENT"
			List<PayrollSchedule> payrollSchedules = payrollScheduleDao
					.getPayrollScheduleList(payrollFreqId);

			Boolean isDeleted = false;
			for (PayrollSchedule payrollSchedule : payrollSchedules) {
				if (isDeleted) {
					payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.CURRENT);
					break;
				}
				if (payrollSchedule.getStatus().equals(
						com.epayroll.entity.PayrollSchedule.Status.CURRENT)) {
					payrollSchedule.setStatus(com.epayroll.entity.PayrollSchedule.Status.DELETED);
					isDeleted = true;
				}
			}

		} catch (InstanceNotFoundException e) {
			logger.error("Error in savePayrollData:", e);
		}
	}

	private Long savePayrollData(Company company, PayrollSchedule payrollSchedule,
			PayRollForm payRollForm) {
		logger.debug(" savePayrollData ");
		Long id = null;

		Payroll payroll = new Payroll();

		payroll.setCompany(company);
		payroll.setPayrollSchedule(payrollSchedule);

		payroll.setStartDate(payrollSchedule.getPeriodStartDate());
		payroll.setEndDate(payrollSchedule.getPeriodEndDate());
		payroll.setCheckDate(payrollSchedule.getCheckDate());
		payroll.setPayrollStatus(Status.ACTIVE);
		payroll.setPayrollType(PayrollType.NORMAL);

		// saving data into employee_payroll
		// fetch employees
		EmployeePayroll employeePayroll = null;
		Set<EmployeePayroll> employeePayrolls = new HashSet<>();

		List<EmployeePaySetup> employeePaySetups = employeePaySetupDao
				.getEmployeePaySetups(payRollForm.getPayrollFrequencyId());
		logger.debug("employeePaySetups.size()::" + employeePaySetups.size());
		if (!employeePaySetups.isEmpty()) {
			for (EmployeePaySetup employeePaySetup : employeePaySetups) {
				Long empId = employeePaySetup.getEmployee().getId();
				logger.debug("empId::" + empId);
				Employee employee = employeePaySetup.getEmployee();

				employeePayroll = new EmployeePayroll();
				employeePayroll.setPayroll(payroll);
				employeePayroll.setEmployee(employee);
				employeePayroll.setDirectDepositStatus(false);
				if (employeePaySetup.getSalary() != null) {
					employeePayroll.setSalary(employeePaySetup.getSalary());
				}
				if (employeePaySetup.getHourlyRate() != null) {
					employeePayroll.setHourlyRate(employeePaySetup.getHourlyRate());
				}
				employeePayroll.setRegularHours(employeePaySetup.getRegularHours());
				employeePayroll.setRowType(RowType.MAIN_ROW);
				employeePayroll.setStatus(com.epayroll.entity.EmployeePayroll.Status.ACTIVE);
				employeePayroll.setPayType(employeePaySetup.getPayType());

				if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {
					// saving earning for particular employee into
					// employee_payroll_earning
					EmployeePayrollEarning employeePayrollEarning = null;
					Set<EmployeePayrollEarning> employeePayrollEarnings = new HashSet<>();
					for (EmployeeEarning employeeEarning : employeeEarningDao
							.getEmployeeEarnings(empId)) {
						logger.debug("in EmployeeEarning ID::" + employeeEarning.getId());

						employeePayrollEarning = new EmployeePayrollEarning();

						employeePayrollEarning.setEmployeeEarning(employeeEarning);
						employeePayrollEarning.setEmployeePayroll(employeePayroll);
						employeePayrollEarning.setEarningValueType(employeeEarning
								.getEarningValueType());
						employeePayrollEarning.setValue(employeeEarning.getValue());
						employeePayrollEarning.setCalculatedY2d(employeePayrollEarningDao
								.getCalculatedEarningY2D(employeeEarning.getId()));
						employeePayrollEarnings.add(employeePayrollEarning);
					}
					employeePayroll.setEmployeePayrollEarnings(employeePayrollEarnings);
				}
				employeePayrolls.add(employeePayroll);

				// saving the Employee's Taxes and Deductions
				saveTaxAndDeductions(employeePayroll, employee);
			}
			payroll.setEmployeePayrolls(employeePayrolls);
			id = payrollDao.save(payroll);
			logger.debug("PayrollID::" + id);
		}

		return id;
	}

	/*
	 * fetch records from associate payroll tables e.g.,(employee_payroll,
	 * employee_payroll_earning)
	 */
	private PayRollForm getEmployeesFromPayroll(Long payrollId) {
		logger.debug("in getEmployeeFromPayroll..");
		PayRollForm payRollForm = new PayRollForm();
		payRollForm.setPayrollId(payrollId);

		PayrollEmployeeModel payrollEmployeeModel = null;
		List<PayrollEmployeeModel> payrollEmployeeModels = new ArrayList<PayrollEmployeeModel>();
		List<EmployeePayroll> employeePayrolls = employeePayrollDao.getEmployeePayroll(payrollId);
		logger.debug("employeePayrolls.size::" + employeePayrolls.size());
		for (EmployeePayroll employeePayroll : employeePayrolls) {
			Long empId = employeePayroll.getEmployee().getId();
			logger.debug("employeePayrollId: " + employeePayroll.getId() + ",empId::" + empId);

			payrollEmployeeModel = new PayrollEmployeeModel();
			payrollEmployeeModel.setEmployeePayrollId(employeePayroll.getId());
			payrollEmployeeModel.setEmployeeName(employeePayroll.getEmployee().getPerson()
					.getFirstName()
					+ " " + employeePayroll.getEmployee().getPerson().getLastName());
			payrollEmployeeModel.setDirectDepositStatus(employeePayroll.getDirectDepositStatus());
			if (employeePayroll.getSalary() != null) {
				payrollEmployeeModel.setSalary(employeePayroll.getSalary().doubleValue());
			}
			if (employeePayroll.getHourlyRate() != null) {
				payrollEmployeeModel.setHourlyRate(employeePayroll.getHourlyRate().doubleValue());
			}
			payrollEmployeeModel.setRegularHours(employeePayroll.getRegularHours().doubleValue());

			// fetching earnings for particular employee
			PayrollEmployeeEarningModel earningModel = null;
			List<PayrollEmployeeEarningModel> earningModels = new ArrayList<>();

			List<EmployeePayrollEarning> employeePayrollEarnings = employeePayrollEarningDao
					.getEmployeePayrollEarningList(employeePayroll.getId());
			if (!employeePayrollEarnings.isEmpty()) {
				for (EmployeePayrollEarning employeePayrollEarning : employeePayrollEarnings) {
					logger.debug("in employeePayrollEarning::");
					earningModel = new PayrollEmployeeEarningModel();
					earningModel.setEmployeePayrollEarningId(employeePayrollEarning.getId());
					if (employeePayrollEarning.getValue() != null) {
						earningModel.setValue(employeePayrollEarning.getValue().doubleValue());
					}
					earningModels.add(earningModel);
				}
			}
			payrollEmployeeModel.setPayrollEmployeeEarnings(earningModels);
			payrollEmployeeModels.add(payrollEmployeeModel);
		}
		payRollForm.setPayrollEmployees(payrollEmployeeModels);

		return payRollForm;
	}

	// ///////////// END ////////////////

	// ////////////// OVERRIDE TAX & DEDUCTION /////////////////////////

	@Override
	public TaxOverrideType[] getTaxOverrideTypes() {
		return TaxOverrideType.values();
	}

	@Override
	public DeductionValueType[] getDeductionOverrideTypes() {
		return DeductionValueType.values();
	}

	@Override
	public PayrollDeductionForm getTaxAndDeductionFromPayroll(Long employeePayrollId) {
		logger.debug(" >>  in getTaxAndDeductionFromPayroll ..");
		PayrollDeductionForm deductionForm = new PayrollDeductionForm();

		try {
			EmployeePayroll employeePayroll = employeePayrollDao.find(employeePayrollId);

			Employee employee = employeePayroll.getEmployee();
			String employeeName = employee.getPerson().getFirstName() + " "
					+ employee.getPerson().getLastName();

			deductionForm.setEmployeeName(employeeName);
			deductionForm.setEmployeePayrollId(employeePayrollId);

			// fetching the tax details
			if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {

				EmployeeTaxModel taxModel = null;
				List<EmployeeTaxModel> taxModels = new ArrayList<>();

				List<EmployeePayrollTax> employeePayrollTaxs = payrollTaxDao
						.getEmployeePrimaryTaxes(employeePayrollId);
				for (EmployeePayrollTax employeePayrollTax : employeePayrollTaxs) {
					taxModel = new EmployeeTaxModel();
					taxModel.setTaxName(employeePayrollTax.getEmployeeTax().getTaxType()
							.getTaxName());
					taxModel.setTaxOverrideType(employeePayrollTax.getTaxOverrideType());
					taxModel.setAmount(employeePayrollTax.getTaxOverrideValue().doubleValue());
					taxModels.add(taxModel);
				}
				deductionForm.setEmployeeTaxes(taxModels);
				logger.debug("taxModels::" + taxModels);
			}

			// fetching the deduction details
			EmployeeDeductionModel deductionModel = null;
			List<EmployeeDeductionModel> deductionModels = new ArrayList<>();

			List<EmployeePayrollDeduction> employeePayrollDeductions = payrollDeductionDao
					.getEmployeeDeductions(employeePayrollId);
			for (EmployeePayrollDeduction employeePayrollDeduction : employeePayrollDeductions) {
				deductionModel = new EmployeeDeductionModel();
				deductionModel.setEmployeePayrollDeductionId(employeePayrollDeduction.getId());
				deductionModel.setDeductionName(employeePayrollDeduction.getEmployeeDeduction()
						.getCompanyDeduction().getDisplayName());
				deductionModel.setDeductionOverrideType(employeePayrollDeduction
						.getDeductionOverrideType());
				deductionModel.setAmount(employeePayrollDeduction.getDeductionOverrideValue()
						.doubleValue());
				deductionModels.add(deductionModel);
			}
			deductionForm.setEmployeeDeductions(deductionModels);
			logger.debug("deductionModels::" + deductionModels);

			logger.debug(" in getTaxAndDeductionFromPayroll .. >> ");
		} catch (InstanceNotFoundException e) {
			logger.error("Error in getTaxAndDeductionFromPayroll ", e);
		}
		return deductionForm;
	}

	@Override
	@Transactional
	public void updateTaxAndDeductions(PayrollDeductionForm deductionForm) {
		logger.debug(" >>  in  updateTaxAndDeductions ");
		try {
			if (deductionForm.getEmployeeTaxes() != null) {
				EmployeePayrollTax payrollTax = null;

				for (EmployeeTaxModel taxModel : deductionForm.getEmployeeTaxes()) {
					payrollTax = payrollTaxDao.find(taxModel.getEmployeePayrollTaxId());
					// payrollTax.setIsTaxOverride(true);

					// Change, Author: Pankaj
					payrollTax.setTaxOverrideFrom(TaxOverrideFrom.FROM_PAYROLL);
					payrollTax.setTaxOverrideType(taxModel.getTaxOverrideType());
					payrollTax.setTaxOverrideValue(new BigDecimal(taxModel.getAmount()));
					payrollTaxDao.update(payrollTax);
				}
			}

			EmployeePayrollDeduction payrollDeduction = null;
			for (EmployeeDeductionModel deductionModel : deductionForm.getEmployeeDeductions()) {
				payrollDeduction = payrollDeductionDao.find(deductionModel
						.getEmployeePayrollDeductionId());
				payrollDeduction
						.setDeductionOverrideType(deductionModel.getDeductionOverrideType());
				payrollDeduction.setDeductionOverrideValue(new BigDecimal(deductionModel
						.getAmount()));
				payrollDeductionDao.update(payrollDeduction);
			}

		} catch (InstanceNotFoundException e) {
			logger.error("Error in updateTaxAndDeductions ", e);
		}
		logger.debug("  updateTaxAndDeductions >>  ");
	}

	private void saveTaxAndDeductions(EmployeePayroll employeePayroll, Employee employee) {
		logger.debug(" >>  in  saveTaxAndDeductions ");

		// saving the tax details
		if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {

			List<EmployeeTax> employeeTaxs = employeeTaxDao.getEmployeeTaxs(employee.getId());
			List<EmployeeW4Detail> employeeW4Details = employeeW4DetailsDao
					.getEmployeeW4Details(employee.getId());
			EmployeePayrollTax payrollTax = null;
			CompanyPayrollTax companyPayrollTax = null;
			for (EmployeeTax employeeTax : employeeTaxs) {
				payrollTax = new EmployeePayrollTax();
				payrollTax.setEmployeePayroll(employeePayroll);
				// payrollTax.setIsTaxOverride(false);
				payrollTax.setEmployeeTax(employeeTax);
				CompanyTax companyTax = companyTaxDao.getCompanyTaxRecord(employeeTax.getEmployee()
						.getCompany().getId(), employeeTax.getTaxType().getId());
				if (companyTax != null) {
					companyPayrollTax = new CompanyPayrollTax();
					companyPayrollTax.setEmployeePayroll(employeePayroll);
					companyPayrollTax.setCompanyTax(companyTax);
					companyPayrollTaxDao.save(companyPayrollTax);
				}

			}
			for (EmployeeW4Detail employeeW4Detail : employeeW4Details) {

				payrollTax = new EmployeePayrollTax();
				payrollTax.setEmployeePayroll(employeePayroll);

				// payrollTax.setIsTaxOverride(false);

				// Author: Pankaj
				// Saving the appropriate Tax override from value
				if (employeeW4Detail.getOverrideFromCompany().equals(true)) {
					payrollTax.setTaxOverrideFrom(TaxOverrideFrom.FROM_COMPANY);
					payrollTax.setTaxOverrideType(employeeW4Detail.getTaxOverrideType());

				} else if (employeeW4Detail.getOverrideFromCompany().equals(false)) {
					payrollTax.setTaxOverrideFrom(TaxOverrideFrom.NO_OVERRIDE);
				}

				payrollTax.setEmployeeTax(employeeW4Detail.getEmployeeTax());
				if (employeeW4Detail.getTaxOverrideValue() != null) {
					payrollTax.setTaxOverrideType(employeeW4Detail.getTaxOverrideType());
					payrollTax.setTaxOverrideValue(employeeW4Detail.getTaxOverrideValue());
				}
			}
		}

		// saving the deduction details
		EmployeePayrollDeduction payrollDeduction = null;
		List<EmployeeDeduction> employeeDeductions = employeeDeductionDao
				.getEmployeeDeductions(employee.getId());
		for (EmployeeDeduction employeeDeduction : employeeDeductions) {
			payrollDeduction = new EmployeePayrollDeduction();

			payrollDeduction.setEmployeePayroll(employeePayroll);
			payrollDeduction.setEmployeeDeduction(employeeDeduction);
			payrollDeduction.setIsDeductionOverride(false);
			payrollDeduction.setDeductionValueType(employeeDeduction.getDeductionValueType());
			payrollDeduction.setValue(employeeDeduction.getValue());
			payrollDeduction.setDeductionType(employeeDeduction.getDeductionType());
			payrollDeduction.setCalculatedY2d(payrollDeductionDao
					.getCalculatedDeductionY2D(employeeDeduction.getId()));
			payrollDeductionDao.save(payrollDeduction);
		}
		logger.debug("  saveTaxAndDeductions >>  ");
	}

	// //////////////// OVERRIDE TAXES & DEDUCTIONS ENDS ////////////////

	// ////////////////// PAYROLL CALCULATION BEGINS ////////////////////

	// /To fetch employee allowances record
	@Override
	public List<EmployeeAllowanceRecord> getEmployeeAllowanceRecord(Long employeeId) {
		return employeeAllowanceRecordDao.getemployeeAllowanceRecords(employeeId);
	}

	public Map<String, List<?>> getBaseValuesForCalculation() {
		Map<String, List<?>> map = new HashMap<>();
		map.put("FederalStateTaxRate", federalStateTaxRateDao.getEntities());
		map.put("FutaSutaTaxRate", futaSutaTaxRateDao.getEntities());
		map.put("DeductionCap", deductionCapDao.getEntities());

		return map;
	}

	// Fetch remaining Payrolls for this year
	public BigDecimal getRemainingPayrollsCount(Long payrollFrequencyId) {
		Long remainingPayrollCount = payrollScheduleDao
				.getRemainingPayrollCount(payrollFrequencyId);
		BigDecimal remainingPayrollsCount = new BigDecimal(remainingPayrollCount);
		logger.debug("remainingPayrollsCount >>> " + remainingPayrollsCount);
		return remainingPayrollsCount;
	}

	// Get employee Tax Sum Map
	public Map<String, BigDecimal> getEmployeeTaxSumMap(EmployeePayrollTax employeePayrollTax) {
		Map<String, BigDecimal> employeeTaxSumMap = new HashMap<>();
		employeeTaxSumMap.put(employeePayrollTax.getEmployeeTax().getTaxType().getTaxName(),
				payrollTaxDao.getCalculatedTaxY2D(employeePayrollTax.getEmployeeTax().getId()));
		logger.debug("employeeTaxSumMap >>> " + employeeTaxSumMap.size());

		return employeeTaxSumMap;
	}

	// Fetching Federal Tax Slabs List according to filing status
	public List<FederalStateTaxRate> getFederalTaxSlabsList(
			List<FederalStateTaxRate> federalStateTaxRateList, Long fedFilingStatus) {
		List<FederalStateTaxRate> fedTaxRateList = new ArrayList<>();

		for (FederalStateTaxRate federalStateTaxRate : federalStateTaxRateList) {

			if (federalStateTaxRate.getFilingStatus() != null) {
				if (federalStateTaxRate.getFilingStatus().getId().equals(fedFilingStatus)
						&& federalStateTaxRate.getTaxType().getTaxName()
								.equals(TaxName.FEDERAL_INCOME_TAX)) {
					fedTaxRateList.add(federalStateTaxRate);
				}
			}
		}
		logger.debug("fedTaxRate size" + fedTaxRateList.size());
		return fedTaxRateList;
	}

	// Calculate FIT with NO Overrides
	public BigDecimal calculateFITWithoutOverrides(BigDecimal fitTaxableIncome,
			FederalStateTaxRate fedTaxMax, List<FederalStateTaxRate> fedTaxRateList,
			Map<String, BigDecimal> employeeTaxSumMap, BigDecimal remainingPayrollsCount) {

		BigDecimal federalIncomeTax = new BigDecimal(0L);
		BigDecimal yearlyFederalIncomeTax = new BigDecimal(0L);
		BigDecimal fitSalAboveSlab = new BigDecimal(0);
		BigDecimal sumFedCalculatedSlab = new BigDecimal(0);
		BigDecimal fedTaxrate = new BigDecimal(0);

		logger.debug("FEDTaxMAx::::::::::::::" + fedTaxMax);

		if (fitTaxableIncome.compareTo(fedTaxMax.getMinValue()) == 1) {

			fitSalAboveSlab = fitTaxableIncome.subtract(fedTaxMax.getMinValue());
			fedTaxrate = fedTaxMax.getEmployeeRate().divide(new BigDecimal(100));
		}

		for (FederalStateTaxRate federalStateTaxRate : fedTaxRateList) {

			if (fitTaxableIncome.compareTo(federalStateTaxRate.getMinValue()) == 1
					&& fitTaxableIncome.compareTo(federalStateTaxRate.getMaxValue()) == -1) {

				fitSalAboveSlab = fitTaxableIncome.subtract(federalStateTaxRate.getMinValue());
				fedTaxrate = federalStateTaxRate.getEmployeeRate().divide(new BigDecimal(100));

				break;
			} else {

				if (federalStateTaxRate.getCalculatedSlabAmount() != null) {
					sumFedCalculatedSlab = sumFedCalculatedSlab.add(federalStateTaxRate
							.getCalculatedSlabAmount());
				}
			}
		}

		logger.debug("fitsalabove slab:::::::::::::::::::::::" + fitSalAboveSlab);
		logger.debug("SUm fed calculated Slab::::::::::::::::::::::" + sumFedCalculatedSlab);
		logger.debug("Fed TAx RAte::::::::::::::::::::" + fedTaxrate);

		yearlyFederalIncomeTax = sumFedCalculatedSlab.add((fitSalAboveSlab).multiply(fedTaxrate));

		logger.debug("Yearly Federal Income Tax::::::::::::::" + yearlyFederalIncomeTax);

		System.out.println("employeeTaxSumMap >>> " + employeeTaxSumMap);
		federalIncomeTax = (yearlyFederalIncomeTax.subtract(employeeTaxSumMap
				.get(TaxName.FEDERAL_INCOME_TAX))).divide(remainingPayrollsCount, 4,
				RoundingMode.CEILING);

		logger.debug("Federal Income Tax::::::::::::::::::" + federalIncomeTax);
		return federalIncomeTax;

	}

	// State Income Tax calculation
	// Fetching State Tax Slabs List according to filing status
	public List<FederalStateTaxRate> getStateTaxSlabsList(
			List<FederalStateTaxRate> federalStateTaxRateList, Long stateFilingStatus,
			Long employeeId) {
		List<FederalStateTaxRate> stateTaxRateList = new ArrayList<>();
		for (FederalStateTaxRate federalStateTaxRate : federalStateTaxRateList) {

			if (federalStateTaxRate.getTaxType().getTaxAuthority().getUsState() != null
					&& federalStateTaxRate.getFilingStatus() != null) {
				if (federalStateTaxRate.getTaxType().getTaxAuthority().getUsState().getId()
						.equals(addressDao.getEmployeeResidentialState(employeeId))
						&& federalStateTaxRate.getFilingStatus().getId().equals(stateFilingStatus)) {

					stateTaxRateList.add(federalStateTaxRate);
				}
			}
		}
		logger.debug("fedTaxRate size" + stateTaxRateList.size());
		return stateTaxRateList;
	}

	// Calculate SIT with NO Overrides
	public BigDecimal calculateSITWithoutOverrides(BigDecimal sitTaxableIncome,
			FederalStateTaxRate stateTaxMax, List<FederalStateTaxRate> stateTaxRateList,
			Map<String, BigDecimal> employeeTaxSumMap, BigDecimal remainingPayrollsCount,
			String stateTaxAuthorityName) {

		BigDecimal stateIncomeTax = new BigDecimal(0);
		BigDecimal sitSalAboveSlab = new BigDecimal(0);
		BigDecimal sumStateCalculatedSlab = new BigDecimal(0);
		BigDecimal yearlyStateIncomeTax = new BigDecimal(0);
		BigDecimal stateTaxRate = new BigDecimal(0);

		logger.debug("State TAx MAx::::" + stateTaxMax);

		if (sitTaxableIncome.compareTo(stateTaxMax.getMinValue()) == 1) {

			sitSalAboveSlab = sitTaxableIncome.subtract(stateTaxMax.getMinValue());
			stateTaxRate = stateTaxMax.getEmployeeRate().divide(new BigDecimal(100));
		}

		logger.debug("sitSalAbove Slab::::::::::::" + sitSalAboveSlab);
		logger.debug("State TAx RAte::::::::::::" + stateTaxRate);

		for (FederalStateTaxRate federalStateTaxRate : stateTaxRateList) {

			if (sitTaxableIncome.compareTo(federalStateTaxRate.getMinValue()) == 1
					&& sitTaxableIncome.compareTo(federalStateTaxRate.getMaxValue()) == -1) {

				sitSalAboveSlab = sitTaxableIncome.subtract(federalStateTaxRate.getMinValue());
				stateTaxRate = federalStateTaxRate.getEmployeeRate().divide(new BigDecimal(100));

				break;
			} else {

				if (federalStateTaxRate.getCalculatedSlabAmount() != null) {
					sumStateCalculatedSlab = sumStateCalculatedSlab.add(federalStateTaxRate
							.getCalculatedSlabAmount());
				}
			}
		}

		logger.debug("sitSalAbove Slab::::::::::::" + sitSalAboveSlab);
		logger.debug("sumStateCalculatedSlab:::::::::::::" + sumStateCalculatedSlab);

		yearlyStateIncomeTax = sumStateCalculatedSlab.add((sitSalAboveSlab).multiply(stateTaxRate));
		logger.debug("yearlyState Income Tax ::::::" + yearlyStateIncomeTax);
		stateIncomeTax = (yearlyStateIncomeTax.subtract(employeeTaxSumMap
				.get(stateTaxAuthorityName))).divide(remainingPayrollsCount, 4,
				RoundingMode.CEILING);

		return stateIncomeTax;
	}

	// Calculating and saving Employer match for deduction
	private BigDecimal saveCompanyDeductionMatch(CompanyDeduction companyDeduction,
			EmployeePayroll employeePayroll, BigDecimal employeeDeductionAmount,
			BigDecimal grossPayCurrentPayroll) {
		BigDecimal hundred = new BigDecimal(100);
		BigDecimal tempEmployerMatch = new BigDecimal(0);
		BigDecimal employeePercent = new BigDecimal(0);

		if (employeePayroll.getEmployee().getEmployeeType() == Type.EMPLOYEE) {
			CompanyPayrollDeduction companyPayrollDeduction = new CompanyPayrollDeduction();
			if (companyDeduction.isEmployerMatchApplicable()) {
				System.out.println(grossPayCurrentPayroll);
				System.out.println(employeeDeductionAmount);
				System.out.println(companyDeduction.getId());
				System.out.println(employeePayroll.getId());
				employeePercent = (employeeDeductionAmount.divide(grossPayCurrentPayroll, 4,
						RoundingMode.CEILING)).multiply(hundred);
				System.out.println(employeePercent);
				if ((employeePercent.compareTo(companyDeduction.getEmployeeFirstValue()) == -1)
						|| (employeePercent.compareTo(companyDeduction.getEmployeeFirstValue()) == 0)) {
					tempEmployerMatch = companyDeduction.getEmployerFirstMatchValue()
							.divide(hundred).multiply(employeePercent).divide(hundred)
							.multiply(grossPayCurrentPayroll);

				}

				else if (employeePercent.compareTo(companyDeduction.getEmployeeFirstValue()) == 1) {
					if (companyDeduction.getEmployerNextMatchValue() == null) {
						tempEmployerMatch = companyDeduction.getEmployerFirstMatchValue()
								.divide(hundred).multiply(companyDeduction.getEmployeeFirstValue())
								.divide(hundred).multiply(grossPayCurrentPayroll);
					} else if (companyDeduction.getEmployerNextMatchValue() != null) {
						if (employeePercent.compareTo(companyDeduction.getEmployeeFirstValue().add(
								companyDeduction.getEmployeeNextValue())) == 1) {
							tempEmployerMatch = (companyDeduction.getEmployerFirstMatchValue()
									.divide(hundred)
									.multiply(companyDeduction.getEmployeeFirstValue())
									.divide(hundred).multiply(grossPayCurrentPayroll))
									.add(companyDeduction.getEmployerNextMatchValue()
											.divide(hundred)
											.multiply(companyDeduction.getEmployeeNextValue())
											.divide(hundred).multiply(grossPayCurrentPayroll));
						} else if ((employeePercent.compareTo(companyDeduction
								.getEmployeeFirstValue().add(
										companyDeduction.getEmployeeNextValue())) == -1)
								|| (employeePercent.compareTo(companyDeduction
										.getEmployeeFirstValue().add(
												companyDeduction.getEmployeeNextValue())) == 0)) {
							tempEmployerMatch = (companyDeduction.getEmployerFirstMatchValue()
									.divide(hundred)
									.multiply(companyDeduction.getEmployeeFirstValue())
									.divide(hundred).multiply(grossPayCurrentPayroll))
									.add(companyDeduction
											.getEmployerNextMatchValue()
											.divide(hundred)
											.multiply(
													employeePercent.subtract(companyDeduction
															.getEmployeeFirstValue()))
											.divide(hundred).multiply(grossPayCurrentPayroll));
						}

					}

				}

				BigDecimal totalCompanyDeductionTillDate = companyPayrollDeductionDao
						.getCompanyPayrollDeductionSum(companyDeduction.getId(), employeePayroll
								.getEmployee().getId());
				if (totalCompanyDeductionTillDate == null) {
					if (tempEmployerMatch.compareTo(companyDeduction.getEmployerMatchLimit()) == 1) {
						tempEmployerMatch = companyDeduction.getEmployerMatchLimit();
					}
					companyPayrollDeduction.setEmployeePayroll(employeePayroll);
					companyPayrollDeduction.setCompanyDeduction(companyDeduction);
					companyPayrollDeduction.setValue(tempEmployerMatch);
					companyPayrollDeductionDao.saveOrUpdate(companyPayrollDeduction);
					System.out.println("Record Inserted 1");
				}

				else if (totalCompanyDeductionTillDate != null
						&& totalCompanyDeductionTillDate.compareTo(companyDeduction
								.getEmployerMatchLimit()) == -1) {
					BigDecimal temp = totalCompanyDeductionTillDate.add(tempEmployerMatch);
					if (temp.compareTo(companyDeduction.getEmployerMatchLimit()) == -1) {
						// Do Nothing
					} else if (temp.compareTo(companyDeduction.getEmployerMatchLimit()) == 1) {
						tempEmployerMatch = companyDeduction.getEmployerMatchLimit().subtract(
								totalCompanyDeductionTillDate);

					}

					if (companyPayrollDeductionDao.getCompanyPayrollDeduction(
							companyDeduction.getId(), employeePayroll.getId()) != null) {
						companyPayrollDeduction = companyPayrollDeductionDao
								.getCompanyPayrollDeduction(companyDeduction.getId(),
										employeePayroll.getId());
						companyPayrollDeduction.setValue(tempEmployerMatch);
						companyPayrollDeductionDao.update(companyPayrollDeduction);
						System.out.println("Record Inserted 2");
					} else if (companyPayrollDeductionDao.getCompanyPayrollDeduction(
							companyDeduction.getId(), employeePayroll.getId()) == null) {
						companyPayrollDeduction.setEmployeePayroll(employeePayroll);
						companyPayrollDeduction.setCompanyDeduction(companyDeduction);
						companyPayrollDeduction.setValue(tempEmployerMatch);
						companyPayrollDeductionDao.save(companyPayrollDeduction);
					}
				}

			}

		}
		return tempEmployerMatch;

	}

	// TODO: #0002 implementing the logic for Employee Taxes
	// Saving Employee Taxes
	public void saveEmployeeTaxes() {

	}

	// TODO: #0001 To separate the logic for saving various type of Employer Tax
	// values.
	// Saving Employer Taxes
	public void saveEmployerTaxes() {

	}

	// Calculating Payroll
	@Override
	@Transactional
	public PayrollPreviewForm calculatePayroll(Long payrollId, Long payrollFrequencyId)
			throws InstanceNotFoundException {

		System.out.println("Inside Service");

		Long companyId = payrollDao.find(payrollId).getCompany().getId();
		PayrollPreviewForm payrollPreviewForm = new PayrollPreviewForm();
		EmployeePayrollModel employeePayrollModel = null;
		List<EmployeePayrollModel> employeePayrollModels = new ArrayList<>();

		BigDecimal remainingPayrollsCount = getRemainingPayrollsCount(payrollFrequencyId);

		// Fetching the Federal State Tax Entity
		List<FederalStateTaxRate> federalStateTaxRateList = federalStateTaxRateDao
				.getFederalStateTaxRates();

		// Fetching the county Tax List
		List<CountyTaxRate> countyTaxRateList = countyTaxRateDao.getCountyTaxRate();

		// Fetching Futa Suta Tax List for the company.The parameter passed can
		// be changed if we get companyId with request or the session
		List<FutaSutaTaxRate> FutaSutaTaxRateList = futaSutaTaxRateDao
				.getFutaSutaTaxRate(companyId);

		// List of Workers Compensation for the company
		List<WorkersCompensationTaxRate> workersCompensationTaxList = workersCompensationTaxRateDao
				.getWorkerCompensationTaxRates(companyId);

		// Creating Map for Workers Compensation Rate as per State
		Map<Long, BigDecimal> workersCompensationRateMap = new HashMap<>();
		for (WorkersCompensationTaxRate workersCompensationTaxRate : workersCompensationTaxList) {
			workersCompensationRateMap.put(workersCompensationTaxRate
					.getCompanyWorkerCompensation().getUsState().getId(),
					workersCompensationTaxRate.getRate());
		}

		// Creating Map for Company Workers Compensation ID as per State
		Map<Long, CompanyWorkerCompensation> companyWorkersCompensationMap = new HashMap<>();
		for (WorkersCompensationTaxRate workersCompensationTaxRate : workersCompensationTaxList) {
			companyWorkersCompensationMap.put(workersCompensationTaxRate
					.getCompanyWorkerCompensation().getUsState().getId(),
					workersCompensationTaxRate.getCompanyWorkerCompensation());
		}

		// Fetch deduction caps for the deductions of current company
		List<DeductionCap> deductionCapList = deductionCapDao.getDeductionCapRecords(companyId);

		// Creating Map for Deduction caps as per company and state
		Map<String, BigDecimal> deductionCapMap = new HashMap<>();
		for (DeductionCap deductionCap : deductionCapList) {
			// Author: Pankaj, applied the null check and duplicate tempkey
			// check
			if (!(deductionCap.getUsState() == null)) {
				String tempKey = (deductionCap.getCompanyDeduction().getId()).toString()
						+ (deductionCap.getUsState().getId()).toString();

				deductionCapMap.put(tempKey, deductionCap.getCapAmount());
			}
		}

		logger.debug("Deduction Cap MAp::::::::::::::" + deductionCapMap);

		// Fetching List of Standard Deductions
		List<StandardDeductionRate> standardDeductionRateList = standardDeductionRateDao
				.getEntities();

		FederalStateTaxRate socialSecurityTaxRecord = null;
		FederalStateTaxRate medicareTaxRecord = null;
		FederalStateTaxRate addlMedicareTaxRecord = null;
		FederalStateTaxRate futaTaxRecord = null;
		FutaSutaTaxRate companyFutaRecord = null;

		for (FederalStateTaxRate federalStateTaxRate : federalStateTaxRateList) {

			// Fetch Social Security Record
			if (federalStateTaxRate.getTaxType().getTaxName().equals(TaxName.SOCIAL_SECURITY)) {
				socialSecurityTaxRecord = federalStateTaxRate;
			}

			// FetchMedicare Tax Record
			if (federalStateTaxRate.getTaxType().getTaxName().equals(TaxName.MEDICARE)) {
				medicareTaxRecord = federalStateTaxRate;
			}

			// Fetch Additional Medicare TAx Record
			if (federalStateTaxRate.getTaxType().getTaxName().equals(TaxName.ADDL_MEDICARE)) {
				addlMedicareTaxRecord = federalStateTaxRate;
			}

			// Fetch FUTA Tax Record
			if (federalStateTaxRate.getTaxType().getTaxName()
					.equals(TaxName.FEDERAL_UNEMPLOYEMENT_TAX)) {
				futaTaxRecord = federalStateTaxRate;
			}
		}

		for (FutaSutaTaxRate futaSutaTaxRate : FutaSutaTaxRateList) {
			// fetch Company's Futa Record
			if (futaSutaTaxRate.getTaxType().getTaxName().equals(TaxName.FEDERAL_UNEMPLOYEMENT_TAX)) {
				companyFutaRecord = futaSutaTaxRate;
			}
		}

		logger.debug("Company FUTA Record::::::::::::::" + companyFutaRecord);

		List<Long> employeesList = employeeDao.getPayrollEmployees(payrollId);

		System.out.println("Employee Fetch completed");

		List<EmployeePayroll> empPayrollList = employeePayrollDao.getEmployeePayroll(payrollId);

		// variables for preview
		BigDecimal directDepositTotal = new BigDecimal(0L);
		BigDecimal employeeTaxTotal = new BigDecimal(0L);
		BigDecimal employerTaxTotal = new BigDecimal(0L);
		BigDecimal vjsProcessingFees = new BigDecimal(0L);
		BigDecimal totalAmountTransferElectronically = new BigDecimal(0L);
		BigDecimal checkAmountTotal = new BigDecimal(0L);
		BigDecimal employeeContractorDeductionTotal = new BigDecimal(0L);
		BigDecimal employerDeductionTotal = new BigDecimal(0L);
		BigDecimal grandTotal = new BigDecimal(0L);
		// end

		// Start of iteration for each Employee
		// for (Long employeeId : employeesList) {
		for (int i = 0; i < 1; i++) {
			// for preview
			employeePayrollModel = new EmployeePayrollModel();

			Long employeeId = 2L; // Dummy Value for testing
			BigDecimal grossPayCurrentPayroll = new BigDecimal(0L);
			BigDecimal grossPayTotalYear = new BigDecimal(0L);
			BigDecimal fedAllowanceTotal = new BigDecimal(0);
			BigDecimal stateAllowanceTotal = new BigDecimal(0);
			BigDecimal employeePretaxDeductionsTotal = new BigDecimal(0);
			BigDecimal employeeNontaxDeductionsTotal = new BigDecimal(0);
			BigDecimal sumSalaries = new BigDecimal(0);
			BigDecimal sumEarnings = new BigDecimal(0L);

			// Gross Pay till date
			BigDecimal empGrossPayY2D = employeePayrollDao.getGrossSalaryPaidY2D(employeeId);

			logger.debug("Employee Gross Pay Y2D:::::::::::::" + empGrossPayY2D);

			List<EmployeeW4Detail> empW4List = employeeW4DetailsDao
					.getEmployeeW4Details(employeeId);

			Long fedFilingStatus = null;
			Long stateFilingStatus = null;
			String stateTaxName = null;
			String stateUnemploymentTaxName = null;
			BigDecimal addlFederalWithholding = new BigDecimal(0);
			BigDecimal addlStateWithholding = new BigDecimal(0);

			// Fetching Federal,State Filing Status and State Income Tax
			// Authority and State Unemployement Tax Authority

			List<EmployeeTax> employeeTaxs = employeeTaxDao.getEmployeeTaxs(employeeId);
			for (EmployeeTax employeeTax : employeeTaxs) {
				if (employeeTax.getTaxType().getTaxAuthority().getUsState() != null
						&& employeeTax.getTaxType().getTaxAuthority().getUsState().getId()
								.equals(addressDao.getEmployeeWorkState(employeeId))
						&& employeeTax.getTaxType().getPaidByCompany()) {
					stateUnemploymentTaxName = employeeTax.getTaxType().getTaxName();
				}
			}
			for (EmployeeW4Detail employeeW4Detail : empW4List) {
				if (employeeW4Detail.getEmployeeTax().getTaxType().getTaxName()
						.equals(TaxName.FEDERAL_INCOME_TAX)) {
					fedFilingStatus = employeeW4Detail.getFilingStatus().getId();
				}
				if (employeeW4Detail.getEmployeeTax().getTaxType().getTaxAuthority().getUsState() != null
						&& employeeW4Detail.getEmployeeTax().getTaxType().getTaxAuthority()
								.getUsState().getId()
								.equals(addressDao.getEmployeeResidentialState(employeeId))) {
					stateFilingStatus = employeeW4Detail.getFilingStatus().getId();
					stateTaxName = employeeW4Detail.getEmployeeTax().getTaxType().getTaxName();

				}

			}

			logger.debug("state Unemployment Tax Name:::::::::::::" + stateUnemploymentTaxName);
			logger.debug("State Filing Status::::::::::::::" + stateFilingStatus);
			logger.debug("Federal Filing Status:::::::::" + fedFilingStatus);
			logger.debug("State TAx Name::::::::::::" + stateTaxName);

			// Federal tax slab list
			List<FederalStateTaxRate> fedTaxRateList = new ArrayList<>();
			fedTaxRateList = getFederalTaxSlabsList(federalStateTaxRateList, fedFilingStatus);

			// Fetch Company's Suta Rate for Employees working State
			FutaSutaTaxRate companySutaRecord = null;
			for (FutaSutaTaxRate futaSutaTaxRate : FutaSutaTaxRateList) {
				if (futaSutaTaxRate.getTaxType().getTaxAuthority().getUsState() != null
						&& futaSutaTaxRate.getTaxType().getTaxAuthority().getUsState().getId()
								.equals(addressDao.getEmployeeWorkState(employeeId))) {
					companySutaRecord = futaSutaTaxRate;
				}
			}

			logger.debug("Company Suta Record::::::::::::::" + companySutaRecord);

			// Fetch Suta Ceiling for Employees working State
			FederalStateTaxRate sutaTaxRecord = null;
			for (FederalStateTaxRate federalStateTaxRate : federalStateTaxRateList) {

				if (federalStateTaxRate.getTaxType().getTaxAuthority().getUsState() != null) {
					if (federalStateTaxRate.getTaxType().getTaxAuthority().getUsState().getId()
							.equals(addressDao.getEmployeeWorkState(employeeId))
							&& federalStateTaxRate.getTaxType().getPaidByCompany()) {
						sutaTaxRecord = federalStateTaxRate;
					}
				}
			}

			// Fetch Last Record of federal Taxes i.e. with MAximum salary as
			// Infinite
			FederalStateTaxRate fedTaxMax = fedTaxRateList.get(fedTaxRateList.size() - 1);

			logger.debug("Fed TAX MAx:::::::::::::" + fedTaxMax);

			// List of Employee Payroll Records for the Employee
			List<EmployeePayroll> tempPayrollList = new ArrayList<EmployeePayroll>();

			for (EmployeePayroll employeePayroll : empPayrollList) {

				if (employeePayroll.getEmployee().getId().equals(employeeId)
						&& (employeePayroll.getRowType().equals(RowType.ADDITIONAL_ROW) || employeePayroll
								.getRowType().equals(RowType.MAIN_ROW))) {
					tempPayrollList.add(employeePayroll);

				}
			}
			// Saving Employee Name for Preview
			String empName = new StringBuilder(tempPayrollList.get(0).getEmployee().getPerson()
					.getFirstName()).append(" ")
					.append(tempPayrollList.get(0).getEmployee().getPerson().getLastName())
					.toString();
			employeePayrollModel.setEmployeeName(empName);
			employeePayrollModel.setEmployeeType(tempPayrollList.get(0).getEmployee()
					.getEmployeeType());
			// ENd

			// Calculating Total of Earnings and Salary
			for (EmployeePayroll employeePayroll : tempPayrollList) {

				List<EmployeePayrollEarning> empPayrollEarnings = new ArrayList<>(
						employeePayroll.getEmployeePayrollEarnings());
				logger.debug("EMployee PAyroll Earnings List:::::::::::::::" + empPayrollEarnings);

				if (empPayrollEarnings.size() > 0) {

					for (EmployeePayrollEarning employeePayrollEarning : empPayrollEarnings) {
						// Updated calculation for calculated earnings:
						// Author: Pankaj
						// TODO: To implement the updated override calculated
						// values.
						if (employeePayrollEarning.getEarningValueType().equals(
								EarningValueType.AMOUNT)) {
							employeePayrollEarning.setCalculatedEarning(employeePayrollEarning
									.getValue());
							sumEarnings = sumEarnings.add(employeePayrollEarning
									.getCalculatedEarning());
						} else if (employeePayrollEarning.getEarningValueType().equals(
								EarningValueType.HOURS)) {
							employeePayrollEarning.setCalculatedEarning(employeePayroll
									.getHourlyRate().multiply(employeePayrollEarning.getValue()));
							sumEarnings = sumEarnings.add(employeePayrollEarning
									.getCalculatedEarning());
						}

					}
				}

				try {

					if (employeePaySetupDao.getEmployeePaySetup(employeePayroll.getEmployee())
							.getPayType().equals(PayType.SALARY)) {
						sumSalaries = sumSalaries.add(employeePayroll.getSalary());
					}

					else if (employeePaySetupDao.getEmployeePaySetup(employeePayroll.getEmployee())
							.getPayType().equals(PayType.HOURLY)) {

						sumSalaries = sumSalaries.add(employeePayroll.getHourlyRate().multiply(
								employeePayroll.getRegularHours()));
					}

				} catch (InstanceNotFoundException e) {

					e.printStackTrace();
				}
			}

			// Gross Pay for this month

			grossPayCurrentPayroll = sumEarnings.add(sumSalaries);

			// Saving Gross Amount for preview
			employeePayrollModel.setGrossAmount(grossPayCurrentPayroll.doubleValue());
			// end
			logger.debug("Gross Pay Current Payroll::::::::::::::" + grossPayCurrentPayroll);

			// TODO Kamran : Save this grosspaycurrentPayroll value in Employee
			// Payroll record

			// Gross Pay for total year
			grossPayTotalYear = (grossPayCurrentPayroll.multiply(remainingPayrollsCount))
					.add(empGrossPayY2D);

			logger.debug("Gross Pay Total Year:::::::::::::::" + grossPayTotalYear);

			List<EmployeeAllowanceRecord> earList = getEmployeeAllowanceRecord(employeeId);
			System.out.println(earList);

			for (EmployeeAllowanceRecord employeeAllowanceRecord : earList) {
				if (employeeAllowanceRecord.getEmployeeW4Detail().getEmployeeTax().getTaxType()
						.getTaxName().equals(TaxName.FEDERAL_INCOME_TAX)) {

					AllowanceRate ar = allowanceRateDao.getAllowanceRate(employeeAllowanceRecord
							.getAllowanceType().getId(), null);
					BigDecimal fedallowance = new BigDecimal(
							employeeAllowanceRecord.getNoOfAllowance());
					fedAllowanceTotal = ar.getAmount().multiply(fedallowance);
				}

				else {

					AllowanceRate ar = allowanceRateDao.getAllowanceRate(employeeAllowanceRecord
							.getAllowanceType().getId(), addressDao
							.getEmployeeResidentialState(employeeId));
					BigDecimal stateallowance = new BigDecimal(
							employeeAllowanceRecord.getNoOfAllowance());
					stateAllowanceTotal = stateAllowanceTotal.add(ar.getAmount().multiply(
							stateallowance));
				}
			}

			logger.debug("State Allowance Total::::::::::" + stateAllowanceTotal);
			logger.debug("Federal Allowance Total:::::::::::" + fedAllowanceTotal);

			// To find the row having Main row type
			EmployeePayroll employeePayrollMain = null;
			for (EmployeePayroll employeePayroll : tempPayrollList) {
				if (employeePayroll.getRowType() == RowType.MAIN_ROW) {
					employeePayrollMain = employeePayroll;
				}
			}

			List<EmployeePayrollTax> employeePayrollTaxes = payrollTaxDao
					.getEmployeeTaxes(employeePayrollMain.getId());

			List<CompanyPayrollTax> companyPayrollTaxes = companyPayrollTaxDao
					.getCompanyPayrollTaxes(employeePayrollMain.getId());

			// Map for getting total of taxes paid
			TaxOverrideFrom fitTaxOverrideFrom = TaxOverrideFrom.NO_OVERRIDE;
			TaxOverrideType fitTaxOverrideType = null;
			String fitTaxName = null;
			BigDecimal fitTaxValue = new BigDecimal("0.00");

			TaxOverrideFrom sitTaxOverrideFrom = TaxOverrideFrom.NO_OVERRIDE;
			TaxOverrideType sitTaxOverrideType = null;
			String sitTaxName = null;
			BigDecimal sitTaxValue = new BigDecimal("0.00");

			Map<String, BigDecimal> employeeTaxSumMap = new HashMap<>();
			Map<String, BigDecimal> CompanyTaxSumMap = new HashMap<>();

			// Map for Company Taxes
			for (CompanyPayrollTax companyPayrollTax : companyPayrollTaxes) {
				CompanyTaxSumMap.put(companyPayrollTax.getCompanyTax().getTaxType().getTaxName(),
						payrollTaxDao
								.getCalculatedTaxY2D(companyPayrollTax.getCompanyTax().getId()));
			}

			for (EmployeePayrollTax employeePayrollTax : employeePayrollTaxes) {

				// With Overrides, fitTaxOverrideType will not be null
				if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(TaxName.FEDERAL_INCOME_TAX)
						&& !(employeePayrollTax.getTaxOverrideFrom()
								.equals(TaxOverrideFrom.NO_OVERRIDE))) {
					fitTaxOverrideFrom = employeePayrollTax.getTaxOverrideFrom();
					fitTaxOverrideType = employeePayrollTax.getTaxOverrideType();
					fitTaxName = TaxName.FEDERAL_INCOME_TAX;
					fitTaxValue = employeePayrollTax.getTaxOverrideValue();

				}

				// No Overrides, fitTaxOverrideType will be null
				else if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(TaxName.FEDERAL_INCOME_TAX)
						&& (employeePayrollTax.getTaxOverrideFrom()
								.equals(TaxOverrideFrom.NO_OVERRIDE))) {
					fitTaxOverrideFrom = employeePayrollTax.getTaxOverrideFrom();
					fitTaxOverrideType = employeePayrollTax.getTaxOverrideType();
					fitTaxName = TaxName.FEDERAL_INCOME_TAX;
					fitTaxValue = employeePayrollTax.getTaxOverrideValue();
				}

				// With Overrides, sitTaxOverrideType will not be null
				if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(stateTaxName)
						&& !(employeePayrollTax.getTaxOverrideFrom()
								.equals(TaxOverrideFrom.NO_OVERRIDE))) {
					sitTaxOverrideFrom = employeePayrollTax.getTaxOverrideFrom();
					sitTaxOverrideType = employeePayrollTax.getTaxOverrideType();
					sitTaxName = stateTaxName;
					sitTaxValue = employeePayrollTax.getTaxOverrideValue();

				}
				// No Overrides, sitTaxOverrideType will be null
				else if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(stateTaxName)
						&& (employeePayrollTax.getTaxOverrideFrom()
								.equals(TaxOverrideFrom.NO_OVERRIDE))) {
					sitTaxOverrideFrom = employeePayrollTax.getTaxOverrideFrom();
					sitTaxOverrideType = employeePayrollTax.getTaxOverrideType();
					sitTaxName = stateTaxName;
					sitTaxValue = employeePayrollTax.getTaxOverrideValue();
				}

				employeeTaxSumMap.put(
						employeePayrollTax.getEmployeeTax().getTaxType().getTaxName(),
						payrollTaxDao.getCalculatedTaxY2D(employeePayrollTax.getEmployeeTax()
								.getId()));
			}

			// Fetch List of Payroll Deductions
			List<EmployeePayrollDeduction> payrollDeductions = new ArrayList<>(
					employeePayrollMain.getEmployeePayrollDeductions());

			logger.debug("PAyroll Deduction List for Employee::::::::::::::::::::::"
					+ payrollDeductions);

			// Map for getting total of deductions accrued till date
			Map<Long, BigDecimal> employeeDeductionSumMap = new HashMap<>();
			for (EmployeePayrollDeduction employeePayrollDeduction : payrollDeductions) {

				employeeDeductionSumMap.put(employeePayrollDeduction.getId(), payrollDeductionDao
						.getCalculatedDeductionY2D(employeePayrollDeduction.getEmployeeDeduction()
								.getId()));
			}

			logger.debug("PAyroll Deduction Sum MAp::::::::::::::::::" + employeeDeductionSumMap);
			BigDecimal employeeDeduction = new BigDecimal(0);
			BigDecimal companyDeduction = new BigDecimal(0);

			// Updated the override logic for deductions
			// Author: Pankaj
			// TODO: To implement the updated override calculated values.
			for (EmployeePayrollDeduction employeePayrollDeduction : payrollDeductions) {

				// Updated Logic for Deductions: Kamran
				if (employeePayrollDeduction.getDeductionOverrideType().equals(
						DeductionValueType.AMOUNT)) {
					if (employeePayrollDeduction.getEmployeeDeduction().getTargetValue() == null) {
						if (employeePayrollDeduction.getIsDeductionOverride().equals(true)) {
							employeePayrollDeduction
									.setCalculatedDeduction(employeePayrollDeduction
											.getDeductionOverrideValue());

						} else if (employeePayrollDeduction.getIsDeductionOverride().equals(false)) {
							employeePayrollDeduction
									.setCalculatedDeduction(employeePayrollDeduction.getValue());

						}
					} else if (employeePayrollDeduction.getEmployeeDeduction().getTargetValue() != null) {
						if (employeePayrollDeduction.getIsDeductionOverride().equals(true)) {
							if (employeePayrollDeduction
									.getEmployeeDeduction()
									.getAmountpaid()
									.compareTo(
											employeePayrollDeduction.getEmployeeDeduction()
													.getTargetValue()) == -1) {
								if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getDeductionOverrideValue())
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == 1)) {
									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getEmployeeDeduction()
													.getTargetValue()
													.subtract(
															employeePayrollDeduction
																	.getEmployeeDeduction()
																	.getAmountpaid()));

								} else if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getDeductionOverrideValue())
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == -1)
										|| (employeePayrollDeduction
												.getEmployeeDeduction()
												.getAmountpaid()
												.add(employeePayrollDeduction
														.getDeductionOverrideValue())
												.compareTo(
														employeePayrollDeduction
																.getEmployeeDeduction()
																.getTargetValue()) == 0)) {

									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getDeductionOverrideValue());
								}
							}
						}
						//
						else if (employeePayrollDeduction.getIsDeductionOverride().equals(false)) {
							if (employeePayrollDeduction
									.getEmployeeDeduction()
									.getAmountpaid()
									.compareTo(
											employeePayrollDeduction.getEmployeeDeduction()
													.getTargetValue()) == -1) {
								if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getValue())
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == 1)) {
									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getEmployeeDeduction()
													.getTargetValue()
													.subtract(
															employeePayrollDeduction
																	.getEmployeeDeduction()
																	.getAmountpaid()));

								} else if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getValue())
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == -1)
										|| (employeePayrollDeduction
												.getEmployeeDeduction()
												.getAmountpaid()
												.add(employeePayrollDeduction.getValue())
												.compareTo(
														employeePayrollDeduction
																.getEmployeeDeduction()
																.getTargetValue()) == 0)) {

									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getValue());
								}
							}
						}
					}

				}
				// //Else for above
				if (employeePayrollDeduction.getDeductionOverrideType().equals(
						DeductionValueType.PERCENTAGE)) {
					if (employeePayrollDeduction.getEmployeeDeduction().getTargetValue() == null) {
						if (employeePayrollDeduction.getIsDeductionOverride().equals(true)) {
							employeePayrollDeduction
									.setCalculatedDeduction(employeePayrollDeduction
											.getDeductionOverrideValue()
											.multiply(grossPayCurrentPayroll)
											.divide(new BigDecimal(100)));

						} else if (employeePayrollDeduction.getIsDeductionOverride().equals(false)) {
							employeePayrollDeduction
									.setCalculatedDeduction(employeePayrollDeduction.getValue()
											.multiply(grossPayCurrentPayroll)
											.divide(new BigDecimal(100)));

						}
					} else if (employeePayrollDeduction.getEmployeeDeduction().getTargetValue() != null) {
						if (employeePayrollDeduction.getIsDeductionOverride().equals(true)) {
							if (employeePayrollDeduction
									.getEmployeeDeduction()
									.getAmountpaid()
									.compareTo(
											employeePayrollDeduction.getEmployeeDeduction()
													.getTargetValue()) == -1) {
								if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getDeductionOverrideValue()
												.multiply(grossPayCurrentPayroll)
												.divide(new BigDecimal(100)))
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == 1)) {
									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getEmployeeDeduction()
													.getTargetValue()
													.subtract(
															employeePayrollDeduction
																	.getEmployeeDeduction()
																	.getAmountpaid()));

								} else if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getDeductionOverrideValue()
												.multiply(grossPayCurrentPayroll)
												.divide(new BigDecimal(100)))
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == -1)
										|| (employeePayrollDeduction
												.getEmployeeDeduction()
												.getAmountpaid()
												.add(employeePayrollDeduction
														.getDeductionOverrideValue()
														.multiply(grossPayCurrentPayroll)
														.divide(new BigDecimal(100)))
												.compareTo(
														employeePayrollDeduction
																.getEmployeeDeduction()
																.getTargetValue()) == 0)) {

									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getDeductionOverrideValue()
													.multiply(grossPayCurrentPayroll)
													.divide(new BigDecimal(100)));
								}
							}
						}
						//
						else if (employeePayrollDeduction.getIsDeductionOverride().equals(false)) {
							if (employeePayrollDeduction
									.getEmployeeDeduction()
									.getAmountpaid()
									.compareTo(
											employeePayrollDeduction.getEmployeeDeduction()
													.getTargetValue()) == -1) {
								if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getValue()
												.multiply(grossPayCurrentPayroll)
												.divide(new BigDecimal(100)))
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == 1)) {
									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getEmployeeDeduction()
													.getTargetValue()
													.subtract(
															employeePayrollDeduction
																	.getEmployeeDeduction()
																	.getAmountpaid()));

								} else if ((employeePayrollDeduction
										.getEmployeeDeduction()
										.getAmountpaid()
										.add(employeePayrollDeduction.getValue()
												.multiply(grossPayCurrentPayroll)
												.divide(new BigDecimal(100)))
										.compareTo(
												employeePayrollDeduction.getEmployeeDeduction()
														.getTargetValue()) == -1)
										|| (employeePayrollDeduction
												.getEmployeeDeduction()
												.getAmountpaid()
												.add(employeePayrollDeduction.getValue()
														.multiply(grossPayCurrentPayroll)
														.divide(new BigDecimal(100)))
												.compareTo(
														employeePayrollDeduction
																.getEmployeeDeduction()
																.getTargetValue()) == 0)) {

									employeePayrollDeduction
											.setCalculatedDeduction(employeePayrollDeduction
													.getValue().multiply(grossPayCurrentPayroll)
													.divide(new BigDecimal(100)));
								}
							}
						}
					}

				}
				// /

				// Saving the record
				payrollDeductionDao.saveOrUpdate(employeePayrollDeduction);

				// saving deduction for preview
				employeeDeduction = employeeDeduction.add(employeePayrollDeduction
						.getDeductionOverrideValue());
				// end

				// Calculating company match for the deduction
				BigDecimal tempComDed = saveCompanyDeductionMatch(employeePayrollDeduction
						.getEmployeeDeduction().getCompanyDeduction(),
						employeePayrollDeduction.getEmployeePayroll(),
						employeePayrollDeduction.getCalculatedDeduction(), grossPayCurrentPayroll);
				companyDeduction = companyDeduction.add(tempComDed);

				// Logic for calculating total of Pre-tax and Post Tax
				// deductions
				if (employeePayrollDeduction.getEmployeeDeduction().getTargetValue() == null) {
					if (employeeDeductionSumMap.get(employeePayrollDeduction.getId()).compareTo(
							deductionCapMap
									.get(employeePayrollDeduction.getEmployeeDeduction()
											.getCompanyDeduction().getId().toString()
											+ addressDao.getEmployeeResidentialState(employeeId)
													.toString())) == -1) {
						if (deductionCapMap.get(
								employeePayrollDeduction.getEmployeeDeduction()
										.getCompanyDeduction().getId().toString()
										+ addressDao.getEmployeeResidentialState(employeeId)
												.toString()).compareTo(
								(employeePayrollDeduction.getCalculatedDeduction()
										.multiply(remainingPayrollsCount))
										.add(employeeDeductionSumMap.get(employeePayrollDeduction
												.getId()))) == 1) {

							if (employeePayrollDeduction.getEmployeeDeduction().getDeductionType()
									.equals(DeductionType.PRE_TAX)) {
								logger.debug("Inside Pre-TAx::::::::::::::::::::::::::");

								employeePretaxDeductionsTotal = employeePretaxDeductionsTotal
										.add(employeeDeductionSumMap.get(
												employeePayrollDeduction.getId()).add(
												(employeePayrollDeduction.getCalculatedDeduction()
														.multiply(remainingPayrollsCount))));
							}

							else if (employeePayrollDeduction.getEmployeeDeduction()
									.getDeductionType().equals(DeductionType.NON_TAX)) {

								employeeNontaxDeductionsTotal = employeeNontaxDeductionsTotal
										.add(employeeDeductionSumMap.get(
												employeePayrollDeduction.getId()).add(
												(employeePayrollDeduction.getCalculatedDeduction()
														.multiply(remainingPayrollsCount))));

							}
						} else {
							if (employeePayrollDeduction.getEmployeeDeduction().getDeductionType()
									.equals(DeductionType.PRE_TAX)) {

								employeePretaxDeductionsTotal = employeePretaxDeductionsTotal
										.add(deductionCapMap.get(employeePayrollDeduction
												.getEmployeeDeduction().getCompanyDeduction()
												.getId().toString()
												+ addressDao
														.getEmployeeResidentialState(employeeId)
														.toString()));
							}

							else if (employeePayrollDeduction.getEmployeeDeduction()
									.getDeductionType().equals(DeductionType.NON_TAX)) {

								employeeNontaxDeductionsTotal = employeeNontaxDeductionsTotal
										.add(deductionCapMap.get(employeePayrollDeduction
												.getEmployeeDeduction().getCompanyDeduction()
												.getId().toString()
												+ addressDao
														.getEmployeeResidentialState(employeeId)
														.toString()));
							}

						}

					}
				} else if (employeePayrollDeduction.getEmployeeDeduction().getTargetValue() != null) {

					if (employeePayrollDeduction
							.getEmployeeDeduction()
							.getAmountpaid()
							.compareTo(
									employeePayrollDeduction.getEmployeeDeduction()
											.getTargetValue()) == -1) {
						if ((employeePayrollDeduction.getEmployeeDeduction().getAmountpaid()
								.add(employeePayrollDeduction.getCalculatedDeduction()))
								.compareTo(employeePayrollDeduction.getEmployeeDeduction()
										.getTargetValue()) == 1) {
							if (employeePayrollDeduction.getEmployeeDeduction().getDeductionType()
									.equals(DeductionType.PRE_TAX)) {

								employeePretaxDeductionsTotal = employeePretaxDeductionsTotal
										.add(employeePayrollDeduction.getEmployeeDeduction()
												.getTargetValue());
							}

							else if (employeePayrollDeduction.getEmployeeDeduction()
									.getDeductionType().equals(DeductionType.NON_TAX)) {

								employeeNontaxDeductionsTotal = employeeNontaxDeductionsTotal
										.add(employeePayrollDeduction.getEmployeeDeduction()
												.getTargetValue());

							}
						}

						else if (((employeePayrollDeduction.getEmployeeDeduction().getAmountpaid()
								.add(employeePayrollDeduction.getCalculatedDeduction()))
								.compareTo(employeePayrollDeduction.getEmployeeDeduction()
										.getTargetValue()) == -1)
								|| ((employeePayrollDeduction.getEmployeeDeduction()
										.getAmountpaid().add(employeePayrollDeduction
										.getCalculatedDeduction()))
										.compareTo(employeePayrollDeduction.getEmployeeDeduction()
												.getTargetValue()) == 0)) {
							if (employeePayrollDeduction.getEmployeeDeduction().getDeductionType()
									.equals(DeductionType.PRE_TAX)) {

								employeePretaxDeductionsTotal = employeePretaxDeductionsTotal
										.add(employeePayrollDeduction
												.getEmployeeDeduction()
												.getAmountpaid()
												.add(employeePayrollDeduction
														.getCalculatedDeduction()));
							}

							else if (employeePayrollDeduction.getEmployeeDeduction()
									.getDeductionType().equals(DeductionType.NON_TAX)) {

								employeeNontaxDeductionsTotal = employeeNontaxDeductionsTotal
										.add(employeePayrollDeduction
												.getEmployeeDeduction()
												.getAmountpaid()
												.add(employeePayrollDeduction
														.getCalculatedDeduction()));

							}
						}
					}

				}

			}
			// saving deduction for preview
			employeePayrollModel.setEmployeeDeduction(employeeDeduction.doubleValue());
			employeePayrollModel.setEmployerDeduction(companyDeduction.doubleValue());
			// end

			logger.debug("Pre Tax Deduction Total::::::::::::::::" + employeePretaxDeductionsTotal);
			logger.debug("Non TAx Deduction Total:::::::::::::::::::"
					+ employeeNontaxDeductionsTotal);

			BigDecimal federalIncomeTax = new BigDecimal(0);

			BigDecimal stateIncomeTax = new BigDecimal(0);
			BigDecimal yearlyEmployeeSocialSecurityTax = new BigDecimal(0L);
			BigDecimal yearlyCompanySocialSecurityTax = new BigDecimal(0L);
			BigDecimal employeeSocialSecurityTax = new BigDecimal(0);
			BigDecimal companySocialSecurityTax = new BigDecimal(0);
			BigDecimal yearlyEmployeeMedicareTax = new BigDecimal(0L);
			BigDecimal yearlyCompanyMedicareTax = new BigDecimal(0L);
			BigDecimal employeeMedicareTax = new BigDecimal(0);
			BigDecimal companyMedicareTax = new BigDecimal(0);
			BigDecimal yearlyCountyTax = new BigDecimal(0L);
			BigDecimal countyTax = new BigDecimal(0);
			BigDecimal fitTaxableIncome = new BigDecimal(0L);
			BigDecimal sstTaxableIncome = new BigDecimal(0L);
			BigDecimal mediTaxableIncome = new BigDecimal(0L);
			BigDecimal sitTaxableIncome = new BigDecimal(0L);
			BigDecimal totalFederalTax = new BigDecimal(0);
			BigDecimal stateStandardDeduction = new BigDecimal(0);
			BigDecimal federalUnemployementTax = new BigDecimal(0);
			BigDecimal stateUnemployementTax = new BigDecimal(0);
			BigDecimal workersCompensationAmount = new BigDecimal(0);

			// Updated Code for FIT Calculation begins
			// Calculation of Federal taxable income.
			fitTaxableIncome = grossPayTotalYear.subtract(fedAllowanceTotal)
					.subtract(employeePretaxDeductionsTotal)
					.subtract(employeeNontaxDeductionsTotal);

			logger.debug("FIT TAxable Income:::::::::::::" + fitTaxableIncome);

			// For no overrides & Additional Overrides
			// TODO: Need to check for the below condition, if a Null check is
			// required for TaxOverrideType or not.
			if (fitTaxOverrideFrom.equals(TaxOverrideFrom.NO_OVERRIDE)
					|| (fitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_DOLLAR_AMOUNT))
					|| (fitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_PERCENTAGE_AMOUNT))) {

				// NO Override
				federalIncomeTax = calculateFITWithoutOverrides(fitTaxableIncome, fedTaxMax,
						fedTaxRateList, employeeTaxSumMap, remainingPayrollsCount);

				// Overrides with ADDITIONAL_DOLLAR_AMOUNT
				if (fitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_DOLLAR_AMOUNT)) {
					federalIncomeTax = fitTaxValue.add(federalIncomeTax);
				}

				// Overrides with ADDITIONAL_PERCENTAGE_AMOUNT
				else if (fitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_PERCENTAGE_AMOUNT)) {
					federalIncomeTax = federalIncomeTax.add(fitTaxValue.multiply(fitTaxableIncome)
							.divide(new BigDecimal("100.00")));
				}

			}

			// Override with FLAT_DOLLAR_AMOUNT
			if (fitTaxOverrideType != null
					&& fitTaxOverrideType.equals(TaxOverrideType.FLAT_DOLLAR_AMOUNT)
					&& !fitTaxOverrideFrom.equals(TaxOverrideFrom.NO_OVERRIDE)
					&& fitTaxName.equals(TaxName.FEDERAL_INCOME_TAX)) {
				federalIncomeTax = fitTaxValue;
			}

			// Override with FLAT_PERCENTAGE_AMOUNT
			else if (fitTaxOverrideType != null
					&& fitTaxOverrideType.equals(TaxOverrideType.FLAT_PERCENTAGE_AMOUNT)
					&& !fitTaxOverrideFrom.equals(TaxOverrideFrom.NO_OVERRIDE)
					&& fitTaxName.equals(TaxName.FEDERAL_INCOME_TAX)) {
				federalIncomeTax = fitTaxValue.multiply(fitTaxableIncome).divide(
						new BigDecimal("100.00"));
			}

			federalIncomeTax = federalIncomeTax.add(addlFederalWithholding);

			logger.debug("FIT::::::::::::::" + federalIncomeTax);

			// TODO: To implement the updated override calculated values.
			// Calculation of Federal Tax ends

			sstTaxableIncome = grossPayTotalYear.subtract(employeeNontaxDeductionsTotal);
			logger.debug("Social Security Taxable Income:::::::::::::::" + sstTaxableIncome);

			if (sstTaxableIncome.compareTo(socialSecurityTaxRecord.getCeiling()) == 1) {
				yearlyEmployeeSocialSecurityTax = socialSecurityTaxRecord.getCeiling().multiply(
						socialSecurityTaxRecord.getEmployeeRate().divide(new BigDecimal(100)));
				yearlyCompanySocialSecurityTax = socialSecurityTaxRecord.getCeiling().multiply(
						socialSecurityTaxRecord.getCompanyRate().divide(new BigDecimal(100)));
			} else {
				yearlyEmployeeSocialSecurityTax = sstTaxableIncome.multiply(socialSecurityTaxRecord
						.getEmployeeRate().divide(new BigDecimal(100)));
				yearlyCompanySocialSecurityTax = sstTaxableIncome.multiply(socialSecurityTaxRecord
						.getCompanyRate().divide(new BigDecimal(100)));
			}

			if (employeeTaxSumMap.get(TaxName.SOCIAL_SECURITY) != null) {
				employeeSocialSecurityTax = (yearlyEmployeeSocialSecurityTax
						.subtract(employeeTaxSumMap.get(TaxName.SOCIAL_SECURITY))).divide(
						remainingPayrollsCount, 4, RoundingMode.CEILING);
			} else {
				employeeSocialSecurityTax = (yearlyEmployeeSocialSecurityTax).divide(
						remainingPayrollsCount, 4, RoundingMode.CEILING);
			}

			logger.debug("EMployee SOcial Security Tax::::::::::::" + employeeSocialSecurityTax);

			if (CompanyTaxSumMap.get(TaxName.SOCIAL_SECURITY) != null) {
				companySocialSecurityTax = (yearlyCompanySocialSecurityTax
						.subtract(CompanyTaxSumMap.get(TaxName.SOCIAL_SECURITY))).divide(
						remainingPayrollsCount, 4, RoundingMode.CEILING);
			} else {
				companySocialSecurityTax = (yearlyEmployeeSocialSecurityTax).divide(
						remainingPayrollsCount, 4, RoundingMode.CEILING);
			}

			logger.debug("Company TAx SUm MAp:::::::::::::" + CompanyTaxSumMap);

			logger.debug("Company SOcial Security Tax::::::::::::" + companySocialSecurityTax);

			// Calculation of Medicare Tax for Employee
			mediTaxableIncome = grossPayTotalYear.subtract(employeeNontaxDeductionsTotal);

			if (mediTaxableIncome.compareTo(addlMedicareTaxRecord.getCeiling()) == 1) {
				yearlyEmployeeMedicareTax = mediTaxableIncome.multiply(
						medicareTaxRecord.getEmployeeRate().divide(new BigDecimal(100))).add(
						mediTaxableIncome.subtract(addlMedicareTaxRecord.getCeiling())
								.multiply(
										addlMedicareTaxRecord.getEmployeeRate().divide(
												new BigDecimal(100))));
			} else {
				yearlyEmployeeMedicareTax = mediTaxableIncome.multiply(medicareTaxRecord
						.getEmployeeRate().divide(new BigDecimal(100)));
			}

			if (employeeTaxSumMap.get(TaxName.MEDICARE) != null) {
				employeeMedicareTax = (yearlyEmployeeMedicareTax.subtract(employeeTaxSumMap
						.get(TaxName.MEDICARE))).divide(remainingPayrollsCount, 4,
						RoundingMode.CEILING);
			} else {
				employeeMedicareTax = (yearlyCompanyMedicareTax).divide(remainingPayrollsCount, 4,
						RoundingMode.CEILING);
			}

			logger.debug("Employee Medicare Tax::::::::::::" + employeeMedicareTax);

			// Calculation of Medicare Tax for Employer
			yearlyCompanyMedicareTax = mediTaxableIncome.multiply(medicareTaxRecord
					.getCompanyRate().divide(new BigDecimal(100)));
			logger.debug("Yearly Company MEdicare Tax::::::::::::::" + yearlyCompanyMedicareTax);

			if (CompanyTaxSumMap.get(TaxName.MEDICARE) != null) {
				companyMedicareTax = (yearlyCompanyMedicareTax.subtract(CompanyTaxSumMap
						.get(TaxName.MEDICARE))).divide(remainingPayrollsCount, 4,
						RoundingMode.CEILING);
			} else {
				companyMedicareTax = (yearlyCompanyMedicareTax).divide(remainingPayrollsCount, 4,
						RoundingMode.CEILING);
			}

			logger.debug("Company Medicare Tax::::::::::::" + companyMedicareTax);

			// Standard Deduction value for the employee as per work state
			StandardDeductionRate standardDeductionRateRecord = null;

			for (StandardDeductionRate standardDeductionRate : standardDeductionRateList) {
				System.out.println("Printing Address ::");
				if (standardDeductionRate.getUsState() != null
						&& standardDeductionRate.getFilingStatus().getId()
								.equals(stateFilingStatus)
						&& standardDeductionRate.getUsState().getId()
								.equals(addressDao.getEmployeeResidentialState(employeeId))) {
					System.out.println("Printing Address ::::::::::::   "
							+ addressDao.getEmployeeResidentialState(employeeId));
					standardDeductionRateRecord = standardDeductionRate;
				}

			}

			logger.debug("Prinitng Deduction List");
			System.out.println(standardDeductionRateRecord);

			if (standardDeductionRateRecord.getRateValueType().equals(RateValueType.AMOUNT)) {
				stateStandardDeduction = standardDeductionRateRecord.getRate();
			} else if (standardDeductionRateRecord.getRateValueType().equals(
					RateValueType.PERCENTAGE)) {
				if (standardDeductionRateRecord.getMaxValue().compareTo(
						grossPayTotalYear.multiply(standardDeductionRateRecord.getRate()).divide(
								new BigDecimal(100))) == -1) {
					stateStandardDeduction = standardDeductionRateRecord.getMaxValue();
				} else if (standardDeductionRateRecord.getMinValue().compareTo(
						grossPayTotalYear.multiply(standardDeductionRateRecord.getRate()).divide(
								new BigDecimal(100))) == 1) {
					stateStandardDeduction = standardDeductionRateRecord.getMinValue();
				} else {
					stateStandardDeduction = grossPayTotalYear.multiply(
							standardDeductionRateRecord.getRate()).divide(new BigDecimal(100));
				}

			}

			logger.debug("State Standard Deduction::::::::::" + stateStandardDeduction);
			// Updated Code for SIT Calculation begins
			// List of State Tax Slabs

			// Getting a list of slabs for states
			List<FederalStateTaxRate> stateTaxRateList = getStateTaxSlabsList(
					federalStateTaxRateList, fedFilingStatus, employeeId);

			// Fetch Last Record of state Taxes i.e. with Maximum salary as
			// Infinite
			FederalStateTaxRate stateTaxMax = stateTaxRateList.get(stateTaxRateList.size() - 1);

			// Calculation of State taxable income
			sitTaxableIncome = grossPayTotalYear.subtract(stateAllowanceTotal)
					.subtract(employeePretaxDeductionsTotal)
					.subtract(employeeNontaxDeductionsTotal).subtract(stateStandardDeduction);

			logger.debug("SIT Taxable Income:::::::::::::" + sitTaxableIncome);

			// stateIncomeTax = calculateSITWithoutOverrides(sitTaxableIncome,
			// stateTaxMax,
			// stateTaxRateList, employeeTaxSumMap, remainingPayrollsCount);

			// For no overrides & Additional Overrides
			// TODO: Need to check for the below condition, if a Null check is
			// required for TaxOverrideType or not.
			if (sitTaxOverrideFrom.equals(TaxOverrideFrom.NO_OVERRIDE)
					|| (sitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_DOLLAR_AMOUNT))
					|| (sitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_PERCENTAGE_AMOUNT))) {

				// NO Override
				stateIncomeTax = calculateSITWithoutOverrides(sitTaxableIncome, stateTaxMax,
						stateTaxRateList, employeeTaxSumMap, remainingPayrollsCount, stateTaxName);

				logger.debug("stateIncomeTax >>>>> " + stateIncomeTax);

				// Overrides with ADDITIONAL_DOLLAR_AMOUNT
				if (sitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_DOLLAR_AMOUNT)) {
					stateIncomeTax = sitTaxValue.add(stateIncomeTax);
				}

				// Overrides with ADDITIONAL_PERCENTAGE_AMOUNT
				else if (sitTaxOverrideType.equals(TaxOverrideType.ADDITIONAL_PERCENTAGE_AMOUNT)) {
					stateIncomeTax = stateIncomeTax.add(sitTaxValue.multiply(sitTaxableIncome)
							.divide(new BigDecimal("100.00")));
				}

			}

			// Override with FLAT_DOLLAR_AMOUNT
			if (sitTaxOverrideType != null
					&& sitTaxOverrideType.equals(TaxOverrideType.FLAT_DOLLAR_AMOUNT)
					&& !sitTaxOverrideFrom.equals(TaxOverrideFrom.NO_OVERRIDE)
					&& sitTaxName.equals(stateTaxName)) {
				stateIncomeTax = sitTaxValue;
			}

			// Override with FLAT_PERCENTAGE_AMOUNT
			else if (sitTaxOverrideType != null
					&& sitTaxOverrideType.equals(TaxOverrideType.FLAT_PERCENTAGE_AMOUNT)
					&& !sitTaxOverrideFrom.equals(TaxOverrideFrom.NO_OVERRIDE)
					&& sitTaxName.equals(stateTaxName)) {
				stateIncomeTax = sitTaxValue.multiply(sitTaxableIncome).divide(
						new BigDecimal("100.00"));
			}

			logger.debug("SIT ::::::::::" + stateIncomeTax);

			// Local Tax Calculation
			for (CountyTaxRate countyTaxRate : countyTaxRateList) {
				if (addressDao.getEmployeeResidentialCounty(employeeId) != null) {
					if (countyTaxRate.getUsCounty().getId()
							.equals(addressDao.getEmployeeResidentialCounty(employeeId))) {
						countyTax = (sitTaxableIncome.multiply(countyTaxRate.getRate())
								.subtract(employeeTaxSumMap.get(TaxName.COUNTY_TAX))).divide(
								remainingPayrollsCount, 4, RoundingMode.CEILING);
					}
				}

			}

			// State Income Tax Value(Includes Local Tax)
			stateIncomeTax = stateIncomeTax.add(addlStateWithholding).add(countyTax);

			logger.debug("SIT with Local Tax ::::::::::" + stateIncomeTax);

			// State Tax calculation Ends

			// TODO Kamran : Eligibility for Futa and Suta

			// FUTA Calculation

			if (empGrossPayY2D.compareTo(futaTaxRecord.getCeiling()) == -1) {
				if (futaTaxRecord.getCeiling().subtract(empGrossPayY2D)
						.compareTo(grossPayCurrentPayroll) == 1) {
					federalUnemployementTax = grossPayCurrentPayroll.multiply(
							companyFutaRecord.getRate()).divide(new BigDecimal(100));
				} else if (futaTaxRecord.getCeiling().subtract(empGrossPayY2D)
						.compareTo(grossPayCurrentPayroll) == -1) {
					federalUnemployementTax = (futaTaxRecord.getCeiling().subtract(empGrossPayY2D))
							.multiply(companyFutaRecord.getRate()).divide(new BigDecimal(100));
				}
			}

			logger.debug("Federal Unemployment Tax:::::::::::::::" + federalUnemployementTax);

			// SUTA Calculation

			if (empGrossPayY2D.compareTo(sutaTaxRecord.getCeiling()) == -1) {
				if (sutaTaxRecord.getCeiling().subtract(empGrossPayY2D)
						.compareTo(grossPayCurrentPayroll) == 1) {
					stateUnemployementTax = grossPayCurrentPayroll.multiply(
							companySutaRecord.getRate()).divide(new BigDecimal(100));
				} else if (sutaTaxRecord.getCeiling().subtract(empGrossPayY2D)
						.compareTo(grossPayCurrentPayroll) == -1) {
					stateUnemployementTax = (sutaTaxRecord.getCeiling().subtract(empGrossPayY2D))
							.multiply(companySutaRecord.getRate()).divide(new BigDecimal(100));
				}
			}

			logger.debug("State Unemployment Tax:::::::::::::::" + stateUnemployementTax);

			// Workers Compensation Calculation and Save
			workersCompensationAmount = grossPayCurrentPayroll.multiply(
					workersCompensationRateMap.get(addressDao.getEmployeeWorkState(employeeId)))
					.divide(new BigDecimal(100));

			CompanyPayrollWorkerComp companyPayrollWorkerComp = new CompanyPayrollWorkerComp();

			if (companyPayrollWorkerCompDao.getCompanyPayrollWorkerComp(
					companyWorkersCompensationMap.get(addressDao.getEmployeeWorkState(employeeId))
							.getId(), employeePayrollMain.getId()) != null) {
				companyPayrollWorkerComp = companyPayrollWorkerCompDao.getCompanyPayrollWorkerComp(
						companyWorkersCompensationMap.get(
								addressDao.getEmployeeWorkState(employeeId)).getId(),
						employeePayrollMain.getId());
				companyPayrollWorkerCompDao.update(companyPayrollWorkerComp);
			} else {
				companyPayrollWorkerComp.setCompanyWorkerCompensation(companyWorkersCompensationMap
						.get(addressDao.getEmployeeWorkState(employeeId)));
				companyPayrollWorkerComp.setEmployeePayroll(employeePayrollMain);
				companyPayrollWorkerComp.setValue(workersCompensationAmount);
				companyPayrollWorkerCompDao.save(companyPayrollWorkerComp);

			}

			// saving employee taxes for preview
			BigDecimal employeeTaxes = new BigDecimal(0);
			employeeTaxes = employeeTaxes.add(federalIncomeTax).add(stateIncomeTax)
					.add(employeeSocialSecurityTax).add(employeeMedicareTax).add(countyTax);
			employeePayrollModel.setEmployeeTax(employeeTaxes.doubleValue());
			// End

			// TODO: #0002 implementing the logic for Employee Taxes
			// Saving Employee Taxes
			for (EmployeePayrollTax employeePayrollTax : employeePayrollTaxes) {
				if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(TaxName.FEDERAL_INCOME_TAX)) {
					employeePayrollTax.setCalculatedTax(federalIncomeTax);
					payrollTaxDao.update(employeePayrollTax);
				} else if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(stateTaxName)) {
					employeePayrollTax.setCalculatedTax(stateIncomeTax);
					payrollTaxDao.update(employeePayrollTax);
				} else if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(TaxName.SOCIAL_SECURITY)) {
					employeePayrollTax.setCalculatedTax(employeeSocialSecurityTax);
					payrollTaxDao.update(employeePayrollTax);
				} else if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(TaxName.MEDICARE)) {
					employeePayrollTax.setCalculatedTax(employeeMedicareTax);
					payrollTaxDao.update(employeePayrollTax);
				} else if (employeePayrollTax.getEmployeeTax().getTaxType().getTaxName()
						.equals(TaxName.COUNTY_TAX)) {
					employeePayrollTax.setCalculatedTax(countyTax);
					payrollTaxDao.update(employeePayrollTax);
				}

			}

			logger.debug("Printing::::::::" + stateIncomeTax + "   " + federalIncomeTax);

			// saving employer taxes for preview
			BigDecimal employerTaxes = new BigDecimal(0);
			employerTaxes = employerTaxes.add(companySocialSecurityTax).add(companyMedicareTax)
					.add(federalUnemployementTax).add(stateUnemployementTax);
			employeePayrollModel.setEmployerTax(employerTaxes.doubleValue());
			// End

			// TODO: #0001
			// Saving Employer Taxes
			List<CompanyTax> companyTaxes = new ArrayList<>();
			companyTaxes = companyTaxDao.getCompanyTaxes(companyId);

			for (CompanyTax companyTax : companyTaxes) {

				if (companyTax.getTaxType().getTaxName().equals(TaxName.SOCIAL_SECURITY)) {

					if (companyPayrollTaxDao.getCompanyPayrollTaxRecord(companyTax.getId(),
							employeePayrollMain.getId()) != null) {
						CompanyPayrollTax companyPayrollTax = companyPayrollTaxDao
								.getCompanyPayrollTaxRecord(companyTax.getId(),
										employeePayrollMain.getId());
						companyPayrollTax.setTaxCalculated(companySocialSecurityTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.update(companyPayrollTax);
					} else {
						CompanyPayrollTax companyPayrollTax = new CompanyPayrollTax();
						companyPayrollTax.setTaxCalculated(companySocialSecurityTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.save(companyPayrollTax);
					}

				} else if (companyTax.getTaxType().getTaxName().equals(TaxName.MEDICARE)) {
					if (companyPayrollTaxDao.getCompanyPayrollTaxRecord(companyTax.getId(),
							employeePayrollMain.getId()) != null) {
						CompanyPayrollTax companyPayrollTax = companyPayrollTaxDao
								.getCompanyPayrollTaxRecord(companyTax.getId(),
										employeePayrollMain.getId());
						companyPayrollTax.setTaxCalculated(companyMedicareTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.update(companyPayrollTax);
					} else {
						CompanyPayrollTax companyPayrollTax = new CompanyPayrollTax();
						companyPayrollTax.setTaxCalculated(companyMedicareTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.save(companyPayrollTax);
					}

				} else if (companyTax.getTaxType().getTaxName()
						.equals(TaxName.FEDERAL_UNEMPLOYEMENT_TAX)) {
					if (companyPayrollTaxDao.getCompanyPayrollTaxRecord(companyTax.getId(),
							employeePayrollMain.getId()) != null) {
						CompanyPayrollTax companyPayrollTax = companyPayrollTaxDao
								.getCompanyPayrollTaxRecord(companyTax.getId(),
										employeePayrollMain.getId());
						companyPayrollTax.setTaxCalculated(federalUnemployementTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.update(companyPayrollTax);
					} else {
						CompanyPayrollTax companyPayrollTax = new CompanyPayrollTax();
						companyPayrollTax.setTaxCalculated(federalUnemployementTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.save(companyPayrollTax);
					}

				} else if (companyTax.getTaxType().getTaxName().equals(stateUnemploymentTaxName)) {
					if (companyPayrollTaxDao.getCompanyPayrollTaxRecord(companyTax.getId(),
							employeePayrollMain.getId()) != null) {
						CompanyPayrollTax companyPayrollTax = companyPayrollTaxDao
								.getCompanyPayrollTaxRecord(companyTax.getId(),
										employeePayrollMain.getId());
						companyPayrollTax.setTaxCalculated(stateUnemployementTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.update(companyPayrollTax);
					} else {
						CompanyPayrollTax companyPayrollTax = new CompanyPayrollTax();
						companyPayrollTax.setTaxCalculated(stateUnemployementTax);
						companyPayrollTax.setCompanyTax(companyTax);
						companyPayrollTax.setEmployeePayroll(employeePayrollMain);
						companyPayrollTaxDao.save(companyPayrollTax);
					}

				}
			}

			employeePayrollModels.add(employeePayrollModel);

			// directDepositTotal = new BigDecimal(0L);
			employeeTaxTotal = employeeTaxTotal.add(employeeTaxes);
			employerTaxTotal = employerTaxTotal.add(employerTaxes);
			vjsProcessingFees = new BigDecimal(100);
			// checkAmountTotal = new BigDecimal(0L);
			employeeContractorDeductionTotal = employeeContractorDeductionTotal
					.add(employeeDeduction);
			employerDeductionTotal = employerDeductionTotal.add(companyDeduction);

		}
		grandTotal = grandTotal.add(employeeTaxTotal).add(employerTaxTotal)
				.add(employeeContractorDeductionTotal).add(employerDeductionTotal);

		// totalAmountTransferElectronically = new BigDecimal(0L);
		payrollPreviewForm.setEmployeeTaxTotal(employeeTaxTotal.doubleValue());
		payrollPreviewForm.setEmployerTaxTotal(employerTaxTotal.doubleValue());
		payrollPreviewForm.setEmployeeContractorDeductionTotal(employeeContractorDeductionTotal
				.doubleValue());
		payrollPreviewForm.setEmployerDeductionTotal(employerDeductionTotal.doubleValue());
		payrollPreviewForm.setVjsProcessingFees(vjsProcessingFees.doubleValue());
		payrollPreviewForm.setGrandTotal(grandTotal.doubleValue());
		payrollPreviewForm.setEmployeePayrollModels(employeePayrollModels);

		return payrollPreviewForm;

		// //////////////////// END ////////////////////////////////
	}

	@Override
	public Integer setCheckStubMessage(Long payrollId, String checkStubMessage) {
		return payrollDao.setCheckStubMessage(payrollId, checkStubMessage);
	}

	public MailMessage prepareMail(Long companyId, String link, PayrollSchedule payrollSchedule) {
		// List<String> approverEmails = userDao.getApprovers(companyId);
		String[] approverEmails = (String[]) userDao.getApprovers(companyId).toArray();
		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(approverEmails);
		mailMessage.setSubject("Payroll Approval");
		mailMessage.setContent("<html><body>Hello All,"
				+ " <br/> A payroll has been run for your company for Period "
				+ payrollSchedule.getPeriodStartDate() + " TO "
				+ payrollSchedule.getPeriodEndDate()
				+ " Please approve the payroll by going to this link  : <a href='" + link
				+ "'>Change Pin</a></body></html>");
		return mailMessage;
	}

	@Override
	public void submitForApproval(Long payrollId, PayrollSchedule payrollSchedule, String link) {

		Payroll payroll = null;
		try {
			payroll = payrollDao.find(payrollId);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		prepareMail(payroll.getCompany().getId(), link, payrollSchedule);
		payrollScheduleDao.setPayrollScheduleStatus(payrollSchedule.getId(),
				com.epayroll.entity.PayrollSchedule.Status.SUBMITTED_FOR_APPROVAL);

	}

	@Override
	public List<Payroll> getPayrollList(Long companyId) {
		return payrollDao.getPayrollList(companyId);
	}

	@Override
	public List<EmployeePayroll> getEmployeePayrollList(Long payrollId) {
		return employeePayrollDao.getEmployeePayroll(payrollId);
	}

	@Override
	public List<PayrollCompany> getPayrollCompanyList() {
		return payrollCompanyDao.getEntities();
	}
}