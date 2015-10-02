package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.AllowanceRate;

@Repository
public class AllowanceRateDaoImpl extends GenericDaoImpl<AllowanceRate, Long> implements
		AllowanceRateDao {
	private Logger logger = LoggerFactory.getLogger(AllowanceRateDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<AllowanceRate> getAllowancesRates(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllowanceRate> getAllowanceRecordList(Long allowanceTypeId) {
		return getCriteria().add(Restrictions.eq("allowanceType.id", allowanceTypeId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AllowanceRate> getFederalAllowanceRates() {
		String query = "select ar from AllowanceRate ar where ar.usState IS NULL";
		Query query1 = getSession().createQuery(query);
		List<AllowanceRate> allowanceRates = query1.list();
		logger.debug("allowanceRates::" + allowanceRates);
		return allowanceRates;
	}

	@Override
	public List<AllowanceRate> getStateAllowanceRates(Long stateId) {
		String query = "select ar from AllowanceRate ar where ar.usState.id=?";
		Query query1 = getSession().createQuery(query);
		query1.setParameter(0, stateId);
		@SuppressWarnings("unchecked")
		List<AllowanceRate> allowanceRates = query1.list();
		logger.debug("allowanceRates::" + allowanceRates);
		return allowanceRates;
	}

	// Kk
	@Override
	public AllowanceRate getAllowanceRate(Long allowanceTypeId, Long usStateId) {
		Criteria criteria = getSession().createCriteria(AllowanceRate.class, "ar");
		if (usStateId == null) {
			criteria.add(Restrictions.eq("ar.allowanceType.id", allowanceTypeId)).add(
					Restrictions.isNull("ar.usState.id"));
		} else {
			criteria.add(Restrictions.eq("ar.allowanceType.id", allowanceTypeId)).add(
					Restrictions.eq("ar.usState.id", usStateId));
		}
		return (AllowanceRate) criteria.uniqueResult();
	}

}
