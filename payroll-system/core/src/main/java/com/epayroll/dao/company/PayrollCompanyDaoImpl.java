package com.epayroll.dao.company;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.PayrollCompany;

@Repository
public class PayrollCompanyDaoImpl extends GenericDaoImpl<PayrollCompany, Long> implements
		PayrollCompanyDao {

}