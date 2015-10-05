package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.UsState;

public interface UsStateDao extends GenericDao<UsState, Long> {

	List<UsState> findStates();

	List<UsState> getCriteriaForUsState(Long stateId);

	List<UsState> getStates();

}