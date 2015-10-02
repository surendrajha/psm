package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.exception.InstanceNotFoundException;

@SuppressWarnings("unchecked")
@Repository
public class CompanyEarningDaoImpl extends GenericDaoImpl<CompanyEarning, Long> implements
		CompanyEarningDao {

	private Logger logger = LoggerFactory.getLogger(CompanyEarningDaoImpl.class);

	@Override
	public List<CompanyEarning> getCompanyEarnings(Company company, String categoryType) {
		// Query query = getSession()
		// .createQuery(
		// "from CompanyEarning c INNER JOIN c.earning e INNER JOIN e.earningCategory ec where ec.category = :category and c.company.id = :compId");
		// query.setString("category", categoryType);
		// query.setLong("compId", company.getId());
		// return query.list();

		return getCriteria().createAlias("earning", "e").createAlias("e.earningCategory", "ec")
				.add(Restrictions.eq("ec.category", categoryType))
				.add(Restrictions.eq("company.id", company.getId())).list();

	}

	@Override
	public Long getPayrollCount(Long id) throws InstanceNotFoundException {
		logger.debug(">> getPayrollCount");
		String queryString = "select count(pe.id) from EmployeePayrollEarning pe Inner Join pe.employeeEarning ee Inner Join ee.companyEarning ce where ce.id=?";
		Query query = getSession().createQuery(queryString);
		query.setLong(0, id);
		Long count = (Long) query.uniqueResult();
		logger.debug("Count=" + count);
		logger.debug("getPayrollCount >>");
		return count;

	}

	@Override
	public List<CompanyEarning> getCompanyEarnings(Long companyId) {
		logger.debug("In getcompanyEarnings Company Id:" + companyId);
		return getCriteria().add(Restrictions.eq("company.id", companyId))
				.addOrder(Order.asc("id")).list();
	}

	@Override
	public List<CompanyEarning> getCompanyEarningsByEarningId(Long earningId) {
		Query query = getSession().createQuery(
				"select ce from CompanyEarning ce INNER JOIN ce.earning cee where cee.id=?");
		query.setParameter(0, earningId);
		List<CompanyEarning> companyEarnings = query.list();
		return companyEarnings;
	}

}
