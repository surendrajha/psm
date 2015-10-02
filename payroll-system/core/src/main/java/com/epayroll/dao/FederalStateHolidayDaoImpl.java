package com.epayroll.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.FederalStateHoliday;

@Repository
public class FederalStateHolidayDaoImpl extends GenericDaoImpl<FederalStateHoliday, Long> implements
		FederalStateHolidayDao {
	private Logger logger = LoggerFactory.getLogger(FederalStateHolidayDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<FederalStateHoliday> getFederalStateHolidays(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

}