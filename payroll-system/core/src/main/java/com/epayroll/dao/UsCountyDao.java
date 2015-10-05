package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.UsCounty;

public interface UsCountyDao extends GenericDao<UsCounty, Long> {

	List<UsCounty> getCounties(Long usStateId);

	List<UsCounty> getTaxTypeUsCounties(Long usStateId);
}