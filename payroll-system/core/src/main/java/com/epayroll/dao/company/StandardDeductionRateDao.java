package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.StandardDeductionRate;

public interface StandardDeductionRateDao extends GenericDao<StandardDeductionRate, Long> {

	List<StandardDeductionRate> getStandardDeductionRates(Long usStateId);

	List<StandardDeductionRate> getFederalStandardDeductions();

	List<StandardDeductionRate> getStateStandardDeductions(Long stateId);

	List<StandardDeductionRate> getStandardDeductionRates();

}