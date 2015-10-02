package com.epayroll.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.TransactionBody;

@Repository
public class TransactionBodyDaoImpl extends GenericDaoImpl<TransactionBody, Long> implements
		TransactionBodyDao {
	private Logger logger = LoggerFactory.getLogger(TransactionBodyDaoImpl.class);

}
