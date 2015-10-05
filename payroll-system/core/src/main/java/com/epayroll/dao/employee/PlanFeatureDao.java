package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.PlanFeature;

public interface PlanFeatureDao extends GenericDao<PlanFeature, Long> {

	List<PlanFeature> getPlanFeatures(Long payrollPlanId);

}