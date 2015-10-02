package com.epayroll.service.employeeAccess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.constant.company.AddressType;
import com.epayroll.constant.employee.TaxName;
import com.epayroll.dao.AddressDao;
import com.epayroll.dao.EmployeePayrollEarningDao;
import com.epayroll.dao.employee.EmployeePayrollDao;
import com.epayroll.dao.employee.EmployeePayrollDeductionDao;
import com.epayroll.dao.employee.EmployeePayrollTaxDao;
import com.epayroll.dao.employee.EmployeeW4DetailsDao;
import com.epayroll.dao.employee.EmploymentHistoryDao;
import com.epayroll.entity.Address;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayroll.RowType;
import com.epayroll.entity.EmployeePayrollDeduction;
import com.epayroll.entity.EmployeePayrollEarning;
import com.epayroll.entity.EmployeePayrollTax;
import com.epayroll.entity.EmployeeW4Detail;
import com.epayroll.entity.Payroll;
import com.epayroll.form.employeeAccess.PayStubsForm;
import com.epayroll.model.employeeAccess.PayStubModel;

/**
 * @author Rajul Tiwari
 */
@Service
public class EmployeeAccessPayStubsInfoServiceImpl implements EmployeeAccessPayStubsInfoService {
	private Logger logger = LoggerFactory.getLogger(EmployeeAccessPayStubsInfoServiceImpl.class);

	@Autowired
	private EmploymentHistoryDao employmentHistoryDao;

	@Autowired
	private EmployeePayrollDao employeePayrollDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private EmployeePayrollEarningDao employeePayrollEarningDao;

	@Autowired
	private EmployeePayrollDeductionDao employeePayrollDeductionDao;

	@Autowired
	private EmployeePayrollTaxDao employeePayrollTaxDao;

	@Autowired
	private EmployeeW4DetailsDao employeeW4DetailDao;

	@Override
	public List<Integer> getYearListForPayStubs(Long employeeId) {
		logger.debug(">> getYearListForPayStubs");
		List<Integer> yearList = new ArrayList<Integer>();
		Date date = employmentHistoryDao.getHireDateForPayStubs(employeeId);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		int year = calendar1.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		for (int i = year; i <= currentYear; i++) {
			yearList.add(i);
		}
		logger.debug("yearlist ::" + yearList);
		logger.debug("getYearListForPayStubs >>");
		return yearList;
	}

	// for check date list and payroll id
	@Override
	public List<Payroll> getPayrollCheckDateList(int year, Long employeeId) {
		logger.debug(">> getPayrollCheckDateList");
		List<Payroll> payrollCheckDateList = employeePayrollDao.getPayrollCheckDateList(employeeId,
				year);
		logger.debug("payrollCheckDateList ::" + payrollCheckDateList);
		logger.debug("getPayrollCheckDateList >>");
		return payrollCheckDateList;
	}

