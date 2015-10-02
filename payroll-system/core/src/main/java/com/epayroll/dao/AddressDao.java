package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.Address;

public interface AddressDao extends GenericDao<Address, Long> {
	Address getAddress(Long employeeId, String addressType);

	Address getCompanyAddress(Long companyId, String addressType);

	Long getEmployeeWorkState(Long employeeId);

	Long getEmployeeResidentialCounty(Long employeeId);

	List<Address> getAddressList(Long usStateId);

	List<Address> getAddresses(Long usCountyId);

	List<Address> getAddressesList(Long usCityId);

	Long getEmployeeResidentialState(Long employeeId);

}
