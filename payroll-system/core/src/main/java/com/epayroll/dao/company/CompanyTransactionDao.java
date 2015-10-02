package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.FundTransaction;

public interface CompanyTransactionDao extends GenericDao<FundTransaction, Long> {

	List<FundTransaction> getCompanyTransactions(Long fundCategoryId);

	List<FundTransaction> getCompanyTransaction(Long paymentModeId);

}