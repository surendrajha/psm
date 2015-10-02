package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.WorkersCompensationTaxRateView;

@Repository
public class WorkersCompensationTaxRateViewDaoImpl extends
		GenericDaoImpl<WorkersCompensationTaxRateView, Long> implements
		WorkersCompensationTaxRateViewDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkersCompensationTaxRateView> getWorkersCompensationRates(Long companyId) {
		return getCriteria().add(Restrictions.eq("id.companyId", companyId)).list();
	}
}