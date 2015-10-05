package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.company.AddressType;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyTax;

@SuppressWarnings("unchecked")
@Repository
public class CompanyTaxDaoImpl extends GenericDaoImpl<CompanyTax, Long> implements CompanyTaxDao {
	private Logger logger = LoggerFactory.getLogger(CompanyTaxDaoImpl.class);

	@Override
	public Criteria getCriteriaForTaxList(Company company) {
		logger.debug(">> getCriteriaForTaxList");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("company.id", company.getId()));
		logger.debug("getCriteriaForTaxList >>");
		return criteria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyTax> getFederalTaxList(Long companyId) {
		logger.debug(">> getFederalTaxList");
		Query query = getSession()
				.createQuery(
						"select ct from CompanyTax ct inner join ct.taxType tt inner join tt.taxAuthority ta "
								+ "where ta.usState IS NULL and ta.usCounty IS NULL and ct.company.id=? and "
								+ "tt.paidByEmployee=? and tt.paidByCompany=?");
		query.setLong(0, companyId);
		query.setBoolean(1, false);
		query.setBoolean(2, true);
		List<CompanyTax> federalTaxList = query.list();
		logger.debug("federalTaxList ::" + federalTaxList);
		logger.debug("getFederalTaxList >>");
		return federalTaxList;
	}

	@Override
	public List<CompanyTax> getStateTaxList(Long companyId) {
		logger.debug(">> getStateTaxList");
		Query query = getSession()
				.createQuery(
						"select ct from CompanyTax ct inner join ct.company c inner join c.addresses addrs "
								+ " where c.id=:comId and addrs.addressType.type=:addrType and "
								+ "ct.taxType.id in (select tt.id from TaxType tt INNER JOIN tt.taxAuthority ta "
								+ "where ta.usCounty IS NULL AND ta.usState.id=addrs.usState.id)");
		query.setParameter("comId", companyId);
		query.setParameter("addrType", AddressType.WORK);
		@SuppressWarnings("unchecked")
		List<CompanyTax> stateTaxList = query.list();
		logger.debug("stateTaxList ::" + stateTaxList);
		logger.debug("getStateTaxList >>");
		return stateTaxList;
	}

	@Override
	public List<CompanyTax> getLocalTaxList(Long companyId) {
		logger.debug(">> getLocalTaxList");
		Query query = getSession()
				.createQuery(
						"select ct from CompanyTax ct inner join ct.company c inner join c.addresses addrs "
								+ " where c.id=:comId and addrs.addressType.type=:addrType and "
								+ "ct.taxType.id in (select tt.id from TaxType tt INNER JOIN tt.taxAuthority ta "
								+ "where ta.usState.id=addrs.usState.id AND ta.usCounty.id=addrs.usCounty.id)");
		query.setParameter("comId", companyId);
		query.setParameter("addrType", AddressType.WORK);
		@SuppressWarnings("unchecked")
		List<CompanyTax> localTaxList = query.list();
		logger.debug("localTaxList::" + localTaxList);
		logger.debug("getLocalTaxList >>");
		return localTaxList;
	}

	@Override
	public CompanyTax getCompanyTaxRecord(Long companyId, Long taxTypeId) {

		return (CompanyTax) getCriteria().add(Restrictions.eq("company.id", companyId))
				.add(Restrictions.eq("taxType.id", taxTypeId)).uniqueResult();
	}

	@Override
	public List<CompanyTax> getCompanyTaxes(Long companyId) {

		return getCriteria().add(Restrictions.eq("company.id", companyId)).list();
	}

}
