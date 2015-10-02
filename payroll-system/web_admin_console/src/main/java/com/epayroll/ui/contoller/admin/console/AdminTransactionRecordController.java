package com.epayroll.ui.contoller.admin.console;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Company;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.FundCategory;
import com.epayroll.entity.FundTransaction;
import com.epayroll.entity.FundTransaction.CheckStatus;
import com.epayroll.entity.FundTransaction.TransactionStatus;
import com.epayroll.entity.FundTransaction.TransactionType;
import com.epayroll.entity.PaymentMode;
import com.epayroll.entity.Payroll;
import com.epayroll.entity.PayrollCompany;
import com.epayroll.entity.TransactionBody;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.FundTransactionForm;
import com.epayroll.service.adminConsole.AdminOtherDataService;
import com.epayroll.service.adminConsole.FundTransactionService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.service.payroll.PayrollService;

/**
 * 
 * @author Uma
 * 
 */

@RequestMapping("/adminConsole/transactionRecord")
@Controller
public class AdminTransactionRecordController {
	private Logger logger = LoggerFactory.getLogger(AdminTransactionRecordController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PayrollService payrollService;

	@Autowired
	private AdminOtherDataService adminOtherDataService;

	@Autowired
	private FundTransactionService fundTransactionService;

	@RequestMapping(value = "/getTransactionRecordForm", method = RequestMethod.GET)
	public ModelAndView getTransactionRecordForm() {
		logger.debug(">>getTransactionRecordForm");
		ModelAndView modelView = new ModelAndView("transactionRecordPage");
		List<Company> companies = companyService.getCompanyList();
		modelView.addObject("companies", companies);
		logger.debug("companies::" + companies);
		List<TransactionBody> transactionBodies = adminOtherDataService.getTransactionBodyList();
		modelView.addObject("transactionBodies", transactionBodies);
		logger.debug("transactionBodies::" + transactionBodies);
		List<PayrollCompany> payrollCompanies = payrollService.getPayrollCompanyList();
		modelView.addObject("payrollCompanies", payrollCompanies);
		logger.debug("payrollCompanies::" + payrollCompanies);
		List<PaymentMode> paymentModes = adminOtherDataService.getPaymentModes();
		modelView.addObject("paymentModes", paymentModes);
		logger.debug("paymentModes::" + paymentModes);
		TransactionStatus[] transactionStatus = fundTransactionService.getTransactionStatus();
		modelView.addObject("transactionStatus", transactionStatus);
		logger.debug("transactionStatus::" + transactionStatus);
		TransactionType[] transactionTypes = fundTransactionService.getTransactionTypes();
		modelView.addObject("transactionTypes", transactionTypes);
		logger.debug("transactionTypes::" + transactionTypes);
		List<FundCategory> fundCategories = adminOtherDataService.getFundCategories();
		modelView.addObject("fundCategories", fundCategories);
		logger.debug("fundCategories::" + fundCategories);
		CheckStatus[] checkStatus = fundTransactionService.getCheckStatus();
		modelView.addObject("checkStatus", checkStatus);
		logger.debug("checkStatus::" + checkStatus);
		List<FundTransaction> fundTransactions = fundTransactionService.getFundTransactions();
		modelView.addObject("fundTransactions", fundTransactions);
		logger.debug("fundTransactions::" + fundTransactions);
		logger.debug("getTransactionRecordForm>>");
		return modelView;
	}

	@RequestMapping(value = "/getPayrolls/{companyId}", method = RequestMethod.GET)
	public ModelAndView getPayrolls(@PathVariable Long companyId) {
		logger.debug(">>getPayrolls");
		ModelAndView modelView = new ModelAndView("transactionRecordPage");
		List<Payroll> payrolls = payrollService.getPayrollList(companyId);
		modelView.addObject("payrolls", payrolls);
		logger.debug("payrolls::" + payrolls);
		logger.debug("getPayrolls>>");
		return modelView;
	}

	@RequestMapping(value = "/getEmployeePayrolls/{payrollId}", method = RequestMethod.GET)
	public ModelAndView getEmployeePayrolls(@PathVariable Long payrollId) {
		logger.debug(">>getEmployeePayrolls");
		ModelAndView modelView = new ModelAndView("transactionRecordPage");
		List<EmployeePayroll> employeePayrolls = payrollService.getEmployeePayrollList(payrollId);
		modelView.addObject("employeePayrolls", employeePayrolls);
		logger.debug("employeePayrolls::" + employeePayrolls);
		logger.debug("getEmployeePayrolls>>");
		return modelView;
	}

	@RequestMapping(value = "/addTransactionRecord", method = RequestMethod.POST)
	public ModelAndView addTransactionRecord(@ModelAttribute FundTransactionForm fundTransactionForm) {
		logger.debug(">>addTransactionRecord");
		ModelAndView modelView = new ModelAndView("transactionRecordPage");
		try {
			fundTransactionService.addFundTransaction(fundTransactionForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		logger.debug("addTransactionRecord>>");
		return modelView;
	}

	@RequestMapping(value = "/showUpdateTransactionRecordForm/{fundTransactionId}", method = RequestMethod.GET)
	public ModelAndView showUpdateTransactionRecordForm(@PathVariable Long fundTransactionId) {
		ModelAndView modelView = new ModelAndView("transactionRecordPage");
		logger.debug(">>showUpdateTransactionRecordForm");
		try {
			FundTransactionForm fundTransactionForm = fundTransactionService
					.getFundTransactionForm(fundTransactionId);
			modelView.addObject("fundTransactionForm", fundTransactionForm);
			logger.debug("fundTransactionForm::" + fundTransactionForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		logger.debug("showUpdateTransactionRecordForm>>");
		return modelView;
	}

	@RequestMapping(value = "/updateTransactionRecord", method = RequestMethod.POST)
	public ModelAndView updateTransactionRecord(
			@ModelAttribute FundTransactionForm fundTransactionForm) {
		logger.debug(">>updateTransactionRecord");
		ModelAndView modelView = new ModelAndView("transactionRecordPage");
		try {
			fundTransactionService.updateFundTransaction(fundTransactionForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		logger.debug("updateTransactionRecord>>");
		return modelView;
	}

	@RequestMapping(value = "/deleteTransactionRecord/{fundTransactionId}", method = RequestMethod.GET)
	public ModelAndView deleteTransactionRecord(@PathVariable Long fundTransactionId) {
		logger.debug(">>deleteTransactionRecord");
		ModelAndView modelView = new ModelAndView("transactionRecordPage");
		try {
			fundTransactionService.deleteFundTransaction(fundTransactionId);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		logger.debug("deleteTransactionRecord>>");
		return modelView;
	}
}
