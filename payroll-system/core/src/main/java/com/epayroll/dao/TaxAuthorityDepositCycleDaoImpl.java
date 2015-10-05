package com.epayroll.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.TaxAuthorityDepositCycle;

@Repository
public class TaxAuthorityDepositCycleDaoImpl extends GenericDaoImpl<TaxAuthorityDepositCycle, Long>
		implements TaxAuthorityDepositCycleDao {
	private Logger logger = LoggerFactory.getLogger(TaxAuthorityDepositCycleDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxAuthorityDepositCycle> getTaxAuthorityDepositCycles(Long taxAuthorityId) {
		logger.debug(">> getTaxAuthorityDepositCycles");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("taxAuthority.id", taxAuthorityId));
		List<TaxAuthorityDepositCycle> taxAuthorityDepositCycles = criteria.list();
		logger.debug("taxAuthorityDepositCycles ::" + taxAuthorityDepositCycles);
		logger.debug("getTaxAuthorityDepositCycles >>");
		return taxAuthorityDepositCycles;
	}

}
