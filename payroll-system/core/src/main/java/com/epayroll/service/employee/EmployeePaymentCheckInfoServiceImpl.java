package com.epayroll.service.employee;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayroll.dao.EmployeePayrollEarningDao;
import com.epayroll.dao.employee.EmployeePayrollDao;
import com.epayroll.dao.employee.EmployeePayrollDeductionDao;
import com.epayroll.dao.employee.EmployeePayrollTaxDao;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayrollDeduction;
import com.epayroll.entity.EmployeePayrollEarning;
import com.epayroll.entity.EmployeePayrollTax;
import com.epayroll.form.employee.PaymentCheckInfoForm;

/**
 * @author Rajul Tiwari
 */
@Service
public class EmployeePaymentCheckInfoServiceImpl implements EmployeePaymentCheckInfoService {
	private Logger logger = LoggerFactory.getLogger(EmployeePaymentCheckInfoServiceImpl.class);

	@Autowired
	private EmployeePayrollDeductionDao employeePayrollDeductionDao;

	@Autowired
	private EmployeePayrollDao employeePayrollDao;

	@Autowired
	private EmployeePayrollEarningDao employeePayrollEarningDao;

	@Autowired
	private EmployeePayrollTaxDao employeePayrollTaxDao;

	@Override
	public List<Date> getPayDates(Long employeeId) {
		logger.debug(">> getPayDates");
		List<Date> payDateList = employeePayrollDao.getpayDates(employeeId);
		logger.debug("getPayDates >>");
		return payDateList;
	}

	@Override
	public List<EmployeePayroll> getPaymentes(Long employeeId) {
		logger.debug(">> getPaymentes");
		List<EmployeePayroll> employeePayrollList = employeePayrollDao.getPaymentes(employeeId);
		logger.debug("getPaymentes >>");
		return employeePayrollList;
	}

	@Override
	public List<EmployeePayroll> getSearchedPaymentes(Long employeeId,
			PaymentCheckInfoForm paymentCheckInfoForm) {
		logger.debug(">> getSearchedPaymentes");
		List<EmployeePayroll> employeePayrollList = employeePayrollDao.getSearchedPaymentes(
				employeeId, paymentCheckInfoForm);
		logger.debug("getSearchedPaymentes >>");
		return employeePayrollList;
	}

	@Override
	public List<EmployeePayrollEarning> getEmployeeEarningList(Long employeePayrollId) {
		logger.debug(">> getEmployeeEarningList");
		List<EmployeePayrollEarning> payrollEarningList = employeePayrollEarningDao
				.getEmployeePayrollEarnings(employeePayrollId);
		logger.debug("payrollEarningList::" + payrollEarningList);
		logger.debug("getEmployeeEarningList >>");
		return payrollEarningList;
	}

	@Override
	public List<EmployeePayrollTax> getEmployeeTaxList(Long employeePayrollId) {
		logger.debug(">> getEmployeeTaxList");
		List<EmployeePayrollTax> employeePayrollTaxList = employeePayrollTaxDao
				.getEmployeeTaxes(employeePayrollId);
		logger.debug("employeePayrollTaxList::" + employeePayrollTaxList);
		logger.debug("getEmployeeTaxList >>");
		return employeePayrollTaxList;
	}

	@Override
	public List<EmployeePayrollDeduction> getEmployeeDeductionList(Long employeePayrollId) {
		logger.debug(">> getEmployeeDeductionList");
		List<EmployeePayrollDeduction> employeePayrollDeductionList = employeePayrollDeductionDao
				.getEmployeeDeductions(employeePayrollId);
		logger.debug("employeePayrollDeductionList::" + employeePayrollDeductionList);
		logger.debug("getEmployeeDeductionList >>");
		return employeePayrollDeductionList;
	}

}
