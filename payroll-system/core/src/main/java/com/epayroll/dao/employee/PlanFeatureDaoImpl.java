package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.PlanFeature;

@Repository
public class PlanFeatureDaoImpl extends GenericDaoImpl<PlanFeature, Long> implements PlanFeatureDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanFeature> getPlanFeatures(Long payrollPlanId) {
		return getCriteria().add(Restrictions.eq("payrollPlan.id", payrollPlanId)).list();
	}

}
