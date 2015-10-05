package com.epayroll.dao.company;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.TaxDepositCycle;

@Repository
public class TaxDepositCycleDaoImpl extends GenericDaoImpl<TaxDepositCycle, Long> implements
		TaxDepositCycleDao {

}
