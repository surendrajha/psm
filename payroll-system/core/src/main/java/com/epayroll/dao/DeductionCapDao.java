package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.DeductionCap;

public interface DeductionCapDao extends GenericDao<DeductionCap, Long> {

	List<DeductionCap> getDeductionCaps(Long usStateId);

	List<DeductionCap> getFederalDeductionCap();

	List<DeductionCap> getStateDeductionCap(Long stateId);

	List<DeductionCap> getDeductionCapRecords(Long companyId);

}