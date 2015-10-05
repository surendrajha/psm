package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.StandardDeductionRate;

@Repository
@SuppressWarnings("unchecked")
public class StandardDeductionRateDaoImpl extends GenericDaoImpl<StandardDeductionRate, Long>
		implements StandardDeductionRateDao {

	@Override
	public List<StandardDeductionRate> getStandardDeductionRates(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

	@Override
	public List<StandardDeductionRate> getFederalStandardDeductions() {
		String query = "select sdr from StandardDeductionRate sdr where sdr.usState IS NULL";
		Query query1 = getSession().createQuery(query);
		List<StandardDeductionRate> standardDeductionRates = query1.list();
		return standardDeductionRates;
	}

	@Override
	public List<StandardDeductionRate> getStateStandardDeductions(Long stateId) {
		String query = "select sdr from StandardDeductionRate sdr where sdr.usState.id=?";
		Query query1 = getSession().createQuery(query);
		query1.setParameter(0, stateId);
		@SuppressWarnings("unchecked")
		List<StandardDeductionRate> standardDeductionRates = query1.list();
		return standardDeductionRates;
	}

	@Override
	public List<StandardDeductionRate> getStandardDeductionRates() {

		return getCriteria().list();
	}

}
