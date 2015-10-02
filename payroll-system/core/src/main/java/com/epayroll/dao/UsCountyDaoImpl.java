package com.epayroll.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.UsCounty;

@Repository
public class UsCountyDaoImpl extends GenericDaoImpl<UsCounty, Long> implements UsCountyDao {
	private Logger logger = LoggerFactory.getLogger(UsCountyDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<UsCounty> getCounties(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

	@Override
	public List<UsCounty> getTaxTypeUsCounties(Long usStateId) {
		logger.debug(">> getTaxTypeUsCounties");
		String hqlQuery = "select ta.usCounty from TaxType t inner join t.taxAuthority ta where ta.usState=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, usStateId);
		@SuppressWarnings("unchecked")
		List<UsCounty> usCounties = query.list();
		logger.debug("usCounties ::" + usCounties);
		logger.debug("getTaxTypeUsCounties >>");
		return usCounties;
	}
}