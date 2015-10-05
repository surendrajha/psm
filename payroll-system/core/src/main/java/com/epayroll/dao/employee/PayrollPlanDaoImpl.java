package com.epayroll.dao.employee;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.PayrollPlan;

@Repository
public class PayrollPlanDaoImpl extends GenericDaoImpl<PayrollPlan, Long> implements PayrollPlanDao {

}
