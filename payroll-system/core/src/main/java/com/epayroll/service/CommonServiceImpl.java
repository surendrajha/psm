package com.epayroll.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.UsCityDao;
import com.epayroll.dao.UsCountyDao;
import com.epayroll.dao.UsStateDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.model.dto.City;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private UsStateDao stateDao;

	@Autowired
	private UsCityDao cityDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private UsCountyDao usCountyDao;

	@Override
	@Transactional
	// @PreAuthorize("hasPermission('" + PayrollSectionName.US_STATE
	// + "', T(com.epayroll.constant.PermissionType).INSERT)")
	public UsState addState(UsState state) {
		stateDao.save(state);
		return state;
	}

	@Override
	@Transactional
	public UsState updateState(UsState state) {
		return stateDao.update(state);
	}

	@Override
	@Transactional
	// @PreAuthorize("hasPermission('" + PayrollSectionName.US_STATE
	// + "', T(com.epayroll.constant.PermissionType).DELETE)")
	public void removeState(Long stateId) throws InstanceNotFoundException {
		stateDao.remove(stateId);
	}

	@Override
	@Transactional
	public UsState findState(Long id) throws InstanceNotFoundException {
		return stateDao.find(id);
	}

	@Override
	@Transactional
	public List<UsState> findStates() {
		return stateDao.getEntities();
	}

	@Override
	@Transactional
	public Long addCity(UsCity city) {
		return cityDao.save(city);
	}

	@Override
	@Transactional
	public UsCity updateCity(UsCity city) {
		return cityDao.update(city);
	}

	@Override
	@Transactional
	public void removeCity(UsCity city) throws InstanceNotFoundException {
		cityDao.remove(city);
	}

	@Override
	@Transactional
	public UsCity findCity(Long id) throws InstanceNotFoundException {
		return cityDao.find(id);
	}

	@Override
	@Transactional
	public List<UsCity> findCitites() {
		return cityDao.getEntities();
	}

	@Override
	@Transactional
	public Calendar addCalendar(Calendar calendar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Calendar updateCalendar(Calendar calendar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void removeCalendar(Calendar calendar) {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional
	public Calendar findCalendar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getCompany(Long userId) {
		Company company = companyDao.getCompany(userId);
		return company;
	}

	@Override
	public List<UsCounty> getCounties(Long usStateId) {
		return usCountyDao.getCounties(usStateId);
	}

	@Override
	public List<UsCounty> findCounties() {
		return usCountyDao.getEntities();
	}

	@Override
	public List<UsCity> getCities(Long usStateId) {
		return cityDao.getCities(usStateId);
	}

	@Override
	public JRDataSource getCityReport() {

		List<City> cities = new ArrayList<>();
		City city = null;
		List<UsCity> usCities = cityDao.getEntities();
		for (UsCity usCity : usCities) {
			city = new City();
			city.setId(usCity.getId());
			city.setCityName(usCity.getCityName());
			city.setState(usCity.getUsState().getStateName());
			cities.add(city);
		}
		return new JRBeanCollectionDataSource(cities);
	}

}