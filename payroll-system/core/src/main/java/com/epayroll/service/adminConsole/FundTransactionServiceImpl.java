package com.epayroll.service.adminConsole;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.TransactionBodyDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.FundCategoryDao;
import com.epayroll.dao.company.PayrollCompanyDao;
import com.epayroll.dao.employee.EmployeePayrollDao;
import com.epayroll.dao.employee.PaymentModeDao;
import com.epayroll.dao.payroll.FundTransactionDao;
import com.epayroll.dao.payroll.PayrollDao;
import com.epayroll.entity.FundTransaction;
import com.epayroll.entity.FundTransaction.CheckStatus;
import com.epayroll.entity.FundTransaction.TransactionStatus;
import com.epayroll.entity.FundTransaction.TransactionType;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.FundTransactionForm;

@Service
public class FundTransactionServiceImpl implements FundTransactionService {
	private Logger logger = LoggerFactory.getLogger(FundTransactionServiceImpl.class);
	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private EmployeePayrollDao employeePayrollDao;

	@Autowired
	private FundCategoryDao fundCategoryDao;

	@Autowired
	private PaymentModeDao paymentModeDao;

	@Autowired
	private PayrollDao payrollDao;

	@Autowired
	private PayrollCompanyDao payrollCompanyDao;

	@Autowired
	private TransactionBodyDao transactionBodyDao;

	@Autowired
	private FundTransactionDao fundTransactionDao;

	@Override
	public TransactionStatus[] getTransactionStatus() {
		logger.debug(">>getTransactionStatus");
		TransactionStatus[] transactionStatus = TransactionStatus.values();
		logger.debug("getTransactionStatus>>");
		return transactionStatus;
	}

	@Override
	public TransactionType[] getTransactionTypes() {
		logger.debug(">>getTransactionTypes");
		TransactionType[] transactionTypes = TransactionType.values();
		logger.debug("getTransactionTypes>>");
		return transactionTypes;
	}

	@Override
	public CheckStatus[] getCheckStatus() {
		logger.debug(">>getCheckStatus");
		CheckStatus[] checkStatus = CheckStatus.values();
		logger.debug("getCheckStatus>>");
		return checkStatus;
	}

	@Override
	@Transactional
	public Long addFundTransaction(FundTransactionForm fundTransactionForm)
			throws InstanceNotFoundException {
		logger.debug(">>addFundTransaction");
		FundTransaction fundTransaction = new FundTransaction();
		fundTransaction.setAmount(fundTransactionForm.getAmount());
		fundTransaction.setCheckClearingDate(fundTransactionForm.getCheckClearingDate());
		fundTransaction.setCheckStatus(fundTransactionForm.getCheckStatus());
		fundTransaction.setCheckVoidDate(fundTransactionForm.getCheckVoidDate());
		fundTransaction.setCompany(companyDao.find(fundTransactionForm.getCompanyId()));
		fundTransaction.setDateOfTransaction(fundTransactionForm.getDateOfTransaction());
		fundTransaction.setEmployeePayroll(employeePayrollDao.find(fundTransactionForm
				.getEmployeePayrollId()));
		fundTransaction.setFundCategory(fundCategoryDao.find(fundTransactionForm
				.getFundCategoryId()));
		fundTransaction.setPaymentMode(paymentModeDao.find(fundTransactionForm.getPaymentModeId()));
		fundTransaction.setPayroll(payrollDao.find(fundTransactionForm.getPayrollId()));
		fundTransaction.setPayrollCompany(payrollCompanyDao.find(fundTransactionForm
				.getPayrollCompanyId()));
		fundTransaction.setReferenceNumber(fundTransactionForm.getReferenceNumber());
		fundTransaction.setTransactionBody(transactionBodyDao.find(fundTransactionForm
				.getTransactionBodyId()));
		fundTransaction.setTransactionStatus(fundTransactionForm.getTransactionStatus());
		fundTransaction.setTransactionType(fundTransactionForm.getTransactionType());
		logger.debug("addFundTransaction>>");
		return fundTransactionDao.save(fundTransaction);
	}

