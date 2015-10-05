package com.epayroll.service;

import java.util.Calendar;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.epayroll.entity.Company;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;

public interface CommonService {

	// state
	UsState addState(UsState state);

	UsState updateState(UsState state);

	void removeState(Long id) throws InstanceNotFoundException;

	UsState findState(Long id) throws InstanceNotFoundException;

	List<UsState> findStates();

	// City
	Long addCity(UsCity city);

	UsCity updateCity(UsCity city);

	void removeCity(UsCity city) throws InstanceNotFoundException;

	UsCity findCity(Long id) throws InstanceNotFoundException;

	List<UsCity> findCitites();

	List<UsCity> getCities(Long usStateId);

	// company calendar
	Calendar addCalendar(Calendar calendar);

	Calendar updateCalendar(Calendar calendar);

	void removeCalendar(Calendar calendar);

	public Calendar findCalendar(Long id);

	Company getCompany(Long id);

	List<UsCounty> getCounties(Long usStateId);

	List<UsCounty> findCounties();

	JRDataSource getCityReport();

}