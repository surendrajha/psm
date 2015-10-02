package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Criteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyTax;

public interface CompanyTaxDao extends GenericDao<CompanyTax, Long> {

	Criteria getCriteriaForTaxList(Company company);

	List<CompanyTax> getFederalTaxList(Long companyId);

	List<CompanyTax> getStateTaxList(Long companyId);

	List<CompanyTax> getLocalTaxList(Long companyId);

	CompanyTax getCompanyTaxRecord(Long companyId, Long taxTypeId);

	List<CompanyTax> getCompanyTaxes(Long companyId);
}