	@Override
	@Transactional
	public PayStubModel getPayStubModel(Employee employee, PayStubsForm payStubsForm) {
		logger.debug(">> getPayStubModel");
		PayStubModel payStubModel = new PayStubModel();

		// payroll Info (payDate,payPeriod)
		payStubModel.setPayDate(payStubsForm.getCheckDate());
		payStubModel.setPayPeriod(payStubsForm.getStartDate() + " to " + payStubsForm.getEndDate());

		// Employee Info
		payStubModel.setEmployeeName(employee.getPerson().getLastName() + ","
				+ employee.getPerson().getFirstName());
		payStubModel.setEmployeeId(employee.getEmployeeId());

		// Employee Address
		Address employeeAddress = addressDao.getAddress(employee.getId(), AddressType.RESIDENCE);
		payStubModel.setEmployeeAddress(employeeAddress.getStreetAddress() + ","
				+ employeeAddress.getCityName() + "," + employeeAddress.getCountyName() + ","
				+ employeeAddress.getUsState().getStateName() + "-" + employeeAddress.getPinZip());

		// Company Info
		payStubModel.setCompanyName(employee.getCompany().getLegalName());

		// Company Address
		Address companyAddress = addressDao.getCompanyAddress(employee.getCompany().getId(),
				AddressType.LEGAL_ADDRESS);
		payStubModel.setCompanyStreetAddress(companyAddress.getStreetAddress());
		payStubModel.setCompanyCityName(companyAddress.getCityName());
		payStubModel.setCompanyCountyName(companyAddress.getCountyName());
		payStubModel.setCompanyStateName(companyAddress.getUsState().getStateName());
		payStubModel.setCompanyPinZip(companyAddress.getPinZip());

		// Fit and Sit
		List<EmployeeW4Detail> employeeW4Details = employeeW4DetailDao
				.getEmployeeW4Details(employee.getId());

		EmployeeW4Detail employeeFitDetail = null;
		EmployeeW4Detail employeeSitDetail = null;
		for (EmployeeW4Detail employeeW4Detail : employeeW4Details) {
			if (employeeW4Detail.getEmployeeTax().getTaxType().getTaxName()
					.equalsIgnoreCase(TaxName.FEDERAL_INCOME_TAX)) {
				employeeFitDetail = employeeW4Detail;
			} else {
				employeeSitDetail = employeeW4Detail;
			}
		}

		Long noOfFitAllowances = (long) 0;
		Long noOfSitAllowances = (long) 0;

		Iterator<EmployeeAllowanceRecord> iter = employeeFitDetail.getEmployeeAllowanceRecords()
				.iterator();
		while (iter.hasNext()) {
			noOfFitAllowances += iter.next().getNoOfAllowance();
		}

		iter = employeeSitDetail.getEmployeeAllowanceRecords().iterator();
		while (iter.hasNext()) {
			noOfSitAllowances += iter.next().getNoOfAllowance();
		}
		// Fit
		payStubModel.setFitFilingStatus(employeeFitDetail.getFilingStatus().getStatus());
		payStubModel.setFitNoOfAllowance(noOfFitAllowances);
		payStubModel.setFitAdditionalWithholding(employeeFitDetail.getAdditionalWithholding());
		payStubModel.setFitTaxOverrideValue(employeeFitDetail.getTaxOverrideValue());

		// Sit
		payStubModel.setSitFilingStatus(employeeSitDetail.getFilingStatus().getStatus());
		payStubModel.setSitNoOfAllowance(noOfSitAllowances);
		payStubModel.setSitAdditionalWithholding(employeeSitDetail.getAdditionalWithholding());
		payStubModel.setSitTaxOverrideValue(employeeSitDetail.getTaxOverrideValue());

		// EmployeePayrollValues
		List<EmployeePayroll> employeePayrollValues = employeePayrollDao.getEmployeePayrollValues(
				employee.getId(), payStubsForm.getPayrollId());

		payStubModel.setEmployeePayrolls(employeePayrollValues);

		// grossPay ,totalDeduction, totalTax
		BigDecimal grossPay = new BigDecimal(0);
		BigDecimal totalDeduction = new BigDecimal(0);
		BigDecimal totalTax = new BigDecimal(0);
		for (EmployeePayroll employeePayroll : employeePayrollValues) {
			grossPay = grossPay.add(employeePayroll.getGrossPay());
			totalDeduction = totalDeduction.add(employeePayroll.getTotalDeduction());
			totalTax = totalTax.add(employeePayroll.getTotalTax());
			// netPay
			if (employeePayroll.getRowType().equals(RowType.MAIN_ROW)) {
				payStubModel.setNetPay(employeePayroll.getNetCheckAmount().add(
						employeePayroll.getNetDirectDeposit()));
				logger.debug("PayStubMode.getNetPay :: " + payStubModel.getNetPay());
			}
		}
		payStubModel.setEmployeePayrollGrossPay(grossPay);
		payStubModel.setTotalDeduction(totalDeduction);
		payStubModel.setTotalTax(totalTax);

		// EmployeePayrollEarning
		List<EmployeePayrollEarning> employeePayrollEarnings = employeePayrollEarningDao
				.getEmployeePayrollEarnings(employee.getId(), payStubsForm.getPayrollId());
		payStubModel.setEmployeePayrollEarnings(employeePayrollEarnings);

		// EmployeePayrollDeduction
		List<EmployeePayrollDeduction> employeePayrollDeductions = employeePayrollDeductionDao
				.getEmployeePayrollDeductions(employee.getId(), payStubsForm.getPayrollId());
		payStubModel.setEmployeePayrollDeductions(employeePayrollDeductions);

		// EmployeePayrollTax
		List<EmployeePayrollTax> employeePayrollTaxs = employeePayrollTaxDao
				.getEmployeePayrollTaxs(employee.getId(), payStubsForm.getPayrollId());
		payStubModel.setEmployeePayrollTaxs(employeePayrollTaxs);

		logger.debug("PayStubModel :: " + payStubModel);

		logger.debug("getPayStubModel >>");
		return payStubModel;
	}
}
