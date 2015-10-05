package com.epayroll.dao.company;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.PayrollFrequency.PayFrequencyType;
import com.epayroll.entity.PayrollSchedule;
import com.epayroll.entity.PayrollSchedule.Status;
import com.epayroll.exception.InstanceNotFoundException;

@SuppressWarnings("unchecked")
@Repository
public class PayrollScheduleDaoImpl extends GenericDaoImpl<PayrollSchedule, Long> implements
		PayrollScheduleDao {

	private Logger logger = LoggerFactory.getLogger(PayrollScheduleDaoImpl.class);

	@Override
	public Criteria getCriteriaForPayrollSchedule(String payrollScheduleStatus,
			Long payrollFrequencyId) {
		return getCriteria().add(Restrictions.eq("status", payrollScheduleStatus)).add(
				Restrictions.eq("payrollFrequency.id", payrollFrequencyId));
	}

	// Payroll Module // SK
	@Override
	public PayrollSchedule getCurrentPayrollSchedule(Long payrollFrequencyId) {
		logger.debug(">> getCurrentPayrollSchedule");
		PayrollSchedule currentPayrollSchedule = (PayrollSchedule) getCriteria()
				.add(Restrictions.eq("payrollFrequency.id", payrollFrequencyId))
				.add(Restrictions.eq("status", Status.CURRENT)).uniqueResult();
		logger.debug("currentPayrollSchedule :: " + currentPayrollSchedule);
		logger.debug("getCurrentPayrollSchedule >>");
		return currentPayrollSchedule;
	}

	@Override
	public Long getRemainingPayrollCount(Long payrollFrequencyId) {
		return (Long) getCriteria()
				.add(Restrictions.or(Restrictions.eq("status", Status.CURRENT),
						Restrictions.eq("status", Status.FUTURE)))
				.setProjection(Projections.rowCount()).uniqueResult();

	}

	@Override
	public List<PayrollSchedule> getPayrollScheduleList(Long payrollFrequencyId) {
		return getCriteria().add(Restrictions.eq("payrollFrequency.id", payrollFrequencyId))
				.add(Restrictions.ge("checkDate", new Date())).addOrder(Order.asc("checkDate"))
				.list();
	}

	@Override
	@Transactional
	public void setPayrollScheduleStatus(Long payrollScheduleId, Status status) {
		PayrollSchedule payrollSchedule = null;
		try {
			payrollSchedule = find(payrollScheduleId);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		payrollSchedule.setStatus(status);
		update(payrollSchedule);

	}

	@Override
	public PayrollSchedule getNextFuturePayrollSchedule(Long payrollFrequencyId) {
		logger.debug(">> getNextFuturePayrollSchedule");
		PayrollSchedule nextFuturePayrollSchedule = (PayrollSchedule) getCriteria()
				.add(Restrictions.eq("payrollFrequency.id", payrollFrequencyId))
				.add(Restrictions.eq("status", Status.FUTURE)).addOrder(Order.asc("checkDate"))
				.setMaxResults(1).uniqueResult();
		logger.debug("nextFuturePayrollSchedule :: " + nextFuturePayrollSchedule);
		logger.debug("getNextFuturePayrollSchedule >>");
		return nextFuturePayrollSchedule;
	}

	// @Override
	// public List<PayrollSchedule> getPayrollSchedules(Long companyId) {
	// return getCriteria().createAlias("payrollFrequency",
	// "pf").createAlias("pf.company", "c")
	// .add(Restrictions.eq("c.id",
	// companyId)).addOrder(Order.asc("checkDate")).list();
	// }

	@Override
	public List<PayrollSchedule> getPreviousPayrollScheduleList(Date perioStartDate,
			Date periodEndDate, Long companyId) {
		return getCriteria()
				.createAlias("payrollFrequency", "pf")
				.createAlias("pf.company", "c")
				.add(Restrictions.eq("c.id", companyId))
				.add(Restrictions.ge("periodStartDate", perioStartDate))
				.add(Restrictions.le("periodEndDate", periodEndDate))
				.add(Restrictions.or(Restrictions.eq("status", Status.PROCESSED),
						Restrictions.eq("status", Status.DELETED)))
				.addOrder(Order.asc("checkDate")).list();
	}

	@Override
	public Long getPayrollScheduleRowCount(Long companyId) {
		Long count = (Long) getCriteria()
				.createAlias("payrollFrequency", "pf")
				.createAlias("pf.company", "c")
				.add(Restrictions.eq("c.id", companyId))
				.add(Restrictions.or(Restrictions.eq("status", Status.PROCESSED),
						Restrictions.eq("status", Status.DELETED)))
				.addOrder(Order.asc("checkDate")).setProjection(Projections.rowCount())
				.uniqueResult();
		return count;
	}

	@Override
	public DetachedCriteria getCriteriaForPayrollSchedules(Long companyId) {
		return getDetachedCriteria()
				.createAlias("payrollFrequency", "pf")
				.createAlias("pf.company", "c")
				.add(Restrictions.eq("c.id", companyId))
				.add(Restrictions.or(Restrictions.eq("status", Status.PROCESSED),
						Restrictions.eq("status", Status.DELETED)))
				.addOrder(Order.asc("checkDate"));
	}

	@Override
	public void deletePayrollSchedules(List<Long> payrollSchedulesUpdatedId,
			PayFrequencyType payFrequencyType, Long payrollFrequencyId)
			throws InstanceNotFoundException {
		logger.debug(">> deletePayrollSchedules");
		logger.debug("payrollSchedulesUpdatedId::" + payrollSchedulesUpdatedId);
		if (!payrollSchedulesUpdatedId.isEmpty()) {
			String ids = payrollSchedulesUpdatedId.toString().replace("[", "'").replace("]", "'")
					.replace(", ", "','");
			logger.debug("ids:::" + ids);
			logger.debug("payrollFrequencyId:::" + payrollFrequencyId);
			logger.debug("payFrequencyType:::" + payFrequencyType);
			Query query1 = getSession()
					.createQuery(
							"select ps from PayrollSchedule ps inner join ps.payrollFrequency psf where psf.id=? and psf.payFrequencyType=? and ps.status=? and ps.id not in("
									+ ids + ")");
			query1.setParameter(0, payrollFrequencyId);
			query1.setParameter(1, payFrequencyType);
			query1.setParameter(2, Status.FUTURE);
			List<PayrollSchedule> payrollSchedules = query1.list();
			logger.debug("payrollSchedules:::" + payrollSchedules);
			for (PayrollSchedule payrollSchedule : payrollSchedules) {
				logger.debug(" payrollSchedule.getId()" + payrollSchedule.getId());
				Query query2 = getSession().createQuery(
						"delete from PayrollSchedule ps where ps.id=?");
				query2.setParameter(0, payrollSchedule.getId());
				int deletedRows = query2.executeUpdate();
				logger.debug("deletedRows:::" + deletedRows);
			}
		}
		logger.debug(" deletePayrollSchedules>>");
	}
}