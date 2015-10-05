package com.epayroll.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.FutaSutaTaxRate;

@Repository
@SuppressWarnings("unchecked")
public class FutaSutaTaxRateDaoImpl extends GenericDaoImpl<FutaSutaTaxRate, Long> implements
		FutaSutaTaxRateDao {
	private Logger logger = LoggerFactory.getLogger(FutaSutaTaxRateDaoImpl.class);

	@Override
	public List<FutaSutaTaxRate> getFutaSutaTaxRates(Long taxTypeId) {
		logger.debug(">> getFutaTaxRates");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("taxType.id", taxTypeId));
		List<FutaSutaTaxRate> futaTaxRates = criteria.list();
		logger.debug("futaTaxRates ::" + futaTaxRates);
		logger.debug("getFutaTaxRates >>");
		return futaTaxRates;
	}

	@Override
	public List<FutaSutaTaxRate> getFutaSutaTaxRate(Long companyId) {

		return getCriteria().add(Restrictions.eq("company.id", companyId)).list();
	}

}
