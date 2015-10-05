package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.CompanyFeature;

@Repository
public class CompanyFeatureDaoImpl extends GenericDaoImpl<CompanyFeature, Long> implements
		CompanyFeatureDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyFeature> getCompanyFeatures(Long planFeatureId) {
		return getCriteria().add(Restrictions.eq("planFeature.id", planFeatureId)).list();
	}

}
