package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.CompanyFeature;

public interface CompanyFeatureDao extends GenericDao<CompanyFeature, Long> {

	List<CompanyFeature> getCompanyFeatures(Long planFeatureId);

}