package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.FederalStateHoliday;

public interface FederalStateHolidayDao extends GenericDao<FederalStateHoliday, Long> {

	List<FederalStateHoliday> getFederalStateHolidays(Long usStateId);

}