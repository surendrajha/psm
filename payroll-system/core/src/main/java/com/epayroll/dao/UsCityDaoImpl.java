package com.epayroll.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.UsCity;

@Repository
public class UsCityDaoImpl extends GenericDaoImpl<UsCity, Long> implements UsCityDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UsCity> getCities(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

}