package com.epayroll.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.DeductionCap;

@Repository
@SuppressWarnings("unchecked")
public class DeductionCapDaoImpl extends GenericDaoImpl<DeductionCap, Long> implements
		DeductionCapDao {
	private Logger logger = LoggerFactory.getLogger(DeductionCapDaoImpl.class);

	@Override
	public List<DeductionCap> getDeductionCaps(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

	@Override
	public List<DeductionCap> getFederalDeductionCap() {
		String query = "select dc from DeductionCap dc where dc.usState IS NULL";
		Query query1 = getSession().createQuery(query);
		List<DeductionCap> deductionCaps = query1.list();
		logger.debug("deductionCaps::" + deductionCaps);
		return deductionCaps;
	}

	@Override
	public List<DeductionCap> getStateDeductionCap(Long stateId) {
		String query = "select dc from DeductionCap dc where dc.usState.id=?";
		Query query1 = getSession().createQuery(query);
		query1.setParameter(0, stateId);
		@SuppressWarnings("unchecked")
		List<DeductionCap> deductionCaps = query1.list();
		logger.debug("deductionCaps::" + deductionCaps);
		return deductionCaps;
	}

	@Override
	public List<DeductionCap> getDeductionCapRecords(Long companyId) {

		return getCriteria().createAlias("companyDeduction", "cd")
				.createAlias("cd.company", "company").add(Restrictions.eq("company.id", companyId))
				.list();
	}

}