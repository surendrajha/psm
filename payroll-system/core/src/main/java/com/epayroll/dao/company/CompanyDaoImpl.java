package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.company.AddressType;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Company;
import com.epayroll.entity.Company.Status;
import com.epayroll.form.employeeAccess.CompanyForm;

@Repository
public class CompanyDaoImpl extends GenericDaoImpl<Company, Long> implements CompanyDao {

	private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

	@Override
	public List<Object[]> getSearchedCompanyList(CompanyForm companyForm) {
		logger.debug(">> getSearchedCompanyList");

		String bHQL = "select c.legalName, cAddresses.usCity.cityName, cAddresses.usState.stateName, c.businessPhone, c.businessFax, c.webAddress,c.fein, c.status from Company c INNER JOIN c.addresses cAddresses  where cAddresses.addressType.type='"
				+ AddressType.LEGAL_ADDRESS + "'";

		if (companyForm.getBusinessFax() != null) {
			bHQL = bHQL + "and c.businessFax=" + "'" + companyForm.getBusinessFax() + "'";
		}
		if (companyForm.getBusinessPhone() != null) {
			bHQL = bHQL + "and c.businessPhone=" + "'" + companyForm.getBusinessPhone() + "'";
		}
		if (companyForm.getStatus() != null) {
			bHQL = bHQL + "and c.status=:status ";
		}
		if (companyForm.getFein() != null) {
			bHQL = bHQL + "and c.fein=" + "'" + companyForm.getFein() + "'";
		}
		if (companyForm.getLegalName() != null) {
			bHQL = bHQL + "and c.legalName=" + "'" + companyForm.getLegalName() + "'";
		}
		if (companyForm.getWebAddress() != null) {
			bHQL = bHQL + "and c.webAddress=" + "'" + companyForm.getWebAddress() + "'";
		}
		if (companyForm.getLegalAddressCityId() != null) {
			bHQL = bHQL + "and cAddresses.usCity.id=" + "'" + companyForm.getLegalAddressCityId()
					+ "'";
		}
		if (companyForm.getLegalAddressStateId() != null) {
			bHQL = bHQL + "and cAddresses.usState.id=" + "'" + companyForm.getLegalAddressStateId()
					+ "'";
		}
		logger.debug("Search HQL : " + bHQL);

		Query query = getSession().createQuery(bHQL);
		if (companyForm.getStatus() != null) {
			query.setParameter("status", companyForm.getStatus());
		}
		@SuppressWarnings("unchecked")
		List<Object[]> companyList = query.list();
		logger.debug("searchedCompanies::" + companyList);
		logger.debug("getSearchedCompanyList >>");
		return companyList;
	}

	@Override
	public Integer deactivateCompany(Long companyId) {
		logger.debug(">> deactivateCompany");
		Query query = getSession().createQuery(
				"update Company c set c.status=:status where c.id=:companyId");
		query.setParameter("status", Status.INACTIVE);
		query.setParameter("companyId", companyId);
		Integer rows = query.executeUpdate();
		logger.debug("deactivateCompany >>");
		return rows;
	}

	@Override
	public Integer reactivateCompany(Long companyId) {
		logger.debug(">> reactivateCompany");
		Query query = getSession().createQuery(
				"update Company c set c.status=:status where c.id=:companyId");
		query.setParameter("status", Status.ACTIVE);
		query.setParameter("companyId", companyId);
		Integer rows = query.executeUpdate();
		logger.debug("reactivateCompany >>");
		return rows;
	}

	@Override
	public Company getCompany(Long userId) {
		logger.debug(">> getCompany");
		Company company = (Company) getCriteria().createAlias("companyUsers", "compUsers")
				.add(Restrictions.eq("compUsers.user.id", userId)).uniqueResult();
		logger.debug("getCompany >>");
		return company;
	}
}