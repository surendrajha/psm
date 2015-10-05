package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.UsCity;

public interface UsCityDao extends GenericDao<UsCity, Long> {

	List<UsCity> getCities(Long usStateId);
}