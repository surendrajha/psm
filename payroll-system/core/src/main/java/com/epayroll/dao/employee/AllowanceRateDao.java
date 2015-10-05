package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.AllowanceRate;

public interface AllowanceRateDao extends GenericDao<AllowanceRate, Long> {

	List<AllowanceRate> getAllowancesRates(Long usStateId);

	List<AllowanceRate> getAllowanceRecordList(Long allowanceTypeId);

	List<AllowanceRate> getFederalAllowanceRates();

	List<AllowanceRate> getStateAllowanceRates(Long stateId);

	AllowanceRate getAllowanceRate(Long allowanceTypeId, Long usStateId);

}