	@Override
	public FundTransactionForm getFundTransactionForm(Long fundTransactionId)
			throws InstanceNotFoundException {
		logger.debug(">>getFundTransactionForm");
		FundTransactionForm fundTransactionForm = new FundTransactionForm();
		FundTransaction fundTransaction = fundTransactionDao.find(fundTransactionId);
		fundTransactionForm.setAmount(fundTransaction.getAmount());
		fundTransactionForm.setCheckClearingDate(fundTransaction.getCheckClearingDate());
		fundTransactionForm.setCheckStatus(fundTransaction.getCheckStatus());
		fundTransactionForm.setCheckVoidDate(fundTransaction.getCheckVoidDate());
		fundTransactionForm.setCompanyId(fundTransaction.getCompany().getId());
		fundTransactionForm.setDateOfTransaction(fundTransaction.getDateOfTransaction());
		fundTransactionForm.setEmployeePayrollId(fundTransaction.getEmployeePayroll().getId());
		fundTransactionForm.setFundCategoryId(fundTransaction.getFundCategory().getId());
		fundTransactionForm.setId(fundTransactionId);
		fundTransactionForm.setPaymentModeId(fundTransaction.getPaymentMode().getId());
		fundTransactionForm.setPayrollCompanyId(fundTransaction.getPayrollCompany().getId());
		fundTransactionForm.setPayrollId(fundTransaction.getPayroll().getId());
		fundTransactionForm.setReferenceNumber(fundTransaction.getReferenceNumber());
		fundTransactionForm.setTransactionBodyId(fundTransaction.getTransactionBody().getId());
		fundTransactionForm.setTransactionStatus(fundTransaction.getTransactionStatus());
		fundTransactionForm.setTransactionType(fundTransaction.getTransactionType());
		logger.debug("getFundTransactionForm>>");
		return fundTransactionForm;
	}

	@Override
	@Transactional
	public void updateFundTransaction(FundTransactionForm fundTransactionForm)
			throws InstanceNotFoundException {
		logger.debug(">>updateFundTransaction");
		FundTransaction fundTransaction = fundTransactionDao.find(fundTransactionForm.getId());
		fundTransaction.setAmount(fundTransactionForm.getAmount());
		fundTransaction.setCheckClearingDate(fundTransactionForm.getCheckClearingDate());
		fundTransaction.setCheckStatus(fundTransactionForm.getCheckStatus());
		fundTransaction.setCheckVoidDate(fundTransactionForm.getCheckVoidDate());
		fundTransaction.setCompany(companyDao.find(fundTransactionForm.getCompanyId()));
		fundTransaction.setDateOfTransaction(fundTransactionForm.getDateOfTransaction());
		fundTransaction.setEmployeePayroll(employeePayrollDao.find(fundTransactionForm
				.getEmployeePayrollId()));
		fundTransaction.setFundCategory(fundCategoryDao.find(fundTransactionForm
				.getFundCategoryId()));
		fundTransaction.setPaymentMode(paymentModeDao.find(fundTransactionForm.getPaymentModeId()));
		fundTransaction.setPayroll(payrollDao.find(fundTransactionForm.getPayrollId()));
		fundTransaction.setPayrollCompany(payrollCompanyDao.find(fundTransactionForm
				.getPayrollCompanyId()));
		fundTransaction.setReferenceNumber(fundTransactionForm.getReferenceNumber());
		fundTransaction.setTransactionBody(transactionBodyDao.find(fundTransactionForm
				.getTransactionBodyId()));
		fundTransaction.setTransactionStatus(fundTransactionForm.getTransactionStatus());
		fundTransaction.setTransactionType(fundTransactionForm.getTransactionType());
		fundTransactionDao.update(fundTransaction);
		logger.debug("updateFundTransaction>>");
	}

	@Override
	@Transactional
	public void deleteFundTransaction(Long fundTransactionId) throws InstanceNotFoundException {
		// TODO verification is required
		logger.debug(">>deleteFundTransaction");
		fundTransactionDao.remove(fundTransactionId);
		logger.debug("deleteFundTransaction>>");
	}

	@Override
	public List<FundTransaction> getFundTransactions() {
		return fundTransactionDao.getEntities();
	}
}
