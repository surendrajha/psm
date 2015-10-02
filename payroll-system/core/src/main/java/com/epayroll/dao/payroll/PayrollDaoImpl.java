package com.epayroll.dao.payroll;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.entity.Payroll;
import com.epayroll.entity.Payroll.Status;
import com.epayroll.exception.InstanceNotFoundException;

@Repository
public class PayrollDaoImpl extends GenericDaoImpl<Payroll, Long> implements PayrollDao {

	private Logger logger = LoggerFactory.getLogger(PayrollDaoImpl.class);

	@Override
	public Long getPayrollScheduleCount(Long payrollScheduleId) {
		logger.debug(" >> getPayrollScheduleCount");
		Query query = getSession().createQuery(
				"select count(*) from Payroll p inner join p.payrollSchedule ps where ps.id=?");
		query.setParameter(0, payrollScheduleId);
		Long count = (Long) query.uniqueResult();
		return count;
	}

	@Override
	public Long getPayrollFrequencyCount(Long payrollFrequencyId) {
		Query query = getSession()
				.createQuery(
						"select count(*) from Payroll p inner join p.payrollSchedule ps inner join ps.payrollFrequency  pf where pf.id=?");
		query.setParameter(0, payrollFrequencyId);
		Long count = (Long) query.uniqueResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees(Long companyId, Long payFrequencyId, Type employeeType) {
		// TODO Auto-generated method stub

		Criteria criteria = getSession().createCriteria(Employee.class, "emp")
				.createAlias("employeePaySetups", "eps")
				.add(Restrictions.eq("emp.employeeType", employeeType))
				.add(Restrictions.eq("emp.company.id", companyId))
				.add(Restrictions.eq("eps.payrollFrequency.id", payFrequencyId));

		return criteria.list();

	}

	@Override
	public Payroll getPayrollRecord(Long companyId, Long payrollScheduleId) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(Payroll.class, "payroll")
				.add(Restrictions.eq("payroll.company.id", companyId))
				.add(Restrictions.eq("payroll.payrollSchedule.id", payrollScheduleId));

		return (Payroll) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeTax> getemployeePrimaryTaxList(Long employeeId) {
		Query query = getSession()
				.createQuery(
						"select et from EmployeeTax et where et.employee.id=? and et.employeeW4Detail.id IS NOT NUll");
		query.setParameter(0, employeeId);
		return query.list();

	}

	// SK
	@Override
	public Payroll getPayroll(Long companyId, Long payrollScheduleId) {
		return (Payroll) getCriteria().add(Restrictions.eq("company.id", companyId))
				.add(Restrictions.eq("payrollSchedule.id", payrollScheduleId)).uniqueResult();
	}

	// kk
	@Override
	public Integer setCheckStubMessage(Long payrollId, String checkStubMessage) {
		Payroll payroll = null;
		Payroll savedPayroll = null;
		try {
			payroll = find(payrollId);
		} catch (InstanceNotFoundException e) {

			e.printStackTrace();
		}
		payroll.setCheckStubMessage(checkStubMessage);
		savedPayroll = update(payroll);
		if (savedPayroll.getCheckStubMessage().equals(checkStubMessage)) {
			return 1;
		} else {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payroll> getPayrollList(Long companyId) {
		return getCriteria().add(Restrictions.eq("company.id", companyId))
				.add(Restrictions.eq("payrollStatus", Status.PROCESSED)).list();
	}
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Date> getpayDates(Long employeeId) {
	// logger.debug(">> getpayDates");
	// String hqlQuery =
	// "select p.checkDate from Payroll p inner join p.employeePayrolls empPay where empPay.employee.id=?";
	// Query query = getSession().createQuery(hqlQuery);
	// query.setLong(0, employeeId);
	// List<Date> payDateList = query.list();
	// logger.debug("payDateList ::" + payDateList);
	// logger.debug("getpayDates >>");
	// return payDateList;
	// }

}