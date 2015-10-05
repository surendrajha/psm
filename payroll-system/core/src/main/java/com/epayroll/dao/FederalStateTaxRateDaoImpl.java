package com.epayroll.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.FederalStateTaxRate;

@Repository
public class FederalStateTaxRateDaoImpl extends GenericDaoImpl<FederalStateTaxRate, Long> implements
		FederalStateTaxRateDao {
	private Logger logger = LoggerFactory.getLogger(FederalStateTaxRateDaoImpl.class);

	@Override
	public List<FederalStateTaxRate> getFederalStateTaxRates(Long filingStatusId, Long taxTypeId) {
		logger.debug(">> getFederalStateTaxRates");

		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("filingStatus.id", filingStatusId));
		criteria.add(Restrictions.eq("taxType.id", taxTypeId));
		@SuppressWarnings("unchecked")
		List<FederalStateTaxRate> federalStateTaxRates = criteria.list();
		logger.debug("FederalStateTaxRates :: " + federalStateTaxRates);

		logger.debug("getFederalStateTaxRates >>");
		return federalStateTaxRates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FederalStateTaxRate> getMedicareSocialSecurityRates() {
		logger.debug(">> getMedicareSocialSecurityRates");
		String hqlQuery = "select fstr from FederalStateTaxRate fstr inner join fstr.taxType tt inner join  tt.taxAuthority ta "
				+ "where ta.usCounty IS NULL AND ta.usState IS NULL AND tt.paidByEmployee=? AND tt.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setBoolean(0, true);
		query.setBoolean(1, true);
		List<FederalStateTaxRate> federalStateTaxRates = query.list();
		logger.debug("federalStateTaxRates ::" + federalStateTaxRates);
		logger.debug("getMedicareSocialSecurityRates >>");
		return federalStateTaxRates;
	}

	@Override
	public FederalStateTaxRate getFutaRate() {
		logger.debug(">> getFutaRate");
		String hqlQuery = "select fstr from FederalStateTaxRate fstr inner join fstr.taxType tt inner join  tt.taxAuthority ta "
				+ "where ta.usCounty IS NULL AND ta.usState IS NULL AND tt.paidByEmployee=? AND tt.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setBoolean(0, false);
		query.setBoolean(1, true);
		FederalStateTaxRate federalStateTaxRate = (FederalStateTaxRate) query.uniqueResult();
		logger.debug("federalStateTaxRate ::" + federalStateTaxRate);
		logger.debug("getFutaRate >>");
		return federalStateTaxRate;
	}

	@Override
	public List<FederalStateTaxRate> getFederalStateTaxRates() {
		Criteria criteria = getSession().createCriteria(FederalStateTaxRate.class, "fstr");
		criteria.createAlias("fstr.taxType", "tt");
		criteria.createAlias("tt.taxAuthority", "ta");
		criteria.createAlias("ta.usState", "state", CriteriaSpecification.LEFT_JOIN);
		criteria.addOrder(Order.asc("state.id")).addOrder(Order.asc("minValue"));
		return criteria.list();
	}

	@Override
	public FederalStateTaxRate getSutaRate(Long stateId) {
		logger.debug(">> getSutaRate");
		String hqlQuery = "select fstr from FederalStateTaxRate fstr "
				+ "where fstr.taxType.taxAuthority.usCounty IS NULL AND "
				+ "fstr.taxType.taxAuthority.usState=? AND fstr.taxType.paidByEmployee=? "
				+ "AND fstr.taxType.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, stateId);
		query.setBoolean(1, false);
		query.setBoolean(2, true);
		FederalStateTaxRate federalStateTaxRate = (FederalStateTaxRate) query.uniqueResult();
		logger.debug("federalStateTaxRate ::" + federalStateTaxRate);
		logger.debug("getSutaRate >>");
		return federalStateTaxRate;
	}

}
