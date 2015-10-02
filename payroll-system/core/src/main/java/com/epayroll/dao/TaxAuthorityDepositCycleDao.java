package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.TaxAuthorityDepositCycle;

public interface TaxAuthorityDepositCycleDao extends GenericDao<TaxAuthorityDepositCycle, Long> {

	List<TaxAuthorityDepositCycle> getTaxAuthorityDepositCycles(Long taxAuthorityId);

}
