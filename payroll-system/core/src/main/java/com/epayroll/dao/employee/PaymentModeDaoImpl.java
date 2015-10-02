package com.epayroll.dao.employee;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.PaymentMode;

@Repository
public class PaymentModeDaoImpl extends GenericDaoImpl<PaymentMode, Long> implements PaymentModeDao {

}
