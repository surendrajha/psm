package com.epayroll.dao.company;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.CompanyBankInfo;

@Repository
public class CompanyBankInfoDaoImpl extends GenericDaoImpl<CompanyBankInfo, Long> implements
		CompanyBankInfoDao {

}