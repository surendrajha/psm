package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.FederalStateTaxRate;

public interface FederalStateTaxRateDao extends GenericDao<FederalStateTaxRate, Long> {

	List<FederalStateTaxRate> getFederalStateTaxRates();

	List<FederalStateTaxRate> getFederalStateTaxRates(Long filingStatusId, Long taxTypeId);

	List<FederalStateTaxRate> getMedicareSocialSecurityRates();

	FederalStateTaxRate getFutaRate();

	FederalStateTaxRate getSutaRate(Long stateId);

}
