package com.epayroll.dao.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.FundTransaction;

@Repository
public class FundTransactionDaoImpl extends GenericDaoImpl<FundTransaction, Long> implements
		FundTransactionDao {

	private Logger logger = LoggerFactory.getLogger(FundTransactionDaoImpl.class);

}