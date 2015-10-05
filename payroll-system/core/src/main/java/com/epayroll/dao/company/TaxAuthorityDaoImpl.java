package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.TaxAuthority;

@Repository
public class TaxAuthorityDaoImpl extends GenericDaoImpl<TaxAuthority, Long> implements
		TaxAuthorityDao {
	private Logger logger = LoggerFactory.getLogger(TaxAuthorityDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxAuthority> getStateTaxAuthorities(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

	@Override
	public Long getFederalAuhtorityId() {
		logger.debug(">> getFederalAuhtorityId");
		String hqlQuery = "select ta.id from TaxAuthority ta "
				+ "where ta.usCounty IS NULL AND ta.usState IS NULL";
		Query query = getSession().createQuery(hqlQuery);
		Long federalAuhtorityId = (Long) query.uniqueResult();
		logger.debug("federalAuhtorityId ::" + federalAuhtorityId);
		logger.debug("getFederalAuhtorityId >>");
		return federalAuhtorityId;
	}

	@Override
	public Long getStateAuhtorityId(Long stateId) {
		logger.debug(">> getStateAuhtorityId");
		String hqlQuery = "select ta.id from TaxAuthority ta "
				+ "where ta.usCounty IS NULL AND ta.usState.id=?";
		Query query = getSession().createQuery(hqlQuery);
		query.setParameter(0, stateId);
		Long stateAuhtorityId = (Long) query.uniqueResult();
		logger.debug("stateAuhtorityId ::" + stateAuhtorityId);
		logger.debug("getStateAuhtorityId >>");
		return stateAuhtorityId;
	}

}
