package com.epayroll.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.WorkersCompensationTaxRate;

@Repository
@SuppressWarnings("unchecked")
public class WorkersCompensationTaxRateDaoImpl extends
		GenericDaoImpl<WorkersCompensationTaxRate, Long> implements WorkersCompensationTaxRateDao {
	private Logger logger = LoggerFactory.getLogger(WorkersCompensationTaxRateDaoImpl.class);

	@Override
	public Date getDate() {
		logger.debug(">> getDate");
		String hqlQuery = "select min(wctr.wef) from WorkersCompensationTaxRate wctr";
		Query query = getSession().createQuery(hqlQuery);
		Date date = (Date) query.uniqueResult();
		logger.debug("date :: " + date);
		logger.debug("getDate >>");
		return date;
	}

	@Override
	public List<WorkersCompensationTaxRate> getworkersCompensationTaxRateList(Date fromDate,
			Date toDate) {
		logger.debug(">> getworkersCompensationTaxRateList");
		String hqlQuery = "select wctr from WorkersCompensationTaxRate wctr where wctr.wef between :fromDate and :toDate order by wctr.wef DESC";
		Query query = getSession().createQuery(hqlQuery);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		@SuppressWarnings("unchecked")
		List<WorkersCompensationTaxRate> workersCompensationTaxRateList = query.list();
		logger.debug("workersCompensationTaxRateList :: " + workersCompensationTaxRateList);
		logger.debug("getworkersCompensationTaxRateList >>");
		return workersCompensationTaxRateList;
	}

	@Override
	public WorkersCompensationTaxRate getWorkerCompensationTaxRateList(
			Long companyWorkersCompensationId) {
		return (WorkersCompensationTaxRate) getCriteria()
				.createAlias("companyWorkerCompensation", "cwc")
				.add(Restrictions.eq("cwc.id", companyWorkersCompensationId)).uniqueResult();
	}

	@Override
	public List<WorkersCompensationTaxRate> getWorkerCompensationTaxRates(Long companyId) {

		return getCriteria().createAlias("companyWorkerCompensation", "cwc")
				.createAlias("cwc.company", "company")
				.add(Restrictions.eq("company.id", companyId)).list();
	}

}
