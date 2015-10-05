package com.epayroll.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.FundTransaction.CheckStatus;
import com.epayroll.entity.FundTransaction.TransactionStatus;
import com.epayroll.entity.FundTransaction.TransactionType;
import com.epayroll.ui.contoller.admin.console.AdminTransactionRecordController;

/**
 * 
 * @author Uma
 * 
 */

public class AdminTransactionRecordControllerTest extends TestRoot {

	@Autowired
	private AdminTransactionRecordController adminTransactionRecordController;

	// @Test
	public void getTransactionRecordForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/getTransactionRecordForm");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTransactionRecordController);
		ModelAndViewAssert.assertViewName(mav, "transactionRecordPage");
	}

	// @Test
	public void getPayrolls() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/getPayrolls/1");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTransactionRecordController);
		ModelAndViewAssert.assertViewName(mav, "transactionRecordPage");
	}

	// @Test
	public void getEmployeePayrolls() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/getEmployeePayrolls/33");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTransactionRecordController);
		ModelAndViewAssert.assertViewName(mav, "transactionRecordPage");
	}

	// @Test
	public void addTransactionRecord() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addTransactionRecord");
		request.setParameter("employeePayrollId", "64");
		request.setParameter("payrollId", "33");
		request.setParameter("transactionBodyId", "1");
		request.setParameter("companyId", "1");
		request.setParameter("payrollCompanyId", "1");
		request.setParameter("paymentModeId", "1");
		request.setParameter("referenceNumber", "100");
		request.setParameter("transactionStatus", TransactionStatus.SUCCESS.toString());
		request.setParameter("transactionType", TransactionType.DEBIT.toString());
		request.setParameter("amount", "1");
		request.setParameter("fundCategoryId", "1");
		request.setParameter("dateOfTransaction", "16/02/2013");
		request.setParameter("checkStatus", CheckStatus.VOID.toString());
		request.setParameter("checkVoidDate", "15/02/2013");
		request.setParameter("checkClearingDate", "13/02/2013");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTransactionRecordController);
		ModelAndViewAssert.assertViewName(mav, "transactionRecordPage");
	}

	// @Test
	public void showUpdateTransactionRecordForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateTransactionRecordForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTransactionRecordController);
		ModelAndViewAssert.assertViewName(mav, "transactionRecordPage");
	}

	// @Test
	public void updateTransactionRecord() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateTransactionRecord");
		request.setParameter("id", "1");
		request.setParameter("employeePayrollId", "65");
		request.setParameter("payrollId", "33");
		request.setParameter("transactionBodyId", "1");
		request.setParameter("companyId", "2");
		request.setParameter("payrollCompanyId", "1");
		request.setParameter("paymentModeId", "1");
		request.setParameter("referenceNumber", "101");
		request.setParameter("transactionStatus", TransactionStatus.DECLINED.toString());
		request.setParameter("transactionType", TransactionType.CREDIT.toString());
		request.setParameter("amount", "200");
		request.setParameter("fundCategoryId", "1");
		request.setParameter("dateOfTransaction", "16/01/2013");
		request.setParameter("checkStatus", CheckStatus.WRITTEN.toString());
		request.setParameter("checkVoidDate", "15/01/2013");
		request.setParameter("checkClearingDate", "13/01/2013");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTransactionRecordController);
		ModelAndViewAssert.assertViewName(mav, "transactionRecordPage");
	}

	// @Test
	public void deleteTransactionRecord() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteTransactionRecord/1");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTransactionRecordController);
		ModelAndViewAssert.assertViewName(mav, "transactionRecordPage");
	}

}