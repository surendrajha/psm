package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.FutaSutaTaxRate;

public interface FutaSutaTaxRateDao extends GenericDao<FutaSutaTaxRate, Long> {

	List<FutaSutaTaxRate> getFutaSutaTaxRates(Long taxTypeId);

	List<FutaSutaTaxRate> getFutaSutaTaxRate(Long companyId);

}
