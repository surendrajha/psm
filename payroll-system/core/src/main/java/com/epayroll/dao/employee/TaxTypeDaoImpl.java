package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.company.AddressType;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Address;
import com.epayroll.entity.Company;
import com.epayroll.entity.TaxType;

@Repository
public class TaxTypeDaoImpl extends GenericDaoImpl<TaxType, Long> implements TaxTypeDao {
	private Logger logger = LoggerFactory.getLogger(TaxTypeDaoImpl.class);

	@Override
	public List<TaxType> getFederalTaxType() {
		String query = "select t from TaxType t inner join  t.taxAuthority ta where ta.usCounty IS NULL AND ta.usState IS NULL";
		Query query1 = getSession().createQuery(query);
		@SuppressWarnings("unchecked")
		List<TaxType> taxTypeList = query1.list();
		return taxTypeList;
	}

	@Override
	public List<TaxType> getStateTaxType(Company company) {
		Long stateId = null;
		for (Address address : company.getAddresses()) {
			stateId = address.getUsState().getId();
		}
		String query = "select t from TaxType t inner join t.taxAuthority ta  inner join  ta.usState taus where ta.usCounty IS NULL AND taus.id=?";
		Query query1 = getSession().createQuery(query);
		query1.setParameter(0, stateId);
		@SuppressWarnings("unchecked")
		List<TaxType> taxType = query1.list();
		return taxType;
	}

