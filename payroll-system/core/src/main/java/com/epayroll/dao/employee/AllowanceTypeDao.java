package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.AllowanceType;

public interface AllowanceTypeDao extends GenericDao<AllowanceType, Long> {

	List<AllowanceType> getFederalAllowanceTypes();

	List<AllowanceType> getStateAllowanceTypes(Long stateId);

}