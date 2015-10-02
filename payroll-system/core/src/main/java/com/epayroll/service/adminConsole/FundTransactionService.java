package com.epayroll.service.adminConsole;

import java.util.List;

import com.epayroll.entity.FundTransaction;
import com.epayroll.entity.FundTransaction.CheckStatus;
import com.epayroll.entity.FundTransaction.TransactionStatus;
import com.epayroll.entity.FundTransaction.TransactionType;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.FundTransactionForm;

/**
 * 
 * @author Uma
 * 
 */
public interface FundTransactionService {

	TransactionStatus[] getTransactionStatus();

	TransactionType[] getTransactionTypes();

	CheckStatus[] getCheckStatus();

	Long addFundTransaction(FundTransactionForm fundTransactionForm)
			throws InstanceNotFoundException;

	FundTransactionForm getFundTransactionForm(Long fundTransactionId)
			throws InstanceNotFoundException;

	void updateFundTransaction(FundTransactionForm fundTransactionForm)
			throws InstanceNotFoundException;

	void deleteFundTransaction(Long fundTransactionId) throws InstanceNotFoundException;

	List<FundTransaction> getFundTransactions();

}
