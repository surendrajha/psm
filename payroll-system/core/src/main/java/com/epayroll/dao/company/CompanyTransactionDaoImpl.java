package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.FundTransaction;

@Repository
public class CompanyTransactionDaoImpl extends GenericDaoImpl<FundTransaction, Long> implements
		CompanyTransactionDao {
	private Logger logger = LoggerFactory.getLogger(CompanyTransactionDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<FundTransaction> getCompanyTransactions(Long fundCategoryId) {
		return getCriteria().add(Restrictions.eq("fundCategory.id", fundCategoryId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FundTransaction> getCompanyTransaction(Long paymentModeId) {
		return getCriteria().add(Restrictions.eq("paymentMode.id", paymentModeId)).list();
	}

}
