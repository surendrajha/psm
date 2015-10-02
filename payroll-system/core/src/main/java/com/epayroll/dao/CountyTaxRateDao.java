package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.CountyTaxRate;

public interface CountyTaxRateDao extends GenericDao<CountyTaxRate, Long> {

	List<CountyTaxRate> getCountyTaxRates(Long usCountyId);

	List<CountyTaxRate> getCountyTaxRateList(Long usStateId);

	List<CountyTaxRate> getCountyTaxRate();
}
