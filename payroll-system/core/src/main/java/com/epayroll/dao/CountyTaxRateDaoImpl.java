package com.epayroll.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.CountyTaxRate;

@Repository
@SuppressWarnings("unchecked")
public class CountyTaxRateDaoImpl extends GenericDaoImpl<CountyTaxRate, Long> implements
		CountyTaxRateDao {
	private Logger logger = LoggerFactory.getLogger(CountyTaxRateDaoImpl.class);

	@Override
	public List<CountyTaxRate> getCountyTaxRates(Long usCountyId) {
		return getCriteria().add(Restrictions.eq("usCounty.id", usCountyId)).list();
	}

	@Override
	public List<CountyTaxRate> getCountyTaxRateList(Long usStateId) {
		logger.debug("getCountyTaxRateList");
		String hqlQuery = "select ctr from CountyTaxRate ctr inner join ctr.taxType tt inner join  tt.taxAuthority ta where ta.usState=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, usStateId);

		List<CountyTaxRate> countyTaxRates = query.list();
		logger.debug("countyTaxRates ::" + countyTaxRates);
		logger.debug("getCountyTaxRateList");
		return countyTaxRates;
	}

	@Override
	public List<CountyTaxRate> getCountyTaxRate() {
		return getCriteria().list();
	}
}
