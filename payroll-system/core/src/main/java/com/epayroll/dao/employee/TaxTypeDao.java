package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.TaxType;

public interface TaxTypeDao extends GenericDao<TaxType, Long> {

	List<TaxType> getFederalTaxType();

	List<TaxType> getStateTaxType(Company company);

	List<TaxType> getStateTaxTypes(Long employeeId);

	List<TaxType> getLocalTaxType(Company company);

	List<TaxType> getLocalTaxTypes(Long employeeId);

	TaxType getFutaTaxTypeList(Long companyId);

	List<TaxType> getStateTaxTypeList(Long companyId);

	List<TaxType> getLocalTaxTypeList(Long companyId);

	Long getFitTaxTypeId();

	Long getSitTaxTypeId(Long usStateId);

	Long getFutaTaxTypeId();

	List<TaxType> getMedicareSocialSecurityTaxes();

	Long getCountyTaxTypeId(Long usStateId);

	TaxType getCountyTaxType(Long countyId, Long usStateId);

	Long getSutaTaxTypeId(Long usStateId);
}