	@Override
	public List<TaxType> getLocalTaxType(Company company) {
		Long countyId = null;
		for (Address address : company.getAddresses()) {
			countyId = address.getUsCounty().getId();
		}
		String query = "select t from TaxType t inner join t.taxAuthority ta inner join ta.usCounty tau  where ta.usState IS NOT NULL AND tau.id=?";
		Query query1 = getSession().createQuery(query);
		query1.setParameter(0, countyId);
		@SuppressWarnings("unchecked")
		List<TaxType> taxTypeList = query1.list();
		return taxTypeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxType> getStateTaxTypes(Long employeeId) {
		logger.debug(">> getStateTaxTypes");

		Query query = getSession()
				.createQuery(
						"select distinct(tt) from TaxType tt inner join tt.taxAuthority ta, Employee e inner join e.addresses addrs "
								+ " where e.id=:empId and addrs.addressType.type=:addrType and "
								+ " ta.usCounty IS NULL AND ta.usState.id=addrs.usState.id");
		query.setParameter("empId", employeeId);
		query.setParameter("addrType", AddressType.WORK);

		List<TaxType> stateTaxTypes = query.list();
		logger.debug("stateTaxTypes ::" + stateTaxTypes);
		logger.debug("getStateTaxTypes >>");
		return stateTaxTypes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxType> getLocalTaxTypes(Long employeeId) {
		logger.debug(">> getLocalTaxTypes");

		Query query = getSession()
				.createQuery(
						"select distinct(tt) from TaxType tt inner join tt.taxAuthority ta, Employee e inner join e.addresses addrs "
								+ " where e.id=:empId and addrs.addressType.type=:addrType and "
								+ " ta.usCounty.id=addrs.usCounty.id AND ta.usState.id=addrs.usState.id");
		query.setParameter("empId", employeeId);
		query.setParameter("addrType", AddressType.WORK);

		List<TaxType> localTaxTypes = query.list();
		logger.debug("localTaxTypes ::" + localTaxTypes);
		logger.debug("getLocalTaxTypes >>");
		return localTaxTypes;
	}

	@Override
	public TaxType getFutaTaxTypeList(Long companyId) {
		logger.debug(">>getFutaTaxTypeList");
		String query = "select t from TaxType t inner join  t.taxAuthority ta where ta.usCounty IS NULL AND ta.usState IS NULL AND t.paidByCompany IS TRUE AND t.paidByEmployee IS FALSE";
		Query query1 = getSession().createQuery(query);
		TaxType taxType = (TaxType) query1.uniqueResult();
		logger.debug("getFutaTaxTypeList>>");
		return taxType;
	}

	@Override
	public List<TaxType> getStateTaxTypeList(Long companyId) {
		logger.debug(">>getStateTaxTypeList");
		Query query = getSession().createQuery(
				"select tt from TaxType tt inner join tt.taxAuthority ta, Company c inner join c.addresses addrs "
						+ " where c.id=:comId and addrs.addressType.type=:addrType and "
						+ " ta.usCounty IS NULL AND ta.usState.id=addrs.usState.id");
		query.setParameter("comId", companyId);
		query.setParameter("addrType", AddressType.LEGAL_ADDRESS);

		@SuppressWarnings("unchecked")
		List<TaxType> stateTaxTypes = query.list();
		logger.debug("stateTaxTypes ::" + stateTaxTypes);
		logger.debug("getStateTaxTypeList >>");
		return stateTaxTypes;
	}

	@Override
	public List<TaxType> getLocalTaxTypeList(Long companyId) {
		logger.debug(">> getLocalTaxTypeList");

		Query query = getSession().createQuery(
				"select tt from TaxType tt inner join tt.taxAuthority ta, Company c inner join c.addresses addrs "
						+ " where c.id=:compId and addrs.addressType.type=:addrType and "
						+ " ta.usCounty.id=addrs.usCounty.id AND ta.usState.id=addrs.usState.id");
		query.setParameter("compId", companyId);
		query.setParameter("addrType", AddressType.LEGAL_ADDRESS);
		@SuppressWarnings("unchecked")
		List<TaxType> localTaxTypes = query.list();
		logger.debug("localTaxTypes ::" + localTaxTypes);
		logger.debug("getLocalTaxTypeList >>");
		return localTaxTypes;
	}

	@Override
	public Long getFitTaxTypeId() {
		logger.debug(">> getTaxtypeId");
		String hqlQuery = "select t.id from TaxType t inner join  t.taxAuthority ta "
				+ "where ta.usCounty IS NULL AND ta.usState IS NULL AND t.paidByEmployee=? AND t.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setBoolean(0, true);
		query.setBoolean(1, false);
		Long taxTypeId = (Long) query.uniqueResult();
		logger.debug("taxTypeId ::" + taxTypeId);
		logger.debug("getTaxtypeId >>");
		return taxTypeId;
	}

	@Override
	public Long getSitTaxTypeId(Long usStateId) {
		logger.debug(">> getSitTaxTypeId");
		String hqlQuery = "select t.id from TaxType t inner join  t.taxAuthority ta where ta.usCounty IS NULL AND ta.usState=? AND t.paidByEmployee=? AND t.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, usStateId);
		query.setBoolean(1, true);
		query.setBoolean(2, false);
		Long taxTypeId = (Long) query.uniqueResult();
		logger.debug("taxTypeId ::" + taxTypeId);
		logger.debug("getSitTaxTypeId >>");
		return taxTypeId;
	}

	@Override
	public Long getFutaTaxTypeId() {
		logger.debug(">> getFutaTaxTypeId");
		String hqlQuery = "select t.id from TaxType t inner join  t.taxAuthority ta "
				+ "where ta.usCounty IS NULL AND ta.usState IS NULL AND t.paidByEmployee=? AND t.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setBoolean(0, false);
		query.setBoolean(1, true);
		Long taxTypeId = (Long) query.uniqueResult();
		logger.debug("taxTypeId ::" + taxTypeId);
		logger.debug("getFutaTaxTypeId >>");
		return taxTypeId;
	}

	@Override
	public Long getSutaTaxTypeId(Long usStateId) {
		logger.debug(">> getSitTaxTypeId");
		String hqlQuery = "select t.id from TaxType t inner join  t.taxAuthority ta where ta.usCounty IS NULL AND ta.usState=? AND t.paidByEmployee=? AND t.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, usStateId);
		query.setBoolean(1, false);
		query.setBoolean(2, true);
		Long taxTypeId = (Long) query.uniqueResult();
		logger.debug("taxTypeId ::" + taxTypeId);
		logger.debug("getSitTaxTypeId >>");
		return taxTypeId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxType> getMedicareSocialSecurityTaxes() {
		logger.debug(">> getMedicareSocialSecurityTaxes");
		String hqlQuery = "select t from TaxType t inner join  t.taxAuthority ta "
				+ "where ta.usCounty IS NULL AND ta.usState IS NULL AND t.paidByEmployee=? AND t.paidByCompany=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setBoolean(0, true);
		query.setBoolean(1, true);
		List<TaxType> taxTypes = query.list();
		logger.debug("taxTypes ::" + taxTypes);
		logger.debug("getMedicareSocialSecurityTaxes >>");
		return taxTypes;
	}

	@Override
	public Long getCountyTaxTypeId(Long usStateId) {
		logger.debug(">> getCountyTaxTypeId");
		String hqlQuery = "select t.id from TaxType t inner join  t.taxAuthority ta where ta.usCounty IS Not NULL AND ta.usState=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, usStateId);
		Long taxTypeId = (Long) query.uniqueResult();
		logger.debug("taxTypeId ::" + taxTypeId);
		logger.debug("getCountyTaxTypeId >>");
		return taxTypeId;
	}

	@Override
	public TaxType getCountyTaxType(Long countyId, Long usStateId) {
		logger.debug(">> getCountyTaxType");
		String hqlQuery = "select t from TaxType t inner join  t.taxAuthority ta where ta.usCounty =? AND ta.usState=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, countyId);
		query.setLong(1, usStateId);
		TaxType taxType = (TaxType) query.uniqueResult();
		logger.debug("taxType ::" + taxType);
		logger.debug("getCountyTaxType >>");
		return taxType;
	}
}
